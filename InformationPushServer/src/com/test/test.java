package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bean.Notice;
import com.dao.LectureDao;
import com.dao.UserDao;
import com.servlet.LoginServlet;

public class test {
	public static void main(String[] args) throws Exception {
	
		//addnew();
		
		//getsohu();
		//test test1 = new test();
		//test1.getnotice();
		
		/*UserDao userDao = new UserDao();
		List<String> showuser = userDao.userinformation("testone");
		System.out.println(showuser.get(2));
		System.out.println(showuser.get(4));
		Boolean yyy =  userDao.select("testone", "testone1");
		System.out.println(yyy);*/
		
		String test1 = LoginServlet.hobby;
		System.out.println(test1);
	}
	
	//获取notice第二级页面的所有链接地址并返回
		public List<Notice> getnotice() throws Exception {
			// 由于获取的连接只是一部分,所以要拼接
			String url = "http://dean.xjtu.edu.cn/jxxx/zhtz.htm";
			String mainlink = "http://dean.xjtu.edu.cn";
			List<Notice> notices = new ArrayList<>();

			Connection conn = Jsoup.connect(url).timeout(30000);
			Document doc = conn.get();
			// 查找后半部
			Elements half = doc.select("div.list-li");
			//System.out.println(half);
			
			for(Element element:half) {
				Element link = element.select("a").first();
				String a = link.attr("href");
				String achange = a.substring(2);
			    String fullurl = mainlink + achange;
			    System.out.println(fullurl);
			    
				String Text = link.text();
				System.out.println(Text);
				//System.out.println(links);
				
				Element time = element.select("span").first();
				String timeText = time.text();
				System.out.println(timeText);
				Notice notice = new Notice();
				notice.setNotice_title(Text);
				notice.setNotice_time(timeText);
				notice.setNotice_url(a);
				notices.add(notice);
				
				
				
				
			}

			
			
			/*Elements links =  half.select("a");
			for (Element element : links) {
				// 拼接完整的连接
				String a = element.attr("href");
				String achange = a.substring(2);
			    String fullurl = link + achange;
				String Text = element.text();
				
				notice.setNotice_title(Text);
				notice.setNotice_url(fullurl);
				System.out.println(Text);
				System.out.println(fullurl);
			}
			
			Elements times = half.select("span.list_time");
			for(Element element:times) {
				String timeText = element.text();
				notice.setNotice_time(timeText);
				notices.add(notice);
				System.out.println(timeText);
			}*/
			
			return notices;
			
		}
	
	public List<Notice> getnotice1() throws Exception {
		// 由于获取的连接只是一部分,所以要拼接
		String url = "http://dean.xjtu.edu.cn/jxxx/zhtz.htm";
		String link = "http://dean.xjtu.edu.cn";
		List<Notice> notices = new ArrayList<>();

		Connection conn = Jsoup.connect(url).timeout(30000);
		Document doc = conn.get();
		// 查找后半部
		Elements half = doc.select("div.list_main_content");
		Notice notice = new Notice();

		
		//System.out.println(half);
		Elements links =  half.select("a");
		for (Element element : links) {
			// 拼接完整的连接
			String a = element.attr("href");
			String achange = a.substring(2);
		    String fullurl = link + achange;
			String Text = element.text();
			
			notice.setNotice_title(Text);
			notice.setNotice_url(fullurl);
			notices.add(notice);
			System.out.println(Text);
			System.out.println(fullurl);
		}
		
		Elements times = half.select("span.list_time");
		for(Element element:times) {
			String timeText = element.text();
			notice.setNotice_time(timeText);
			notices.add(notice);
			System.out.println(timeText);
		}
		
		return notices;
		
		
	}
	


	
	/*public static void gethupu() throws Exception {
		String url = "https://soccer.hupu.com";
    	Connection conn = Jsoup.connect(url).timeout(30000);  
        Document doc = conn.get();
        
        Elements links = doc.select("a.hotclink");
        for(int i = 1; i <= 15; i++) {
        	Element link = links.get(i);
        	String linkHref = link.attr("href");
        	String linkText = link.text();
        	System.out.println(linkHref);
        	System.out.println(linkText);
        	
        }
	}*/
	
	/*public static void getsohu() throws IOException {
		String url = "http://sports.sohu.com/guoneizuqiu.shtml";
    	Connection conn = Jsoup.connect(url).timeout(30000);  
        Document doc = conn.get();
        
        Element masthead = doc.getElementById("cut01");
        //System.out.println(masthead);
        
        Elements links = masthead.select("a");//查找所有a元素
        for(int i = 1; i <= 12; i++) {
        	Element link = links.get(i);
        	String linkHref = link.attr("href");
        	String linkText = link.text();
        	System.out.println(linkHref);
        	System.out.println(linkText);
        	
        }
      
	}
	
	public void addnewsohu() throws IOException {
		String url = "http://sports.sohu.com/guoneizuqiu.shtml";
    	Connection conn = Jsoup.connect(url).timeout(30000);  
        Document doc = conn.get();
        
        Element masthead = doc.getElementById("cut01");
        //System.out.println(masthead);
        
        Elements links = masthead.select("a");//查找所有a元素
        Element link = links.get(1);
       	String linkHref = link.attr("href");
       	String linkText = link.text();
       	System.out.println(linkHref);
       	System.out.println(linkText);
	}*/
}
