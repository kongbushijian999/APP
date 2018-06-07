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
		// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
				final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //添加成功  
                        if(responseResult.equals("true")){  
                            Toast.makeText(UserHobbyActivity.this, "添加成功！", Toast.LENGTH_SHORT).show(); 
                            Intent intent = new Intent(UserHobbyActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }  
                        //添加失败  
                        else{  
                            Toast.makeText(UserHobbyActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();  
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
				// TODO 自动生成的方法存根
				final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //删除成功  
                        if(responseResult.equals("true")){  
                            Toast.makeText(UserHobbyActivity.this, "删除成功！", Toast.LENGTH_SHORT).show(); 
                            Intent intent = new Intent(UserHobbyActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }  
                        //添加失败  
                        else{  
                            Toast.makeText(UserHobbyActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();  
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
