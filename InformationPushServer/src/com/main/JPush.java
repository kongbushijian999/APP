package com.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dao.Football_SohuDao;
import com.dao.LectureDao;
import com.dao.NoticeDao;
import com.test.JPushtest;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class JPush {

	protected static final Logger LOG = LoggerFactory.getLogger(JPush.class);
	private static final String appKey ="1a98134f57ad5c328b472c7f";
	private static final String masterSecret = "7fa12542b387bd4aee8987f2";
	public static String ALERT = "���ͳɹ�";
	
	public static void main(String[] args) {

		Main main = new Main();
		// ִ��ʱ�䣬ʱ�䵥λΪ����,�������趨������С�ڵ���0
		Integer cacheTime = 300000;
		// �ӳ�ʱ�䣬ʱ�䵥λΪ����,�������趨������С�ڵ���0
		Integer delay = 1000;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// ��Ҫִ�еĲ���
				//1.��ȡ��Ϣ���ж��Ƿ�������Ϣ���Ǿʹ�����ݿ⣬���ǣ���ִ��
				//2.�������ݿ⣬�õ�������Ϣ
				//3.������Ϣ
				try {
					if (main.addnewlecture()) {
						//��ʱ����µ���Ϣ�����Ѿ��������ݿ�
						LectureDao lectureDao = new LectureDao();
						List<String> jpush = lectureDao.push();
						//��ʱ����Ϣ�Ѿ���ӵ�List jpush����
						ALERT = jpush.get(0)+"  "+jpush.get(2);
						SendPush();
					} else {
						System.out.println();
					}
					if (main.addnoticenew()) {
						NoticeDao noticeDao = new NoticeDao();
						List<String> jpushnotice = noticeDao.push();
						ALERT = jpushnotice.get(0)+"  "+jpushnotice.get(2);
						SendPush();
					}
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				
					
			}
		}, 1000, 6000000);
		
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				try {
					if (main.addsohunew()) {
						Football_SohuDao football_SohuDao = new Football_SohuDao();
						List<String> jpushsohu = football_SohuDao.push();
						ALERT = jpushsohu.get(0)+"  "+jpushsohu.get(1);
						SendPush();
					}
				} catch (SQLException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}, 1000, 60000);
		
		
	}
	
	
	
	
	public static void SendPush() {
	    // HttpProxy proxy = new HttpProxy("localhost", 3128);
	    // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert();
        
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
	}
	
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
}
