package com.challenge.aconex.core;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.challenge.aconex.data.VehicleRecord;
import com.challenge.aconex.parser.InputFileParser;
import com.challenge.aconex.parser.InputFileParserIF;
import com.challenge.aconex.service.VehicleRecordService;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class TestCoreController {

	private CoreController controller;
	private InputFileParserIF fileParser;
	private VehicleRecordServiceIF recordService;
	
	@Before
	public void setup(){
		controller = new CoreController();
		fileParser = new InputFileParser();
		recordService = new VehicleRecordService();
		controller.setInputParser(fileParser);
		controller.setVehicleRecordService(recordService);
	}
	
	@After
	public void teardown(){
		fileParser = null;
		controller = null;
	}
	
	@Test
	public void invokeController_properFile_assertVehicleRecords(){
		String fileLoc = "src/test/resources/com/challenge/aconex/core/VehicleSurveyData.txt";		
		List<VehicleRecord> vehicleRecords = controller.getVehicleRecords(fileLoc);
		assertNotNull(vehicleRecords);
		//We know the sample data file has records of 5 vehicles.
		assertTrue(vehicleRecords.size() == 5);
	}
	
	@Test
	public void invokeController_junkFile_assertEmptyVehicleRecords(){
		String fileLoc = "src/test/resources/com/challenge/aconex/core/JunkedVehicleSurveyData.txt";
		List<VehicleRecord> vehicleRecords = controller.getVehicleRecords(fileLoc);
		
		assertNull(vehicleRecords);
	}
}
