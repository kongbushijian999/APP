package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.Util;

public class Football_MenuDao {
	
	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public Football_MenuDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    
    public boolean check(String football_net_url) throws SQLException {
    	
    	String sql = "select * from football_menu where football_net_url = '"+football_net_url+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String football_net_name, String football_net_url) throws SQLException {
    	String sql = "insert into football_menu(football_net_name,football_net_url) values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// 创建一个Statement对象
		ps.setString(1, football_net_name);
		ps.setString(2, football_net_url);
		
		
		System.out.println(sql);
		ps.executeUpdate();// 执行sql语句
    }

    public void delete(String football_net_url) throws SQLException {
    	String sql = "delete from football_menu where football_net_url = '"+football_net_url+"'"; 
    	rs = st.executeQuery(sql);
    }
}
