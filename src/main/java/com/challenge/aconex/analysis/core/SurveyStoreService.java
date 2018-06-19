package com.challenge.aconex.analysis.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.challenge.aconex.data.VehicleRecord;

/**
 * Provides API implementations for Survey Store.
 * @author mural
 *
 */
public class SurveyStoreService implements SurveyStoreServiceIF{

	private Map<Integer, List<VehicleRecord>> aDirectionStore = new HashMap<Integer, List<VehicleRecord>>();
	private Map<Integer, List<VehicleRecord>> bDirectionStore = new HashMap<Integer, List<VehicleRecord>>();

	public void initializeSurveyStoreService(List<VehicleRecord> vehicleRecords)
			throws IllegalArgumentException {

		// Initialize the store according to day.
		for (VehicleRecord rec : vehicleRecords) {
			if (rec.getDirection().equals("A")) {
				addVehicleRecordSafely(rec, aDirectionStore);
			} else if (rec.getDirection().equals("B")) {
				addVehicleRecordSafely(rec, bDirectionStore);
			} else {
				throw new IllegalArgumentException(
						"The records have invalid data.");
			}
		}
	}

	private void addVehicleRecordSafely(VehicleRecord rec,
			Map<Integer, List<VehicleRecord>> store) {
		if (store.containsKey(rec.getDay())) {
			List<VehicleRecord> recordsAtKey = store.get(rec.getDay());
			recordsAtKey.add(rec);
			store.replace(rec.getDay(), recordsAtKey);
		} else {
			List<VehicleRecord> recordsAtKey = new ArrayList<VehicleRecord>();
			recordsAtKey.add(rec);
			store.put(rec.getDay(), recordsAtKey);
		}
	}

	public List<VehicleRecord> getRecordsOfDay(int day, String direction) {
		List<VehicleRecord> results = new ArrayList<VehicleRecord>();

		if (direction.equals("A")) {
			results = aDirectionStore.get(day);
		} else {
			results = bDirectionStore.get(day);
		}

		return results;
	}

	public List<VehicleRecord> getRecordsOfDayByTime(int day, String direction,
			long startTimeInMillis, long endTimeInMillis) {
		List<VehicleRecord> results = new ArrayList<VehicleRecord>();

		if (direction.equals("A")) {
			results = getRecordsForDuration(day, startTimeInMillis,
					endTimeInMillis, aDirectionStore);
		} else {
			results = getRecordsForDuration(day, startTimeInMillis,
					endTimeInMillis, bDirectionStore);
		}

		return results;
	}

	private List<VehicleRecord> getRecordsForDuration(int day,
			long startTimeInMillis, long endTimeInMillis,
			Map<Integer, List<VehicleRecord>> store) {
		List<VehicleRecord> results = new ArrayList<VehicleRecord>();
		List<VehicleRecord> allDayRecords = store.get(day);
		for (VehicleRecord rec : allDayRecords) {
			if (rec.getTimestamp() > startTimeInMillis
					&& rec.getTimestamp() < endTimeInMillis) {
				results.add(rec);
			}
		}
		return results;
	}

	public Set<Integer> getAvailableDays() {
		return aDirectionStore.keySet().size() > bDirectionStore.keySet()
				.size() ? aDirectionStore.keySet() : bDirectionStore.keySet();
	}

}
