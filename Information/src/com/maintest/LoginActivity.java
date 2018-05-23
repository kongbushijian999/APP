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
    //登录用户名输入框  
    private EditText et_username;  
    //登录密码输入框  
    private EditText et_password;  
    //登录按钮  
    private Button bt_login;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.login);  
  
        //获取组件  
        init();  
  
        //对登录按钮的点击监控  
        bt_login.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //登录成功  
                        if(responseResult.equals("true")){  
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();  
                        }  
                        //登录失败  
                        else{  
                            Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();  
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
     * 获取组件 
     */  
    private void init() {  
        et_username = (EditText)findViewById(R.id.username);  
        et_password = (EditText)findViewById(R.id.password);  
        bt_login = (Button)findViewById(R.id.login);  
    }  
}  
