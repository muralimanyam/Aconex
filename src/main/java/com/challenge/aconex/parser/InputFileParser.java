package com.challenge.aconex.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This will parse the file that contains the vehicle survey data.
 * 
 * @author mural
 *
 */
public class InputFileParser implements InputFileParserIF{

	public List<String> retrieveStrings(String fileName) throws Exception {
		File f = new File(fileName);
		if (!f.exists()) {
			throw new Exception("File location is not appropriate");
		}
		List<String> result = new ArrayList<String>();
		BufferedReader br = null;
		Pattern linePattern = Pattern.compile("([a-zA-Z])(\\d+)");
		try {
			br = new BufferedReader(new FileReader(f));
			String line;
			while ((line = br.readLine()) != null) {
				Matcher matcher = linePattern.matcher(line);
				if (matcher.matches()) {
					result.add(line.trim());
				} else {
					throw new Exception(
							"The Vehicle survey data has improper feed");
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}

		return result;
	}
}
