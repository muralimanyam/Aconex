package com.challenge.aconex.analysis.util;

import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

	public static int getHourFromMillisInHHFormat(long millis) throws NumberFormatException{
		return Integer.valueOf(String.format("%02d",
				TimeUnit.MILLISECONDS.toHours(millis)));
	}
}
