package com.test;

import java.util.Timer;
import java.util.TimerTask;

public class timetest {
public static void main(String[] args) {
	JPushtest jPushtest = new JPushtest();
		// ִ��ʱ�䣬ʱ�䵥λΪ����,���߿������趨������С�ڵ���0
		Integer cacheTime = 3000;
		// �ӳ�ʱ�䣬ʱ�䵥λΪ����,���߿������趨������С�ڵ���0
		Integer delay = 1000;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				// ��Ҫִ�еĲ���
				System.out.println("diһ��");
				jPushtest.SendPush();
			}
		}, delay, cacheTime);
}
}
