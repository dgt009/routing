/** 
 * Copyright (C) Maritime Data Systems, GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by dhruvil, May 27, 2015
 */

package com.mds.routing.execution;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.mds.routing.CLI.CLI;
import com.mds.routing.execution.mainExecution;

public class CliTest {

	String[] args={"-i","7128851","-dbh","localhost","-dbu","postgres","-dbp","anypassword","-tname","vessels","-cname","imo"};
CLI cli = new CLI(args);
//	mainExecution execution = new mainExecution();
	
	

	@Test
	public void test() throws SecurityException, IOException {
		try {
			cli.parse();
			assertTrue(true);
//			execution.main(args);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
//			e.printStackTrace();
		}
		
	}

}
