package com.receiver;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * ״̬�ص�
 */

public class MyJPushMessageReceiver extends JPushMessageReceiver {

    private static final String TAG = "MyJPushMessageReceiver";

    /**
     * tag��ɾ��ĵĲ������ڴ˷����лص����
     */
    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
        //����2���ص�����
        Log.e(TAG, "onTagOperatorResult��ѯ�õ��ı���: " + jPushMessage.getAlias());
        Log.e(TAG, "onTagOperatorResult��ѯ�õ��ı�ǩ: " + jPushMessage.getTags());
        Log.e(TAG, "onTagOperatorResult������0Ϊ�ɹ�: " + jPushMessage.getErrorCode());
        Log.e(TAG, "onTagOperatorResult����ı�ʾ: " + jPushMessage.getSequence());
    }

    /**
     * ��ѯĳ��tag�뵱ǰ�û��İ�״̬�Ĳ������ڴ˷����лص����
     */
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
        Log.e(TAG, "onCheckTagOperatorResult������0Ϊ�ɹ�: " + jPushMessage.getErrorCode());
    }

    /**
     * alias��صĲ������ڴ˷����лص����
     */
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        Log.e(TAG, "onAliasOperatorResult������0Ϊ�ɹ�: " + jPushMessage.getErrorCode());
    }
}