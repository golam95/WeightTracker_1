package com.java.weighttracker.model;

public class weightInfo {
	private int weight_id;
	private String weight_morning;
	private String weight_evening;
	private String weight_average;
	private String weight_date;
	private String username;

	public weightInfo() {

	}

	public weightInfo(int weight_id, String weight_morning, String weight_evening, String weight_average,
			String weight_date, String username) {
		super();
		this.weight_id = weight_id;
		this.weight_morning = weight_morning;
		this.weight_evening = weight_evening;
		this.weight_average = weight_average;
		this.weight_date = weight_date;
		this.username = username;

	}

	public int getWeight_id() {
		return weight_id;
	}

	public void setWeight_id(int weight_id) {
		this.weight_id = weight_id;
	}

	public String getWeight_morning() {
		return weight_morning;
	}

	public void setWeight_morning(String weight_morning) {
		this.weight_morning = weight_morning;
	}

	public String getWeight_evening() {
		return weight_evening;
	}

	public void setWeight_evening(String weight_evening) {
		this.weight_evening = weight_evening;
	}

	public String getWeight_average() {
		return weight_average;
	}

	public void setWeight_average(String weight_average) {
		this.weight_average = weight_average;
	}

	public String getWeight_date() {
		return weight_date;
	}

	public void setWeight_date(String weight_date) {
		this.weight_date = weight_date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}