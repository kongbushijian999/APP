package com.maintest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;  
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import com.toserver.LoginToServer;
import com.util.Constant;
import com.util.StringUtil;  
  
public class LoginActivity extends Activity {  
	//登录用户名输入框  
    private EditText et_username;  
    //登录密码输入框  
    private EditText et_password;  
    //登录按钮  
    private Button bt_login;  
    private TextView tvResult;
    public static String showuser;
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.login);  
  
        //获取组件  
        et_username = (EditText)findViewById(R.id.username);  
        et_password = (EditText)findViewById(R.id.password);  
        tvResult = (TextView) findViewById(R.id.tv_result);
        bt_login = (Button)findViewById(R.id.login);   
        bt_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!StringUtil.isEmpty(et_username.getText().toString())
						&& !StringUtil.isEmpty(et_password.getText().toString())) {
					Log.e("WangJ", "都不空");
					login(et_username.getText().toString(), et_password.getText().toString());
					Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(LoginActivity.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
				}
			}
		});
  

  
    }  
    
    private void login(String username, String password) {
		String registerUrlStr = Constant.URL_Login + "?username=" + username + "&password=" + password;
		new MyAsyncTask(tvResult).execute(registerUrlStr);
	}
    /** 
     * AsyncTask类的三个泛型参数： 
     * （1）Param 在执行AsyncTask是需要传入的参数，可用于后台任务中使用 
     * （2）后台任务执行过程中，如果需要在UI上先是当前任务进度，则使用这里指定的泛型作为进度单位 
     * （3）任务执行完毕后，如果需要对结果进行返回，则这里指定返回的数据类型 
     */  
    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {  
  
        private TextView tv; // 举例一个UI元素，后边会用到  
  
        public MyAsyncTask(TextView v) {  
            tv = v;  
        }  
  
        @Override  
        protected void onPreExecute() {  
            Log.w("WangJ", "task onPreExecute()");  
        }  
  
        /** 
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数 
         */  
        @Override  
        protected String doInBackground(String... params) {  
            Log.w("WangJ", "task doInBackground()");  
            HttpURLConnection connection = null;  
            StringBuilder response = new StringBuilder();  
            try {  
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文  
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接  
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST  
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间  
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间  
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理  
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
                String line;  
                while ((line = reader.readLine()) != null) {  
                    response.append(line);  
                }  
            } catch (MalformedURLException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参  
        }  
  
        @Override  
        protected void onProgressUpdate(Integer... values) {  
            // 如果在doInBackground方法，那么就会立刻执行本方法  
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用  
        }  
  
        /** 
         * 运行在UI线程中，所以可以直接操作UI元素 
         * @param s 
         */  
        @Override  
        protected void onPostExecute(String s) {  
            Log.w("WangJ", "task onPostExecute()");  
            showuser = s;
            tv.setText(s);  
        }  
  
    }  
    /* //对登录按钮的点击监控  
    bt_login.setOnClickListener(new OnClickListener() {  
        @Override  
        public void onClick(View v) {  
        	
        	final String checkusername = et_username.getText().toString().trim();
        	final String checkpassword = et_password.getText().toString().trim();
        	if (TextUtils.isEmpty(checkusername)) {
				Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			}
        	if (TextUtils.isEmpty(checkpassword)) {
				Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
			}
        	
            final Handler myHandler = new Handler(){  
                public void handleMessage(Message msg){  
                    String responseResult = (String)msg.obj;  
                    //登录成功  
                    if(responseResult.equals("true")){  
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show(); 
                        Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }  
                    //登录失败  
                    else{  
                        Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();  
                    }  
                }  
            };  

            new Thread(new Runnable() {  
                @Override  
                public void run() {  
                    LoginToServer loginToServer = new LoginToServer();  
                    try {  
                        String result = loginToServer.doPost(et_username.getText().toString().trim(), et_password.getText().toString().trim());  
                        Message msg = new Message();  
                        msg.obj = result;  
                        myHandler.sendMessage(msg);  

                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }).start();  
        }  
    });  */
}  
