package com.java.weighttracker.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainGui {
	public static void main(String[] arg) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			Tracker.createAndShowGUI();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
