package com.mds.routing.CLI;



import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.mds.routing.validation.imoValidation;

public class CLI {

	private String[] args = null;
	private Options options = new Options();
	private static final Logger LOGGER = Logger.getLogger(CLI.class.getName());
	public CLI(String args[]) {
		LogManager logMan = LogManager.getLogManager();
	
		try {
			logMan.readConfiguration(Thread.currentThread().getClass()
					.getResourceAsStream("/logging.properties"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.args = args;
		options.addOption("i", "imo", true, "IMO number");
		options.addOption("dbh", "host address", true, "Host Address");
		options.addOption("dbu", "db user", true, "database username");
		options.addOption("dbp", "db password", true, "database password");
		options.addOption("tname", "table name", true, "table name");
		options.addOption("cname", "column name", true, "column name");
		options.addOption("help", "complete Description", true, "help menu");
	}

	public void parse() {
		LOGGER.log(Level.INFO, "inside parse()");
		CommandLineParser parser = new BasicParser();
		CommandLine commandLine = null;
		boolean isvalid, isInDatabase = false;
		imoValidation imoValidation = new imoValidation();
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE,"inside parse() parse EXCEPTION ",e);
		}
		cliParameter parameter = new cliParameter();

		if (commandLine.hasOption("i")) {
			parameter.setI(commandLine.getOptionValue("i"));
		} else {
			System.out.println("i parameter missing");
		}
		if (commandLine.hasOption("dbh")) {
			parameter.setDbh(commandLine.getOptionValue("dbh"));
		} else {
			System.out.println("dbh parameter missing");
		}
		if (commandLine.hasOption("dbu")) {
			parameter.setDbu(commandLine.getOptionValue("dbu"));
		} else {
			System.out.println("dbu parameter missing");
		}
		if (commandLine.hasOption("dbp")) {
			parameter.setDbp(commandLine.getOptionValue("dbp"));
		} else {
			System.out.println("dbp parameter missing");
			parameter.setDbp("");
		}
		if (commandLine.hasOption("tname")) {
			parameter.setTname(commandLine.getOptionValue("tname"));
		} else {
			System.out.println("tname parameter missing");
		}
		if (commandLine.hasOption("cname")) {
			parameter.setCname(commandLine.getOptionValue("cname"));
		} else {
			System.out.println("cname parameter missing");
		}
		if (commandLine.hasOption("help")) {
			System.out.println("help menu...");

		}

		isvalid = imoValidation.isImoValid(parameter);
//		System.out.println("valid: " + isvalid);
		try {
			isInDatabase = imoValidation.isInDatabase(parameter);
		} catch (NumberFormatException e) {

			LOGGER.log(Level.WARNING,"inside parse() NumberFormatException   please enter a valid 7 digit imo number");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE,"inside parse() SQL EXCEPTION ",e);
		}
//		System.out.println("isInDatabase: " + isInDatabase);

		
		if(isInDatabase && isvalid){
			LOGGER.log(Level.INFO, "system exit 0");
			System.exit(0);
		}
		if(!isInDatabase){
			LOGGER.log(Level.INFO, "system exit 1");
			System.exit(1);
		}
		if(isInDatabase && !isvalid){
			LOGGER.log(Level.INFO, "system exit 2");
			System.exit(2);
		}
		
		
		
		
	}
}
