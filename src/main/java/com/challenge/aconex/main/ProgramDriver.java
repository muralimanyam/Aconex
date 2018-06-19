package com.challenge.aconex.main;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

import com.challenge.aconex.analysis.VehicleSurveyAnalysisIF;
import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;
import com.challenge.aconex.core.CoreController;
import com.challenge.aconex.data.VehicleRecord;
import com.challenge.aconex.parser.InputFileParserIF;
import com.challenge.aconex.service.VehicleRecordServiceIF;

public class ProgramDriver {

	private static final Logger LOGGER = Logger.getLogger(ProgramDriver.class
			.getName());

	public static void main(String... strings) {
		String dataFileLocation = null;
		if (strings.length > 0) {
			dataFileLocation = strings[0];
		} else {
			dataFileLocation = "com/challenge/aconex/core/RealData.txt";
		}
		LOGGER.info("The location of survey data is : " + dataFileLocation);
		CoreController controller = new CoreController();

		// Load and set File parser.
		ServiceLoader<InputFileParserIF> fileParserLoader = ServiceLoader
				.load(InputFileParserIF.class);
		if (fileParserLoader.iterator().hasNext()) {
			Iterator<InputFileParserIF> iterator = fileParserLoader.iterator();
			controller.setInputParser(iterator.next());
		}

		// Load and set vehicle record service.
		ServiceLoader<VehicleRecordServiceIF> recordServiceLoader = ServiceLoader
				.load(VehicleRecordServiceIF.class);
		if (recordServiceLoader.iterator().hasNext()) {
			Iterator<VehicleRecordServiceIF> iterator = recordServiceLoader
					.iterator();
			controller.setVehicleRecordService(iterator.next());
		}

		List<VehicleRecord> vehicleRecords = controller
				.getVehicleRecords(dataFileLocation);

		LOGGER.info("Size of records : " + vehicleRecords.size());

		if (vehicleRecords.size() > 0) {
			// Load the survey store service.
			ServiceLoader<SurveyStoreServiceIF> storeServiceLoader = ServiceLoader
					.load(SurveyStoreServiceIF.class);
			if (storeServiceLoader.iterator().hasNext()) {
				Iterator<SurveyStoreServiceIF> iterator = storeServiceLoader
						.iterator();
				controller.setSurveyStoreService(iterator.next());
			}

			// Now load the Analysers.
			ServiceLoader<VehicleSurveyAnalysisIF> analyserServiceLoader = ServiceLoader
					.load(VehicleSurveyAnalysisIF.class);
			Iterator<VehicleSurveyAnalysisIF> iterator = analyserServiceLoader
					.iterator();
			while (iterator.hasNext()) {
				controller.addAnalyser(iterator.next());
			}

			try {
				controller.manageAnalysis(vehicleRecords);
			} catch (Exception e) {
				LOGGER.severe("Exception occurred while running analysis : "
						+ e.getMessage());
			}
		}
	}
}
