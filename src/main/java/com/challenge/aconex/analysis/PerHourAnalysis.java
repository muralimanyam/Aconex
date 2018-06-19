package com.challenge.aconex.analysis;



public class PerHourAnalysis extends AbstractVehicleCountPerTimeAnalysis{
	
	private static final long MILLIS = 1 * 60 * 60 * 1000;

	@Override
	public void printDataOfAnalysis() throws Exception {
		System.out.println("\n");
		System.out
				.println("---------------- Count of Vehicles in each direction per hour ----------------");
		super.printDataOfAnalysis();

	}

	@Override
	public long getMillisecondFactor() {
		return MILLIS;
	}

	@Override
	public int getHourFactor() {
		return 1;
	}

}
