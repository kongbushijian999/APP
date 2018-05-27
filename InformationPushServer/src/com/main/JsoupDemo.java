package com.main;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bean.Lecture;  

public class JsoupDemo {
	
	
	//��ȡ�ڶ���ҳ����������ӵ�ַ������  
    public List<String> link(String url) throws Exception{  
        //���ڻ�ȡ������ֻ��һ����,����Ҫƴ��  
        String link = "http://meeting.xjtu.edu.cn";  
        List<String> href = new ArrayList<>(); 
        
        Connection conn = Jsoup.connect(url).timeout(30000);  
        Document doc = conn.get();  
        //���Һ�벿
        Elements ele = doc.select("ul.d-list >li>a");  
        for(Element element:ele){  
            String a = element.attr("href");  
            //ƴ������������  
            String full = link+a;  
            href.add(full);  
        }  
        return href;  
    }  
      
      
    //���ݵڶ���ҳ������ӣ�����ȡ������Ϣ����  
    public List<Lecture> get(List<String> href) throws IOException { 

        List<Lecture> ls=new ArrayList<>();  
        for(String h:href){  
            Connection conn=Jsoup.connect(h).timeout(30000);  
            Document doc = conn.get(); 
           
            Elements trs = doc.select("table").select("tr");  
            Lecture lec=new Lecture();
            Elements tds0 = trs.get(0).select("td");  
            lec.setLecture_title(tds0.get(0).text());
            
            Elements tds1 = trs.get(1).select("td");
            lec.setLecture_time(tds1.get(0).text());
            
            Elements tds2 = trs.get(2).select("td");
            lec.setLecture_location(tds2.get(0).text());
            
            Elements tds3 = trs.get(3).select("td");
            lec.setLecturer(tds3.get(0).text());
            
            Elements tds4 = trs.get(4).select("td");
            lec.setLecture_content(tds4.get(0).text());
            
            Elements tds5 = trs.get(5).select("td");
            lec.setLecturer_introduction(tds5.get(0).text());
            
            Elements tds6 = trs.get(6).select("td");
            lec.setLecture_video(tds6.get(0).text());
            ls.add(lec);  
           
        }  
        return ls;  
    }  

    //������µ���Ϣ
    public static String newlecture() throws IOException {
    	String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";
    	Connection conn = Jsoup.connect(url).timeout(30000);  
        Document doc = conn.get();
        
        Element masthead = doc.select("ul.d-list").first();
        System.out.println(masthead);
        
        Element link = masthead.select("a").first();//���ҵ�һ��aԪ��
        System.out.println(link);
        
        String linkText = link.text(); // "example""//ȡ�����ӵ�ַ�е��ı�
        System.out.println(linkText);
        return linkText;
    }
}
