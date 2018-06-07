package com.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.maintest.MainActivity;
import com.maintest.SecondActivity;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jpush.android.api.JPushInterface;

/**
 * �Զ��������
 * 
 * ������������ Receiver����
 * 1) Ĭ���û����������
 * 2) ���ղ����Զ�����Ϣ
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

    @Override
    public void onReceive(final Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyJpushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyJpushReceiver] ����Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyJpushReceiver] ���յ������������Զ�����Ϣ: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            //��Ҫ�Զ���֪ͨչʾ

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyJpushReceiver] ���յ�����������֪ͨ");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyJpushReceiver] ���յ�����������֪ͨ��ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyJpushReceiver] �û��������֪ͨ");
            //�������дһЩ��ת��ĳ����Ĵ��룬�û������ͻ������ת
            String url = skiptourl(bundle);
            Intent intent2= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyJpushReceiver] �û��յ���RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //��������� JPushInterface.EXTRA_EXTRA �����ݴ�����룬������µ�Activity�� ��һ����ҳ��..
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyJpushReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
            Log.d(TAG, "[MyJpushReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private String skiptourl(Bundle bundle) {
		// TODO �Զ����ɵķ������
    	String content = (String) bundle.get(JPushInterface.EXTRA_ALERT);
    	String pattern = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
    	// ���� Pattern ����
        Pattern r = Pattern.compile(pattern);
   
        // ���ڴ��� matcher ����
        Matcher m = r.matcher(content);
        if (m.find( )) {
           System.out.println("Found value: " + m.group(0) );
           return m.group(0);
        } else {
           System.out.println("NO MATCH");
           return "http://www.baidu.com";
        }
	}

	// ��ӡ���е� intent extra ����
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } 
            else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}

