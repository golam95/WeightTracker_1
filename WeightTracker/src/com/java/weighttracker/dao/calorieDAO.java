package com.java.weighttracker.dao;

import java.sql.SQLException;
import com.java.weighttracker.model.calorieburnInfo;

public interface calorieDAO {

	public void add_calorie(calorieburnInfo add_calorie) throws SQLException;

	public void delete_calorie(int id) throws SQLException;

	public boolean checkdate_calorie(String check_date, String check_uesrname) throws SQLException;

}
