package com.challenge.aconex.analysis;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.challenge.aconex.mock.MockVehicleRecordService;
import com.challenge.aconex.service.VehicleRecordServiceIF;
public class TestPerHourAnalysis extends AbstractTestPrintAnalysis{

//	private static final Logger LOGGER = Logger.getLogger(TestPerHourAnalysis.class.getName());
	@Test
	public void invokeAnalysis_inputInitedStore_assertPrintStrings(){
		if(store != null){
			VehicleRecordServiceIF mockRecordService = new MockVehicleRecordService();			
			try {
				store.initializeSurveyStoreService(mockRecordService.prepareVehicleRecords(null));
			} catch (Exception e) {
				fail("Problem running test on per hour analysis: Couldn't init store. ");
			}
			
			VehicleSurveyAnalysisIF perHourAnalysis = new PerHourAnalysis();
			perHourAnalysis.setSurveyAnalysisService(store);
			
			try {
				perHourAnalysis.printDataOfAnalysis();
			} catch (Exception e) {
				fail("Per hour Analyis failed : " + e.getMessage());
			}
			
			assertTrue(!getConsoleString().isEmpty());
		}else{
			fail("Problem running test on per hour analysis: Where's the store?");
		}
	}
	
}
