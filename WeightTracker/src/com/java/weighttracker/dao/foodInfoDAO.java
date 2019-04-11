package com.java.weighttracker.dao;

import java.sql.SQLException;

import com.java.weighttracker.model.foodInfo;
import com.java.weighttracker.model.storefoodInfo;

public interface foodInfoDAO {

	public void add_food(foodInfo add_food) throws SQLException;

	public void delete_food(int delete_food) throws SQLException;

	public void update_food(foodInfo update_foodinfo) throws SQLException;

	public boolean checkdate_NameFood(String check_date, String check_uesrname) throws SQLException;

	// storefood info information gather

	public void add_storefood(storefoodInfo add_storefood) throws SQLException;

	public void delete_storefood(int delete_storefood) throws SQLException;

	public boolean checkstorefood_username(String storefood_username) throws SQLException;

	public boolean checkstorefood_foodname(String storefood_name) throws SQLException;

}
