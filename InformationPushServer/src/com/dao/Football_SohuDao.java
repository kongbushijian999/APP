package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.Util;

public class Football_SohuDao {

	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public Football_SohuDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    
    public boolean check(String sohu_title) throws SQLException {
    	
    	String sql = "select * from football_sohu where sohu_title = '"+sohu_title+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String sohu_title, String sohu_url) throws SQLException {
    	String sql = "insert into football_sohu(sohu_title,sohu_url) values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// 创建一个Statement对象
		ps.setString(1, sohu_title);
		ps.setString(2, sohu_url);
		
		System.out.println(sql);
		ps.executeUpdate();// 执行sql语句
    }
    
 //数据库里信息条数
 	public int rowCount() throws SQLException {
 		ResultSet rset = st.executeQuery("select * from football_sohu");
 		int rowCount = 0;
 		while (rset.next()) {
 			rowCount++;
 		}
 		return rowCount;
 	}
 	
 	
 	// 推送数据库里的消息
 	public List<String> push() throws SQLException {
 		int count = rowCount();
 		String sql = "select * from football_sohu where sohu_id = '" + count + "'";
 		rs = st.executeQuery(sql);
 		
 		List<String> push = new ArrayList<>();
 		while(rs.next()){
 			String sohu_title = rs.getString("sohu_title");
 			String sohu_url = rs.getString("sohu_url");
 			push.add(sohu_title);
 			push.add(sohu_url);
 			System.out.println("信息"+sohu_title+"   "+sohu_url);
 		}
 		for (int i = 0; i < push.size(); i++) {
 			System.out.println(push.get(i));
 		}
 		return push;
 		
 		
 	}
}
