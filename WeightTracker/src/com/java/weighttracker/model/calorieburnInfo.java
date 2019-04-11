package com.java.weighttracker.model;

public class calorieburnInfo {
	private int calorie_id;
	private String calorie_intake;
	private String calorie_burn;
	private String calorie_difference;
	private String calorie_date;
	private String calorie_username;

	public calorieburnInfo() {

	}

	public calorieburnInfo(int calorie_id, String calorie_intake, String calorie_burn, String calorie_difference,
			String calorie_date, String calorie_username) {
		super();
		this.calorie_id = calorie_id;
		this.calorie_intake = calorie_intake;
		this.calorie_burn = calorie_burn;
		this.calorie_difference = calorie_difference;
		this.calorie_date = calorie_date;
		this.calorie_username = calorie_username;
	}

	public int getCalorie_id() {
		return calorie_id;
	}

	public void setCalorie_id(int calorie_id) {
		this.calorie_id = calorie_id;
	}

	public String getCalorie_intake() {
		return calorie_intake;
	}

	public void setCalorie_intake(String calorie_intake) {
		this.calorie_intake = calorie_intake;
	}

	public String getCalorie_burn() {
		return calorie_burn;
	}

	public void setCalorie_burn(String calorie_burn) {
		this.calorie_burn = calorie_burn;
	}

	public String getCalorie_difference() {
		return calorie_difference;
	}

	public void setCalorie_difference(String calorie_difference) {
		this.calorie_difference = calorie_difference;
	}

	public String getCalorie_date() {
		return calorie_date;
	}

	public void setCalorie_date(String calorie_date) {
		this.calorie_date = calorie_date;
	}

	public String getCalorie_username() {
		return calorie_username;
	}

	public void setCalorie_username(String calorie_username) {
		this.calorie_username = calorie_username;
	}

}
