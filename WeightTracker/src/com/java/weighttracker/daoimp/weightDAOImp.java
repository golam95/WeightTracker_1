package com.java.weighttracker.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.weighttracker.dao.weightDAO;
import com.java.weighttracker.model.weightInfo;
import com.java.weighttracker.model.weight_changeInfo;

public class weightDAOImp implements weightDAO {

	@Override
	public void add_weight(weightInfo weightinfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into weight value(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, weightinfo.getWeight_id());
			ps.setString(2, weightinfo.getWeight_morning());
			ps.setString(3, weightinfo.getWeight_evening());
			ps.setString(4, weightinfo.getWeight_average());
			ps.setString(5, weightinfo.getWeight_date());
			ps.setString(6, weightinfo.getUsername());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public void delete_weight(int id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from weight where weight_id='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update_weight(weightInfo weightinfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update weight set weight_morning =?, weight_evening=?, weight_average=?,weight_date=?,username=?  where weight_id =?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, weightinfo.getWeight_morning());
			ps.setString(2, weightinfo.getWeight_evening());
			ps.setString(3, weightinfo.getWeight_average());
			ps.setString(4, weightinfo.getWeight_date());
			ps.setString(5, weightinfo.getUsername());
			ps.setInt(6, weightinfo.getWeight_id());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkdate_weight(String check_date, String check_uesrname) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from weight where weight_date ='" + check_date + "' And username   ='"
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

	@Override
	public void add_dailychange(weight_changeInfo changeInfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into daily_change value(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, changeInfo.getChange_id());
			ps.setString(2, changeInfo.getChange_amount());
			ps.setString(3, changeInfo.getChange_date());
			ps.setString(4, changeInfo.getChange_username());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

}
