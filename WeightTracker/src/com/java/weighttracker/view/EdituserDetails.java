package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.java.weighttracker.daoimp.DBConnection;
import com.java.weighttracker.daoimp.userInfoDAOImp;
import com.java.weighttracker.model.userInfo;

public class EdituserDetails extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_Id, txt_name, txt_age, txt_gender;
	private JButton btn_userInfoupdate, btn_userInfocancel;
	Font textsize_small = new Font("Arial", Font.BOLD, 14);
	private static String getuserName = null;
	private static String getuserRole = null;
	private userInfoDAOImp userinfo = new userInfoDAOImp();

	public EdituserDetails(String username, String userrole) {
		getuserName = username;
		getuserRole = userrole;
		this.createuserDetails();

	}

	public void createuserDetails() {
		try {
			this.setSize(500, 250);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setLayout(null);
			JLabel lbl_Id = new JLabel("Id");
			JLabel lbl_name = new JLabel("Name");
			JLabel lbl_age = new JLabel("Age");
			JLabel lbl_gender = new JLabel("Gender");
			txt_Id = new JTextField();
			txt_name = new JTextField();
			txt_age = new JTextField();
			txt_gender = new JTextField();
			btn_userInfoupdate = new JButton("Update");
			btn_userInfocancel = new JButton("Cancel");
			btn_userInfoupdate.setBackground(Color.decode("#1A3A2D"));
			btn_userInfocancel.setBackground(Color.decode("#1A3A2D"));
			btn_userInfoupdate.addActionListener(this);
			btn_userInfocancel.addActionListener(this);
			txt_Id.setEditable(false);
			txt_name.setEditable(false);
			lbl_Id.setBounds(20, 20, 100, 30);
			lbl_name.setBounds(20, 70, 100, 30);
			lbl_age.setBounds(20, 120, 100, 30);
			lbl_gender.setBounds(20, 170, 100, 30);
			txt_Id.setBounds(120, 20, 200, 30);
			txt_name.setBounds(120, 70, 200, 30);
			txt_age.setBounds(120, 120, 200, 30);
			txt_gender.setBounds(120, 170, 200, 30);
			btn_userInfoupdate.setBounds(360, 20, 110, 30);
			btn_userInfocancel.setBounds(360, 70, 110, 30);
			txt_Id.setFont(textsize_small);
			txt_name.setFont(textsize_small);
			txt_age.setFont(textsize_small);
			txt_gender.setFont(textsize_small);
			lbl_Id.setFont(textsize_small);
			lbl_name.setFont(textsize_small);
			lbl_age.setFont(textsize_small);
			lbl_gender.setFont(textsize_small);
			btn_userInfoupdate.setFont(textsize_small);
			btn_userInfocancel.setFont(textsize_small);
			getalluser_Info(getuserName);
			this.add(lbl_Id);
			this.add(txt_Id);
			this.add(lbl_name);
			this.add(txt_name);
			this.add(lbl_age);
			this.add(txt_age);
			this.add(lbl_gender);
			this.add(txt_gender);
			this.add(btn_userInfoupdate);
			this.add(btn_userInfocancel);
			this.getContentPane().setBackground(Color.decode("#483C32"));
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error editInformation" + ex.toString());

		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(btn_userInfoupdate)) {
			edituserInformation();
		} else if (source.equals(btn_userInfocancel)) {
			dispose();
		}

	}

	@SuppressWarnings("resource")
	public void getalluser_Info(String userame) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM user WHERE user_name='" + userame + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String userId = rs.getString(1).trim();
				String userName = rs.getString(2).trim();
				String userGender = rs.getString(3).trim();
				String userAge = rs.getString(4).trim();
				txt_Id.setText(userId);
				txt_name.setText(userName);
				txt_age.setText(userAge);
				txt_gender.setText(userGender);
				rs = null;

			}

			con.close();
		}

		catch (Exception ex) {

		}

	}

	public void edituserInformation() {
		String id = txt_Id.getText();
		String name = txt_name.getText().toString();
		String age = txt_age.getText();
		String gender = txt_gender.getText().toString();
		if (id.equals("") || name.equals("") || age.equals("") || gender.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!");
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String day = (dateFormat.format(date));
			try {
				userinfo.updateUser(
						new userInfo(Integer.parseInt(id), name, gender, Integer.parseInt(age), day, getuserRole));
				JOptionPane.showMessageDialog(null, "Update Succefully!!");
				resetall();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void resetall() {
		txt_Id.setText("");
		txt_name.setText("");
		txt_age.setText("");
		txt_gender.setText("");
	}

}
