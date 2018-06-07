package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.Util;

public class UserDao {

	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public UserDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    public boolean select(String username, String password) throws Exception {

    	String sql = "select * from user where username = '"+username+"' and userpassword = '"+password+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public List<String> userinformation(String username) throws SQLException {
    	String sql = "select * from user where username = '"+username+"'";
    	rs = st.executeQuery(sql);
    	List<String> userinformation = new ArrayList<>();
    	if (rs.next()) {
			String musername = rs.getString(2);
			String mpassword = rs.getString(3);
			String mcollege = rs.getString(4);
			String mhobby = rs.getString(5);
			String memail = rs.getString(6);
			//System.out.println(musername+mpassword+mcollege);
			userinformation.add(musername);
			userinformation.add(mpassword);
			userinformation.add(mcollege);
			userinformation.add(mhobby);
			userinformation.add(memail);
			return userinformation;
		}
    	return null;
    }
    
    public boolean selectregister(String username) throws SQLException {
    	
    	String sql = "select * from user where username = '"+username+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String username, String hobby, String email, String password, String college) throws SQLException {
    	String sql = "insert into user(username,userhobby,useremail,userpassword,usercollege) values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// 创建一个Statement对象
		ps.setString(1, username);
		ps.setString(2, hobby);
		ps.setString(3, email);
		ps.setString(4, password);
		ps.setString(5, college);
		
		
		System.out.println(sql);
		ps.executeUpdate();// 执行sql语句
    }
}
