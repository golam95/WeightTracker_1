package com.java.weighttracker.dao;

import java.sql.SQLException;
import com.java.weighttracker.model.weightInfo;
import com.java.weighttracker.model.weight_changeInfo;

public interface weightDAO {

	public void add_weight(weightInfo weightinfo) throws SQLException;

	public void delete_weight(int id) throws SQLException;

	public void update_weight(weightInfo u) throws SQLException;

	public boolean checkdate_weight(String check_date, String check_uesrname) throws SQLException;

	public void add_dailychange(weight_changeInfo weightinfo) throws SQLException;
}
