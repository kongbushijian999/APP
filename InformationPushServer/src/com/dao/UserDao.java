package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    
    public boolean select(String username, String password) throws SQLException {
    	
    	String sql = "select * from user where username = '"+username+"' and userpassword = '"+password+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert() {
    	
    }
}
