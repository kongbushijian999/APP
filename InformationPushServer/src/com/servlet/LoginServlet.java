package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.util.Util;

public class LoginServlet extends HttpServlet{
	
	public static String hobby = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // TODO Auto-generated method stub  
      
		
		
		/*String code = "";  
        String message = "";  */
  
        String username = request.getParameter("username");  
        String password = request.getParameter("password"); 
        
        String musername = null;
        String musercollege = null;
        String muserhobby = null;
        String museremail = null;
        
        UserDao userDao;
		try {
			userDao = new UserDao();
			if (userDao.select(username, password)) {
				System.out.println("success");
				List<String> showuser= userDao.userinformation(username);
				System.out.println(showuser);
				if (showuser != null) {
					musername = showuser.get(1);
					musercollege = showuser.get(2);
					muserhobby = showuser.get(3);
					museremail = showuser.get(4);
					hobby = showuser.get(3);
				}
				
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ  
		response.getWriter().append(musername).append("  ").append(musercollege).append("  ").append(muserhobby).append("  ").append(museremail);
       /* Connection connect;
		try {
			connect = Util.connectionDB();
			try {  
	            Statement statement = connect.createStatement();  
	            String sql = "select account from user where account='" + username  
	                    + "' and password='" + password + "'";  
	            //LogUtil.log(sql);  
	            ResultSet result = statement.executeQuery(sql);  
	            if (result.next()) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����  
	                code = "200";  
	                message = "��½�ɹ�";  
	            } else {  
	  
	                code = "100";  
	                message = "��¼ʧ�ܣ����벻ƥ����˺�δע��";  
	            }  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}  */
        
        
        
        /*if ("hahaha".equals(username) && "123456".equals(password)) { // �ܲ鵽���˺ţ�˵���Ѿ�ע�����  
		    code = "200";  
		    message = "success";  
		} else {  
  
		    code = "100";  
		    message = "false";  
		}  
        
        response.getWriter().append("code:").append(code).append(";message:").append(message);  */
    }  
	
	
}
