/** 
 * Copyright (C) Maritime Data Systems, GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by dhruvil, May 26, 2015
 */

package com.mds.routing.validation;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mds.routing.CLI.cliParameter;
import com.mds.routing.database.databaseConnection;

public class ImoValidationTest {
	cliParameter parameter;
	databaseConnection dbConnection;
	Connection connection;
	imoValidation validation;
	String createDB, dropDB, createTable, insertTable;
	PreparedStatement pstatement, pstatement1, pstatement2, pstatement3;
	Statement statement;

	@Before
	public void setUp() throws InvalidPropertiesFormatException, IOException, SQLException {
		parameter = new cliParameter();
		dbConnection = new databaseConnection();
		parameter.setDbh("localhost");
		parameter.setDbu("postgres");
		parameter.setDbp("");
		parameter.setDbname("postgres");
		parameter.setTname("test_vessels");
		parameter.setCname("imo");

		//connection using postgres database
		connection = dbConnection.getConnection(parameter);
		Properties properties = new Properties();
		FileInputStream fos = new FileInputStream(
				"src/test/resources/Properties.xml");
		properties.loadFromXML(fos);
		parameter.setDbname("test");
		dropDB = properties.getProperty("dropDB"); // ?=database name
		dropDB = dropDB.replace("?1", parameter.getDbname());
		createDB = properties.getProperty("createDB"); // ?=database name
		createDB = createDB.replace("?1", parameter.getDbname());
		createTable = properties.getProperty("createTable"); 
		//insertTable = properties.getProperty("insertTable");

//		statement = connection.createStatement();
//		ResultSet doesExist = statement
//				.executeQuery("SELECT datname FROM pg_catalog.pg_database WHERE datname = 'test';");// if database already exists
//		connection.close();
//		
//		if(doesExist.next()){
//			connection = dbConnection.getConnection(parameter);
//			statement = connection.createStatement();
//			statement.execute(dropDB);
//			statement = connection.createStatement();
//			statement.execute(createDB);
//			connection.close();
//		}
//		else{
//			connection = dbConnection.getConnection(parameter);
//			statement = connection.createStatement();
//			statement.execute(createDB);
//			connection.close();
//		}
//		parameter.setDbname("test");
//		connection = dbConnection.getConnection(parameter);
//		statement = connection.createStatement();
//		statement.execute(createTable);
//		statement = connection.createStatement();
//		statement.execute(insertTable);
//		connection.close();
		
		 pstatement =connection.prepareStatement(createDB);
		 boolean dbexist;
		 dbexist=pstatement.execute();
		 connection.close();
		 if(dbexist){
		 assertTrue(!dbexist);
		 }
		
		
		 //connection using test database
		 parameter.setDbname("test");
		 connection = dbConnection.getConnection(parameter);
		 pstatement= connection.prepareStatement(createTable);//creates table and inserts values, Properties file
		 pstatement.execute();
		 connection.close();


	}

	@After
	public void tearDown() throws Exception {
		parameter.setDbname("postgres");
		connection = dbConnection.getConnection(parameter);
		statement = connection.createStatement();
		statement.execute(dropDB);
		connection.close();
	}

	@Test
	public void isImovalidandIsInDatabase() throws NumberFormatException,
			SQLException {

		String true_imo = "7128851"; // imo from database,used in properties file
		String false_imo = "9274122"; // random imo

		connection = dbConnection.getConnection(parameter);
		parameter.setI(true_imo);
		validation = new imoValidation(connection);
		assertTrue(validation.isImoValid(parameter));
		parameter.setI(false_imo);
		assertTrue(!validation.isImoValid(parameter));

		parameter.setI(true_imo);
		parameter.setCname("imo");
		parameter.setTname("test_vessels");

		assertTrue(validation.isInDatabase(parameter));
		parameter.setI(false_imo);
		assertTrue(!validation.isInDatabase(parameter));
//		System.out.println("donee");
		connection.close();

	}

}
