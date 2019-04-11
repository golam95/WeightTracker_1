package com.java.weighttracker.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.weighttracker.dao.calorieDAO;
import com.java.weighttracker.model.calorieburnInfo;


public class calorieDAOImp implements calorieDAO {

	@Override
	public void add_calorie(calorieburnInfo add_calorie) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into calorie_burn value(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, add_calorie.getCalorie_id());
			ps.setString(2, add_calorie.getCalorie_intake());
			ps.setString(3, add_calorie.getCalorie_burn());
			ps.setString(4, add_calorie.getCalorie_difference());
			ps.setString(5, add_calorie.getCalorie_date());
			ps.setString(6, add_calorie.getCalorie_username());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public void delete_calorie(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkdate_calorie(String check_date, String check_uesrname) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from calorie_burn where calorie_date='" + check_date + "' And calorie_username='"
				+ check_uesrname + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(check_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return false;
	}

}
