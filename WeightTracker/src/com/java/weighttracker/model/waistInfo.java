package com.java.weighttracker.model;

public class waistInfo {
	private int waist_id;
	private String waist_morning;

	public waistInfo(int waist_id, String waist_morning, String waist_evening, String waist_average, String waist_date,
			String username) {
		super();
		this.waist_id = waist_id;
		this.waist_morning = waist_morning;
		this.waist_evening = waist_evening;
		this.waist_average = waist_average;
		this.waist_date = waist_date;
		this.username = username;
	}

	public int getWaist_id() {
		return waist_id;
	}

	public void setWaist_id(int waist_id) {
		this.waist_id = waist_id;
	}

	public String getWaist_morning() {
		return waist_morning;
	}

	public void setWaist_morning(String waist_morning) {
		this.waist_morning = waist_morning;
	}

	public String getWaist_evening() {
		return waist_evening;
	}

	public void setWaist_evening(String waist_evening) {
		this.waist_evening = waist_evening;
	}

	public String getWaist_average() {
		return waist_average;
	}

	public void setWaist_average(String waist_average) {
		this.waist_average = waist_average;
	}

	public String getWaist_date() {
		return waist_date;
	}

	public void setWaist_date(String waist_date) {
		this.waist_date = waist_date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String waist_evening;
	private String waist_average;
	private String waist_date;
	private String username;

}