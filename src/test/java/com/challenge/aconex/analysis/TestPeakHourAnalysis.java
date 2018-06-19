package com.challenge.aconex.analysis;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.challenge.aconex.mock.MockVehicleRecordService;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class TestPeakHourAnalysis extends AbstractTestPrintAnalysis{

	@Test
	public void invokeAnalysis_inputInitedStore_assertPrintStrings(){
		if(store != null){
			VehicleRecordServiceIF mockRecordService = new MockVehicleRecordService();			
			try {
				store.initializeSurveyStoreService(mockRecordService.prepareVehicleRecords(null));
			} catch (Exception e) {
				fail("Problem running test on peak hour analysis: Couldn't init store. ");
			}
			
			VehicleSurveyAnalysisIF analysis = new PeakHourAnalysis();
			analysis.setSurveyAnalysisService(store);
			
			try {
				analysis.printDataOfAnalysis();
			} catch (Exception e) {
				fail("Peak hour Analyis failed : " + e.getMessage());
			}
			
			assertTrue(!getConsoleString().isEmpty());
		}else{
			fail("Problem running test on peak hour analysis: Where's the store?");
		}
	}
}
