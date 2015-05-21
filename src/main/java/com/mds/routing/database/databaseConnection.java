package com.mds.routing.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mds.routing.CLI.cliParameter;

public class databaseConnection {

	// Connection connection = DriverManager.getConnection(url, username,
	// password);
	public Connection getConnection(cliParameter parameter) {
		Connection conn = null;
//		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:postgresql://"+parameter.getDbh()+"/routing";
		
		Properties props = new Properties();
		props.setProperty("user",parameter.getDbu());
		
		
		if(parameter.getDbp().equals("")){
			props.setProperty("password","");	
		}
		else
		{
			props.setProperty("password",parameter.getDbp());
		}
		
		
		// props.setProperty("ssl","true");
		try {
			conn = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			System.out
					.println("Error in establishing connection with database");
		}

		System.out.println("connection Established with database");
		return conn;
	}
}
