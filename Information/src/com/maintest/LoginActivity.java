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
	//��¼�û��������  
    private EditText et_username;  
    //��¼���������  
    private EditText et_password;  
    //��¼��ť  
    private Button bt_login;  
    private TextView tvResult;
    public static String showuser;
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.login);  
  
        //��ȡ���  
        et_username = (EditText)findViewById(R.id.username);  
        et_password = (EditText)findViewById(R.id.password);  
        tvResult = (TextView) findViewById(R.id.tv_result);
        bt_login = (Button)findViewById(R.id.login);   
        bt_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!StringUtil.isEmpty(et_username.getText().toString())
						&& !StringUtil.isEmpty(et_password.getText().toString())) {
					Log.e("WangJ", "������");
					login(et_username.getText().toString(), et_password.getText().toString());
					Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(LoginActivity.this, "�˺š����붼����Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}
			}
		});
  

  
    }  
    
    private void login(String username, String password) {
		String registerUrlStr = Constant.URL_Login + "?username=" + username + "&password=" + password;
		new MyAsyncTask(tvResult).execute(registerUrlStr);
	}
    /** 
     * AsyncTask����������Ͳ����� 
     * ��1��Param ��ִ��AsyncTask����Ҫ����Ĳ����������ں�̨������ʹ�� 
     * ��2����̨����ִ�й����У������Ҫ��UI�����ǵ�ǰ������ȣ���ʹ������ָ���ķ�����Ϊ���ȵ�λ 
     * ��3������ִ����Ϻ������Ҫ�Խ�����з��أ�������ָ�����ص��������� 
     */  
    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {  
  
        private TextView tv; // ����һ��UIԪ�أ���߻��õ�  
  
        public MyAsyncTask(TextView v) {  
            tv = v;  
        }  
  
        @Override  
        protected void onPreExecute() {  
            Log.w("WangJ", "task onPreExecute()");  
        }  
  
        /** 
         * @param params �����params��һ�����飬��AsyncTask�ڼ��������ǵ���execute()��������Ĳ��� 
         */  
        @Override  
        protected String doInBackground(String... params) {  
            Log.w("WangJ", "task doInBackground()");  
            HttpURLConnection connection = null;  
            StringBuilder response = new StringBuilder();  
            try {  
                URL url = new URL(params[0]); // ����һ��URL,ע������ðٶ���ҳʵ�飬��ʹ��https��ͷ�������ȡ�������ر���  
                connection = (HttpURLConnection) url.openConnection(); // �򿪸�URL����  
                connection.setRequestMethod("GET"); // �������󷽷�����POST��GET��������������GET����˵��POST��ʱ������POST  
                connection.setConnectTimeout(80000); // �������ӽ����ĳ�ʱʱ��  
                connection.setReadTimeout(80000); // �������籨���շ���ʱʱ��  
                InputStream in = connection.getInputStream();  // ͨ�����ӵ���������ȡ�·����ģ�Ȼ�����Java��������  
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
            return response.toString(); // ���ﷵ�صĽ������ΪonPostExecute���������  
        }  
  
        @Override  
        protected void onProgressUpdate(Integer... values) {  
            // �����doInBackground��������ô�ͻ�����ִ�б�����  
            // ��������UI�߳���ִ�У����Ը���UIԪ�أ����͵ľ��Ǹ��½��������ȣ�һ����������ʱ��ʹ��  
        }  
  
        /** 
         * ������UI�߳��У����Կ���ֱ�Ӳ���UIԪ�� 
         * @param s 
         */  
        @Override  
        protected void onPostExecute(String s) {  
            Log.w("WangJ", "task onPostExecute()");  
            showuser = s;
            tv.setText(s);  
        }  
  
    }  
    /* //�Ե�¼��ť�ĵ�����  
    bt_login.setOnClickListener(new OnClickListener() {  
        @Override  
        public void onClick(View v) {  
        	
        	final String checkusername = et_username.getText().toString().trim();
        	final String checkpassword = et_password.getText().toString().trim();
        	if (TextUtils.isEmpty(checkusername)) {
				Toast.makeText(LoginActivity.this, "�û�������Ϊ��", Toast.LENGTH_SHORT).show();
			}
        	if (TextUtils.isEmpty(checkpassword)) {
				Toast.makeText(LoginActivity.this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
			}
        	
            final Handler myHandler = new Handler(){  
                public void handleMessage(Message msg){  
                    String responseResult = (String)msg.obj;  
                    //��¼�ɹ�  
                    if(responseResult.equals("true")){  
                        Toast.makeText(LoginActivity.this, "��¼�ɹ���", Toast.LENGTH_SHORT).show(); 
                        Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }  
                    //��¼ʧ��  
                    else{  
                        Toast.makeText(LoginActivity.this, "��¼ʧ�ܣ�", Toast.LENGTH_SHORT).show();  
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
