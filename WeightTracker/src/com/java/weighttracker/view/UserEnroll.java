package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.java.weighttracker.daoimp.DBConnection;
import com.java.weighttracker.daoimp.userInfoDAOImp;

public class UserEnroll extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_ok;
	private JTextField txt_name;
	private userInfoDAOImp userinfo = new userInfoDAOImp();
	private static String get_gender = null;
	private static String get_userrole = null;

	public UserEnroll(JFrame parent) {
		super(JOptionPane.getFrameForComponent(parent), "User Details", true);
		this.createEnrollpanel();
	}

	public void createEnrollpanel() {
		try {
			this.setSize(400, 200);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_name = new JLabel("Enter Name");
			txt_name = new JTextField();
			btn_ok = new JButton("Ok");
			lbl_name.setBounds(50, 60, 180, 30);
			txt_name.setBounds(130, 60, 210, 30);
			btn_ok.setBounds(130, 110, 100, 30);
			btn_ok.setForeground(Color.white);
			btn_ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					loginManagement();
				}

			});
			this.add(lbl_name);
			this.add(txt_name);
			this.add(btn_ok);
			this.getContentPane().setBackground(Color.decode("#483C32"));
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error Occure Enrollpane" + ex.toString());

		}
	}

	private void loginManagement() {

		String input = txt_name.getText().toString();
		if (!input.equals("")) {
			try {
				if (userinfo.checkUser(input) == true) {
					Connection con = null;
					try {
						con = DBConnection.getConnecttion();
					} catch (Exception e) {

					}
					try {
						Statement stmt = con.createStatement();
						String query = "select * from user where user_name ='" + input + "'";
						ResultSet rs = stmt.executeQuery(query);
						while (rs.next()) {
							get_gender = rs.getString(3).trim();
							get_userrole = rs.getString(6).trim();
						}
						rs = null;
						con.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Welcome " + input);
					dispose();
					txt_name.setText("");
					DashboardTracker dashboard = new DashboardTracker(input, get_gender, get_userrole);
					Tracker.disposeall();

				} else {
					JOptionPane.showMessageDialog(null, "Sorry Not Match  " + input + "\n" + "Try Again..,");
					txt_name.setText("");
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Sorry Field MustNot Empty!!!");
		}

	}

}