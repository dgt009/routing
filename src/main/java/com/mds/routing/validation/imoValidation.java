package com.mds.routing.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mds.routing.CLI.CLI;
import com.mds.routing.CLI.cliParameter;
import com.mds.routing.database.databaseConnection;

public class imoValidation {
	private static final Logger LOGGER = Logger.getLogger(CLI.class.getName());
	// validity of imo
	
	private Connection sqlConnection;
	public imoValidation(Connection connection){
		this.sqlConnection = connection;
	}
	
	public boolean isImoValid(cliParameter parameter) {
		LOGGER.log(Level.INFO, "inside isImoValid()");
		String imo = parameter.getI();
//		System.out.println(parameter.getI());

		if (imo == null || imo.length() != 7) {
			return false;
		}
		char[] a = imo.toCharArray();
		int sum = 0;
		for (int i = 0; i < 6; i++) {
			sum += (a[i] - 48) * (a.length - i);
		}
		return sum % 10 == a[6] - 48;

	}

	// checks whether imo is in database
	public boolean isInDatabase(cliParameter parameter)
			throws NumberFormatException, SQLException {
		LOGGER.log(Level.INFO, "inside isInDatabase()");

		String query = "SELECT * FROM "+parameter.getTname()+" where "+parameter.getCname()+"=?";
//		System.out.println(query);
		PreparedStatement preparedStatement = null;
		boolean isindatabase = false;
		try {
			preparedStatement = this.sqlConnection.prepareStatement(query);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,"inside isInDatabase() SQL EXCEPTION",e);
			
		}
		//setting imo parameter
		preparedStatement.setInt(1, Integer.parseInt(parameter.getI()));
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			isindatabase = true;

		}
		return isindatabase;
	}

}
