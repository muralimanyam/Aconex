package com.challenge.aconex.analysis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;
import com.challenge.aconex.data.VehicleRecord;

public class RoughDistanceBetweenCarsAnalysis implements
		VehicleSurveyAnalysisIF {

	private SurveyStoreServiceIF service;

	public void setSurveyAnalysisService(SurveyStoreServiceIF service) {
		this.service = service;
	}

	private static final long MILLIS = 1 * 60 * 60 * 1000;

	public void printDataOfAnalysis() throws Exception {

		Set<Integer> days = this.service.getAvailableDays();
		System.out.println("\n");
		System.out
				.println("---------------- Rough distance between Vehicles im Meters in each direction per hour ----------------");
		for (int i = 0; i <= (23); i++) {
			long avgDistancePerDayForA = 0;
			long avgDistancePerDayForB = 0;
			for (int day : days) {
				long avgDistanceA = 0;
				long avgDistanceB = 0;
				List<VehicleRecord> getRecordsForHourForA = this.service
						.getRecordsOfDayByTime(day, "A", i * MILLIS, (i + 1)
								* MILLIS);
				if (getRecordsForHourForA != null
						&& getRecordsForHourForA.size() > 0) {
					for (int j = 0; j < getRecordsForHourForA.size() - 1; j++) {
						double speedOfsecondCar = (2.5 * 60 * 60)
								/ (getRecordsForHourForA.get(j + 1)
										.getTimestampEnd() - getRecordsForHourForA
										.get(j + 1).getTimestamp());
						avgDistanceA += (long) (((getRecordsForHourForA.get(
								j + 1).getTimestamp() - getRecordsForHourForA
								.get(j).getTimestampEnd()) ) / 1000 * speedOfsecondCar);
					}
				}
				avgDistanceA = avgDistanceA /getRecordsForHourForA.size();
				avgDistancePerDayForA += avgDistanceA;
				List<VehicleRecord> getRecordsForHourForB = this.service
						.getRecordsOfDayByTime(day, "B", i * MILLIS, (i + 1)
								* MILLIS);
				if (getRecordsForHourForB != null
						&& getRecordsForHourForB.size() > 0) {
					for (int j = 0; j < getRecordsForHourForB.size() - 1; j++) {
						double speedOfsecondCar = (2.5 * 60 * 60)
								/ (getRecordsForHourForB.get(j + 1)
										.getTimestampEnd() - getRecordsForHourForB
										.get(j + 1).getTimestamp());
						avgDistanceB += (long) (((getRecordsForHourForB.get(
								j + 1).getTimestamp() - getRecordsForHourForB
								.get(j).getTimestampEnd())) / 1000 * speedOfsecondCar );
					}
				}
				avgDistanceB = avgDistanceB /getRecordsForHourForB.size();
				avgDistancePerDayForB += avgDistanceB;
			}

			System.out.println("| "
					+ String.format(
							"%02d:%02d",
							TimeUnit.MILLISECONDS.toHours(i * MILLIS),
							TimeUnit.MILLISECONDS.toMinutes(i * MILLIS)
									- TimeUnit.HOURS
											.toMinutes(TimeUnit.MILLISECONDS
													.toHours(i * MILLIS)))
					+ " || " + avgDistancePerDayForA / days.size() + " || " + avgDistancePerDayForB
					/ days.size() + " || ");
		}

	}
}
