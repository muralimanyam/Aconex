package com.challenge.aconex.analysis;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.challenge.aconex.mock.MockVehicleRecordService;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class TestPerHalfHourAnalysis extends AbstractTestPrintAnalysis{

	@Test
	public void invokeAnalysis_inputInitedStore_assertPrintStrings(){
		if(store != null){
			VehicleRecordServiceIF mockRecordService = new MockVehicleRecordService();			
			try {
				store.initializeSurveyStoreService(mockRecordService.prepareVehicleRecords(null));
			} catch (Exception e) {
				fail("Problem running test on per half hour analysis: Couldn't init store. ");
			}
			
			VehicleSurveyAnalysisIF analysis = new PerHalfHourAnalysis();
			analysis.setSurveyAnalysisService(store);
			
			try {
				analysis.printDataOfAnalysis();
			} catch (Exception e) {
				fail("Per half hour Analyis failed : " + e.getMessage());
			}
			
			assertTrue(!getConsoleString().isEmpty());
		}else{
			fail("Problem running test on per half hour analysis: Where's the store?");
		}
	}
}
