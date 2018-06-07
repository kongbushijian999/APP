package com.maintest;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends Activity {  
    
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		
		Button button1 = (Button)findViewById(R.id.fimageButton1);
		Button button2 = (Button)findViewById(R.id.fimageButton2);
		Button button3 = (Button)findViewById(R.id.fimageButton3);
		Button button4 = (Button)findViewById(R.id.fimageButton4);
		
		/*button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
			}
		});*/
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(FirstActivity.this, HobbyActivity.class);
				startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(FirstActivity.this, CalendarActivity.class);
				startActivity(intent);
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(FirstActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		TextView textView = (TextView)findViewById(R.id.textView1);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://meeting.xjtu.edu.cn/lecturenotice/4874.htm"));
				startActivity(intent);
			}
		});
		
	}


  
}

