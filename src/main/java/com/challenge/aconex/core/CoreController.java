package com.challenge.aconex.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.challenge.aconex.analysis.VehicleSurveyAnalysisIF;
import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;
import com.challenge.aconex.data.VehicleRecord;
import com.challenge.aconex.parser.InputFileParserIF;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class CoreController {

	private static final Logger LOGGER = Logger.getLogger(CoreController.class
			.getName());


	public List<VehicleRecord> getVehicleRecords(String vehicleSurveyLocation) {
		List<VehicleRecord> vehicleRecords = null;
		try {
			List<String> surveyDataStrings = fileParser
					.retrieveStrings(vehicleSurveyLocation);

			vehicleRecords = recordService
					.prepareVehicleRecords(surveyDataStrings);
		} catch (Exception e) {
			LOGGER.severe("Severe exception occurred while retrieving survey data : "
					+ e.getMessage());
		}
		return vehicleRecords;
	}
	
	public void manageAnalysis(List<VehicleRecord> vehicleRecords) throws Exception{
		if(this.storeService != null){
			this.storeService.initializeSurveyStoreService(vehicleRecords);
		}else{
			throw new Exception("Store service is not available. Cannot run the analysis.");
		}
		
		if(analysers != null && analysers.size() > 0){
			for(VehicleSurveyAnalysisIF analyser : analysers){
				analyser.setSurveyAnalysisService(storeService);
				analyser.printDataOfAnalysis();
			}
		}else{
			throw new Exception("No analysers were found!");
		}
		
	}

	private InputFileParserIF fileParser = null;
	private VehicleRecordServiceIF recordService = null;

	public void setInputParser(InputFileParserIF fileParser) {
		this.fileParser = fileParser;
	}

	public void setVehicleRecordService(VehicleRecordServiceIF recordService) {
		this.recordService = recordService;
	}

	private List<VehicleSurveyAnalysisIF> analysers = new ArrayList<VehicleSurveyAnalysisIF>();

	private SurveyStoreServiceIF storeService = null;

	public void addAnalyser(VehicleSurveyAnalysisIF analyser) {
		this.analysers.add(analyser);
	}
	
	public void setSurveyStoreService(SurveyStoreServiceIF storeService){
		this.storeService = storeService;
	}
}
