package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.Util;

public class LectureDao {

	private Util util;
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public LectureDao() throws Exception {
		util = new Util();
		conn = util.connectionDB();
		st = conn.createStatement();
	}
	/*
	 * //����������Ϣ�������ݿ� public void insert(String lecture_title, String lecture_time,
	 * String lecture_location, String lecturer, String lecture_content, String
	 * lecturer_introduction, String lecture_video) throws Exception{ // ����һ��SQL���
	 * String sql =
	 * "insert into lecture(lecture_title,lecture_time,lecture_location,lecturer,lecture_content,lecturer_introduction,lecture_video) values(?,?,?,?,?,?,?)"
	 * ; PreparedStatement ps = conn.prepareStatement(sql);// ����һ��Statement����
	 * ps.setString(1, lecture_title); ps.setString(2, lecture_time);
	 * ps.setString(3, lecture_location); ps.setString(4, lecturer); ps.setString(5,
	 * lecture_content); ps.setString(6, lecturer_introduction); ps.setString(7,
	 * lecture_video);
	 * 
	 * System.out.println(sql); ps.executeUpdate();// ִ��sql��� }
	 */

	// ����������Ϣ�������ݿ�
	public void insert(String lecture_title, String lecture_time, String lecture_url) throws Exception {
		// ����һ��SQL���
		String sql = "insert into lecture(lecture_title,lecture_time,lecture_url) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);// ����һ��Statement����
		ps.setString(1, lecture_title);
		ps.setString(2, lecture_time);
		ps.setString(3, lecture_url);

		System.out.println(sql);
		ps.executeUpdate();// ִ��sql���
	}

	// ��ѯ�Ƿ��Ѿ��������ݿ���
	public Boolean check(String linkText) throws SQLException {
		String sql = "select * from lecture where lecture_title = '" + linkText + "'";
		rs = st.executeQuery(sql);
		if (rs.next()) {
			return true;
		}
		return false;

	}

	// ���ݿ�����Ϣ����
	public int rowCount() throws SQLException {
		ResultSet rset = st.executeQuery("select * from lecture");
		int rowCount = 0;
		while (rset.next()) {
			rowCount++;
		}
		return rowCount;
	}
	
	
	// �������ݿ������Ϣ
	public List<String> push() throws SQLException {
		int count = rowCount();
		String sql = "select * from lecture where lecture_id = '" + count + "'";
		rs = st.executeQuery(sql);
		
		List<String> push = new ArrayList<>();
		while(rs.next()){
			String lecture_title = rs.getString("lecture_title");
			String lecture_time = rs.getString("lecture_time");
			String lecture_url = rs.getString("lecture_url");
			push.add(lecture_title);
			push.add(lecture_time);
			push.add(lecture_url);
			System.out.println("��Ϣ"+lecture_title+"   "+lecture_time+"  "+lecture_url);
		}
		for (int i = 0; i < push.size(); i++) {
			System.out.println(push.get(i));
		}
		return push;
		
		
	}

}
