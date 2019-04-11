package com.java.weighttracker.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.weighttracker.dao.exerciseDAO;
import com.java.weighttracker.model.exerciseInfo;

public class exerciseDAOImp implements exerciseDAO {

	@Override
	public void addExercise(exerciseInfo add_exercise) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into exercise value(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, add_exercise.getExercise_id());
			ps.setString(2, add_exercise.getExercie_name());
			ps.setString(3, add_exercise.getExercise_calorie());
			ps.setString(4, add_exercise.getExercise_date());
			ps.setString(5, add_exercise.getExercise_uername());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public void delete_Exercise(int id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from exercise where exercise_id='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update_Exercise(exerciseInfo exerciseInfo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update exercise set exercise_name =?, exercise_calories=?, exercise_date=?,exercise_username=? where exercise_id =?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, exerciseInfo.getExercie_name());
			ps.setString(2, exerciseInfo.getExercise_calorie());
			ps.setString(3, exerciseInfo.getExercise_date());
			ps.setString(4, exerciseInfo.getExercise_uername());
			ps.setInt(5, exerciseInfo.getExercise_id());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkdate_calorie(String check_date, String check_uesrname) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from exercise where exercise_date='" + check_date + "' And exercise_username ='"
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
