package com.mds.routing.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mds.routing.CLI.cliParameter;
import com.mds.routing.database.databaseConnection;

public class imoValidation {

	// validity of imo
	public boolean isImoValid(cliParameter parameter) {
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
		databaseConnection db = new databaseConnection();
		Connection connection = db.getConnection(parameter);
		String query = "SELECT * FROM "+parameter.getTname()+" where "+parameter.getCname()+"=?";
		PreparedStatement preparedStatement = null;
		boolean isindatabase = false;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(Integer.parseInt(parameter.getI()));
		preparedStatement.setInt(1, Integer.parseInt(parameter.getI()));
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			isindatabase = true;

		}
		return isindatabase;
	}

}
