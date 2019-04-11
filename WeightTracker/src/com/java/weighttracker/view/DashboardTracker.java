package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import com.java.weighttracker.daoimp.DBConnection;

public class DashboardTracker extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_retriveimage = new JLabel(new ImageIcon("images/option.png"));
	String current_date = new SimpleDateFormat("hh:mm:ss a").format(new Date());
	private JButton btn_1, btn_2, btn_3, btn_4, btn_5, btn_6;
	Font f1 = new Font("Arial", Font.BOLD, 13);
	Font f2 = new Font("Arial", Font.BOLD, 23);
	Font f3 = new Font("Arial", Font.BOLD, 26);
	private JLabel lbl_logout = new JLabel("logout");
	private JLabel lbl_editprofile = new JLabel("Profile");
	private JLabel lbl_retrivename = new JLabel();
	private JPanel pan_1, pan_option;
	private static String getuserName = null;
	private static String getuserGender = null;
	private static String getuserRole = null;
	JButton lbl_titlepanelbutton = new JButton();

	public DashboardTracker(String userName, String userGender, String userrole) {
		getuserName = userName;
		getuserGender = userGender;
		getuserRole = userrole;
		this.createDashboard();
	}

	public void createDashboard() {
		try {
			this.setSize(1364, 716);
			this.setLayout(null);
			this.setResizable(false);
			Border thickBorder = new LineBorder(Color.ORANGE, 12);
			// basic setting
			JLabel lbl_day = new JLabel();
			lbl_day.setText(current_date);
			lbl_day.setBounds(515, 15, 300, 30);
			lbl_day.setForeground(Color.ORANGE);
			lbl_day.setFont(f3);
			lbl_retrivename.setText(getuserName);
			lbl_retriveimage.setBounds(1205, 15, 80, 30);
			lbl_retriveimage.addMouseListener(this);
			lbl_retrivename.setBounds(1170, 15, 100, 30);
			pan_option = new JPanel();
			pan_option.setLayout(null);
			pan_option.add(lbl_editprofile);
			pan_option.add(lbl_logout);
			pan_option.setBounds(1240, 45, 80, 60);
			pan_option.setBackground(Color.ORANGE);
			pan_option.setVisible(false);
			lbl_editprofile.setBounds(14, 5, 70, 20);
			lbl_logout.setBounds(14, 30, 70, 20);
			lbl_editprofile.setForeground(Color.decode("#488FCF"));
			lbl_logout.setForeground(Color.decode("#488FCF"));
			lbl_logout.setFont(f1);
			lbl_editprofile.setFont(f1);
			lbl_editprofile.addMouseListener(this);
			lbl_logout.addMouseListener(this);
			this.add(pan_option);
			// option
			btn_1 = new JButton("Add");
			btn_1.setForeground(Color.white);
			btn_1.setBounds(260, 100, 125, 105);
			btn_1.setBackground(Color.decode("#1A3A2D"));
			btn_1.setFont(f1);
			btn_1.setBorder(thickBorder);
			btn_2 = new JButton("UserDetails");
			btn_2.setForeground(Color.white);
			btn_2.setBounds(420, 100, 125, 105);
			btn_2.setBackground(Color.decode("#1A3A2D"));
			btn_2.setFont(f1);
			btn_2.setBorder(thickBorder);
			btn_3 = new JButton("DailyChange");
			btn_3.setForeground(Color.white);
			btn_3.setBounds(580, 100, 125, 105);
			btn_3.setBackground(Color.decode("#1A3A2D"));
			btn_3.setFont(f1);
			btn_3.setBorder(thickBorder);
			btn_4 = new JButton("All Activity");
			btn_4.setForeground(Color.white);
			btn_4.setBounds(740, 100, 125, 105);
			btn_4.setBackground(Color.decode("#1A3A2D"));
			btn_4.setFont(f1);
			btn_4.setBorder(thickBorder);
			btn_5 = new JButton("About");
			btn_5.setForeground(Color.white);
			btn_5.setBounds(900, 100, 125, 105);
			btn_5.setBackground(Color.decode("#1A3A2D"));
			btn_5.setFont(f1);
			btn_5.setBorder(thickBorder);
			btn_6 = new JButton("Generate Report");
			btn_6.setForeground(Color.white);
			btn_6.setBounds(445, 580, 500, 40);
			btn_6.setBackground(Color.orange);
			btn_6.setFont(f1);
			btn_6.addActionListener(this);
			JLabel lbl_titlepanel = new JLabel("Total User");
			JLabel lbl_titlepanelimg = new JLabel(new ImageIcon("images/run.png"));
			lbl_titlepanel.setBounds(50, 10, 180, 40);
			lbl_titlepanelimg.setBounds(50, 40, 100, 150);
			lbl_titlepanelbutton.setBounds(0, 200, 230, 60);
			lbl_titlepanel.setFont(f2);
			lbl_titlepanel.setForeground(Color.white);
			lbl_titlepanelbutton.setFont(f2);
			lbl_titlepanel.setForeground(Color.white);
			lbl_titlepanelbutton.setForeground(Color.black);
			lbl_titlepanelbutton.setBackground(Color.ORANGE);
			pan_1 = new JPanel();
			pan_1.setBounds(570, 260, 230, 250);
			pan_1.setBackground(Color.decode("#0D620F"));
			pan_1.setLayout(null);
			pan_1.add(lbl_titlepanel);
			pan_1.add(lbl_titlepanelimg);
			pan_1.add(lbl_titlepanelbutton);
			btn_1.addActionListener(this);
			btn_2.addActionListener(this);
			btn_2.addMouseListener(this);
			btn_3.addActionListener(this);
			btn_4.addActionListener(this);
			btn_5.addActionListener(this);
			this.add(lbl_retrivename);
			this.add(lbl_day);
			this.add(lbl_retriveimage);
			this.add(btn_1);
			this.add(btn_2);
			this.add(btn_3);
			this.add(btn_4);
			this.add(btn_5);
			this.add(btn_6);
			this.add(pan_1);
			fill_totaluser();
			this.getContentPane().setBackground(Color.decode("#483C32"));
			manageRole();
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error" + ex.toString());
		}
	}

	@Override
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(btn_1)) {
			AddDetails addtails = new AddDetails(this, getuserName, getuserGender);
		} else if (source.equals(btn_2)) {
			ViewallUser viewuser = new ViewallUser(this);
		} else if (source.equals(btn_3)) {
			viewDailychange change = new viewDailychange(this, getuserName);
		} else if (source.equals(btn_4)) {
			viewallActivity activity = new viewallActivity(this, getuserName);
		} else if (source.equals(btn_5)) {
			aboutAuthor about = new aboutAuthor(this);
		} else if (source.equals(btn_6)) {
			Report report = new Report(getuserName, this);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public void mouseClicked(MouseEvent event) {
		Object source = event.getSource();
		if (source.equals(lbl_logout)) {
			this.setVisible(false);
			dispose();
			Tracker.createAndShowGUI();
			pan_option.setVisible(false);
		} else if (source.equals(lbl_editprofile)) {
			EdituserDetails edituserdetails = new EdituserDetails(getuserName, getuserRole);
			pan_option.setVisible(false);
		} else if (source.equals(lbl_retriveimage)) {
			pan_option.setVisible(true);
		} else if (source.equals(btn_2)) {
			if (btn_2.isEnabled() == false) {
				JOptionPane.showMessageDialog(null, "You have no permission!!!");

			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// manage role system
	private void manageRole() {
		if (getuserRole.equals("1")) {
			pan_1.setVisible(true);
			btn_2.setEnabled(true);
		} else {
			pan_1.setVisible(false);
			btn_2.setEnabled(false);
		}

	}

	public void fill_totaluser() {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error" + e.toString());
		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT COUNT(user_id)  from user";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count = rs.getString("COUNT(user_id)");
				lbl_titlepanelbutton.setText(count);
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error" + ex.toString());
		}

	}

}
