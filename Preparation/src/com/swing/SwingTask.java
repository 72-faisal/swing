package com.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingTask implements ActionListener {

	JButton b1, b2;

	public SwingTask() {

		JFrame jf = new JFrame("Index Screen");
		jf.setVisible(true);
		jf.setSize(1080, 720);
		jf.setLayout(null);

		b1 = new JButton("Register");
		b1.setBounds(500, 300, 100, 50);
		jf.add(b1);

		b2 = new JButton("Login");
		b2.setBounds(500, 400, 100, 50);
		jf.add(b2);

		b1.addActionListener(this);
		b2.addActionListener(this);

	}

	public static void main(String[] args) {

		new SwingTask();

	}

	public static Connection getconnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingframe1", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return con;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == b1) {
			new SwingRegister();
		}

		if (e.getSource() == b2) {
			new SwingLogin();
		}
	}

}
