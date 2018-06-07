package com.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.nodes.Element;

import com.bean.Football_Sohu;
import com.bean.Lecture;
import com.bean.Notice;
import com.dao.Football_SohuDao;
import com.dao.LectureDao;
import com.dao.NoticeDao;
import com.jsoup.JsoupDemo;
import com.sun.org.apache.regexp.internal.recompile;

public class Main {

	public static void main(String[] args) throws Exception {
		 //lecturestart();
		// addnewlecture();
		//addnoticestart();
		// addnoticenew();
		 addsohustart();
		// addsohunew();
	}

	// 插入lecture初始数据
	public static void lecturestart() throws Exception {
		int k = 0; // 用来计数
		LectureDao md = new LectureDao(); // 用来对获取到的数据插入数据库

		String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";// 爬取的地址
		// 获取JsoupDemo类的对象
		JsoupDemo jsoup = new JsoupDemo();
		List<String> href = jsoup.link(url);

		List<Lecture> m = jsoup.get(href);

		for (Lecture e : m) {
			k++;
			// 插入数据库
			md.insert(e.getLecture_title(), e.getLecture_time(), e.getLecture_url());
			System.out.println(k);
		}
	}

	// 判断是否插入新的lecture数据
	public static Boolean addnewlecture() throws Exception {
		// 获取JsoupDemo类的对象
		JsoupDemo jsoup = new JsoupDemo();
		String linkText = jsoup.newlecture(); // 得到用来判断的数据

		// 进行判断
		LectureDao md = new LectureDao();
		boolean b = md.check(linkText);

		// 插入新的数据
		if (b) {
			System.out.println("没有新的讲座信息");
			return false;
		} else {
			lecturestartnew();
			System.out.println("获得新的讲座信息");
			return true;
		}

	}

	// 插入lecture新的数据
	public static void lecturestartnew() throws Exception {
		int k = 0; // 用来计数
		LectureDao md = new LectureDao(); // 用来对获取到的数据插入数据库

		String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";// 爬取的地址
		// 获取JsoupDemo类的对象
		JsoupDemo jsoup = new JsoupDemo();
		List<String> href = jsoup.link(url);

		List<Lecture> m = jsoup.get(href);

		Lecture e = m.get(0);
		// 插入数据库
		md.insert(e.getLecture_title(), e.getLecture_time(), e.getLecture_url());
		System.out.println(k);

	}

	// 插入notice初始数据
	public static void addnoticestart() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Notice> notices = jsoup.getnotice();
		NoticeDao noticeDao = new NoticeDao();

		for (Notice e : notices) {
			noticeDao.insert(e.getNotice_title(), e.getNotice_time(), e.getNotice_url());
		}
	}

	// 插入notice新的数据
	public static boolean addnoticenew() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Notice> notices = jsoup.getnoticenew();
		NoticeDao noticeDao = new NoticeDao();

		Notice e = notices.get(0);
		if (noticeDao.check(e.getNotice_title())) {
			System.out.println("信息已存在");
			return false;
		} else {
			noticeDao.insert(e.getNotice_title(), e.getNotice_time(), e.getNotice_url());
			System.out.println("信息已插入");
			return true;
		}

	}

	// 插入sohu初始数据
	public static void addsohustart() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Football_Sohu> sohus = jsoup.getsohu();
		Football_SohuDao sohuDao = new Football_SohuDao();

		for (Football_Sohu e : sohus) {
			sohuDao.insert(e.getSohu_title(), e.getSohu_url());
		}
	}

	// 插入sohu新的数据
	public static boolean addsohunew() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Football_Sohu> sohus = jsoup.getsohunew();
		Football_SohuDao sohuDao = new Football_SohuDao();

		Football_Sohu e = sohus.get(0);
		if (sohuDao.check(e.getSohu_title())) {
			System.out.println("信息已存在");
			return false;
		} else {
			sohuDao.insert(e.getSohu_title(), e.getSohu_url());
			System.out.println("信息已插入");
			return true;
		}

	}
}
