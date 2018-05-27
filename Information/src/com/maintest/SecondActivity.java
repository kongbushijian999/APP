package com.maintest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		Button button1 = (Button)findViewById(R.id.imageButton1);
		Button button2 = (Button)findViewById(R.id.imageButton2);
		Button button3 = (Button)findViewById(R.id.imageButton3);
		Button button4 = (Button)findViewById(R.id.imageButton4);
		
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
				Intent intent = new Intent(SecondActivity.this, HobbyActivity.class);
				startActivity(intent);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(SecondActivity.this, CalendarActivity.class);
				startActivity(intent);
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(SecondActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

	
}
