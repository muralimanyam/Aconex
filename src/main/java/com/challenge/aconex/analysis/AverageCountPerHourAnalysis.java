package com.challenge.aconex.analysis;


public class AverageCountPerHourAnalysis extends AbstractAverageVehicleCountPerTimeAnalysis{


	private static final long MILLIS = 1 * 60 * 60 * 1000;
	@Override
	public long getMillisecondFactor() {
		return MILLIS;
	}
	
	@Override
	public void printDataOfAnalysis() throws Exception {
		System.out.println("\n");
		System.out
				.println("---------------- Average Count of Vehicles in each direction per hour ----------------");
		super.printDataOfAnalysis();

	}

	@Override
	public int getHourFactor() {
		return 1;
	}

}
