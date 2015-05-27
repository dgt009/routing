package com.mds.routing.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mds.routing.CLI.CLI;
import com.mds.routing.CLI.cliParameter;

/**
 * 
 * C
 * 
 * @author dhruvil
 * 
 */
public class databaseConnection {
	private static final Logger LOGGER = Logger.getLogger(CLI.class.getName());

	// Connection connection = DriverManager.getConnection(url, username,
	// password);
	public static Connection getConnection(cliParameter parameter) {
		LOGGER.log(Level.INFO, "inside getConnection()");
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		String url = "jdbc:postgresql://" + parameter.getDbh() + ":5432/"
				+ parameter.getDbname();
//		System.out.println(url);

		Properties props = new Properties();
		props.setProperty("user", parameter.getDbu());

		if (parameter.getDbp().equals("")) {
			props.setProperty("password", "");
		} else {
			props.setProperty("password", parameter.getDbp());
		}

		// props.setProperty("ssl","true");
		try {
			conn = DriverManager.getConnection(url, props);

			// test query
			/*
			 * Statement statement = conn.createStatement();
			 * statement.execute("select * from pg_tables"); ResultSet rs =
			 * statement.getResultSet(); while (rs.next()){
			 * System.out.println(rs.getString("tablename")); } //
			 * statement.execute("select * from vessels limit 10");
			 */

			// System.out.println("connection Established with database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "inside getConnection()", e);

			// System.out.println("Error in establishing connection with database");
		}

		return conn;
	}
}
