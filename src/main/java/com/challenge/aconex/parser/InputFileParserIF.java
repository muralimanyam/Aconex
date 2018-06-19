package com.challenge.aconex.parser;

import java.util.List;

/**
 * Interface definition for Input file parser. * 
 * 
 * @author mural
 *
 */
public interface InputFileParserIF {

	public List<String> retrieveStrings(String fileName) throws Exception;
}
