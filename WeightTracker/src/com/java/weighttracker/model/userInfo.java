package com.java.weighttracker.model;

public class userInfo {
	private int user_id;
	private String user_Name;
	private String user_Gener;
	private int user_Age;
	private String user_date;
	private String user_role;

	public userInfo(int user_id, String user_Name, String user_Gener, int user_Age, String user_date,
			String user_role) {
		super();
		this.user_id = user_id;
		this.user_Name = user_Name;
		this.user_Gener = user_Gener;
		this.user_Age = user_Age;
		this.user_date = user_date;
		this.user_role = user_role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getUser_Gener() {
		return user_Gener;
	}

	public void setUser_Gener(String user_Gener) {
		this.user_Gener = user_Gener;
	}

	public int getUser_Age() {
		return user_Age;
	}

	public void setUser_Age(int user_Age) {
		this.user_Age = user_Age;
	}

	public String getUser_date() {
		return user_date;
	}

	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
}