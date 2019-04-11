package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Tracker extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton enroll, signup, exit, btn_what;
	public static final Dimension PREFERREDSIZE = new Dimension(1250, 710);
	static JFrame frame = new Tracker();
	JLabel lbl_image = new JLabel(new ImageIcon("images/first.png"));

	public Tracker() {
		super("Weight Tracker");
		this.create_layout();

	}

	public static void createAndShowGUI() {
		try {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setMinimumSize(PREFERREDSIZE);
			frame.setPreferredSize(PREFERREDSIZE);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createGui" + ex.toString());
		}

	}

	public static void disposeall() {
		frame.setVisible(false);
	}

	public void create_layout() {
		try {
			lbl_image.setBounds(10, 10, 1223, 502);

			// 10, 10, 1223, 662
			Border thickBorder = new LineBorder(Color.decode("#44619D"), 10);
			Font textsize = new Font("Arial", Font.BOLD, 14);

			enroll = new JButton("Enroll Now");
			signup = new JButton("Enter Detail");
			exit = new JButton("Exit");
			enroll.setBackground(Color.decode("#090015"));
			enroll.setForeground(Color.white);
			signup.setBackground(Color.decode("#090015"));
			exit.setBackground(Color.decode("#090015"));
			signup.setForeground(Color.white);
			exit.setForeground(Color.white);
			enroll.setFont(textsize);
			signup.setFont(textsize);
			exit.setFont(textsize);
			enroll.setBounds(360, 565, 150, 73);
			signup.setBounds(530, 565, 150, 73);
			exit.setBounds(710, 565, 150, 73);
			this.add(lbl_image);
			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int confirm = JOptionPane.showConfirmDialog(null, "Do you want to Exit?");
					if (confirm == 0) {
						dispose();
					}
				}

			});

			signup.addActionListener(this);
			enroll.addActionListener(this);
			this.getContentPane().setBackground(Color.decode("#483C32"));
			this.add(exit);
			this.add(enroll);
			this.add(signup);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createGui" + ex.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(signup)) {
			AdduserDetails user = new AdduserDetails(this);
		} else if (source.equals(enroll)) {
			UserEnroll enroll = new UserEnroll(this);
		}

	}
}
