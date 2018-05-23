package com.maintest;

import android.app.Activity;
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;  
import android.view.View;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.Toast;  
  
import java.io.IOException;  
  
public class LoginActivity extends Activity {  
    //��¼�û��������  
    private EditText et_username;  
    //��¼���������  
    private EditText et_password;  
    //��¼��ť  
    private Button bt_login;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.login);  
  
        //��ȡ���  
        init();  
  
        //�Ե�¼��ť�ĵ�����  
        bt_login.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //��¼�ɹ�  
                        if(responseResult.equals("true")){  
                            Toast.makeText(LoginActivity.this, "��¼�ɹ���", Toast.LENGTH_LONG).show();  
                        }  
                        //��¼ʧ��  
                        else{  
                            Toast.makeText(LoginActivity.this, "��¼ʧ�ܣ�", Toast.LENGTH_LONG).show();  
                        }  
                    }  
                };  
  
                new Thread(new Runnable() {  
                    @Override  
                    public void run() {  
                        GuestToServer guestToServer = new GuestToServer();  
                        try {  
                            String result = guestToServer.doPost(et_username.getText().toString().trim(), et_password.getText().toString().trim());  
                            Message msg = new Message();  
                            msg.obj = result;  
                            myHandler.sendMessage(msg);  
  
                        } catch (IOException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }).start();  
            }  
        });  
  
    }  
  
    /** 
     * ��ȡ��� 
     */  
    private void init() {  
        et_username = (EditText)findViewById(R.id.username);  
        et_password = (EditText)findViewById(R.id.password);  
        bt_login = (Button)findViewById(R.id.login);  
    }  
}  
