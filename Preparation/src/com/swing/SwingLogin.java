package com.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class SwingLogin implements ActionListener {

	JLabel l1, l2;
	JTextArea t1, t2;
	JButton b1;

	public SwingLogin() {

		JFrame fj = new JFrame("Login");
		fj.setVisible(true);
		fj.setSize(600, 400);
		fj.setLayout(null);

		l1 = new JLabel("Email");
		l1.setBounds(200, 250, 200, 20);
		fj.add(l1);

		t1 = new JTextArea();
		t1.setBounds(280, 250, 200, 20);
		fj.add(t1);

		l2 = new JLabel("Password");
		l2.setBounds(200, 300, 200, 20);
		fj.add(l2);

		t2 = new JTextArea();
		t2.setBounds(280, 300, 200, 20);
		fj.add(t2);

		b1 = new JButton("Login");
		b1.setBounds(300, 400, 100, 40);
		fj.add(b1);

		b1.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {

			String email = t1.getText();
			String password = t2.getText();

			System.out.println("Email: " + email);
			System.out.println("Password: " + password);

			try {
				Connection con = SwingTask.getconnection();
				String sql = "select * from data where email=? and password=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, email);
				pst.setString(2, password);

				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					String name = rs.getString("name");
					long contact = rs.getLong("contact");
					String address = rs.getString("address");
					String gender = rs.getString("gender");
					String hobbies = rs.getString("hobbies");

					System.out.println("name : " + name);
					System.out.println("contact : " + contact);
					System.out.println("address : " + address);
					System.out.println("gender : " + gender);
					System.out.println("hobbies : " + hobbies);

					System.out.println("data selected");
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		new SwingHome();
	}

}
