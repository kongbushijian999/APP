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
    				System.out.println("����վ�Ѿ�����");
    			} else {
    				football_MenuDao.insert(title, url);
    				System.out.println("��ӳɹ�");
    			}
    		} catch (Exception e) {
    			// TODO �Զ����ɵ� catch ��
    			e.printStackTrace();
    		}
            out.print(true);  
        }  
        else if ("del".equals(buttonnum)) {
        	try {
    			Football_MenuDao football_MenuDao = new Football_MenuDao();
    			if (football_MenuDao.check(url)) {
    				football_MenuDao.delete(url);
    				System.out.println("ɾ���ɹ�");
    			} else {
    				System.out.println("����վ������");
    			}
    		} catch (Exception e) {
    			// TODO �Զ����ɵ� catch ��
    			e.printStackTrace();
    		}
            out.print(true);  
		}
        else{  
            out.print(false);  
        }  
	}
}
