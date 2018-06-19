package com.challenge.aconex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.challenge.aconex.data.VehicleRecord;

public class VehicleRecordService implements VehicleRecordServiceIF{

	private final List<VehicleRecord> vehicleRecordList = new ArrayList<VehicleRecord>();	
	private static final Logger LOGGER = Logger.getLogger(VehicleRecordService.class.getName());

	public List<VehicleRecord> prepareVehicleRecords(List<String> surveyData) throws Exception {
		prepareVehicleRecordsUsingIterateAndMatch(surveyData);
		return vehicleRecordList;
	}

	/*
	 * Brute force way - iterate over each data string and try for a match
	 * using known conditions.
	 * 
	 * - Have 2 indexes, search for AA or ABAB records.
	 */
	private void prepareVehicleRecordsUsingIterateAndMatch(
			List<String> surveyData) throws Exception {
		long lastTimeStamp = -1;
		int day = 1;
		int i, j = 0; // 2 indexes.
		for (i = 0; i < surveyData.size(); i++) {
			String dataAtIndex = surveyData.get(i);
			if (dataAtIndex.startsWith("A")) {
				j = i + 1;
				if (Long.valueOf(surveyData.get(i).substring(1,
						surveyData.get(i).length())) < lastTimeStamp) {
					// So, if a smaller timestamp appears than the last one,
					// it means the record happened for next day.
					day++;
				}
				if (j < surveyData.size() && surveyData.get(j).startsWith("A")) {

					VehicleRecord record = new VehicleRecord("A",
							Long.valueOf(surveyData.get(i).substring(1,
									surveyData.get(i).length())),
							Long.valueOf(surveyData.get(j).substring(1,
									surveyData.get(j).length())), day);
					vehicleRecordList.add(record);

					// move index by 1;
					i += 1;
				} else if (j < surveyData.size() &&  surveyData.get(j).startsWith("B")){
					 
						VehicleRecord record = new VehicleRecord("B",
								Long.valueOf(surveyData.get(i).substring(1,
										surveyData.get(i).length())),
								Long.valueOf(surveyData.get(j + 1).substring(1,
										surveyData.get(j + 1).length())), day);
						vehicleRecordList.add(record);

						// move index by 3;
						i += 3;
					
				}else{
					//Bad data identifier.
					String message = "The survey data is not proper. Aborting op and returning empty data";
					LOGGER.severe(message);
					vehicleRecordList.clear();
					throw new Exception(message);
				}
				lastTimeStamp = Long.valueOf(surveyData.get(i).substring(1,
						surveyData.get(i).length()));
			}else{
				// Incomplete data maybe.
				String message = "Invalid start to survey data. Aborting op and returning empty data";
				LOGGER.severe(message);
				vehicleRecordList.clear();
				throw new Exception(message);
			}
		}
	}
}
