package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.java.weighttracker.daoimp.DBConnection;
import com.java.weighttracker.daoimp.userInfoDAOImp;
import com.java.weighttracker.model.userInfo;

public class ViewallUser extends JDialog implements MouseListener, ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Border thickBorder = new LineBorder(Color.white, 3);
	private JScrollPane user_pane = new JScrollPane();
	private JPanel pan_usertable, pan_header, pan_body;
	private JButton btn_cancel, btn_delete;
	private JTextField txt_search, txt_name;
	private JTable tbl_userDetails = new JTable();
	Font font = new Font("Arial", Font.BOLD, 23);
	Font f = new Font("Arial", Font.BOLD, 15);
	private userInfoDAOImp userinfo = new userInfoDAOImp();

	public ViewallUser(JFrame parent) {
		super(JOptionPane.getFrameForComponent(parent), "User Details", true);
		this.createUser();
	}

	public void createUser() {
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
			pan_usertable.setBounds(1, 100, 795, 183);
			pan_usertable.setBorder(thickBorder);
			tbl_userDetails.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
					new String[] { "Id", "Name", "Gender", "Age", "Date", "Role" }));
			tbl_userDetails.addMouseListener(this);
			tbl_userDetails.setFont(f);
			user_pane.setViewportView(tbl_userDetails);
			pan_usertable.add(user_pane);
			txt_search = new JTextField();
			txt_search.setBounds(250, 50, 200, 30);
			txt_search.addKeyListener(this);
			txt_name = new JTextField();
			txt_name.setVisible(false);
			btn_delete = new JButton("Delete");
			btn_delete.setBounds(470, 50, 80, 30);
			btn_delete.addActionListener(this);
			txt_name.setBounds(50, 10, 20, 30);
			pan_body.add(txt_search);
			pan_body.add(btn_delete);
			pan_body.add(txt_name);
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
			Show_userInfo();
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createUser" + ex.toString());

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int j = tbl_userDetails.getSelectedRow();
		TableModel modelq = tbl_userDetails.getModel();
		txt_name.setText(modelq.getValueAt(j, 1).toString());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// Table code

	public ArrayList<userInfo> receiveorderinfo() {
		ArrayList<userInfo> usersList = new ArrayList<userInfo>();
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		String query = "SELECT * FROM  `user`";
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			userInfo user;
			while (rs.next()) {
				user = new userInfo(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_gender"),
						rs.getInt("user_age"), rs.getString("uset_date"), rs.getString("user_role"));
				usersList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}

	// Display Data In JTable

	public void Show_userInfo() {
		ArrayList<userInfo> list = receiveorderinfo();
		DefaultTableModel model = (DefaultTableModel) tbl_userDetails.getModel();
		Object[] row = new Object[6];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getUser_id();
			row[1] = list.get(i).getUser_Name();
			row[2] = list.get(i).getUser_Gener();
			row[3] = list.get(i).getUser_Age();
			row[4] = list.get(i).getUser_date();
			row[5] = list.get(i).getUser_role();
			model.addRow(row);
		}
	}

	public ArrayList<userInfo> List_wa_Order1(String search) {// warehouse
		ArrayList<userInfo> usersList = new ArrayList<userInfo>();
		Statement st;
		ResultSet rs;
		try {
			Connection con = null;
			try {
				con = DBConnection.getConnecttion();
			} catch (Exception e) {

			}
			st = con.createStatement();
			String searchQuery = "SELECT * FROM `user` WHERE CONCAT(`user_id`, `user_name`, `user_gender`, `user_age`,`uset_date`,`user_role`) LIKE '%"
					+ search + "%'";
			rs = st.executeQuery(searchQuery);
			userInfo user;
			while (rs.next()) {
				user = new userInfo(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_gender"),
						rs.getInt("user_age"), rs.getString("uset_date"), rs.getString("user_role"));
				usersList.add(user);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return usersList;
	}

	public void find_Allusers() {
		ArrayList<userInfo> list = List_wa_Order1(txt_search.getText());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] { "Id", "Name", "Gender", "Age", "Date", "Role" });
		Object[] row = new Object[6];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getUser_id();
			row[1] = list.get(i).getUser_Name();
			row[2] = list.get(i).getUser_Gener();
			row[3] = list.get(i).getUser_Age();
			row[4] = list.get(i).getUser_date();
			row[5] = list.get(i).getUser_role();
			model.addRow(row);
		}
		tbl_userDetails.setModel(model);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(btn_delete)) {
			String user_id = txt_name.getText();
			if (user_id.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Select Row!!!" + JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					userinfo.delete_User2(user_id);
					userinfo.delete_all_1(user_id);
					userinfo.delete_all_2(user_id);
					userinfo.delete_all_3(user_id);
					userinfo.delete_all_4(user_id);
					userinfo.delete_all_5(user_id);
					userinfo.delete_all_6(user_id);
					userinfo.delete_all_7(user_id);
					DefaultTableModel model = (DefaultTableModel) tbl_userDetails.getModel();
					model.setRowCount(0);
					Show_userInfo();
					txt_name.setText("");
					JOptionPane.showMessageDialog(null, "Remove Succefully!!!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error" + ex.toString());
				}

			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String searchByuserName = txt_search.getText();
		find_Allusers();

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
