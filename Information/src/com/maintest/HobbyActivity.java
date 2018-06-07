package com.maintest;

import java.io.IOException;

import com.toserver.HobbyToServer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HobbyActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hobby);
		
		final EditText newneturl = (EditText)findViewById(R.id.newneturl);
		final EditText newnettitle = (EditText)findViewById(R.id.newnettitle);
		Button addnewnet = (Button)findViewById(R.id.addnewnet);
		//Button deletenet = (Button)findViewById(R.id.deletenet);
		
		TextView hobbytextView1 = (TextView)findViewById(R.id.hobbytextView1);
		hobbytextView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234102995_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView2 = (TextView)findViewById(R.id.hobbytextView2);
		hobbytextView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234085113_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView3 = (TextView)findViewById(R.id.hobbytextView3);
		hobbytextView3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234067477_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView4 = (TextView)findViewById(R.id.hobbytextView4);
		hobbytextView4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234067264_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView5 = (TextView)findViewById(R.id.hobbytextView5);
		hobbytextView5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234067243_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView6 = (TextView)findViewById(R.id.hobbytextView6);
		hobbytextView6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234067602_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView7 = (TextView)findViewById(R.id.hobbytextView7);
		hobbytextView7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234066949_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView8 = (TextView)findViewById(R.id.hobbytextView8);
		hobbytextView8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234066942_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView9 = (TextView)findViewById(R.id.hobbytextView9);
		hobbytextView9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234066771_463728"));
				startActivity(intent);
			}
		});
		
		TextView hobbytextView10 = (TextView)findViewById(R.id.hobbytextView10);
		hobbytextView10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sohu.com/a/234066813_463728"));
				startActivity(intent);
			}
		});
		
		
		
		addnewnet.setOnClickListener(new OnClickListener() {
			
			String buttonnum = "add";
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //添加成功  
                        if(responseResult.equals("true")){  
                            Toast.makeText(HobbyActivity.this, "添加成功！", Toast.LENGTH_SHORT).show(); 
                            Intent intent = new Intent(HobbyActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }  
                        //添加失败  
                        else{  
                            Toast.makeText(HobbyActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();  
                        }  
                    }  
                };  
  
                new Thread(new Runnable() {  
                    @Override  
                    public void run() {  
                        HobbyToServer hobbyToServer = new HobbyToServer();  
                        try {  
                            String result = hobbyToServer.doPost(newneturl.getText().toString().trim(),newnettitle.getText().toString().trim(),buttonnum);  
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
	
	    /*deletenet.setOnClickListener(new OnClickListener() {
	    	String buttonnum = "del";
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				final Handler myHandler = new Handler(){  
                    public void handleMessage(Message msg){  
                        String responseResult = (String)msg.obj;  
                        //删除成功  
                        if(responseResult.equals("true")){  
                            Toast.makeText(HobbyActivity.this, "删除成功！", Toast.LENGTH_SHORT).show(); 
                            Intent intent = new Intent(HobbyActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }  
                        //删除失败  
                        else{  
                            Toast.makeText(HobbyActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();  
                        }  
                    }  
                };  
  
                new Thread(new Runnable() {  
                    @Override  
                    public void run() {  
                    	HobbyToServer hobbyToServer = new HobbyToServer();  
                        try {  
                            String result = hobbyToServer.doPost(newneturl.getText().toString().trim(),newnettitle.getText().toString().trim(),buttonnum);  
                            Message msg = new Message();  
                            msg.obj = result;  
                            myHandler.sendMessage(msg);  
  
                        } catch (IOException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }).start();  
			}
		});*/
	}

}
