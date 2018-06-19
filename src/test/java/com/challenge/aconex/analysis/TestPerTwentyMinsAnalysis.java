package com.challenge.aconex.analysis;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.challenge.aconex.mock.MockVehicleRecordService;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class TestPerTwentyMinsAnalysis extends AbstractTestPrintAnalysis{

	@Test
	public void invokeAnalysis_inputInitedStore_assertPrintStrings(){
		if(store != null){
			VehicleRecordServiceIF mockRecordService = new MockVehicleRecordService();			
			try {
				store.initializeSurveyStoreService(mockRecordService.prepareVehicleRecords(null));
			} catch (Exception e) {
				fail("Problem running test on per 20 mins analysis: Couldn't init store. ");
			}
			
			VehicleSurveyAnalysisIF analysis = new PerTwentyMinsAnalysis();
			analysis.setSurveyAnalysisService(store);
			
			try {
				analysis.printDataOfAnalysis();
			} catch (Exception e) {
				fail("Per 20 mins Analyis failed : " + e.getMessage());
			}
			
			assertTrue(!getConsoleString().isEmpty());
		}else{
			fail("Problem running test on per 20 mins analysis: Where's the store?");
		}
	}
}
