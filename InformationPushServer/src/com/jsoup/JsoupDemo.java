package com.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bean.Football_Hupu;
import com.bean.Football_Sohu;
import com.bean.Lecture;
import com.bean.Notice;
import com.dao.Football_HupuDao;

public class JsoupDemo {

	// 获取lecture第二级页面的所有链接地址并返回
	public List<String> link(String url) throws Exception {
		// 由于获取的连接只是一部分,所以要拼接
		String link = "http://meeting.xjtu.edu.cn";
		List<String> href = new ArrayList<>();

		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		// 查找后半部
		Elements ele = doc.select("ul.d-list >li>a");
		for (Element element : ele) {
			String a = element.attr("href");
			// 拼接完整的连接
			String full = link + a;
			href.add(full);
		}
		return href;
	}

	// 根据lecture第二级页面的链接，将爬取到的信息返回
	public List<Lecture> get(List<String> href) throws IOException {

		List<Lecture> ls = new ArrayList<>();
		for (String h : href) {
			Connection conn = Jsoup.connect(h).timeout(30000);
			Document doc = conn.get();

			Elements trs = doc.select("table").select("tr");
			Lecture lec = new Lecture();
			Elements tds0 = trs.get(0).select("td");
			lec.setLecture_title(tds0.get(0).text());

			Elements tds1 = trs.get(1).select("td");
			lec.setLecture_time(tds1.get(0).text());

			lec.setLecture_url(h);

			ls.add(lec);

		}
		return ls;
	}

	// 添加lecture最新的信息
	public static String newlecture() throws IOException {
		String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";
		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();

		Element masthead = doc.select("ul.d-list").first();
		// System.out.println(masthead);

		Element link = masthead.select("a").first();// 查找第一个a元素
		// System.out.println(link);

		String linkText = link.text(); // 取得链接地址中的文本
		// System.out.println(linkText);
		return linkText;
	}

	// 获取notice第二级页面的所有链接地址并返回
	public List<Notice> getnotice() throws Exception {
		// 由于获取的连接只是一部分,所以要拼接
		String url = "http://dean.xjtu.edu.cn/jxxx/zhtz.htm";
		String mainlink = "http://dean.xjtu.edu.cn";
		List<Notice> notices = new ArrayList<>();

		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		// 查找后半部
		Elements half = doc.select("div.list-li");
		// System.out.println(half);

		for (Element element : half) {
			Element link = element.select("a").first();
			String a = link.attr("href");
			String achange = a.substring(2);
			String fullurl = mainlink + achange;
			System.out.println(fullurl);

			String Text = link.text();
			System.out.println(Text);
			// System.out.println(links);

			Element time = element.select("span").first();
			String timeText = time.text();
			System.out.println(timeText);
			Notice notice = new Notice();
			notice.setNotice_title(Text);
			notice.setNotice_time(timeText);
			notice.setNotice_url(fullurl);
			notices.add(notice);

		}

		/*
		 * Elements links = half.select("a"); for (Element element : links) { // 拼接完整的连接
		 * String a = element.attr("href"); String achange = a.substring(2); String
		 * fullurl = link + achange; String Text = element.text();
		 * 
		 * notice.setNotice_title(Text); notice.setNotice_url(fullurl);
		 * System.out.println(Text); System.out.println(fullurl); }
		 * 
		 * Elements times = half.select("span.list_time"); for(Element element:times) {
		 * String timeText = element.text(); notice.setNotice_time(timeText);
		 * notices.add(notice); System.out.println(timeText); }
		 */
		return notices;

	}

	// 获取notice新的信息
	public List<Notice> getnoticenew() throws IOException {
		// 由于获取的连接只是一部分,所以要拼接
		String url = "http://dean.xjtu.edu.cn/jxxx/zhtz.htm";
		String mainlink = "http://dean.xjtu.edu.cn";
		List<Notice> notices = new ArrayList<>();

		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		// 查找后半部
		Elements half = doc.select("div.list-li");
		// System.out.println(half);

		//for (Element element : half) {
			Element element = half.get(2);
			Element link = element.select("a").first();
			String a = link.attr("href");
			String achange = a.substring(2);
			String fullurl = mainlink + achange;
			System.out.println(fullurl);

			String Text = link.text();
			System.out.println(Text);
			// System.out.println(links);

			Element time = element.select("span").first();
			String timeText = time.text();
			System.out.println(timeText);
			Notice notice = new Notice();
			notice.setNotice_title(Text);
			notice.setNotice_time(timeText);
			notice.setNotice_url(fullurl);
			notices.add(notice);

		//}

		return notices;

	}

	// 获得初始sohu信息
	public static List<Football_Sohu> getsohu() throws IOException {
		String url = "http://sports.sohu.com/guoneizuqiu.shtml";
		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		List<Football_Sohu> football_Sohus = new ArrayList<>();

		Element masthead = doc.getElementById("cut01");
		// System.out.println(masthead);

		Elements links = masthead.select("a");// 查找所有a元素
		for (int i = 1; i <= 12; i++) {
			Element link = links.get(i);
			String linkHref = link.attr("href");
			String linkText = link.text();

			Football_Sohu sohu = new Football_Sohu();
			sohu.setSohu_title(linkText);
			sohu.setSohu_url(linkHref);
			System.out.println(linkHref);
			System.out.println(linkText);
			football_Sohus.add(sohu);
		}
		return football_Sohus;

	}

	// 获得新的sohu信息
	public static List<Football_Sohu> getsohunew() throws IOException {
		String url = "http://sports.sohu.com/guoneizuqiu.shtml";
		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		List<Football_Sohu> football_Sohus = new ArrayList<>();

		Element masthead = doc.getElementById("cut01");
		// System.out.println(masthead);

		Elements links = masthead.select("a");// 查找所有a元素
		Element link = links.get(1);
		String linkHref = link.attr("href");
		String linkText = link.text();

		Football_Sohu sohu = new Football_Sohu();
		sohu.setSohu_title(linkText);
		sohu.setSohu_url(linkHref);
		System.out.println(linkHref);
		System.out.println(linkText);
		football_Sohus.add(sohu);

		return football_Sohus;
	}

	// 获得初始hupu信息
	public static List<Football_Hupu> gethupu() throws Exception {
		String url = "https://soccer.hupu.com";
		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		List<Football_Hupu> football_Hupus = new ArrayList<>();

		Elements links = doc.select("a.hotclink");
		for (int i = 1; i <= 15; i++) {
			Element link = links.get(i);
			String linkHref = link.attr("href");
			String linkText = link.text();

			Football_Hupu hupu = new Football_Hupu();
			hupu.setHupu_title(linkText);
			hupu.setHupu_url(linkHref);
			System.out.println(linkHref);
			System.out.println(linkText);
			football_Hupus.add(hupu);

		}
		return football_Hupus;
	}

	// 获得新的hupu信息
	public static List<Football_Hupu> gethupunew() throws Exception {
		String url = "https://soccer.hupu.com";
		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		List<Football_Hupu> football_Hupus = new ArrayList<>();

		Elements links = doc.select("a.hotclink");

		Element link = links.get(0);
		String linkHref = link.attr("href");
		String linkText = link.text();

		Football_Hupu hupu = new Football_Hupu();
		hupu.setHupu_title(linkText);
		hupu.setHupu_url(linkHref);
		System.out.println(linkHref);
		System.out.println(linkText);
		football_Hupus.add(hupu);

		return football_Hupus;
	}

}
