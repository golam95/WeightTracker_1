package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.java.weighttracker.daoimp.userInfoDAOImp;
import com.java.weighttracker.model.userInfo;
import com.java.weighttracker.util.SettingMenu;

public class AdduserDetails extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_save, btn_reset;
	private JTextField txt_Name, txt_Age;
	private JComboBox<String> cmb_gender, cmb_type;
	private String array_gender[] = { "Male", "Female" };
	private String array_type[] = { "User", "Admin" };
	private Font textsize = new Font("Arial", Font.BOLD, 14);
	private userInfoDAOImp userinfo = new userInfoDAOImp();
	private String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	private SettingMenu setting = new SettingMenu();

	public AdduserDetails(JFrame parent) {
		super(JOptionPane.getFrameForComponent(parent), "User Details", true);
		this.createDalog();
	}

	private void createDalog() {
		try {
			this.setSize(400, 270);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_Name = new JLabel("Name: ");
			JLabel lbl_Age = new JLabel("Age: ");
			JLabel lbl_Gender = new JLabel("Gender: ");
			JLabel lbl_type = new JLabel(" Select Type: ");
			txt_Name = new JTextField();
			txt_Age = new JTextField();
			cmb_gender = new JComboBox<String>();
			btn_save = new JButton("Save");
			btn_reset = new JButton("Cancel");
			for (int i = 0; i < array_gender.length; i++) {
				cmb_gender.addItem(array_gender[i]);
			}
			cmb_type = new JComboBox<String>();
			for (int i = 0; i < array_type.length; i++) {
				cmb_type.addItem(array_type[i]);
			}
			lbl_Name.setBounds(50, 20, 180, 30);
			lbl_Age.setBounds(50, 60, 180, 30);
			lbl_Gender.setBounds(50, 100, 180, 30);
			lbl_type.setBounds(50, 140, 180, 30);
			lbl_Name.setForeground(Color.black);
			lbl_Age.setForeground(Color.black);
			lbl_Gender.setForeground(Color.black);
			lbl_type.setForeground(Color.black);
			txt_Name.setBackground(Color.WHITE);
			txt_Age.setBackground(Color.WHITE);
			cmb_gender.setBackground(Color.WHITE);
			cmb_type.setBackground(Color.WHITE);
			txt_Name.setForeground(Color.black);
			txt_Age.setForeground(Color.black);
			cmb_gender.setForeground(Color.black);
			cmb_type.setForeground(Color.black);
			txt_Name.setBounds(130, 20, 210, 30);
			txt_Age.setBounds(130, 60, 210, 30);
			cmb_gender.setBounds(130, 100, 210, 30);
			cmb_type.setBounds(130, 140, 210, 30);
			btn_save.setBounds(130, 180, 100, 30);
			btn_reset.setBounds(240, 180, 100, 30);
			lbl_Name.setFont(textsize);
			lbl_Age.setFont(textsize);
			lbl_Gender.setFont(textsize);
			txt_Name.setFont(textsize);
			txt_Age.setFont(textsize);
			cmb_gender.setFont(textsize);
			btn_save.setFont(textsize);
			btn_reset.setFont(textsize);
			btn_save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					add_userDetails();
				}
			});
			btn_reset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			this.add(lbl_Name);
			this.add(lbl_Age);
			this.add(lbl_Gender);
			this.add(lbl_type);
			this.add(txt_Name);
			this.add(txt_Age);
			this.add(cmb_gender);
			this.add(cmb_type);
			this.add(btn_save);
			this.add(btn_reset);
			this.getContentPane().setBackground(Color.WHITE);
			setting.Numvalidator1(txt_Name);
			setting.Numvalidator(txt_Age);
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

	private void add_userDetails() {
		String userName = txt_Name.getText().toString();
		String userAge = txt_Age.getText().toString();
		String userGender = cmb_gender.getSelectedItem().toString();
		String usertype = cmb_type.getSelectedItem().toString();
		if (userName.equals("") || userAge.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!");
		} else {
			try {
				if (userinfo.checkUser(userName) == true) {
					JOptionPane.showMessageDialog(null, "Sorry userName is Exist");
				} else {
					if (usertype.equals("Admin")) {
						userinfo.addUser(
								new userInfo(0, userName, userGender, Integer.parseInt(userAge), current_date, "1"));
						clear();
						JOptionPane.showMessageDialog(null, "Please Enroll...");
					} else {
						userinfo.addUser(
								new userInfo(0, userName, userGender, Integer.parseInt(userAge), current_date, "0"));
						clear();
						JOptionPane.showMessageDialog(null, "Please Enroll...");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private void clear() {
		txt_Name.setText("");
		txt_Age.setText("");
	}

}
