package com.challenge.aconex.service;

import java.util.List;

import com.challenge.aconex.data.VehicleRecord;

/**
 * Interface definition for Vehicle record service.
 * 
 * <p>Responsibilities of implementations include:
 *   - Take the survey data and identify the vehicles.
 *   - Create Vehicle records.
 *   - Identify badly formed file and signal appropriate exception for program termination.<p>
 *   
 * @author mural
 *
 */
public interface VehicleRecordServiceIF {

	public List<VehicleRecord> prepareVehicleRecords(List<String> surveyData) throws Exception;
}
