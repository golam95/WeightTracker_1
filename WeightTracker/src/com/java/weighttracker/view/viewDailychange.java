package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.java.weighttracker.daoimp.DBConnection;

public class viewDailychange extends JDialog {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	Border thickBorder = new LineBorder(Color.white, 3);
	private JScrollPane user_pane = new JScrollPane();
	private JPanel pan_usertable, pan_header, pan_body;
	private JButton btn_cancel;
	private JList<String> list_dailyActivity = new JList<String>();
	Font font = new Font("Arial", Font.BOLD, 23);
	Font f = new Font("Arial", Font.BOLD, 15);
	private static String geruserName = null;

	public viewDailychange(JFrame parent, String username) {
		super(JOptionPane.getFrameForComponent(parent), "View DailyChange", true);
		geruserName = username;
		this.createviewDailychange();
	}

	public void createviewDailychange() {
		try {
			this.setSize(900, 370);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			this.setResizable(false);
			btn_cancel = new JButton("Exit");
			btn_cancel.setBounds(15, 60, 60, 60);
			btn_cancel.setBorder(thickBorder);
			pan_header = new JPanel();
			pan_header.setLayout(new GridLayout());
			pan_header.setBackground(Color.decode("#3B3147"));
			pan_header.setBounds(100, 0, 800, 50);
			pan_body = new JPanel();
			pan_body.setLayout(null);
			pan_body.setBackground(Color.decode("#483C32"));
			pan_body.setBounds(100, 50, 800, 370);
			pan_usertable = new JPanel();
			pan_usertable.setLayout(new GridLayout(0, 1));
			pan_usertable.setBounds(13, 60, 770, 220);
			JLabel lbl_Intake = new JLabel("Intake");
			lbl_Intake.setBounds(150, 70, 80, 30);
			JLabel lbl_Burn = new JLabel("Burn");
			lbl_Burn.setBounds(300, 70, 80, 30);
			JLabel lbl_Difference = new JLabel("Difference");
			lbl_Difference.setBounds(570, 70, 80, 30);
			JLabel lbl_date = new JLabel("Date");
			lbl_date.setBounds(770, 70, 80, 30);
			lbl_Intake.setFont(f);
			lbl_Burn.setFont(f);
			lbl_Difference.setFont(f);
			lbl_date.setFont(f);
			this.add(lbl_Intake);
			this.add(lbl_Burn);
			this.add(lbl_Difference);
			this.add(lbl_date);
			pan_usertable.setBorder(thickBorder);
			list_dailyActivity.setFont(f);
			user_pane.setViewportView(list_dailyActivity);
			pan_usertable.add(user_pane);
			pan_body.add(pan_usertable);
			this.add(pan_header);
			this.add(pan_body);
			this.add(btn_cancel);
			btn_cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}

			});
			fill_dailyActivity(geruserName);
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createUser" + ex.toString());
		}

	}

	public void fill_dailyActivity(String username) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM calorie_burn WHERE calorie_username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String str_1 = rs.getString(2).trim();
				String str_2 = rs.getString(3).trim();
				String str_3 = rs.getString(4).trim();
				String str_4 = rs.getString(5).trim();
				m.addElement("(" + str_1 + ")----------------------------------------------(" + str_2
						+ ")----------------------------------------------(" + str_3
						+ ")--------------------------------------------(" + str_4 + ")");
			}
			list_dailyActivity.setModel(m);
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createUser" + ex.toString());
		}

	}
}
