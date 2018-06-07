package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;


public class RegisterServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // TODO Auto-generated method stub  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();   
        
        String username = request.getParameter("username");  
        String hobby = request.getParameter("hobby");  
        String email = request.getParameter("email");  
        String password = request.getParameter("password");  
        String passwordtwo = request.getParameter("passwordtwo");  
        String college = request.getParameter("college");  
        
        try {
			UserDao userDao = new UserDao();
			if (userDao.selectregister(username)) {
				System.out.println("�û����ѱ�ע�ᣬ�����������û���");
			}else {
				System.out.println("ע��ɹ���");
				userDao.insert(username,hobby,email,password,college);
			}
			
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        
    }  
}
