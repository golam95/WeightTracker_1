package com.java.weighttracker.dao;

import java.sql.SQLException;
import com.java.weighttracker.model.exerciseInfo;

public interface exerciseDAO {

	public void addExercise(exerciseInfo add_exercise) throws SQLException;

	public void delete_Exercise(int id) throws SQLException;

	public void update_Exercise(exerciseInfo u) throws SQLException;

	public boolean checkdate_calorie(String check_date, String check_uesrname) throws SQLException;

}
