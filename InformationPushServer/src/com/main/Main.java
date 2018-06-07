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

	// ����lecture��ʼ����
	public static void lecturestart() throws Exception {
		int k = 0; // ��������
		LectureDao md = new LectureDao(); // �����Ի�ȡ�������ݲ������ݿ�

		String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";// ��ȡ�ĵ�ַ
		// ��ȡJsoupDemo��Ķ���
		JsoupDemo jsoup = new JsoupDemo();
		List<String> href = jsoup.link(url);

		List<Lecture> m = jsoup.get(href);

		for (Lecture e : m) {
			k++;
			// �������ݿ�
			md.insert(e.getLecture_title(), e.getLecture_time(), e.getLecture_url());
			System.out.println(k);
		}
	}

	// �ж��Ƿ�����µ�lecture����
	public static Boolean addnewlecture() throws Exception {
		// ��ȡJsoupDemo��Ķ���
		JsoupDemo jsoup = new JsoupDemo();
		String linkText = jsoup.newlecture(); // �õ������жϵ�����

		// �����ж�
		LectureDao md = new LectureDao();
		boolean b = md.check(linkText);

		// �����µ�����
		if (b) {
			System.out.println("û���µĽ�����Ϣ");
			return false;
		} else {
			lecturestartnew();
			System.out.println("����µĽ�����Ϣ");
			return true;
		}

	}

	// ����lecture�µ�����
	public static void lecturestartnew() throws Exception {
		int k = 0; // ��������
		LectureDao md = new LectureDao(); // �����Ի�ȡ�������ݲ������ݿ�

		String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";// ��ȡ�ĵ�ַ
		// ��ȡJsoupDemo��Ķ���
		JsoupDemo jsoup = new JsoupDemo();
		List<String> href = jsoup.link(url);

		List<Lecture> m = jsoup.get(href);

		Lecture e = m.get(0);
		// �������ݿ�
		md.insert(e.getLecture_title(), e.getLecture_time(), e.getLecture_url());
		System.out.println(k);

	}

	// ����notice��ʼ����
	public static void addnoticestart() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Notice> notices = jsoup.getnotice();
		NoticeDao noticeDao = new NoticeDao();

		for (Notice e : notices) {
			noticeDao.insert(e.getNotice_title(), e.getNotice_time(), e.getNotice_url());
		}
	}

	// ����notice�µ�����
	public static boolean addnoticenew() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Notice> notices = jsoup.getnoticenew();
		NoticeDao noticeDao = new NoticeDao();

		Notice e = notices.get(0);
		if (noticeDao.check(e.getNotice_title())) {
			System.out.println("��Ϣ�Ѵ���");
			return false;
		} else {
			noticeDao.insert(e.getNotice_title(), e.getNotice_time(), e.getNotice_url());
			System.out.println("��Ϣ�Ѳ���");
			return true;
		}

	}

	// ����sohu��ʼ����
	public static void addsohustart() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Football_Sohu> sohus = jsoup.getsohu();
		Football_SohuDao sohuDao = new Football_SohuDao();

		for (Football_Sohu e : sohus) {
			sohuDao.insert(e.getSohu_title(), e.getSohu_url());
		}
	}

	// ����sohu�µ�����
	public static boolean addsohunew() throws Exception {
		JsoupDemo jsoup = new JsoupDemo();
		List<Football_Sohu> sohus = jsoup.getsohunew();
		Football_SohuDao sohuDao = new Football_SohuDao();

		Football_Sohu e = sohus.get(0);
		if (sohuDao.check(e.getSohu_title())) {
			System.out.println("��Ϣ�Ѵ���");
			return false;
		} else {
			sohuDao.insert(e.getSohu_title(), e.getSohu_url());
			System.out.println("��Ϣ�Ѳ���");
			return true;
		}

	}
}
