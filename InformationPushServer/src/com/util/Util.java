package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
	
	private static final String url = "jdbc:mysql://localhost:3306/appserver?useUnicode=true&characterEncoding=UTF-8";  
	private static final String user = "root";
	private static final String password = "root";
	
	public static Connection connectionDB() throws Exception {  
        //1、加载数据库驱动  
        Class.forName("com.mysql.jdbc.Driver");  
        //2、获取数据库连接  
        Connection conn = DriverManager.getConnection(url, user, password);
          
        return conn;  
    }  

}
