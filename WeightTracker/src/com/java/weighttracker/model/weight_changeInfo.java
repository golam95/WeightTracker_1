package com.java.weighttracker.model;

public class weight_changeInfo {

	private int change_id;
	private String change_amount;
	private String change_date;
	private String change_username;
	
	public weight_changeInfo() {
		
	}

	public weight_changeInfo(int change_id, String change_amount, String change_date, String change_username) {
		super();
		this.change_id = change_id;
		this.change_amount = change_amount;
		this.change_date = change_date;
		this.change_username = change_username;
	}

	public int getChange_id() {
		return change_id;
	}

	public void setChange_id(int change_id) {
		this.change_id = change_id;
	}

	public String getChange_amount() {
		return change_amount;
	}

	public void setChange_amount(String change_amount) {
		this.change_amount = change_amount;
	}

	public String getChange_date() {
		return change_date;
	}

	public void setChange_date(String change_date) {
		this.change_date = change_date;
	}

	public String getChange_username() {
		return change_username;
	}

	public void setChange_username(String change_username) {
		this.change_username = change_username;
	}

}
