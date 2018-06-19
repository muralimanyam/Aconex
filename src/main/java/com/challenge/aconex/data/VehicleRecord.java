package com.challenge.aconex.data;

/**
 * 
 * @author mural
 *
 */
public class VehicleRecord {

	private String direction;
	private long timestampStart;
	private long timestampEnd;
	private int day;
	
	public long getTimestampEnd() {
		return timestampEnd;
	}

	public void setTimestampEnd(long timestampEnd) {
		this.timestampEnd = timestampEnd;
	}

	public VehicleRecord(String direction, long timestamp, long timestampEnd, int day) {
		this.direction = direction;
		this.timestampStart = timestamp;
		this.timestampEnd = timestampEnd;
		this.day = day;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public long getTimestamp() {
		return timestampStart;
	}

	public void setTimestamp(long timestamp) {
		this.timestampStart = timestamp;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "VehicleRecord [direction=" + direction + ", timestampStart="
				+ timestampStart + ", timestampEnd=" + timestampEnd + ", day="
				+ day + "]";
	}

	
}
