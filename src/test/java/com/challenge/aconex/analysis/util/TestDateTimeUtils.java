package com.challenge.aconex.analysis.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDateTimeUtils {

	@Test
	public void invokeGetHours_inputKnownTimestamp_assertWithExpectedHH() {
		long timestamp = 98186;
		assertEquals(00, DateTimeUtils.getHourFromMillisInHHFormat(timestamp));
		
		timestamp = 86389454;
		assertEquals(23, DateTimeUtils.getHourFromMillisInHHFormat(timestamp));
	}
}
