package com.java.weighttracker.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.weighttracker.dao.foodInfoDAO;
import com.java.weighttracker.model.foodInfo;
import com.java.weighttracker.model.storefoodInfo;

public class foodInfoDAOImp implements foodInfoDAO {

	@Override
	public void add_food(foodInfo add_food) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "insert into food value(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, add_food.getFood_id());
			ps.setString(2, add_food.getFood_name());
			ps.setString(3, add_food.getFood_caloried());
			ps.setString(4, add_food.getFood_date());
			ps.setString(5, add_food.getUsername());
			ps.setString(6, add_food.getFood_calorie());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public void delete_food(int delete_food) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from food where food_id='" + delete_food + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update_food(foodInfo update_foodinfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update food set food_name =?, food_gram=?, food_date =?,username=? where food_id =?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, update_foodinfo.getFood_name());
			ps.setString(2, update_foodinfo.getFood_caloried());
			ps.setString(3, update_foodinfo.getFood_date());
			ps.setString(4, update_foodinfo.getUsername());
			ps.setInt(5, update_foodinfo.getFood_id());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add_storefood(storefoodInfo add_storefood) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "insert into storefood value(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, add_storefood.getStorefood_id());
			ps.setString(2, add_storefood.getStorefood_name());
			ps.setString(3, add_storefood.getStorefood_calorie());
			ps.setString(4, add_storefood.getStorefood_username());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public void delete_storefood(int delete_storefood) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkstorefood_username(String storefood_username) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from storefood where foodvirtual_username ='" + storefood_username + "'";
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
	public boolean checkstorefood_foodname(String storefood_name) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from storefood where foodvirtual_name ='" + storefood_name + "'";
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
	public boolean checkdate_NameFood(String check_date, String check_uesrname) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from food where food_date ='" + check_date + "' And username ='" + check_uesrname
				+ "'";
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
