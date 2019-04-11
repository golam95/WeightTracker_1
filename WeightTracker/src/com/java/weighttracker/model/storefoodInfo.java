package com.java.weighttracker.model;

public class storefoodInfo {
	private int storefood_id;
	private String storefood_name;

	public storefoodInfo() {

	}

	public storefoodInfo(int storefood_id, String storefood_name, String storefood_calorie, String storefood_username) {
		super();
		this.storefood_id = storefood_id;
		this.storefood_name = storefood_name;
		this.storefood_calorie = storefood_calorie;
		this.storefood_username = storefood_username;
	}

	private String storefood_calorie;

	public int getStorefood_id() {
		return storefood_id;
	}

	public void setStorefood_id(int storefood_id) {
		this.storefood_id = storefood_id;
	}

	public String getStorefood_name() {
		return storefood_name;
	}

	public void setStorefood_name(String storefood_name) {
		this.storefood_name = storefood_name;
	}

	public String getStorefood_calorie() {
		return storefood_calorie;
	}

	public void setStorefood_calorie(String storefood_calorie) {
		this.storefood_calorie = storefood_calorie;
	}

	public String getStorefood_username() {
		return storefood_username;
	}

	public void setStorefood_username(String storefood_username) {
		this.storefood_username = storefood_username;
	}

	private String storefood_username;

}
