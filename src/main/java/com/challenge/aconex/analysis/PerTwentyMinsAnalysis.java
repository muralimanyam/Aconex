package com.challenge.aconex.analysis;



public class PerTwentyMinsAnalysis extends AbstractVehicleCountPerTimeAnalysis{	


	private static final long MILLIS_PER_HALF_HR = (1 * 60 * 60 * 1000) / 3;

	@Override
	public void printDataOfAnalysis() throws Exception {
		System.out.println("\n");
		System.out
				.println("---------------- Count of Vehicles in each direction per 20 mins ----------------");
		super.printDataOfAnalysis();
	}

	@Override
	public long getMillisecondFactor() {
		return MILLIS_PER_HALF_HR;
	}

	@Override
	public int getHourFactor() {
		return 3;
	}
}
