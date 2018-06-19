package com.challenge.aconex.analysis;

import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;

/**
 * Interface definition for each type of Analysis.
 * @author mural
 *
 */
public interface VehicleSurveyAnalysisIF {

	/**
	 * Set a type of survey store that provides data.
	 * @param service
	 */
	public void setSurveyAnalysisService(SurveyStoreServiceIF service);
	
	/**
	 * Print result of analysis.
	 * @throws Exception
	 */
	public void printDataOfAnalysis() throws Exception;
}
