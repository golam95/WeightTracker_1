package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import com.java.weighttracker.daoimp.DBConnection;
import com.java.weighttracker.daoimp.calorieDAOImp;
import com.java.weighttracker.daoimp.exerciseDAOImp;
import com.java.weighttracker.daoimp.foodInfoDAOImp;
import com.java.weighttracker.daoimp.waistDAOImp;
import com.java.weighttracker.daoimp.weightDAOImp;
import com.java.weighttracker.model.calorieburnInfo;
import com.java.weighttracker.model.exerciseInfo;
import com.java.weighttracker.model.foodInfo;
import com.java.weighttracker.model.storefoodInfo;
import com.java.weighttracker.model.waistInfo;
import com.java.weighttracker.model.weightInfo;
import com.java.weighttracker.model.weight_changeInfo;
import com.toedter.calendar.JDateChooser;

public class AddDetails extends JDialog implements ActionListener, KeyListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private JButton btn_food, btn_exercise, btn_weight, btn_waist;
	private JPanel pan_food, pan_exercise, pan_weight, pan_waist, pan_header;
	private Border thickBorder = new LineBorder(Color.white, 3);
	private String[] user_Exercise = { "Runnig", "Push Up", "Bicycling", "Cooking", "Dancing", "Jumping", "Basketball",
			"Slow Walk", "swimming", "Jogging" };
	private Font textsize = new Font("Arial", Font.BOLD, 14);
	// Food Details
	private JTextField txt_addfooddetails, txt_addfoodecal, txt_mealName, txt_calories, txt_storevaluewithcalorie,
			txt_retrive_Exercise;
	private JButton btn_addfood, btn_mealsave;
	private JComboBox<String> select_Activity, cmb_selectmealtype;
	// Exercise details
	private JTextField txt_exercisevalue_1, txt_totalexercise_minutes, txt_retrive_Food, txt_totalexercise_Name;
	private JButton btn_mealsave_1, btn_addexercise;
	private JComboBox<String> txt_exercisename_1;
	// weight details
	private JTextField txt_morningweight, txt_eveningweight, txt_averageweight, txt_retrive_weightvalue,
			txt_weightAverage, everyday_Activity;
	private JButton btn_averageweight, btn_saveweight;
	// waist details
	private JTextField txt_morningwaist, txt_eveningwaist, txt_averagewaist, txt_retrive_waistvalue, txt_waistAverage;
	private JButton btn_averagewaist, btn_savewaist;
	//
	private waistDAOImp waistdio = new waistDAOImp();
	private weightDAOImp weightdio = new weightDAOImp();
	private foodInfoDAOImp fooddio = new foodInfoDAOImp();
	private calorieDAOImp caloriedio = new calorieDAOImp();
	private exerciseDAOImp exercisedio = new exerciseDAOImp();
	private SimpleDateFormat f3 = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat f4 = new SimpleDateFormat("-MM-yyyy");
	private String str_activity[] = { "Active", "InActive", "Moderately Active" };
	private String[] user_Mealtype = { "Fat", "Alcohol", "Protein", "Carbohydrate" };
	private JDateChooser date_chooser = new JDateChooser();
	private static String getuserName = null;
	private static String getuserGender = null;
	private static String average_weight = null;

	public AddDetails(JFrame parent, String username, String usergender) {
		super(JOptionPane.getFrameForComponent(parent), "User MealDetails", true);
		getuserName = username;
		getuserGender = usergender;
		this.ceateAddtails();
	}

	public void ceateAddtails() {
		try {
			this.setSize(900, 370);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			btn_food = new JButton("Food");
			btn_food.setBounds(20, 20, 60, 60);
			btn_food.setBorder(thickBorder);
			btn_exercise = new JButton("Exercise");
			btn_exercise.setBounds(20, 100, 60, 60);
			btn_exercise.setBorder(thickBorder);
			btn_weight = new JButton("Weight");
			btn_weight.setBounds(20, 180, 60, 60);
			btn_weight.setBorder(thickBorder);
			btn_waist = new JButton("Waist");
			btn_waist.setBounds(20, 260, 60, 60);
			btn_waist.setBorder(thickBorder);
			pan_header = new JPanel();
			pan_header.setLayout(null);
			pan_header.setBackground(Color.decode("#3B3147"));
			pan_header.setBounds(100, 0, 800, 50);
			JLabel lbl_date = new JLabel("Enter Date");
			lbl_date.setBounds(200, 5, 100, 30);
			date_chooser.setBounds(280, 5, 130, 30);
			date_chooser.setBackground(Color.white);
			select_Activity = new JComboBox<String>();
			select_Activity.setBounds(425, 5, 180, 30);
			for (int i = 0; i < str_activity.length; i++) {
				select_Activity.addItem(str_activity[i]);
			}
			//
			select_Activity.setVisible(false);
			pan_header.add(select_Activity);
			pan_header.add(lbl_date);
			pan_header.add(date_chooser);
			pan_food = new JPanel();
			pan_food.setLayout(null);
			pan_food.setBackground(Color.decode("#483C32"));
			pan_food.setBounds(100, 50, 800, 370);
			// Exercise
			pan_exercise = new JPanel();
			pan_exercise.setLayout(null);
			pan_exercise.setBackground(Color.decode("#483C32"));
			pan_exercise.setBounds(100, 50, 800, 370);
			// Weight
			pan_weight = new JPanel();
			pan_weight.setLayout(null);
			pan_weight.setBackground(Color.decode("#483C32"));
			pan_weight.setBounds(100, 50, 800, 370);
			//
			pan_waist = new JPanel();
			pan_waist.setLayout(null);
			pan_waist.setBackground(Color.decode("#483C32"));
			pan_waist.setBounds(100, 50, 800, 370);
			//
			JLabel lbl_mealName = new JLabel("Meal Name");
			JLabel lbl_calories = new JLabel("Amount(cal)");
			JLabel lbl_totalfood = new JLabel("FoodName");
			JLabel lbl_totalfoodkacal = new JLabel("Total(gram)");
			JLabel lbl_storevaluewithcalorie = new JLabel("Total(cal)");
			JLabel lbl_selectmealtype = new JLabel("Food Type");
			txt_addfooddetails = new JTextField();
			txt_addfoodecal = new JTextField("0");
			btn_addfood = new JButton("Add");
			btn_mealsave = new JButton("save");
			txt_mealName = new JTextField();
			txt_calories = new JTextField();
			txt_storevaluewithcalorie = new JTextField("0");
			txt_retrive_Exercise = new JTextField();
			cmb_selectmealtype = new JComboBox<String>();
			for (int j = 0; j < user_Mealtype.length; j++) {
				cmb_selectmealtype.addItem(user_Mealtype[j]);
			}
			txt_mealName.addKeyListener(this);
			txt_retrive_Exercise.setBounds(170, 135, 150, 30);
			btn_mealsave.setBounds(340, 250, 180, 30);
			lbl_storevaluewithcalorie.setBounds(400, 70, 150, 30);
			txt_storevaluewithcalorie.setBounds(520, 70, 200, 30);
			btn_addfood.setBounds(340, 135, 180, 30);
			txt_mealName.setBounds(150, 20, 200, 30);
			txt_calories.setBounds(150, 70, 200, 30);
			lbl_totalfood.setBounds(20, 190, 140, 30);
			lbl_totalfoodkacal.setBounds(400, 190, 140, 30);
			txt_addfooddetails.setBounds(150, 190, 200, 30);
			txt_addfoodecal.setBounds(520, 190, 200, 30);
			lbl_selectmealtype.setBounds(400, 20, 100, 30);
			cmb_selectmealtype.setBounds(520, 20, 200, 30);
			lbl_mealName.setBounds(20, 20, 100, 30);
			lbl_calories.setBounds(20, 70, 100, 30);
			btn_addfood.addActionListener(this);
			btn_mealsave.addActionListener(this);
			
			
			txt_retrive_Exercise.setVisible(false);
			txt_storevaluewithcalorie.setEditable(false);
			txt_addfoodecal.setEditable(false);
			pan_food.add(lbl_mealName);
			pan_food.add(lbl_calories);
			pan_food.add(txt_mealName);
			pan_food.add(txt_calories);
			pan_food.add(txt_addfooddetails);
			pan_food.add(txt_addfoodecal);
			pan_food.add(btn_addfood);
			pan_food.add(lbl_totalfood);
			pan_food.add(lbl_totalfoodkacal);
			pan_food.add(btn_mealsave);
			pan_food.add(txt_storevaluewithcalorie);
			pan_food.add(lbl_selectmealtype);
			pan_food.add(lbl_storevaluewithcalorie);
			pan_food.add(cmb_selectmealtype);
			pan_food.add(txt_retrive_Exercise);
			// Exercisepanel

			// ActionListener
			btn_food.addActionListener(this);
			btn_exercise.addActionListener(this);
			btn_weight.addActionListener(this);
			btn_waist.addActionListener(this);
			pan_exercise.setVisible(false);
			pan_weight.setVisible(false);
			pan_waist.setVisible(false);
			// ActionListener
			exercise_details(pan_exercise);
			weight_details(pan_weight);
			waist_details(pan_waist);
			this.add(pan_waist);
			this.add(pan_weight);
			this.add(pan_exercise);
			this.add(pan_food);
			this.add(pan_header);
			this.add(btn_food);
			this.add(btn_exercise);
			this.add(btn_weight);
			this.add(btn_waist);
			this.setVisible(true);
		} catch (Exception ex1) {
			JOptionPane.showMessageDialog(null, "Erorr AddDetails" + ex1.toString());
		}
	}

	private void exercise_details(JPanel panelname) {
		try {
			JLabel lbl_exercisename_1 = new JLabel("Exercise Name");
			JLabel lbl_exercisevalue_1 = new JLabel("Exercise(mints)");
			JLabel lbl_totalexercise_Name = new JLabel("Exercise Name");
			JLabel lbl_totalexercise_minutes = new JLabel("Exercise(cal)");
			txt_retrive_Food = new JTextField();
			txt_exercisename_1 = new JComboBox<String>();
			txt_exercisevalue_1 = new JTextField();
			txt_exercisevalue_1.addKeyListener(this);
			btn_addexercise = new JButton("Add");
			btn_mealsave_1 = new JButton("Save");
			btn_addexercise.setBackground(Color.decode("#1A3A2D"));
			btn_addexercise.setForeground(Color.white);
			txt_totalexercise_Name = new JTextField();
			txt_totalexercise_minutes = new JTextField("0");
			txt_totalexercise_minutes.setEditable(false);
			txt_retrive_Food.setVisible(false);
			txt_retrive_Food.setBounds(570, 135, 150, 30);
			for (int i = 0; i < user_Exercise.length; i++) {
				txt_exercisename_1.addItem(user_Exercise[i]);
			}
			lbl_exercisename_1.setBounds(20, 20, 140, 30);
			txt_exercisename_1.setBounds(150, 20, 200, 30);
			lbl_exercisevalue_1.setBounds(400, 20, 200, 30);
			txt_exercisevalue_1.setBounds(520, 20, 200, 30);
			lbl_totalexercise_Name.setBounds(20, 190, 140, 30);
			txt_totalexercise_Name.setBounds(150, 190, 200, 30);
			lbl_totalexercise_minutes.setBounds(400, 190, 140, 30);
			txt_totalexercise_minutes.setBounds(520, 190, 200, 30);
			btn_addexercise.setBounds(340, 135, 180, 30);
			btn_mealsave_1.setBounds(340, 250, 180, 30);
			btn_mealsave_1.setBackground(Color.decode("#1A3A2D"));
			btn_mealsave_1.setFont(textsize);
			lbl_exercisename_1.setFont(textsize);
			lbl_exercisevalue_1.setFont(textsize);
			txt_exercisename_1.setFont(textsize);
			txt_exercisevalue_1.setFont(textsize);
			//
			btn_mealsave_1.setBackground(Color.decode("#1A3A2D"));
			btn_mealsave_1.setForeground(Color.white);
			lbl_totalexercise_Name.setFont(textsize);
			txt_totalexercise_Name.setFont(textsize);
			lbl_totalexercise_minutes.setFont(textsize);
			txt_totalexercise_minutes.setFont(textsize);
			btn_mealsave_1.setFont(textsize);
			btn_addexercise.setFont(textsize);

			btn_addexercise.addActionListener(this);
			btn_mealsave_1.addActionListener(this);
			pan_exercise.add(lbl_exercisename_1);
			pan_exercise.add(lbl_exercisevalue_1);
			pan_exercise.add(txt_exercisename_1);
			pan_exercise.add(txt_exercisevalue_1);
			pan_exercise.add(btn_mealsave_1);
			pan_exercise.add(txt_totalexercise_Name);
			pan_exercise.add(lbl_totalexercise_minutes);
			pan_exercise.add(lbl_totalexercise_Name);
			pan_exercise.add(txt_totalexercise_minutes);
			pan_exercise.add(btn_addexercise);
			pan_exercise.add(txt_retrive_Food);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error exerciseDetails" + ex.toString());
		}

	}

	private void weight_details(JPanel panelname) {
		try {
			JLabel lbl_morningweight = new JLabel("Weight(Morning)");
			JLabel lbl_eveningweight = new JLabel("Weight(Evening)");
			JLabel lbl_averageweight = new JLabel("Average");
			JLabel lbl_kg1 = new JLabel("Kg");
			JLabel lbl_kg2 = new JLabel("Kg");
			JLabel lbl_kg3 = new JLabel("Kg");
			txt_morningweight = new JTextField();
			txt_eveningweight = new JTextField();
			txt_averageweight = new JTextField();
			txt_retrive_weightvalue = new JTextField();
			txt_weightAverage = new JTextField();
			btn_averageweight = new JButton("Average");
			btn_saveweight = new JButton("Save");
			btn_averageweight.addActionListener(this);
			btn_saveweight.addActionListener(this);
			txt_retrive_weightvalue.setBounds(50, 200, 200, 30);
			txt_retrive_weightvalue.setFont(textsize);
			txt_weightAverage.setBounds(50, 250, 200, 30);
			txt_weightAverage.setFont(textsize);
			btn_averageweight.setBounds(290, 195, 200, 30);
			btn_averageweight.setForeground(Color.white);
			btn_averageweight.setBackground(Color.decode("#1A3A2D"));
			btn_averageweight.setFont(textsize);
			lbl_morningweight.setBounds(120, 30, 200, 30);
			lbl_eveningweight.setBounds(120, 85, 200, 30);
			lbl_averageweight.setBounds(120, 140, 200, 30);
			txt_morningweight.setBounds(290, 30, 200, 30);
			txt_eveningweight.setBounds(290, 85, 200, 30);
			txt_averageweight.setBounds(290, 140, 200, 30);
			lbl_kg1.setBounds(500, 30, 80, 30);
			lbl_kg2.setBounds(500, 85, 80, 30);
			lbl_kg3.setBounds(500, 140, 80, 30);
			btn_saveweight.setBounds(290, 245, 200, 30);
			everyday_Activity = new JTextField();
			everyday_Activity.setBounds(10, 10, 50, 30);
			btn_saveweight.setForeground(Color.white);
			btn_saveweight.setBackground(Color.decode("#1A3A2D"));
			lbl_morningweight.setFont(textsize);
			lbl_eveningweight.setFont(textsize);
			lbl_averageweight.setFont(textsize);
			txt_morningweight.setFont(textsize);
			txt_eveningweight.setFont(textsize);
			txt_averageweight.setFont(textsize);
			lbl_kg1.setFont(textsize);
			lbl_kg2.setFont(textsize);
			lbl_kg3.setFont(textsize);
			btn_saveweight.setFont(textsize);
			txt_retrive_weightvalue.setVisible(false);
			txt_weightAverage.setVisible(false);
			txt_averageweight.setEditable(false);
			everyday_Activity.setVisible(false);
			pan_weight.add(lbl_morningweight);
			pan_weight.add(lbl_eveningweight);
			pan_weight.add(lbl_averageweight);
			pan_weight.add(txt_morningweight);
			pan_weight.add(txt_eveningweight);
			pan_weight.add(txt_averageweight);
			pan_weight.add(everyday_Activity);
			pan_weight.add(lbl_kg1);
			pan_weight.add(lbl_kg2);
			pan_weight.add(lbl_kg3);
			pan_weight.add(btn_saveweight);
			pan_weight.add(btn_averageweight);
			pan_weight.add(txt_retrive_weightvalue);
			pan_weight.add(txt_weightAverage);
		} catch (Exception ex1) {
			JOptionPane.showMessageDialog(null, "Error weightDetails" + ex1.toString());
		}

	}

	private void waist_details(JPanel panelname) {
		try {
			JLabel lbl_morningwaist = new JLabel("Waist(Morning)");
			JLabel lbl_eveningwaist = new JLabel("Waist(Evening)");
			JLabel lbl_averagewaist = new JLabel("Average(Inch)");
			lbl_morningwaist.setBounds(120, 30, 200, 30);
			lbl_eveningwaist.setBounds(120, 85, 200, 30);
			lbl_averagewaist.setBounds(120, 140, 200, 30);
			lbl_averagewaist.setFont(textsize);
			lbl_eveningwaist.setFont(textsize);
			lbl_morningwaist.setFont(textsize);
			txt_morningwaist = new JTextField();
			txt_morningwaist.setBounds(290, 30, 200, 30);
			txt_morningwaist.setFont(textsize);
			txt_eveningwaist = new JTextField();
			txt_eveningwaist.setBounds(290, 85, 200, 30);
			txt_eveningwaist.setFont(textsize);
			txt_averagewaist = new JTextField();
			txt_averagewaist.setBounds(290, 140, 200, 30);
			txt_averagewaist.setFont(textsize);
			txt_averagewaist.setEditable(false);
			// new textfield =======================================
			txt_retrive_waistvalue = new JTextField();
			txt_retrive_waistvalue.setBounds(50, 200, 200, 30);
			txt_retrive_waistvalue.setFont(textsize);
			txt_waistAverage = new JTextField();
			txt_waistAverage.setBounds(50, 250, 200, 30);
			txt_waistAverage.setFont(textsize);
			// new textfield for waist================================
			JLabel cmb_kg1 = new JLabel("Inch");
			cmb_kg1.setBounds(500, 30, 80, 30);
			cmb_kg1.setFont(textsize);
			JLabel cmb_kg2 = new JLabel("Inch");
			cmb_kg2.setBounds(500, 85, 80, 30);
			cmb_kg2.setFont(textsize);
			btn_averagewaist = new JButton("Average");
			btn_averagewaist.setBounds(290, 195, 200, 30);
			btn_averagewaist.setForeground(Color.white);
			btn_averagewaist.setBackground(Color.decode("#1A3A2D"));
			btn_averagewaist.setFont(textsize);
			btn_averagewaist.addActionListener(this);
			btn_savewaist = new JButton("Save");
			btn_savewaist.setBounds(290, 245, 200, 30);
			btn_savewaist.setForeground(Color.white);
			btn_savewaist.setBackground(Color.decode("#1A3A2D"));
			btn_savewaist.setFont(textsize);
			btn_savewaist.addActionListener(this);
			txt_retrive_waistvalue.setVisible(false);
			txt_waistAverage.setVisible(false);
			txt_waistAverage.setEditable(false);
			pan_waist.add(btn_averagewaist);
			pan_waist.add(txt_morningwaist);
			pan_waist.add(txt_eveningwaist);
			pan_waist.add(txt_averagewaist);
			pan_waist.add(txt_retrive_waistvalue);
			pan_waist.add(txt_waistAverage);
			pan_waist.add(cmb_kg1);
			pan_waist.add(cmb_kg2);
			pan_waist.add(btn_savewaist);
			pan_waist.add(lbl_morningwaist);
			pan_waist.add(lbl_eveningwaist);
			pan_waist.add(lbl_averagewaist);
		} catch (Exception ex1) {
			JOptionPane.showMessageDialog(null, "Error WaistDetails" + ex1.toString());
		}

	}

	private void foodTrue() {
		pan_food.setVisible(true);
		pan_exercise.setVisible(false);
		pan_weight.setVisible(false);
		pan_waist.setVisible(false);
		select_Activity.setVisible(false);
	}

	private void exerciseTrue() {
		pan_exercise.setVisible(true);
		pan_food.setVisible(false);
		pan_weight.setVisible(false);
		pan_waist.setVisible(false);
		select_Activity.setVisible(false);
	}

	private void weightTrue() {
		pan_weight.setVisible(true);
		pan_exercise.setVisible(false);
		pan_food.setVisible(false);
		pan_waist.setVisible(false);
		select_Activity.setVisible(true);
	}

	private void waistTrue() {
		pan_waist.setVisible(true);
		pan_weight.setVisible(false);
		pan_exercise.setVisible(false);
		pan_food.setVisible(false);
		select_Activity.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(btn_food)) {
			foodTrue();
		} else if (source.equals(btn_exercise)) {
			exerciseTrue();
		} else if (source.equals(btn_weight)) {
			weightTrue();
		} else if (source.equals(btn_waist)) {
			waistTrue();
		} else if (source.equals(btn_addfood)) {
			add_Food();
		} else if (source.equals(btn_mealsave)) {
			meal_Save();
		} else if (source.equals(btn_averageweight)) {
			average_Weight();
		} else if (source.equals(btn_saveweight)) {
			add_Weight();
		} else if (source.equals(btn_addexercise)) {
			add_ExerciseType();
		} else if (source.equals(btn_mealsave_1)) {
			save_Exercise();
		} else if (source.equals(btn_averagewaist)) {
			average_Waist();
		} else if (source.equals(btn_savewaist)) {
			add_Waist();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String getmealname = txt_mealName.getText().toString();
		try {
			if (fooddio.checkstorefood_username(getuserName)
					&& fooddio.checkstorefood_foodname(txt_mealName.getText())) {
				Retrive_FoodCalorie(getmealname, getuserName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (txt_mealName.getText().equals("")) {
			txt_calories.setText("");
		}
		String date_name = null;
		try {
			date_name = f3.format(date_chooser.getDate());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Date Field Must Not Empty!!!");
		}
		try {
			if (fooddio.checkdate_NameFood(date_name, getuserName) == true) {
				check_FoodvalueExit(date_name, getuserName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (exercisedio.checkdate_calorie(date_name, getuserName) == true) {
				check_ExercisevalueExit(date_name, getuserName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void add_Food() {
		String getmeal_Name = txt_mealName.getText();
		String getmeal_Calories = txt_calories.getText();
		if (!getmeal_Name.equals("") || !getmeal_Calories.equals("")) {
			try {
				if (fooddio.checkstorefood_username(getuserName) && fooddio.checkstorefood_foodname(getmeal_Name)) {
				} else {
					fooddio.add_storefood(new storefoodInfo(0, getmeal_Name, getmeal_Calories, getuserName));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		}

		mealtypeadd();
	}

	public void meal_Save() {
		String get_foodetails = txt_addfooddetails.getText().toString();
		String get_caloriedetails = txt_addfoodecal.getText().toString();
		String date_name = null;
		if (get_foodetails.equals("") || get_caloriedetails.equals("") || date_chooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Field MustNot Empty!!!");
		} else {
			date_name = f3.format(date_chooser.getDate());
			if (txt_retrive_Exercise.getText().equals("")) {
				try {
					if (fooddio.checkdate_NameFood(date_name, getuserName) == true) {
						System.out.println(date_name + " " + getuserName);
						JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken ggggg!!!");
						set_totalfoodclear();
					} else {
						fooddio.add_food(new foodInfo(0, get_foodetails, get_caloriedetails, date_name, getuserName,
								txt_storevaluewithcalorie.getText()));
						JOptionPane.showMessageDialog(null, "Add a New Position!!");
						set_totalfoodclear();
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				try {
					if (caloriedio.checkdate_calorie(date_name, getuserName) == true) {
						// nothing todo
					} else {
						date_name = f3.format(date_chooser.getDate());
						if (fooddio.checkdate_NameFood(date_name, getuserName) == true) {
							System.out.println(date_name + " " + getuserName);
							JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken kkkkk!!!");
							set_totalfoodclear();
						} else {
							double getfirtid = Double.parseDouble(txt_storevaluewithcalorie.getText());
							double getsecondid = Double.parseDouble(txt_retrive_Exercise.getText());
							double difference = (getfirtid - getsecondid);
							fooddio.add_food(new foodInfo(0, get_foodetails, get_caloriedetails, date_name, getuserName,
									txt_storevaluewithcalorie.getText()));
							caloriedio.add_calorie(new calorieburnInfo(0, Double.toString(getsecondid),
									Double.toString(getfirtid), Double.toString(difference), date_name, getuserName));
							JOptionPane.showMessageDialog(null, "Add a New Position!!");
							set_totalfoodclear();
						}

					}
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void save_Exercise() {
		String get_foodetails = txt_totalexercise_Name.getText().toString();
		String get_caloriedetails = txt_totalexercise_minutes.getText().toString();
		String date_name = null;
		if (get_foodetails.equals("") || get_caloriedetails.equals("") || date_chooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Field MustNot Empty!!!");
		} else {
			if (txt_retrive_Food.getText().equals("")) {
				try {
					date_name = f3.format(date_chooser.getDate());
					if (exercisedio.checkdate_calorie(date_name, getuserName) == true) {
						JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken!!!");
						set_exerciseClear();
					} else {
						date_name = f3.format(date_chooser.getDate());
						exercisedio.addExercise(
								new exerciseInfo(0, get_foodetails, get_caloriedetails, date_name, getuserName));
						JOptionPane.showMessageDialog(null, "Add a New Position!!");
						set_exerciseClear();
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				try {
					if (caloriedio.checkdate_calorie(date_name, getuserName) == true) {
						// nothing todo
					} else {
						date_name = f3.format(date_chooser.getDate());
						if (exercisedio.checkdate_calorie(date_name, getuserName) == true) {
							JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken!!!");
							set_exerciseClear();
						} else {
							double getfirtid = Double.parseDouble(txt_totalexercise_minutes.getText());
							double getsecondid = Double.parseDouble(txt_retrive_Food.getText());

							double difference = (getfirtid - getsecondid);
							exercisedio.addExercise(
									new exerciseInfo(0, get_foodetails, get_caloriedetails, date_name, getuserName));
							caloriedio.add_calorie(new calorieburnInfo(0, Double.toString(getsecondid),
									Double.toString(getfirtid), Double.toString(difference), date_name, getuserName));
							JOptionPane.showMessageDialog(null, "Add a New Position!!");
							set_exerciseClear();
						}

					}
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void average_Waist() {
		int first_waist = Integer.parseInt(txt_morningwaist.getText());
		int second_waist = Integer.parseInt(txt_eveningwaist.getText());
		if ((txt_morningwaist.getText().equals("") || txt_eveningwaist.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			int total_waist = (first_waist + second_waist) / 2;
			txt_averagewaist.setText("" + total_waist);
		}
	}

	public void average_Weight() {
		int first_weight = Integer.parseInt(txt_morningweight.getText());
		int second_weight = Integer.parseInt(txt_eveningweight.getText());
		if ((txt_morningweight.getText().equals("") || txt_eveningweight.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			int total_weight = (first_weight + second_weight) / 2;
			txt_averageweight.setText("" + total_weight);
		}
	}

	public void add_Weight() {
		String morning_weight = txt_morningweight.getText();
		String evening_weight = txt_eveningweight.getText();
		average_weight = txt_averageweight.getText();
		String date_name = null;
		String date_name_spilt = null;
		if (morning_weight.equals("") || evening_weight.equals("") || average_weight.equals("")
				|| date_chooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!");
		} else {
			date_name = f3.format(date_chooser.getDate());
			String getsubstring_Date = date_name.substring(0, 2);
			int dicrement_date = (Integer.parseInt(getsubstring_Date) - 1);
			String check_length = Integer.toString(dicrement_date);
			if (check_length.length() == 1) {
				date_name_spilt = "0" + Integer.toString(dicrement_date) + f4.format(date_chooser.getDate());
			} else {
				date_name_spilt = Integer.toString(dicrement_date) + f4.format(date_chooser.getDate());
			}
			// need to write the code
			// check the date substring
			try {
				// check spilt date Name
				if (weightdio.checkdate_weight(date_name_spilt, getuserName) == true) {
					if (weightdio.checkdate_weight(date_name, getuserName) == true) {
						JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken!!!");
						setclearweight();
					} else {

						Retrive_dailyActivity(date_name_spilt, getuserName);
						//
						double current_weight = Double.parseDouble(txt_averageweight.getText())
								- Double.parseDouble(everyday_Activity.getText());
						String convertcurrent_weight = Double.toString(current_weight);
						weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight, average_weight,
								date_name, getuserName));
						weightdio.add_dailychange(
								new weight_changeInfo(0, convertcurrent_weight, date_name, getuserName));
						setclearweight();
						showEstimateInfo();

					}

				} else {
					int final_decrement = (dicrement_date - 1);
					String add_retrivedate = (Integer.toString(final_decrement) + f4.format(date_chooser.getDate()));

					if ((weightdio.checkdate_weight(add_retrivedate, getuserName) == true)) {
						Retrive_weightDetails(add_retrivedate, getuserName);

						double total_average = ((Double.parseDouble(txt_retrive_weightvalue.getText())
								+ Double.parseDouble(average_weight)) / 2);
						txt_weightAverage.setText(Double.toString(total_average));
						// new code
						// Retrive_dailyActivity(date_name_spilt, getuserName);
						// match previous
						double current_weight = Double.parseDouble(txt_averageweight.getText())
								- Double.parseDouble(txt_weightAverage.getText());

						// not match previous
						double current_weight_1 = Double.parseDouble(txt_averageweight.getText())
								- Double.parseDouble(txt_retrive_weightvalue.getText());

						String convertcurrent_weight = Double.toString(current_weight);
						// new code
						weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight,
								txt_weightAverage.getText(), date_name_spilt, getuserName));
						weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight, average_weight,
								date_name, getuserName));
						// currentlychange here
						weightdio.add_dailychange(new weight_changeInfo(0, Double.toString(current_weight_1),
								date_name_spilt, getuserName));

						weightdio.add_dailychange(
								new weight_changeInfo(0, Double.toString(current_weight_1), date_name, getuserName));
						setclearweight();
						showEstimateInfo();
					} else {
						if (weightdio.checkdate_weight(date_name, getuserName) == true) {
							JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken.!!!");
							setclearweight();
						} else {
							weightdio.add_weight(new weightInfo(0, morning_weight, evening_weight, average_weight,
									date_name, getuserName));
							weightdio.add_dailychange(new weight_changeInfo(0, "0.0", date_name, getuserName));
							setclearweight();
							showEstimateInfo();
						}
					}

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void add_Waist() {
		String morning_waist = txt_morningwaist.getText();
		String evening_waist = txt_eveningwaist.getText();
		String average_waist = txt_averagewaist.getText();
		String date_name = null;
		String date_name_spilt = null;

		if (morning_waist.equals("") || evening_waist.equals("") || average_waist.equals("")
				|| date_chooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!");
		} else {
			date_name = f3.format(date_chooser.getDate());
			String getsubstring_Date = date_name.substring(0, 2);
			int dicrement_date = (Integer.parseInt(getsubstring_Date) - 1);
			date_name_spilt = Integer.toString(dicrement_date) + f4.format(date_chooser.getDate());
			try {
				if (waistdio.checkdate_waist(date_name_spilt, getuserName) == true) {
					if (waistdio.checkdate_waist(date_name, getuserName) == true) {
						JOptionPane.showMessageDialog(null, "Sorry, Today Information is already taken by golam!!!");
						setclearwaist();
					} else {
						waistdio.add_waist(
								new waistInfo(0, morning_waist, evening_waist, average_waist, date_name, getuserName));
						JOptionPane.showMessageDialog(null, "Add a New Position!!");
						setclearwaist();
					}

				} else {
					int final_decrement = (dicrement_date - 1);
					String add_retrivedate = (Integer.toString(final_decrement) + f4.format(date_chooser.getDate()));
					if ((waistdio.checkdate_waist(add_retrivedate, getuserName) == true)) {
						Retrive_waistDetails(add_retrivedate, getuserName);
						double total_average = ((Double.parseDouble(txt_retrive_waistvalue.getText())
								+ Double.parseDouble(average_waist)) / 2);
						txt_waistAverage.setText(Double.toString(total_average));
						waistdio.add_waist(new waistInfo(0, morning_waist, evening_waist, txt_waistAverage.getText(),
								date_name_spilt, getuserName));
						waistdio.add_waist(
								new waistInfo(0, morning_waist, evening_waist, average_waist, date_name, getuserName));
						JOptionPane.showMessageDialog(null, "Add a New Postation!!!");
						setclearwaist();
					} else {
						if (waistdio.checkdate_waist(date_name, getuserName) == true) {
							JOptionPane.showMessageDialog(null,
									"Sorry, Today Information is already taken y last -------!!!");
							setclearwaist();
						} else {
							waistdio.add_waist(new waistInfo(0, morning_waist, evening_waist, average_waist, date_name,
									getuserName));
							JOptionPane.showMessageDialog(null, "Add a New Position!!");
							setclearwaist();
						}
					}

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void Retrive_waistDetails(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from waist where waist_date='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(4).trim();
				txt_retrive_waistvalue.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Retrive_dailyActivity(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from weight where weight_date ='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(4).trim();
				everyday_Activity.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Retrive_weightDetails(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from weight where weight_date ='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(4).trim();
				txt_retrive_weightvalue.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void Retrive_FoodCalorie(String foodname, String usernmecheck) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from storefood where foodvirtual_name='" + foodname
					+ "' And foodvirtual_username='" + usernmecheck + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(3).trim();
				txt_calories.setText(get_gram);
				rs = null;
			}
			con.close();
		} catch (Exception ex) {

		}

	}

	@SuppressWarnings("resource")
	public void check_ExercisevalueExit(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from exercise where exercise_date='" + date + "' And exercise_username='"
					+ username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(3).trim();
				txt_retrive_Exercise.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	public void check_FoodvalueExit(String date, String username) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "select * from food where food_date='" + date + "' And username ='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String get_gram = rs.getString(6).trim();
				txt_retrive_Food.setText(get_gram);
			}
			rs = null;
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setclearwaist() {
		txt_morningwaist.setText("");
		txt_eveningwaist.setText("");
		txt_averagewaist.setText("");
		txt_retrive_waistvalue.setText("");
		txt_waistAverage.setText("");

	}

	private void setclearweight() {
		txt_morningweight.setText("");
		txt_eveningweight.setText("");
		txt_averageweight.setText("");
		txt_retrive_weightvalue.setText("");
		txt_weightAverage.setText("");
	}

	private void setclearfood() {
		txt_mealName.setText("");
		txt_calories.setText("");
	}

	private void set_totalfoodclear() {
		txt_addfooddetails.setText("");
		txt_addfoodecal.setText("0");
		txt_storevaluewithcalorie.setText("0");
	}

	public void set_exerciseClear() {
		txt_totalexercise_Name.setText("");
		txt_totalexercise_minutes.setText("0");
		txt_retrive_Food.setText("");
	}

	private void mealtypeadd() {
		try {
			String getmealtype = cmb_selectmealtype.getSelectedItem().toString();
			String first = txt_mealName.getText();
			double check_fat = Double.parseDouble(txt_calories.getText());
			if (getmealtype.equals("Fat")) {
				double store_calorie = check_fat + Double.parseDouble(txt_storevaluewithcalorie.getText());
				double divided_Fat = check_fat / 9;
				double total = divided_Fat + Double.parseDouble(txt_addfoodecal.getText());
				String add = first + "," + txt_addfooddetails.getText();
				String formattedString2 = String.format("%.3f", total);
				txt_storevaluewithcalorie.setText(Double.toString(store_calorie));
				txt_addfooddetails.setText(add);
				txt_addfoodecal.setText(formattedString2);
				setclearfood();
			} else if (getmealtype.equals("Alcohol")) {
				double store_calorie = check_fat + Double.parseDouble(txt_storevaluewithcalorie.getText());
				double divided_Fat = check_fat / 7;
				double total = divided_Fat + Double.parseDouble(txt_addfoodecal.getText());
				String add = first + "," + txt_addfooddetails.getText();
				String formattedString2 = String.format("%.3f", total);
				txt_storevaluewithcalorie.setText(Double.toString(store_calorie));
				txt_addfooddetails.setText(add);
				txt_addfoodecal.setText(formattedString2);
				setclearfood();
			} else if (getmealtype.equals("Protein") || getmealtype.equals("Carbohydrate")) {
				double store_calorie = check_fat + Double.parseDouble(txt_storevaluewithcalorie.getText());
				double divided_Fat = check_fat / 4;
				double total = divided_Fat + Double.parseDouble(txt_addfoodecal.getText());
				String add = first + "," + txt_addfooddetails.getText();
				String formattedString2 = String.format("%.3f", total);
				txt_storevaluewithcalorie.setText(Double.toString(store_calorie));
				txt_addfooddetails.setText(add);
				txt_addfoodecal.setText(formattedString2);
				setclearfood();
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty");
		}
	}

	private void add_ExerciseType() {
		try {
			String getExerciseSelect = txt_exercisename_1.getSelectedItem().toString();
			String getExerciseValue = txt_exercisevalue_1.getText();
			if (getExerciseSelect.equals("Runnig")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 10.53;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Push Up")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 9.10;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Bicycling")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 6.06;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Cooking")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 0.002;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Dancing")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 0.023;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("Jumping")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 12.4;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}

			} else if (getExerciseSelect.equals("Basketball")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 24.26;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("Slow Walk")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 8.5;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("swimming")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 12.4;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			} else if (getExerciseSelect.equals("Jogging")) {
				if (getExerciseValue.equals("")) {
					JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
				} else {
					double convert_double = Double.parseDouble(getExerciseValue) * 12.4;
					double add_convertdata = convert_double + Double.parseDouble(txt_totalexercise_minutes.getText());
					String formatted_Exercise = String.format("%.2f", add_convertdata);
					txt_totalexercise_minutes.setText(formatted_Exercise);
					txt_totalexercise_Name.setText(getExerciseSelect + "," + txt_totalexercise_Name.getText());
					txt_exercisevalue_1.setText("");
				}
			}

		} catch (Exception ex1) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty");
		}
	}

	private void showEstimateInfo() {
		String get_averageValue = average_weight;
		if (get_averageValue.equals("")) {
			JOptionPane.showMessageDialog(null, "Field Must Not Empty!!!");
		} else {
			if (getuserGender.equalsIgnoreCase("Male")) {

				if (select_Activity.getSelectedItem().equals("Active") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 7.5);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else if (select_Activity.getSelectedItem().equals("InActive") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 5);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else {
					double result_Active = (Double.parseDouble(get_averageValue) * 6);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				}

			} else {

				if (select_Activity.getSelectedItem().equals("Active") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 6);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else if (select_Activity.getSelectedItem().equals("InActive") == true) {
					double result_Active = (Double.parseDouble(get_averageValue) * 4);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				} else {
					double result_Active = (Double.parseDouble(get_averageValue) * 5);
					JOptionPane.showMessageDialog(null,
							"You Must Need To Take " + result_Active + " Calorie For Today");
				}
			}
		}

	}

}
