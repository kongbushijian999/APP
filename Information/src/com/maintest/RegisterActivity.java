package com.maintest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.toserver.RegisterToServer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	protected static final int ERROR = 1;
	protected static final int SUCCESS = 2;

	private EditText reusername;
	private EditText reemail;
	private EditText repassword;
	private EditText repasswordtwo;
	private Spinner recollege;
	private Spinner rehobby;
	private Button register;

	private static String stcollege;
	private static String sthobby;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		

		reusername = (EditText) findViewById(R.id.reusername);
		reemail = (EditText) findViewById(R.id.reemail);
		repassword = (EditText) findViewById(R.id.repassword);
		repasswordtwo = (EditText) findViewById(R.id.repasswordtwo);

		recollege = (Spinner) findViewById(R.id.recollege);
		rehobby = (Spinner) findViewById(R.id.rehobby);
		register = (Button) findViewById(R.id.register);

		recollege.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO 自动生成的方法存根
				String result = parent.getItemAtPosition(position).toString();
				stcollege = result.trim();

			}
		});

		rehobby.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO 自动生成的方法存根
				String result = parent.getItemAtPosition(position).toString();
				sthobby = result.trim();

			}
		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Toast.makeText(RegisterActivity.this, sthobby, Toast.LENGTH_SHORT).show();
				RegisterActivity registerActivity = new RegisterActivity();
				registerActivity.setUserTags();
				final String stusername = reusername.getText().toString().trim();
				final String stemail = reemail.getText().toString().trim();
				final String stpassword = repassword.getText().toString().trim();
				final String stpasswordtwo = repasswordtwo.getText().toString().trim();

				if (TextUtils.isEmpty(stusername)) {
					Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
				}
				if (TextUtils.isEmpty(stemail)) {
					Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
				}
				if (TextUtils.isEmpty(stpassword)) {
					Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
				}
				if (TextUtils.isEmpty(stpasswordtwo)) {
					Toast.makeText(RegisterActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
				}
				if (!stpassword.equals(stpasswordtwo)) {
					Toast.makeText(RegisterActivity.this, "两次密码要一致", Toast.LENGTH_SHORT).show();
				}

				final Handler myHandler = new Handler() {
					public void handleMessage(Message msg) {
						String responseResult = (String) msg.obj;
						// 注册成功
						if (responseResult.equals("true")) {
							Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(RegisterActivity.this, SecondActivity.class);
							startActivity(intent);
						}
						// 注册失败
						else {
							Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
						}
					}
				};

				new Thread(new Runnable() {
					@Override
					public void run() {
						RegisterToServer registerToServer = new RegisterToServer();
						try {
							String result = registerToServer.doPost(stusername, sthobby, stemail, stpassword,
									stpasswordtwo, stcollege);
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
     * 1-2-3-4
     * 增、删、改、查
     */
    public void setBasicSetup(int type) {
        if (type == 1) {
            //设置别名（新的调用会覆盖之前的设置）
            JPushInterface.setAlias(this, 0, "0x123");
            //设置标签（同上）
            JPushInterface.setTags(this, 0, setUserTags());
        } else if (type == 2) {
            //删除别名
            JPushInterface.deleteAlias(this, 0);
            //删除指定标签
            JPushInterface.deleteTags(this, 0, setUserTags());
            //删除所有标签
            JPushInterface.cleanTags(this, 0);
        } else if (type == 3) {
            //增加tag用户量(一般都是登录成功设置userid为目标，在别处新增加比较少见)
            JPushInterface.addTags(this, 0, setUserTags());
        } else if (type == 4) {
            //查询所有标签
            JPushInterface.getAllTags(this, 0);
            //查询别名
            JPushInterface.getAlias(this, 0);
            //查询指定tag与当前用户绑定的状态（MyJPushMessageReceiver获取）
            JPushInterface.checkTagBindState(this, 0, "0x123");
            //获取注册id
            JPushInterface.getRegistrationID(this);
        }
    }

   
    private static Set<String> setUserTags() {
        Set<String> tags = new HashSet<String>();
        tags.add(stcollege);
        tags.add(sthobby);
        return tags;
    }
	
	        

}
