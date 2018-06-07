package com.maintest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CalendarActivity extends Activity {

	private Button startSystemClockButton = null;  
	  
	  
    private List<PackageInfo> allPackageInfos;//系统安装所有软件  
    private List<PackageInfo> clockPackageInfos;//系统时钟软件  
  
    private static final int HANDLE_MESSAGE_KEY = 1001;  
    private Handler myHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            if (msg != null) {  
                switch (msg.what) {  
                    case HANDLE_MESSAGE_KEY:  
                        Toast.makeText(getApplicationContext(), "--app scan over--", Toast.LENGTH_SHORT).show();  
                        Log.d("CXC", "--app scan over--");  
                        break;  
  
                    default:  
                        break;  
  
                }  
  
            }  
  
        }  
    };  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		initViews();  
        initData();  
  
        //begin scan installed applications;  
        //// TODO: 16/1/15  
        new Thread(new ScanInstalledAppsRunnable()).start();  
	}
	
	private void initViews() {  
        startSystemClockButton = (Button) findViewById(R.id.start_system_clock_bt);  
        startSystemClockButton.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                //// TODO: 16/1/15  
                startSystemClock();  
            }  
        });  
    }  
  
    private void initData() {  
        clockPackageInfos = new ArrayList<PackageInfo>();  
    }  
  
  
    class ScanInstalledAppsRunnable implements Runnable {  
        @Override  
        public void run() {  
            allPackageInfos = getPackageManager()  
                    .getInstalledPackages(0);  
  
            if (allPackageInfos == null || allPackageInfos.size() == 0) {  
                //// TODO: 16/1/15 todo nothing  
                return;  
            }  
  
            if (clockPackageInfos == null) {  
                clockPackageInfos = new ArrayList<PackageInfo>();  
            }  
  
            PackageInfo tempPackageInfo = null;  
            for (int i = 0; i < allPackageInfos.size(); i++) {  
                tempPackageInfo = allPackageInfos.get(i);  
                if (tempPackageInfo != null) {  
  
                    if (isSystemApplication(tempPackageInfo.applicationInfo) &&  
                            isClockApplication(tempPackageInfo.packageName)) {  
                        clockPackageInfos.add(tempPackageInfo);  
                    }  
  
                }  
            }  
  
            Message message = myHandler.obtainMessage();  
            message.what = CalendarActivity.HANDLE_MESSAGE_KEY;  
            myHandler.sendMessage(message);  
        }  
    }  
  
  
    private boolean isSystemApplication(ApplicationInfo applicationInfo) {  
        boolean isSystemApp = false;  
        if (applicationInfo != null) {  
            if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0  
                    || (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {  
                isSystemApp = true;  
  
            }  
        }  
        return isSystemApp;  
    }  
  
  
    private boolean isClockApplication(String packageName) {  
        boolean isClockApp = false;  
        if (packageName != null && packageName.contains("clock") && !packageName.contains("widget")) {  
            isClockApp = true;  
        }  
        return isClockApp;  
    }  
  
    private void startSystemClock() {  
        if (clockPackageInfos == null || clockPackageInfos.size() == 0) {  
            Toast.makeText(getApplicationContext(), "--启动系统闹钟失败1--", Toast.LENGTH_SHORT).show();  
            return;  
        }  
  
        Log.d("CXC", "---clock package size :" + clockPackageInfos.size());  
        Log.d("CXC", "---clock package [0] :" + clockPackageInfos.get(0).packageName);  
        try {  
            Intent startSysClockIntent = getPackageManager().getLaunchIntentForPackage(clockPackageInfos.get(0).packageName);  
            startActivity(startSysClockIntent);  
        } catch (Exception e) {  
            Toast.makeText(getApplicationContext(), "--启动系统闹钟失败2--", Toast.LENGTH_SHORT).show();  
        }  
  
  
    }  
}
