package com.java.weighttracker.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class SettingMenu {

	public JMenu setJMenu(JMenu menu) {
		menu.setFont(new Font("Dialog", Font.BOLD, 12));
		menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		menu.setForeground(new Color(0, 0, 0));
		return menu;
	}// Create a Menu

	public JMenuItem setJMenuItem(JMenuItem mnuitem, String sCaption, String imgLocation) {
		mnuitem.setText(sCaption);
		mnuitem.setIcon(new ImageIcon(imgLocation));
		mnuitem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mnuitem.setFont(new Font("Dialog", Font.PLAIN, 12));
		mnuitem.setForeground(new Color(0, 0, 0));
		return mnuitem;
	}// Create a MenuItem

	public JTabbedPane setJTabbedPane(JTabbedPane setTabbed, String setTitle, String setIcon, JPanel setPanel,
			int sLeft, int sTop, int sWidth, int sHeight) {
		setTabbed.setBounds(sLeft, sTop, sWidth, sHeight);
		setTabbed.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setTabbed.setFont(new Font("Dialog", Font.CENTER_BASELINE, 12));
		setTabbed.setForeground(new Color(166, 0, 0));
		setTabbed.addTab(setTitle, new ImageIcon(setIcon), setPanel);
		return setTabbed;
	}// Create a JTabbedPane

	public void Numvalidator(JTextField txtField) {
		txtField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
	}

	public void Numvalidator1(JTextField txtField) {
		txtField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int c = e.getKeyChar();
				if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
	}

}
