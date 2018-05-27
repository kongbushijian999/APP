package com.main;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
	public static void main(String[] args) throws IOException {
	
		addnew();
	}
	
	public static void addnew() throws IOException {
    	String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";
    	Connection conn = Jsoup.connect(url).timeout(30000);  
        Document doc = conn.get();
        
        Element masthead = doc.select("ul.d-list").first();
        System.out.println(masthead);
        
        Element link = masthead.select("a").first();//���ҵ�һ��aԪ��
        System.out.println(link);
        
        String linkText = link.text(); // "example""//ȡ�����ӵ�ַ�е��ı�
        System.out.println(linkText);
    }
}
