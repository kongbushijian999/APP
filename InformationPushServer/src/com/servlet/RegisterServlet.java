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
				System.out.println("用户名已被注册，请重新输入用户名");
			}else {
				System.out.println("注册成功！");
				userDao.insert(username,hobby,email,password,college);
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
    }  
}
