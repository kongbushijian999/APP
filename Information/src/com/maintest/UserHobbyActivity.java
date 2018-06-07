package com.maintest;

import java.io.IOException;


import com.toserver.UserHobbyToServer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserHobbyActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userhobby);
		
		final EditText unewneturl = (EditText)findViewById(R.id.unewneturl);
		final EditText unewnettitle = (EditText)findViewById(R.id.unewnettitle);
		Button uaddnewnet = (Button)findViewById(R.id.uaddnewnet);
		Button udeletenet = (Button)findViewById(R.id.udeletenet);
		
		uaddnewnet.setOnClickListener(new OnClickListener() {
			String buttonnum = "add";
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //��ӳɹ�  
                        if(responseResult.equals("true")){  
                            Toast.makeText(UserHobbyActivity.this, "��ӳɹ���", Toast.LENGTH_SHORT).show(); 
                            Intent intent = new Intent(UserHobbyActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }  
                        //���ʧ��  
                        else{  
                            Toast.makeText(UserHobbyActivity.this, "���ʧ�ܣ�", Toast.LENGTH_SHORT).show();  
                        }  
                    }  
                };  
  
                new Thread(new Runnable() {  
                    @Override  
                    public void run() {  
                    	UserHobbyToServer userHobbyToServer = new UserHobbyToServer();  
                        try {  
                            String result = userHobbyToServer.doPost(unewneturl.getText().toString().trim(),unewnettitle.getText().toString().trim(),buttonnum);  
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
		
		udeletenet.setOnClickListener(new OnClickListener() {
			String buttonnum = "del";
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //ɾ���ɹ�  
                        if(responseResult.equals("true")){  
                            Toast.makeText(UserHobbyActivity.this, "ɾ���ɹ���", Toast.LENGTH_SHORT).show(); 
                            Intent intent = new Intent(UserHobbyActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }  
                        //���ʧ��  
                        else{  
                            Toast.makeText(UserHobbyActivity.this, "ɾ��ʧ�ܣ�", Toast.LENGTH_SHORT).show();  
                        }  
                    }  
                };  
  
                new Thread(new Runnable() {  
                    @Override  
                    public void run() {  
                    	UserHobbyToServer userHobbyToServer = new UserHobbyToServer();  
                        try {  
                            String result = userHobbyToServer.doPost(unewneturl.getText().toString().trim(),unewnettitle.getText().toString().trim(),buttonnum);  
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
}
