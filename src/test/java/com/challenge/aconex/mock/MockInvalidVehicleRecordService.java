package com.challenge.aconex.mock;

import java.util.ArrayList;
import java.util.List;

import com.challenge.aconex.data.VehicleRecord;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class MockInvalidVehicleRecordService implements VehicleRecordServiceIF{

	
	public List<VehicleRecord> prepareVehicleRecords(List<String> surveyData)
			throws Exception {
		
		List<VehicleRecord> result = new ArrayList<VehicleRecord>();
		
		result.add(new VehicleRecord("A", 98186, 98333, 1));
		result.add(new VehicleRecord("A", 499718, 499886, 1));
		result.add(new VehicleRecord("C", 638379, 638520, 1));
		result.add(new VehicleRecord("A", 1016488, 1016648, 2));
		result.add(new VehicleRecord("B", 1058535, 1058659, 2));
		result.add(new VehicleRecord("A", 2146213, 2146349, 2));
		result.add(new VehicleRecord("A", 2211004, 2211128, 3));
		result.add(new VehicleRecord("B", 59961018, 59962158, 3));
		result.add(new VehicleRecord("A", 59964742, 59973984, 4));
		result.add(new VehicleRecord("A", 98186, 98333, 5));
		result.add(new VehicleRecord("A", 25784405, 25784563, 5));
		result.add(new VehicleRecord("B", 59964742, 59973984, 5));		
		
		return result;		
	}
}
