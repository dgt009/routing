package com.mds.routing.execution;

import java.sql.SQLException;

import com.mds.routing.CLI.CLI;

public class mainExecution {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new CLI(args).parse();
	}

}
