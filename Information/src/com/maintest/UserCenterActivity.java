package com.maintest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserCenterActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercenter);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		TextView user = (TextView)findViewById(R.id.showusername);
		user.setText("用户名："+bundle.getString("user"));
		TextView college = (TextView)findViewById(R.id.showusercollege);
		college.setText("学    院："+bundle.getString("college"));
		TextView hobby = (TextView)findViewById(R.id.showuserhobby);
		hobby.setText("爱    好："+bundle.getString("hobby"));
		TextView email = (TextView)findViewById(R.id.showuseremail);
		email.setText("邮    箱："+bundle.getString("email"));
	}
}
