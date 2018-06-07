package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.Util;

public class Football_HupuDao {

	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public Football_HupuDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    
    public boolean check(String hupu_title) throws SQLException {
    	
    	String sql = "select * from football_hupu where hupu_title = '"+hupu_title+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String hupu_title, String hupu_data, String hupu_area, String hupu_url) throws SQLException {
    	String sql = "insert into football_hupu(hupu_title,hupu_data,hupu_area,hupu_url) values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// 创建一个Statement对象
		ps.setString(1, hupu_title);
		ps.setString(2, hupu_data);
		ps.setString(3, hupu_area);
		ps.setString(4, hupu_url);
		
		
		System.out.println(sql);
		ps.executeUpdate();// 执行sql语句
    }
}
