package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Football_MenuDao;

public class UserHobbyServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // TODO Auto-generated method stub  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String title = request.getParameter("title");  
        String url = request.getParameter("url");  
        String buttonnum = request.getParameter("button");  
        if("add".equals(buttonnum)){  
        	try {
    			Football_MenuDao football_MenuDao = new Football_MenuDao();
    			if (football_MenuDao.check(url)) {
    				System.out.println("该网站已经存在");
    			} else {
    				football_MenuDao.insert(title, url);
    				System.out.println("添加成功");
    			}
    		} catch (Exception e) {
    			// TODO 自动生成的 catch 块
    			e.printStackTrace();
    		}
            out.print(true);  
        }  
        else if ("del".equals(buttonnum)) {
        	try {
    			Football_MenuDao football_MenuDao = new Football_MenuDao();
    			if (football_MenuDao.check(url)) {
    				football_MenuDao.delete(url);
    				System.out.println("删除成功");
    			} else {
    				System.out.println("该网站不存在");
    			}
    		} catch (Exception e) {
    			// TODO 自动生成的 catch 块
    			e.printStackTrace();
    		}
            out.print(true);  
		}
        else{  
            out.print(false);  
        }  
	}
}
