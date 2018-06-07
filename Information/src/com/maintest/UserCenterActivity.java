package com.maintest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserCenterActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usercenter);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		TextView user = (TextView)findViewById(R.id.showusername);
		user.setText("�û�����"+bundle.getString("user"));
		TextView college = (TextView)findViewById(R.id.showusercollege);
		college.setText("ѧ    Ժ��"+bundle.getString("college"));
		TextView hobby = (TextView)findViewById(R.id.showuserhobby);
		hobby.setText("��    �ã�"+bundle.getString("hobby"));
		TextView email = (TextView)findViewById(R.id.showuseremail);
		email.setText("��    �䣺"+bundle.getString("email"));
	}
}
