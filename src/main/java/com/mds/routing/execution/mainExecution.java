package com.mds.routing.execution;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mds.routing.CLI.CLI;

public class mainExecution {

	/**
	 * @param args
	 * @throws SQLException
	 */
	private static final Logger LOGGER = Logger.getLogger(CLI.class.getName());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LOGGER.log(Level.INFO, "inside main()");
		new CLI(args).parse();
	}

}
