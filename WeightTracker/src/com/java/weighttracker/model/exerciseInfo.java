package com.java.weighttracker.model;

public class exerciseInfo {
	private int exercise_id;
	private String exercie_name;
	private String exercise_calorie;
	private String exercise_date;
	private String exercise_uername;

	public exerciseInfo() {

	}

	public exerciseInfo(int exercise_id, String exercie_name, String exercise_calorie, String exercise_date,
			String exercise_uername) {
		super();
		this.exercise_id = exercise_id;
		this.exercie_name = exercie_name;
		this.exercise_calorie = exercise_calorie;
		this.exercise_date = exercise_date;
		this.exercise_uername = exercise_uername;
	}

	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public String getExercie_name() {
		return exercie_name;
	}

	public void setExercie_name(String exercie_name) {
		this.exercie_name = exercie_name;
	}

	public String getExercise_calorie() {
		return exercise_calorie;
	}

	public void setExercise_calorie(String exercise_calorie) {
		this.exercise_calorie = exercise_calorie;
	}

	public String getExercise_date() {
		return exercise_date;
	}

	public void setExercise_date(String exercise_date) {
		this.exercise_date = exercise_date;
	}

	public String getExercise_uername() {
		return exercise_uername;
	}

	public void setExercise_uername(String exercise_uername) {
		this.exercise_uername = exercise_uername;
	}

}
