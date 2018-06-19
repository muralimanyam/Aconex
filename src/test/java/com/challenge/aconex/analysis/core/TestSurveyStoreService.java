package com.challenge.aconex.analysis.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.challenge.aconex.mock.MockInvalidVehicleRecordService;
import com.challenge.aconex.mock.MockVehicleRecordService;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class TestSurveyStoreService {

	private SurveyStoreServiceIF store;
	private VehicleRecordServiceIF vrs;
	
	@Before
	public void setup(){
		store = new SurveyStoreService();
		vrs = new MockVehicleRecordService();
	}
	
	@Test
	public void initialize_vehicleRecords_assertInstanceCreation(){
		try {
			store.initializeSurveyStoreService(vrs.prepareVehicleRecords(null));
		} catch (Exception e) {
			fail("Failed to initialize the Survey Store. " + e.getMessage());
		}
	}
	
	@Test
	public void initialize_invalidVehicleRecords_assertInitFailed(){
		vrs = new MockInvalidVehicleRecordService();
		try {
			store.initializeSurveyStoreService(vrs.prepareVehicleRecords(null));
			fail("Survey store initialized even with bad data.");
		} catch (Exception e) {
			assertTrue(e.getMessage(), !e.getMessage().isEmpty());
		}
	}
	
	@After
	public void teardown(){
		store = null;
	}
}
