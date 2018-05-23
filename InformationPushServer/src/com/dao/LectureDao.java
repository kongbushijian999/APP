package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.util.Util;

public class LectureDao {

	private Util util;  
    private Connection conn;  
    private Statement st;  
    
    public LectureDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    //����������Ϣ�������ݿ�
    public void insert(String lecture_title, String lecture_time, String lecture_location, String lecturer, String lecture_content, String lecturer_introduction, String lecture_video) throws Exception{
		// ����һ��SQL���
		String sql = "insert into lecture(lecture_title,lecture_time,lecture_location,lecturer,lecture_content,lecturer_introduction,lecture_video) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// ����һ��Statement����
		ps.setString(1, lecture_title);
		ps.setString(2, lecture_time);
		ps.setString(3, lecture_location);
		ps.setString(4, lecturer);
		ps.setString(5, lecture_content);
		ps.setString(6, lecturer_introduction);
		ps.setString(7, lecture_video);
		
		System.out.println(sql);
		ps.executeUpdate();// ִ��sql���
		
    	
    }
    

}
