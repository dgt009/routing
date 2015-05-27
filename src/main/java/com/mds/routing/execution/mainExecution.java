package com.mds.routing.execution;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.mds.routing.CLI.CLI;

public class mainExecution {

	/**
	 * @param args
	 * @throws SQLException
	 */
	private static final Logger LOGGER = Logger.getLogger(CLI.class.getName());
	public static void main(String[] args) throws SecurityException, IOException, SQLException {
		// TODO Auto-generated method stub
		LogManager logMan = LogManager.getLogManager();
		logMan.readConfiguration(Thread.currentThread().getClass()
				.getResourceAsStream("/logging.properties"));
		LOGGER.log(Level.INFO, "inside main()");
	//	mainExecution.main(args)
		CLI commandLine = new CLI(args);
		int result = commandLine.parse();
		System.exit(result);
	}

	
	/*
	 *  arguments
	 *  
	 * -i 7128851 -dbh localhost -dbu postgres -dbp anypassword -tname vessels -cname imo
	 */
}
