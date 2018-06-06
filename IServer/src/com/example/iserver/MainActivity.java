package com.example.iserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText etAccount;
	private EditText etPassword;
	private TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etAccount = (EditText) findViewById(R.id.et_account);
		etPassword = (EditText) findViewById(R.id.et_password);
		tvResult = (TextView) findViewById(R.id.tv_result);

		Button btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!StringUtil.isEmpty(etAccount.getText().toString())
						&& !StringUtil.isEmpty(etPassword.getText().toString())) {
					Log.e("WangJ", "������");
					login(etAccount.getText().toString(), etPassword.getText().toString());
					Intent intent = new Intent(MainActivity.this, XXXActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this, "�˺š����붼����Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}
			}
		});

		Button btnRegister = (Button) findViewById(R.id.btn_register);
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!StringUtil.isEmpty(etAccount.getText().toString())
						&& !StringUtil.isEmpty(etPassword.getText().toString())) {
					Log.e("WangJ", "������");
					register(etAccount.getText().toString(), etPassword.getText().toString());
				} else {
					Toast.makeText(MainActivity.this, "�˺š����붼����Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void register(String account, String password) {
		String registerUrlStr = Constant.URL_Register + "?account=" + account + "&password=" + password;
		new MyAsyncTask(tvResult).execute(registerUrlStr);
	}

	private void login(String account, String password) {
		String registerUrlStr = Constant.URL_Login + "?account=" + account + "&password=" + password;
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
            tv.setText(s);  
        }  
  
    }  
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
