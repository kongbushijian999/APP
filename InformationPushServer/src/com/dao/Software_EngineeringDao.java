package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.Util;

public class Software_EngineeringDao {

	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public Software_EngineeringDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    
    public boolean check(String software_notice_title) throws SQLException {
    	
    	String sql = "select * from software_engineering where software_notice_title = '"+software_notice_title+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String software_notice_title, String software_notice_detail, String software_notice_time, String software_notice_url) throws SQLException {
    	String sql = "insert into software_engineering(software_notice_title,software_notice_detail,software_notice_time,software_notice_url) values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// 创建一个Statement对象
		ps.setString(1, software_notice_title);
		ps.setString(2, software_notice_detail);
		ps.setString(3, software_notice_time);
		ps.setString(4, software_notice_url);
		
		
		System.out.println(sql);
		ps.executeUpdate();// 执行sql语句
    }
}
