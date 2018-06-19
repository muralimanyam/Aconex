package com.challenge.aconex.analysis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;
import com.challenge.aconex.data.VehicleRecord;

public class AverageSpeedPerHourAnalysis implements VehicleSurveyAnalysisIF {

	private SurveyStoreServiceIF service;

	public void setSurveyAnalysisService(SurveyStoreServiceIF service) {
		this.service = service;
	}

	private static final long MILLIS = 1 * 60 * 60 * 1000;

	public void printDataOfAnalysis() throws Exception {

		Set<Integer> days = this.service.getAvailableDays();
		System.out.println("\n");
		System.out
				.println("---------------- Average Speed of Vehicles in each direction per hour ----------------");
		for (int i = 0; i <= (23); i++) {
			float totalSpeedA = 0F;
			int countA = 0;
			float totalSpeedB = 0F;
			int countB = 0;
			for (int day : days) {
				List<VehicleRecord> getRecordsForHourForA = this.service
						.getRecordsOfDayByTime(day, "A", i * MILLIS, (i + 1)
								* MILLIS);
				if(getRecordsForHourForA != null && getRecordsForHourForA.size() > 0){
					for(VehicleRecord record : getRecordsForHourForA){
						totalSpeedA += ((2.5 * 60 * 60) / (record.getTimestampEnd() - record.getTimestamp()));
					}
					
					countA += getRecordsForHourForA.size();
				}
				
				List<VehicleRecord> getRecordsForHourForB = this.service
						.getRecordsOfDayByTime(day, "B", i * MILLIS, (i + 1)
								* MILLIS);
				if(getRecordsForHourForB != null && getRecordsForHourForB.size() > 0){
					for(VehicleRecord record : getRecordsForHourForB){
						totalSpeedB += ((2.5 * 60 * 60) / (record.getTimestampEnd() - record.getTimestamp()));
					}
					
					countB += getRecordsForHourForB.size();
				}
				
			}

			System.out.println("| "
					+ String.format(
							"%02d:%02d",
							TimeUnit.MILLISECONDS.toHours(i * MILLIS),
							TimeUnit.MILLISECONDS.toMinutes(i * MILLIS)
									- TimeUnit.HOURS
											.toMinutes(TimeUnit.MILLISECONDS
													.toHours(i * MILLIS)))
					+ " || " + totalSpeedA / countA + " || " + totalSpeedB / countB
					 + " || ");
		}

	}

}
