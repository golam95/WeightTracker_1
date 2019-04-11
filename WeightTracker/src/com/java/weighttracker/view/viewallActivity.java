package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.java.weighttracker.daoimp.DBConnection;
import com.java.weighttracker.daoimp.calorieDAOImp;
import com.java.weighttracker.daoimp.exerciseDAOImp;
import com.java.weighttracker.daoimp.foodInfoDAOImp;
import com.java.weighttracker.daoimp.waistDAOImp;
import com.java.weighttracker.daoimp.weightDAOImp;
import com.java.weighttracker.model.exerciseInfo;
import com.java.weighttracker.model.foodInfo;
import com.java.weighttracker.model.waistInfo;
import com.java.weighttracker.model.weightInfo;

public class viewallActivity extends JDialog implements ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] str_array = { "Food", "Exercise", "Weight", "Waist" };
	private JScrollPane scroll_panel_list = new JScrollPane();
	private JLabel lbl_userview_1, lbl_userview_2, lbl_userview_3, lbl_userview_4, lbl_userview_5, lbl_userview_6;
	private JLabel lbl_selectday;
	private JComboBox<String> selectday;
	private JTextField txt_userview_1, txt_userview_2, txt_userview_3, txt_userview_4, txt_userview_5, txt_userview_6;
	// update button
	private JButton btn_userview_update, btn_weightview_update, btn_waistview_update, btn_exerciseview_update;
	// delete button
	private JButton btn_weightview_delete, btn_userview_delete, btn_waistview_delete, btn_exerciseview_delete;
	// close button
	private JButton btn_userview_close;
	// Declare JList
	private JList<String> list_selectdayview = new JList<String>();
	String food_1 = "Food Id", food_2 = "Food(gram)", food_3 = "Date", food_4 = "UserName", food_5 = "Food(cal)",
			food_6 = "Food Name";
	String ex_1 = "Exercise Id", ex_2 = "Exercise Name", ex_3 = "Exercise(Cal)", ex_4 = "Date", ex_5 = "UserName";
	String waist_1 = "Waist Id", waist_2 = "Waist Morning", waist_3 = "Waist Evening", waist_4 = "Waist Average",
			waist_5 = "Date", waist_6 = "UserName";
	String wei_1 = "Weight Id", wei_2 = "Weight Morning", wei_3 = "Weight Evening", wei_4 = "Weight Average",
			wei_5 = "Date", wei_6 = "UserName";
	private JPanel pan_left = new JPanel();
	private JButton btn_exit = new JButton("Exit");
	private static String getuserName = null;
	private waistDAOImp waistdio = new waistDAOImp();
	private weightDAOImp weightdio = new weightDAOImp();
	private foodInfoDAOImp fooddio = new foodInfoDAOImp();
	private exerciseDAOImp exercisedio = new exerciseDAOImp();
	private Border thickBorder = new LineBorder(Color.white, 3);

	public viewallActivity(JFrame parent, String username) {
		super(JOptionPane.getFrameForComponent(parent), "Generate Report", true);
		getuserName = username;
		this.createviewallActivity();
	}

	public void createviewallActivity() {
		try {
			this.setSize(1100, 550);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			lbl_selectday = new JLabel("Please Select");
			lbl_selectday.setBounds(300, 20, 150, 30);
			selectday = new JComboBox<String>();
			for (int i = 0; i < str_array.length; i++) {
				selectday.addItem(str_array[i]);
			}
			selectday.setBounds(430, 20, 350, 30);
			list_selectdayview.addListSelectionListener(this);
			scroll_panel_list.setViewportView(list_selectdayview);
			scroll_panel_list.setBounds(180, 120, 300, 350);

			lbl_userview_1 = new JLabel(food_1);
			lbl_userview_2 = new JLabel(food_2);
			lbl_userview_3 = new JLabel(food_3);
			lbl_userview_4 = new JLabel(food_4);
			lbl_userview_5 = new JLabel(food_5);
			lbl_userview_6 = new JLabel(food_6);
			//
			txt_userview_1 = new JTextField();
			txt_userview_2 = new JTextField();
			txt_userview_3 = new JTextField();
			txt_userview_4 = new JTextField();
			txt_userview_5 = new JTextField();
			txt_userview_6 = new JTextField();
			//
			lbl_userview_1.setBounds(560, 120, 100, 30);
			lbl_userview_2.setBounds(560, 170, 100, 30);
			lbl_userview_3.setBounds(560, 220, 100, 30);
			lbl_userview_4.setBounds(560, 270, 100, 30);
			lbl_userview_5.setBounds(560, 320, 100, 30);
			lbl_userview_6.setBounds(560, 370, 100, 30);
			//
			txt_userview_1.setBounds(700, 120, 280, 30);
			txt_userview_2.setBounds(700, 170, 280, 30);
			txt_userview_3.setBounds(700, 220, 280, 30);
			txt_userview_4.setBounds(700, 270, 280, 30);
			txt_userview_5.setBounds(700, 320, 280, 30);
			txt_userview_6.setBounds(700, 370, 280, 30);
			txt_userview_1.setEditable(false);
			txt_userview_4.setEditable(false);
			txt_userview_5.setEditable(false);
			txt_userview_6.setEditable(false);
			//
			btn_userview_update = new JButton("Update");
			btn_weightview_update = new JButton("Update");
			btn_waistview_update = new JButton("Update");
			btn_exerciseview_update = new JButton("Update");
			btn_weightview_delete = new JButton("Delete");
			btn_userview_delete = new JButton("Delete");
			btn_waistview_delete = new JButton("Delete");
			btn_exerciseview_delete = new JButton("Delete");
			btn_userview_close = new JButton("Close");
			btn_userview_update.setBounds(700, 437, 80, 30);
			btn_weightview_update.setBounds(700, 437, 80, 30);
			btn_waistview_update.setBounds(700, 437, 80, 30);
			btn_exerciseview_update.setBounds(700, 437, 80, 30);
			btn_weightview_delete.setBounds(800, 437, 80, 30);
			btn_userview_delete.setBounds(800, 437, 80, 30);
			btn_waistview_delete.setBounds(800, 437, 80, 30);
			btn_exerciseview_delete.setBounds(800, 437, 80, 30);
			btn_userview_close.setBounds(900, 437, 80, 30);
			selectday.addActionListener(this);
			pan_left.setBounds(0, 0, 130, 550);
			pan_left.setLayout(null);
			// pan_left.setBackground(Color.orange);

			btn_exit.setBounds(20, 20, 60, 60);
			btn_exit.setBorder(thickBorder);
			btn_exit.addActionListener(this);
			pan_left.add(btn_exit);

			btn_weightview_update.addActionListener(this);
			btn_weightview_delete.addActionListener(this);
			btn_waistview_update.addActionListener(this);
			btn_waistview_delete.addActionListener(this);
			btn_exerciseview_update.addActionListener(this);
			btn_exerciseview_delete.addActionListener(this);
			btn_userview_update.addActionListener(this);
			btn_userview_delete.addActionListener(this);
			btn_userview_close.addActionListener(this);
			this.add(pan_left);
			//
			this.add(lbl_userview_1);
			this.add(lbl_userview_2);
			this.add(lbl_userview_3);
			this.add(lbl_userview_4);
			this.add(lbl_userview_5);
			this.add(lbl_userview_6);
			this.add(txt_userview_1);
			this.add(txt_userview_2);
			this.add(txt_userview_3);
			this.add(txt_userview_4);
			this.add(txt_userview_5);
			this.add(txt_userview_6);
			// button
			this.add(btn_weightview_update);
			this.add(btn_weightview_delete);
			this.add(btn_waistview_update);
			this.add(btn_waistview_delete);
			this.add(btn_exerciseview_update);
			this.add(btn_exerciseview_delete);
			this.add(btn_userview_update);
			this.add(btn_userview_delete);
			this.add(btn_userview_close);
			// button
			this.add(scroll_panel_list);
			this.add(lbl_selectday);
			this.add(selectday);
			this.getContentPane().setBackground(Color.decode("#483C32"));
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createActivity" + ex.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(selectday)) {
			if (selectday.getSelectedItem().equals("Food") == true) {
				active_buttonFood();
				addtitle_activity1();
				clear_allupdate();
				String query = "SELECT * FROM food WHERE username='" + getuserName + "'";
				fill_listFood(query);
			} else if (selectday.getSelectedItem().equals("Exercise") == true) {
				addtitle_activity2();
				active_buttonExercise();
				clear_allupdate();
				String query = "SELECT * FROM exercise WHERE exercise_username='" + getuserName + "'";
				fill_listFood(query);
			} else if (selectday.getSelectedItem().equals("Weight") == true) {
				addtitle_activity4();
				active_buttonWeight();
				clear_allupdate();
				String query = "SELECT * FROM weight WHERE username='" + getuserName + "'";
				fill_listphysical(query);
			} else if (selectday.getSelectedItem().equals("Waist") == true) {
				addtitle_activity3();
				active_buttonWaist();
				clear_allupdate();
				String query = "SELECT * FROM waist WHERE username='" + getuserName + "'";
				fill_listphysical(query);
			}

		} else if (source.equals(btn_weightview_update)) {
			weight_update();
		} else if (source.equals(btn_weightview_delete)) {
			weight_delete();
		} else if (source.equals(btn_waistview_update)) {
			waist_update();
		} else if (source.equals(btn_waistview_delete)) {
			waist_delete();
		} else if (source.equals(btn_exerciseview_update)) {
			exercise_update();
		} else if (source.equals(btn_exerciseview_delete)) {
			exercise_delete();
		} else if (source.equals(btn_userview_update)) {
			userupdate_view();
		} else if (source.equals(btn_userview_delete)) {
			delete_userview();
		} else if (source.equals(btn_userview_close)) {
			dispose();
		} else if (source.equals(btn_exit)) {
			dispose();
		}

	}

	public void addtitle_activity1() {
		lbl_userview_1.setText(food_1);
		lbl_userview_2.setText(food_6);
		lbl_userview_3.setText(food_2);
		lbl_userview_4.setText(food_3);
		lbl_userview_5.setText(food_4);
		lbl_userview_6.setText(food_5);
	}

	public void addtitle_activity2() {
		lbl_userview_1.setText(ex_1);
		lbl_userview_2.setText(ex_2);
		lbl_userview_3.setText(ex_3);
		lbl_userview_4.setText(ex_4);
		lbl_userview_5.setText(ex_5);
	}

	public void addtitle_activity3() {
		lbl_userview_1.setText(waist_1);
		lbl_userview_2.setText(waist_2);
		lbl_userview_3.setText(waist_3);
		lbl_userview_4.setText(waist_4);
		lbl_userview_5.setText(waist_5);
		lbl_userview_6.setText(waist_6);
	}

	public void addtitle_activity4() {
		lbl_userview_1.setText(wei_1);
		lbl_userview_2.setText(wei_2);
		lbl_userview_3.setText(wei_3);
		lbl_userview_4.setText(wei_4);
		lbl_userview_5.setText(wei_5);
		lbl_userview_6.setText(wei_6);
	}

	public void clear_allupdate() {
		txt_userview_1.setText("");
		txt_userview_2.setText("");
		txt_userview_3.setText("");
		txt_userview_4.setText("");
		txt_userview_5.setText("");
		txt_userview_6.setText("");
	}

	public void active_buttonFood() {
		btn_userview_update.setVisible(true);
		btn_userview_delete.setVisible(true);
		btn_weightview_update.setVisible(false);
		btn_weightview_delete.setVisible(false);
		btn_waistview_update.setVisible(false);
		btn_waistview_delete.setVisible(false);
		btn_exerciseview_update.setVisible(false);
		btn_exerciseview_delete.setVisible(false);
		txt_userview_6.setVisible(true);
		lbl_userview_6.setVisible(true);
	}

	public void active_buttonExercise() {
		btn_exerciseview_update.setVisible(true);
		btn_exerciseview_delete.setVisible(true);
		btn_userview_update.setVisible(false);
		btn_userview_delete.setVisible(false);
		btn_weightview_update.setVisible(false);
		btn_weightview_delete.setVisible(false);
		btn_waistview_update.setVisible(false);
		btn_waistview_delete.setVisible(false);
		txt_userview_6.setVisible(false);
		lbl_userview_6.setVisible(false);
	}

	public void active_buttonWeight() {
		btn_weightview_update.setVisible(true);
		btn_weightview_delete.setVisible(true);
		btn_exerciseview_update.setVisible(false);
		btn_exerciseview_delete.setVisible(false);
		btn_userview_update.setVisible(false);
		btn_userview_delete.setVisible(false);
		btn_waistview_update.setVisible(false);
		btn_waistview_delete.setVisible(false);
		txt_userview_6.setVisible(true);
		lbl_userview_6.setVisible(true);
	}

	public void active_buttonWaist() {
		btn_waistview_update.setVisible(true);
		btn_waistview_delete.setVisible(true);
		btn_exerciseview_update.setVisible(false);
		btn_exerciseview_delete.setVisible(false);
		btn_userview_update.setVisible(false);
		btn_userview_delete.setVisible(false);
		btn_weightview_update.setVisible(false);
		btn_weightview_delete.setVisible(false);
		txt_userview_6.setVisible(true);
		lbl_userview_6.setVisible(true);
	}

	public void fill_listphysical(String str) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = str;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString(5).trim();
				m.addElement(date);
			}
			list_selectdayview.setModel(m);
			con.close();
		} catch (Exception ex) {

		}

	}

	public void fill_listFood(String str) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = str;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString(4).trim();
				m.addElement(date);
			}
			list_selectdayview.setModel(m);
			rs.close();
			con.close();
		} catch (Exception ex) {

		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Food") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from food where food_date='" + goahead1 + "' And username='" + getuserName + "'";
			fill_Food(query);
		}
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Weight") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from weight where weight_date='" + goahead1 + "' And username='" + getuserName
					+ "'";
			fill_Food(query);
		}
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Waist") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from waist where waist_date='" + goahead1 + "' And username='" + getuserName + "'";
			fill_Food(query);
		}
		if (e.getSource() == list_selectdayview && selectday.getSelectedItem().equals("Exercise") == true) {
			String goahead1 = list_selectdayview.getSelectedValue().toString();
			String query = "select * from exercise where exercise_date='" + goahead1 + "' And exercise_username='"
					+ getuserName + "'";
			fill_listExercise(query);
		}

	}

	public void fill_Food(String getquery) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = getquery;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id_1 = rs.getString(1).trim();
				String id_2 = rs.getString(2).trim();
				String id_3 = rs.getString(3).trim();
				String id_4 = rs.getString(4).trim();
				String id_5 = rs.getString(5).trim();
				String id_6 = rs.getString(6).trim();
				txt_userview_1.setText(id_1);
				txt_userview_2.setText(id_2);
				txt_userview_3.setText(id_3);
				txt_userview_4.setText(id_4);
				txt_userview_5.setText(id_5);
				txt_userview_6.setText(id_6);
			}

			con.close();
		}

		catch (Exception ex) {

		}

	}

	public void fill_listExercise(String getquery) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = getquery;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id_1 = rs.getString(1).trim();
				String id_2 = rs.getString(2).trim();
				String id_3 = rs.getString(3).trim();
				String id_4 = rs.getString(4).trim();
				String id_5 = rs.getString(5).trim();
				txt_userview_1.setText(id_1);
				txt_userview_2.setText(id_2);
				txt_userview_3.setText(id_3);
				txt_userview_4.setText(id_4);
				txt_userview_5.setText(id_5);

			}

			con.close();
		}

		catch (Exception ex) {

		}

	}

	private void weight_update() {
		String str_1 = txt_userview_1.getText();
		String str_2 = txt_userview_2.getText();
		String str_3 = txt_userview_3.getText();
		String str_4 = txt_userview_4.getText();
		String str_5 = txt_userview_5.getText();
		String str_6 = txt_userview_6.getText();
		if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")
				|| str_6.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				weightdio.update_weight(new weightInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5, str_6));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	private void weight_delete() {
		String str_1 = txt_userview_1.getText();
		if (str_1.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				weightdio.delete_weight(Integer.parseInt(str_1));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void waist_update() {
		String str_1 = txt_userview_1.getText();
		String str_2 = txt_userview_2.getText();
		String str_3 = txt_userview_3.getText();
		String str_4 = txt_userview_4.getText();
		String str_5 = txt_userview_5.getText();
		String str_6 = txt_userview_6.getText();
		if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")
				|| str_6.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				waistdio.update_waist(new waistInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5, str_6));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private void waist_delete() {
		String str_1 = txt_userview_1.getText();
		if (str_1.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				waistdio.delete_Waist(Integer.parseInt(str_1));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	private void exercise_update() {
		String str_1 = txt_userview_1.getText();
		String str_2 = txt_userview_2.getText();
		String str_3 = txt_userview_3.getText();
		String str_4 = txt_userview_4.getText();
		String str_5 = txt_userview_5.getText();
		if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				exercisedio.update_Exercise(new exerciseInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5));

				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private void exercise_delete() {
		String str_1 = txt_userview_1.getText();
		if (str_1.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				exercisedio.delete_Exercise(Integer.parseInt(str_1));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void userupdate_view() {
		String str_1 = txt_userview_1.getText();
		String str_2 = txt_userview_2.getText();
		String str_3 = txt_userview_3.getText();
		String str_4 = txt_userview_4.getText();
		String str_5 = txt_userview_5.getText();
		String str_6 = txt_userview_6.getText();
		if (str_1.equals("") || str_2.equals("") || str_3.equals("") || str_4.equals("") || str_5.equals("")
				|| str_6.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				fooddio.update_food(new foodInfo(Integer.parseInt(str_1), str_2, str_3, str_4, str_5, str_6));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Update is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void delete_userview() {
		String str_1 = txt_userview_1.getText();
		if (str_1.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			try {
				fooddio.delete_food(Integer.parseInt(str_1));
				clear_allupdate();
				JOptionPane.showMessageDialog(null, "Delete is successfully done!!!");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
