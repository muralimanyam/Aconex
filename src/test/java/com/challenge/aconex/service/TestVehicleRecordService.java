package com.challenge.aconex.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.challenge.aconex.data.VehicleRecord;
import com.challenge.aconex.parser.InputFileParser;
import com.challenge.aconex.parser.InputFileParserIF;

public class TestVehicleRecordService {

	private VehicleRecordServiceIF vrs;
	private InputFileParserIF fileParser;

	@Before
	public void setup() {
		vrs = new VehicleRecordService();
		fileParser = new InputFileParser();
	}

	@Test
	public void prepareVehicleRecords_sampleSurveyData_assert() {
		String fileLoc = "src/test/resources/com/challenge/aconex/core/VehicleSurveyData.txt";
		List<String> surveyDataStrings = getSurveyDataStrings(fileLoc);
		if (surveyDataStrings != null) {
			try {
				List<VehicleRecord> vehRecords = vrs
						.prepareVehicleRecords(surveyDataStrings);
				assertNotNull(vehRecords);
				assertTrue(vehRecords.size() > 0);
			} catch (Exception e) {
				fail("Couldn't run vehicle service test. Failure in preparing vehicle records. "
						+ e.getMessage());
			}
		}
	}

	private List<String> getSurveyDataStrings(String fileLoc) {
		List<String> surveyDataStrings = null;
		try {
			surveyDataStrings = fileParser.retrieveStrings(fileLoc);
		} catch (Exception e) {
			fail("Couldn't run vehicle service test. Failure in getting survey data. "
					+ e.getMessage());
		}
		return surveyDataStrings;
	}

	@Test
	public void prepareVehicleRecords_invalidSurveyData_assertAbort() {
		// What is invalid?: One survey data sequence ABAB in the middle is only
		//  ABA.
		String fileLoc = "src/test/resources/com/challenge/aconex/core/InvalidVehicleSurveyData.txt";
		List<String> surveyDataStrings = getSurveyDataStrings(fileLoc);
		if (surveyDataStrings != null) {
			List<VehicleRecord> vehRecords = null;
			try {
				vehRecords = vrs
						.prepareVehicleRecords(surveyDataStrings);
				fail("The prepare vehicle records method returned properly even though the data is improper.");
			} catch (Exception e) {
				assertNull(vehRecords);
				assertTrue(!e.getMessage().isEmpty());
			}
		}
	}
	
	@Test
	public void prepareVehicleRecords_wrongIdentifier_assertAbort() {
		// What is wrong?: One survey data sequence ABC. 
		
		String fileLoc = "src/test/resources/com/challenge/aconex/core/SurveyDataWithWrongIdentifier.txt";
		List<String> surveyDataStrings = getSurveyDataStrings(fileLoc);
		if (surveyDataStrings != null) {
			List<VehicleRecord> vehRecords = null;
			try {
				vehRecords = vrs
						.prepareVehicleRecords(surveyDataStrings);
				fail("The prepare vehicle records method returned properly even though the data is improper.");
			} catch (Exception e) {
				assertNull(vehRecords);
				assertTrue(!e.getMessage().isEmpty());
			}
		}
	}

	@After
	public void teardown() {
		vrs = null;
	}
}
