package com.challenge.aconex.parser;

import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.challenge.aconex.parser.InputFileParser;

import static org.junit.Assert.*;

public class TestInputFileParser {

	private InputFileParser parser;
	private static final Logger LOGGER = Logger.getLogger(TestInputFileParser.class.getName());
	
	@Before
	public void setup(){
		parser = new InputFileParser();
	}
	
	@Test
	public void invokeparser_properFile_assertValues(){
		String fileLoc = "src/test/resources/com/challenge/aconex/core/VehicleSurveyData.txt";
		List<String> parsedStrings = null;
		try {
			parsedStrings = parser.retrieveStrings(fileLoc);
		} catch (Exception e) {
			fail("Test for Invoking parser failed : " + e.getMessage());
		}
		
		assertNotNull(parsedStrings);
		assertTrue(parsedStrings.size() > 0);
		
		assertTrue(parsedStrings.contains("B638523"));
		LOGGER.info("Parsed Strings for record : " + parsedStrings);
	}
	
	@Test
	public void invokeparser_improperFile_assertReadableValues(){
		String fileLoc = "src/test/resources/com/challenge/aconex/core/JunkedVehicleSurveyData.txt";
		List<String> parsedStrings = null;
		try {
			parsedStrings = parser.retrieveStrings(fileLoc);
			fail("Parsing test case passed even though input data is junk.");
		} catch (Exception e) {
			
			assertTrue(e.getMessage().length() > 0);
			assertTrue(parsedStrings == null);
		}		
	}
	
	@After
	public void teardown(){
		parser = null;
	}
}
