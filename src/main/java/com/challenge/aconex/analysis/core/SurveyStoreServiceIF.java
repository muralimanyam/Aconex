package com.challenge.aconex.analysis.core;

import java.util.List;
import java.util.Set;

import com.challenge.aconex.data.VehicleRecord;

/**
 * Interface definition for Survey store.
 * 
 * Implementation shall be responsible to arrange all the parsed Vehicle records
 * into proper data structure and satisfy the APIs.
 * 
 * @author mural
 *
 */
public interface SurveyStoreServiceIF {

	public List<VehicleRecord> getRecordsOfDay(int day, String direction);
	
	public List<VehicleRecord> getRecordsOfDayByTime(int day, String direction,
			long startTimeInMillis, long endTimeInMillis);
	
	public Set<Integer> getAvailableDays();
	
	public void initializeSurveyStoreService(List<VehicleRecord> vehicleRecords) throws Exception;
}
