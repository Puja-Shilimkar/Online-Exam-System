package com.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	//BL for making connection to DB
	public static Connection getDBConnection()
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("---Driver Class Get Loaded---");
		//JDBC
		//Step1:make Connection
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb","root","root");
		//localhost:current machine
		//3306:port no where mysql database service is running
		
		System.out.println("---Connected With DB---");
		return con;//valid Connection
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		return null;//in case of any exception
	}

}
