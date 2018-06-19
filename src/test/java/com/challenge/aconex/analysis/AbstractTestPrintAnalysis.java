package com.challenge.aconex.analysis;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import com.challenge.aconex.analysis.core.SurveyStoreService;
import com.challenge.aconex.analysis.core.SurveyStoreServiceIF;

public abstract class AbstractTestPrintAnalysis {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	protected SurveyStoreServiceIF store;

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		store = new SurveyStoreService();
	}

	public String getConsoleString() {
		return outContent.toString();
	}

	public String getErrorString() {
		return errContent.toString();
	}

	@After
	public void teardown() {
		System.setOut(System.out);
		System.setErr(System.err);
		store = null;
	}
}
