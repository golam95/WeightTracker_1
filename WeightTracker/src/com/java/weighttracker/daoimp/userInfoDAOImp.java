package com.java.weighttracker.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.weighttracker.dao.userInfoDAO;
import com.java.weighttracker.model.userInfo;

public class userInfoDAOImp implements userInfoDAO {

	private static final String UPDATE = "Update user set user_name=?,user_gender=?,user_age=?,uset_date=?,user_role=? where user_id=?";

	@Override
	public void addUser(userInfo user) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "insert into user value(?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, user.getUser_id());
			ps.setString(2, user.getUser_Name());
			ps.setString(3, user.getUser_Gener());
			ps.setInt(4, user.getUser_Age());
			ps.setString(5, user.getUser_date());
			ps.setString(6, user.getUser_role());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	@Override
	public void updateUser(userInfo user) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = UPDATE;
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, user.getUser_Name());
			ps.setString(2, user.getUser_Gener());
			ps.setInt(3, user.getUser_Age());
			ps.setString(4, user.getUser_date());
			ps.setString(5, user.getUser_role());
			ps.setInt(6, user.getUser_id());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkUser(String username) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from user where user_name='" + username + "'";
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
	public void delete_User2(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from user where user_name='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_1(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from calorie_burn where calorie_username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_2(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from daily_change where change_username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_3(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from exercise where exercise_username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_4(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from  food where username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_5(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from storefood where foodvirtual_username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_6(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from waist where username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_all_7(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from weight where username='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
