package com.challenge.aconex.analysis;

import java.util.List;
import java.util.Set;

import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;
import com.challenge.aconex.data.VehicleRecord;

public class PeakHourAnalysis implements VehicleSurveyAnalysisIF {

	private SurveyStoreServiceIF service;

	private static final long MILLIS = 1 * 60 * 60 * 1000;

	public long getMillisecondFactor() {
		return MILLIS;
	}

	public int getHourFactor() {
		return 1;
	}

	public void setSurveyAnalysisService(SurveyStoreServiceIF service) {
		this.service = service;
	}

	public void printDataOfAnalysis() throws Exception {

		Set<Integer> days = this.service.getAvailableDays();
		/*
		 * int peakValueA = 0; int peakValueB = 0;
		 */
		PeakRecord peakRecA = new PeakRecord(0, 0);
		PeakRecord peakRecB = new PeakRecord(0, 0);
		for (int i = 0; i <= (23 * getHourFactor()); i++) {

			for (int day : days) {
				List<VehicleRecord> records = this.service
						.getRecordsOfDayByTime(day, "A", i
								* getMillisecondFactor(), (i + 1)
								* getMillisecondFactor());
				if (records.size() > peakRecA.getPeakValue()) {
					peakRecA.setPeakValue(records.size());
					peakRecA.setDay(day);
				}
				records = this.service.getRecordsOfDayByTime(day, "B", i
						* getMillisecondFactor(), (i + 1)
						* getMillisecondFactor());

				if (records.size() > peakRecB.getPeakValue()) {
					peakRecB.setPeakValue(records.size());
					peakRecB.setDay(day);
				}

			}
		}
		System.out.println("\n");
		System.out.println("------------- Peak Hour time ----------------");
		System.out.println("Peak hour time value for Direction A : "
				+ peakRecA.getPeakValue() + " on day " + peakRecA.getDay());
		System.out.println("Peak hour time value for Direction B : "
				+ peakRecB.getPeakValue() + " on day " + peakRecB.getDay());
	}

	class PeakRecord {

		private int peakValue;
		private int day;

		PeakRecord(int peakValue, int day) {
			this.peakValue = peakValue;
			this.day = day;
		}

		public int getPeakValue() {
			return peakValue;
		}

		public void setPeakValue(int peakValue) {
			this.peakValue = peakValue;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}
	}
}
