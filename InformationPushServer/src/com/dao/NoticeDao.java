package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.*;

public class NoticeDao {
	
	private Util util;  
    private Connection conn;  
    private Statement st;  
    private ResultSet rs;
    
    public NoticeDao() throws Exception{  
        util = new Util();  
        conn = util.connectionDB(); 
        st = conn.createStatement();  
    }  
    
    
    //������Ϣ�Ƿ��Ѿ��������ݿ�����
    public boolean check(String notice_title) throws SQLException {
    	
    	String sql = "select * from notice where notice_title = '"+notice_title+"'";
    	rs = st.executeQuery(sql);
    	if (rs.next()) {
			return true;
		}
		return false;
    }
    
    public void insert(String notice_title, String notice_time, String notice_url) throws SQLException {
    	String sql = "insert into notice(notice_title,notice_time,notice_url) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// ����һ��Statement����
		ps.setString(1, notice_title);
		ps.setString(2, notice_time);
		ps.setString(3, notice_url);
		
		
		System.out.println(sql);
		ps.executeUpdate();// ִ��sql���
    }
    
 // ���ݿ�����Ϣ����
 	public int rowCount() throws SQLException {
 		ResultSet rset = st.executeQuery("select * from notice");
 		int rowCount = 0;
 		while (rset.next()) {
 			rowCount++;
 		}
 		return rowCount;
 	}
    
    
   // �������ݿ������Ϣ
 	public List<String> push() throws SQLException {
 		int count = rowCount();
 		String sql = "select * from notice where notice_id = '" + count + "'";
 		rs = st.executeQuery(sql);
 		
 		List<String> push = new ArrayList<>();
 		while(rs.next()){
 			String notice_title = rs.getString("notice_title");
 			String notice_time = rs.getString("notice_time");
 			String notice_url = rs.getString("notice_url");
 			push.add(notice_title);
 			push.add(notice_time);
 			push.add(notice_url);
 			System.out.println("��Ϣ"+notice_title+"   "+notice_time+"  "+notice_url);
 		}
 		for (int i = 0; i < push.size(); i++) {
 			System.out.println(push.get(i));
 		}
 		return push;
 		
 		
 	}

}
