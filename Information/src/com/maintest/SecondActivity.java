package com.maintest;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
				String userall = LoginActivity.showuser;
				String[] usersplit = userall.split("  ");
				String user = usersplit[0];
				String college = usersplit[1];
				String hobby = usersplit[2];
				String email = usersplit[3];
				Intent intent = new Intent(SecondActivity.this, UserCenterActivity.class);
				Bundle bundle = new Bundle();
				bundle.putCharSequence("user", user);
				bundle.putCharSequence("college", college);
				bundle.putCharSequence("hobby", hobby);
				bundle.putCharSequence("email", email);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		TextView noticetextView1 = (TextView)findViewById(R.id.noticetextView1);
		noticetextView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dean.xjtu.edu.cn/info/1095/5763.htm"));
				startActivity(intent);
			}
		});
		
		TextView noticetextView2 = (TextView)findViewById(R.id.noticetextView2);
		noticetextView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dean.xjtu.edu.cn/info/1091/5715.htm"));
				startActivity(intent);
			}
		});
		
		TextView noticetextView3 = (TextView)findViewById(R.id.noticetextView3);
		noticetextView3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dean.xjtu.edu.cn/info/1021/5892.htm"));
				startActivity(intent);
			}
		});
		
		TextView noticetextView4 = (TextView)findViewById(R.id.noticetextView4);
		noticetextView4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dean.xjtu.edu.cn/info/1024/5888.htm"));
				startActivity(intent);
			}
		});
		
		TextView noticetextView5 = (TextView)findViewById(R.id.noticetextView5);
		noticetextView5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dean.xjtu.edu.cn/info/1095/5877.htm"));
				startActivity(intent);
			}
		});
		
		TextView textView1 = (TextView)findViewById(R.id.textView1);
		textView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://meeting.xjtu.edu.cn/lecturenotice/4915.htm"));
				startActivity(intent);
			}
		});
		
		TextView textView2 = (TextView)findViewById(R.id.textView2);
		textView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://meeting.xjtu.edu.cn/lecturenotice/4902.htm"));
				startActivity(intent);
			}
		});
		
		TextView textView3 = (TextView)findViewById(R.id.textView3);
		textView3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://meeting.xjtu.edu.cn/lecturenotice/4914.htm"));
				startActivity(intent);
			}
		});
		
		TextView textView4 = (TextView)findViewById(R.id.textView4);
		textView4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://meeting.xjtu.edu.cn/lecturenotice/4918.htm"));
				startActivity(intent);
			}
		});
		
		TextView textView5 = (TextView)findViewById(R.id.textView5);
		textView5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://meeting.xjtu.edu.cn/lecturenotice/4905.htm"));
				startActivity(intent);
			}
		});
	}

	
}
