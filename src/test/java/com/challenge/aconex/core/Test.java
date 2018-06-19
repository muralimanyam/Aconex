package com.challenge.aconex.core;

import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String... strings) {
		long millis = 70327401;
		String result = String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toDays(millis),
				TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
								.toHours(millis)), // The change is in this line
				TimeUnit.MILLISECONDS.toSeconds(millis)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(millis)));

		// long days = TimeUnit.MILLISECONDS.toDays(millis);

		int days = (int) (millis / 86400000);

		System.out.println("Time : " + result);

		System.out.println("Days : " + days);
	}
}
