package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // TODO Auto-generated method stub  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        if("hahaha".equals(username) && "123456".equals(password)){  
            out.print(true);  
        }  
        else{  
            out.print(false);  
        }  
    }  
}
