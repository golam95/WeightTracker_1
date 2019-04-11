package com.java.weighttracker.dao;

import java.sql.SQLException;

import com.java.weighttracker.model.userInfo;

public interface userInfoDAO {

	public void addUser(userInfo u) throws SQLException;

	public void delete_User2(String id) throws SQLException;

	public void updateUser(userInfo u) throws SQLException;

	public boolean checkUser(String username) throws SQLException;

	public void delete_all_1(String id) throws SQLException;

	public void delete_all_2(String id) throws SQLException;

	public void delete_all_3(String id) throws SQLException;

	public void delete_all_4(String id) throws SQLException;

	public void delete_all_5(String id) throws SQLException;

	public void delete_all_6(String id) throws SQLException;

	public void delete_all_7(String id) throws SQLException;

}
