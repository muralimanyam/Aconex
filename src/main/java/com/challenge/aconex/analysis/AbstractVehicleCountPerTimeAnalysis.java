package com.challenge.aconex.analysis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;

public abstract class AbstractVehicleCountPerTimeAnalysis implements VehicleSurveyAnalysisIF{

	private SurveyStoreServiceIF service;

	
	public void setSurveyAnalysisService(SurveyStoreServiceIF service){
		this.service = service;
	}
	
	public abstract long getMillisecondFactor();
	
	public abstract int getHourFactor();


	public void printDataOfAnalysis() throws Exception {
		
		Set<Integer> days = this.service.getAvailableDays();
		for (int day : days) {
			System.out.println("\n");
			System.out.println(" ---------- Day " + day + " ------------");
			System.out.println("| HOUR || A || B ||");
			for (int i = 0; i <= (23 * getHourFactor()); i++) {
				System.out
						.println("| "
								+ String.format(
										"%02d:%02d",
										TimeUnit.MILLISECONDS.toHours(i
												* getMillisecondFactor()),
										TimeUnit.MILLISECONDS.toMinutes(i
												* getMillisecondFactor())
												- TimeUnit.HOURS
														.toMinutes(TimeUnit.MILLISECONDS
																.toHours(i
																		* getMillisecondFactor())))
								+ " || "
								+ this.service.getRecordsOfDayByTime(day, "A",
										i * getMillisecondFactor(),
										(i + 1) * getMillisecondFactor()).size() /*!= null ? this.service.getRecordsOfDayByTime(day, "A",
												i * getMillisecondFactor(),
												(i + 1) * getMillisecondFactor()).size() : 0*/
								+ " || "
								+ this.service.getRecordsOfDayByTime(day, "B",
										i * getMillisecondFactor(),
										(i + 1) * getMillisecondFactor()).size()/* != null ? this.service.getRecordsOfDayByTime(day, "B",
												i * getMillisecondFactor(),
												(i + 1) * getMillisecondFactor()).size() : 0*/
								+ " || ");

			}
			System.out.println(" ---------------- End of day " + day
					+ " ------------------");
		}

	}
}
