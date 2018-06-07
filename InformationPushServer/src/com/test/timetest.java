package com.test;

import java.util.Timer;
import java.util.TimerTask;

public class timetest {
public static void main(String[] args) {
	JPushtest jPushtest = new JPushtest();
		// 执行时间，时间单位为毫秒,读者可自行设定，不得小于等于0
		Integer cacheTime = 3000;
		// 延迟时间，时间单位为毫秒,读者可自行设定，不得小于等于0
		Integer delay = 1000;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				// 你要执行的操作
				System.out.println("di一次");
				jPushtest.SendPush();
			}
		}, delay, cacheTime);
}
}
