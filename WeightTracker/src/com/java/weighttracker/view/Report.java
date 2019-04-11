package com.java.weighttracker.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.java.weighttracker.daoimp.DBConnection;

public class Report extends JDialog implements ActionListener, Printable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_close = new JButton("Close");
	private JButton btn_print = new JButton("Print");
	private JLabel txt_averageweight = new JLabel();
	// private JTextField txt_firstEntryweiht = new JTextField();
	// private JTextField txt_lastEntryweiht = new JTextField();
	JPanel pan_graph = new JPanel();
	Font font = new Font("Arial", Font.BOLD, 17);
	private static String getuserName = null;
	private static String total = null;
	private static double finalaverage = 0;

	public Report(String username, JFrame parent) {
		super(JOptionPane.getFrameForComponent(parent), "Report", true);
		getuserName = username;
		this.showReport();

	}

	public void showReport() {
		this.setSize(1000, 550);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		JLabel lbl_averageweight = new JLabel("Average Weight Loss(kg)= ");
		lbl_averageweight.setBounds(180, 30, 300, 30);
		lbl_averageweight.setForeground(Color.WHITE);
		txt_averageweight.setBounds(400, 30, 100, 30);
		txt_averageweight.setForeground(Color.red);
		btn_close.setBounds(580, 30, 200, 30);
		btn_print.setBounds(580, 70, 200, 30);
		btn_close.setBackground(Color.decode("#1A3A2D"));
		btn_print.setBackground(Color.decode("#1A3A2D"));
		btn_close.setForeground(Color.WHITE);
		btn_print.setForeground(Color.WHITE);
		btn_close.setFont(font);
		btn_print.setFont(font);
		btn_close.addActionListener(this);
		btn_print.addActionListener(this);
		lbl_averageweight.setFont(font);
		txt_averageweight.setFont(font);
		pan_graph.setBounds(50, 170, 900, 330);
		pan_graph.setLayout(new GridLayout(0, 1));
		pan_graph.setBackground(Color.black);
		this.add(pan_graph);
		this.add(lbl_averageweight);
		this.add(txt_averageweight);
		this.add(btn_close);
		this.add(btn_print);
		draw_graph(getuserName);
		fill_totalman(getuserName);
		this.getContentPane().setBackground(Color.decode("#483C32"));
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source.equals(btn_close)) {
			dispose();

		} else if (source.equals(btn_print)) {
			PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setPrintable(this);
			if (printJob.printDialog()) {
				try {
					printJob.print();
				} catch (Exception PrintException) {
					PrintException.printStackTrace();
				}
				printJob.cancel();
			}
			printJob.cancel();
		}

	}

	@Override
	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pf.getImageableX() + 5, pf.getImageableY() + 5);
		Font f = new Font("Monospaced", Font.PLAIN, 12);
		g2.setFont(f);
		paint(g2);
		return Printable.PAGE_EXISTS;
	}

	@SuppressWarnings("deprecation")
	public void draw_graph(String username) {
		try {
			Connection conn = DBConnection.getConnecttion();
			String query = "Select change_date,change_amount from daily_change where change_username='" + username
					+ "'";
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, query);
			JFreeChart chartsa = ChartFactory.createLineChart("Weight Tracker", "Date", "Weight Change", dataset,
					PlotOrientation.VERTICAL, false, true, true);
			chartsa.setBackgroundPaint(Color.BLACK);
			chartsa.getTitle().setPaint(Color.WHITE);
			CategoryPlot plot = chartsa.getCategoryPlot();
			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			plot.setRangeGridlinePaint(Color.BLACK);
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel chartpanel = new ChartPanel(chartsa);
			pan_graph.add(chartpanel);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void fill_totalman(String username) {

		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT COUNT(weight_id) from weight WHERE username='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count = rs.getString("COUNT(weight_id)");
				total = count;

			}
			rs.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}

		try {
			double average = 0;
			Statement stmt = con.createStatement();
			String query = "SELECT *from daily_change WHERE change_username='" + username + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String count_totalweight = rs.getString(2);
				average += Double.parseDouble(count_totalweight);
			}
			finalaverage = average / Integer.parseInt(total);
			String formattedString2 = String.format("%.3f", finalaverage);
			txt_averageweight.setText((formattedString2));
			rs.close();
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}

	}

}
