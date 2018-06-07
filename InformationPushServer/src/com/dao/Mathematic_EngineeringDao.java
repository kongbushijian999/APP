package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.Util;

public class Mathematic_EngineeringDao {

	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public Mathematic_EngineeringDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    
    public boolean check(String mathematic_notice_title) throws SQLException {
    	
    	String sql = "select * from mathematic_engineering where mathematic_notice_title = '"+mathematic_notice_title+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String mathematic_notice_title, String mathematic_notice_time, String mathematic_notice_url) throws SQLException {
    	String sql = "insert into mathematic_engineering(mathematic_notice_title,mathematic_notice_time,mathematic_notice_url) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// 创建一个Statement对象
		ps.setString(1, mathematic_notice_title);
		ps.setString(2, mathematic_notice_time);
		ps.setString(3, mathematic_notice_url);
		
		
		System.out.println(sql);
		ps.executeUpdate();// 执行sql语句
    }
}
