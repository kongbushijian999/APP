package com.main;

import java.util.List;

import com.bean.Lecture;
import com.dao.LectureDao;

public class Main {
	
	public static void main(String[] args) throws Exception {
		int k = 0;                       //用来计数  
        LectureDao md = new LectureDao();  //用来对获取到的数据插入数据库  
        
        String url = "http://meeting.xjtu.edu.cn/lecturenotices/list/1";//爬取的地址  
        //获取JsoupDemo类的对象
        JsoupDemo jsoup = new JsoupDemo();  
        List<String> href = jsoup.link(url); 
        
        List<Lecture> m = jsoup.get(href);  
        
        for(Lecture e:m){  
            k++;  
            //插入数据库  
            md.insert(e.getLecture_title(), e.getLecture_time(), e.getLecture_location(), e.getLecturer(), e.getLecture_content(), e.getLecturer_introduction(), e.getLecture_video());
            System.out.println(k);  
        }  
       
	}

}
