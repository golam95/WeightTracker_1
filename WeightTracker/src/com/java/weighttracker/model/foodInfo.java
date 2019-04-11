package com.java.weighttracker.model;

public class foodInfo {
	private int food_id;
	private String food_name;
	private String food_caloried;
	private String food_date;
	private String username;
	private String food_calorie;

	public foodInfo() {

	}

	public foodInfo(int food_id, String food_name, String food_caloried, String food_date, String username,
			String food_calorie) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.food_caloried = food_caloried;
		this.food_date = food_date;
		this.username = username;
		this.food_calorie = food_calorie;
	}

	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getFood_caloried() {
		return food_caloried;
	}

	public void setFood_caloried(String food_caloried) {
		this.food_caloried = food_caloried;
	}

	public String getFood_date() {
		return food_date;
	}

	public void setFood_date(String food_date) {
		this.food_date = food_date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFood_calorie() {
		return food_calorie;
	}

	public void setFood_calorie(String food_calorie) {
		this.food_calorie = food_calorie;
	}
}
