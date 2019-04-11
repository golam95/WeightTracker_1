package com.java.weighttracker.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.weighttracker.dao.waistDAO;
import com.java.weighttracker.model.waistInfo;


public class waistDAOImp implements waistDAO {

	@Override
	public void add_waist(waistInfo waistinfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "insert into waist value(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, waistinfo.getWaist_id());
			ps.setString(2, waistinfo.getWaist_morning());
			ps.setString(3, waistinfo.getWaist_evening());
			ps.setString(4, waistinfo.getWaist_average());
			ps.setString(5, waistinfo.getWaist_date());
			ps.setString(6, waistinfo.getUsername());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public void delete_Waist(int waist_id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from waist where waist_id='" + waist_id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update_waist(waistInfo waistinfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update waist set waist_morning =?, waist_evening=?, waist_average=?,waist_date=?,username=? where waist_id =?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, waistinfo.getWaist_morning());
			ps.setString(2, waistinfo.getWaist_evening());
			ps.setString(3, waistinfo.getWaist_average());
			ps.setString(4, waistinfo.getWaist_date());
			ps.setString(5, waistinfo.getUsername());
			ps.setInt(6, waistinfo.getWaist_id());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkdate_waist(String check_date, String check_uesrname) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from waist where waist_date='" + check_date + "' And username  ='"
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
