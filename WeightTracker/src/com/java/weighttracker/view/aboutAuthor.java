package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class aboutAuthor extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_facebook, btn_gmail, btn_twiter;
	private JLabel lbl_area;
	private static final String str_works = "Works At Daffodil International Academy";
	private static final String str_likes = "Likes : Playing football and cricket";
	private static final String str_followme = "Follow...";
	Font f = new Font("Arial", Font.BOLD, 15);
	Border thickBorder = new LineBorder(Color.ORANGE, 12);
	JLabel lbl_author = new JLabel(new ImageIcon("images/download.png"));

	public aboutAuthor(JFrame parent) {
		super(JOptionPane.getFrameForComponent(parent), "About Author", true);
		this.create_Aboutpanel();
	}

	public void create_Aboutpanel() {
		try {
			this.setSize(600, 400);
			this.setLayout(null);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			JPanel pan_image = new JPanel();
			pan_image.setLayout(null);
			pan_image.setBounds(200, 50, 200, 150);
			pan_image.setBorder(thickBorder);
			lbl_author.setBounds(0, 0, 200, 150);
			pan_image.add(lbl_author);
			lbl_area = new JLabel(str_works);
			lbl_area.setBounds(130, 220, 450, 30);
			lbl_area.setFont(f);
			JLabel lbl_likes = new JLabel(str_likes);
			lbl_likes.setBounds(130, 240, 300, 30);
			lbl_likes.setFont(f);
			JLabel lbl_floow = new JLabel(str_followme);
			lbl_floow.setBounds(130, 260, 100, 30);
			lbl_floow.setFont(f);
			btn_facebook = new JButton("Facebook");
			btn_facebook.setBounds(180, 320, 80, 30);
			btn_gmail = new JButton("Gmail");
			btn_gmail.setBounds(270, 320, 80, 30);
			btn_twiter = new JButton("Twiter");
			btn_twiter.setBounds(360, 320, 80, 30);
			btn_facebook.addActionListener(this);
			btn_gmail.addActionListener(this);
			btn_twiter.addActionListener(this);
			this.getContentPane().setBackground(Color.decode("#483C32"));
			this.add(lbl_likes);
			this.add(lbl_floow);
			this.add(btn_facebook);
			this.add(btn_gmail);
			this.add(btn_twiter);
			this.add(lbl_area);
			this.add(pan_image);
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error about frame" + ex.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(btn_facebook)) {
			follow_facebook();
		} else if (source.equals(btn_gmail)) {
			follow_Gmail();
		} else if (source.equals(btn_twiter)) {
			follow_Twiter();
		}

	}

	public void follow_facebook() {
		try {
			String help_url = "http://allabouthealthwellness.blogspot.com/2011/01/understanding-different-types-of.html";
			Desktop.getDesktop().browse(URI.create(help_url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void follow_Gmail() {
		try {
			String help_url = "http://allabouthealthwellness.blogspot.com/2011/01/understanding-different-types-of.html";
			Desktop.getDesktop().browse(URI.create(help_url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void follow_Twiter() {
		try {
			String help_url = "http://allabouthealthwellness.blogspot.com/2011/01/understanding-different-types-of.html";
			Desktop.getDesktop().browse(URI.create(help_url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
