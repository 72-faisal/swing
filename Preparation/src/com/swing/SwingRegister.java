package com.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class SwingRegister implements ActionListener {

	JLabel l1, l2, l3, l4, l5, l6, l7;
	JTextArea t1, t2, t3, t4, t5;
	JRadioButton r1, r2;
	JCheckBox c1, c2, c3;
	JButton b1;
	

	public SwingRegister() {
		JFrame fr = new JFrame("Register");
		fr.setVisible(true);
		fr.setSize(600, 400);
		fr.setLayout(null);

		l1 = new JLabel("Name");
		l1.setBounds(200, 200, 200, 20);
		fr.add(l1);

		t1 = new JTextArea();
		t1.setBounds(280, 200, 200, 20);
		fr.add(t1);

		l2 = new JLabel("Contact");
		l2.setBounds(200, 250, 200, 20);
		fr.add(l2);

		t2 = new JTextArea();
		t2.setBounds(280, 250, 200, 20);
		fr.add(t2);

		l3 = new JLabel("Address");
		l3.setBounds(200, 300, 200, 20);
		fr.add(l3);

		t3 = new JTextArea();
		t3.setBounds(280, 300, 200, 20);
		fr.add(t3);

		l4 = new JLabel("Email");
		l4.setBounds(200, 350, 200, 20);
		fr.add(l4);

		t4 = new JTextArea();
		t4.setBounds(280, 350, 200, 20);
		fr.add(t4);

		l5 = new JLabel("Password");
		l5.setBounds(200, 400, 200, 20);
		fr.add(l5);

		t5 = new JTextArea();
		t5.setBounds(280, 400, 200, 20);
		fr.add(t5);

		l6 = new JLabel("Gender");
		l6.setBounds(200, 450, 200, 20);
		fr.add(l6);

		r1 = new JRadioButton("Male");
		r1.setBounds(280, 450, 60, 20);
		fr.add(r1);

		r2 = new JRadioButton("Female");
		r2.setBounds(340, 450, 100, 20);
		fr.add(r2);

		l7 = new JLabel("Hobbies");
		l7.setBounds(200, 500, 200, 20);
		fr.add(l7);

		c1 = new JCheckBox("Sports");
		c1.setBounds(280, 500, 100, 20);
		fr.add(c1);

		c2 = new JCheckBox("Travelling");
		c2.setBounds(380, 500, 100, 20);
		fr.add(c2);

		c3 = new JCheckBox("Photography");
		c3.setBounds(480, 500, 100, 20);
		fr.add(c3);

		b1 = new JButton("Register");
		b1.setBounds(300, 570, 100, 40);
		fr.add(b1);

//		ButtonGroup bg1 = new ButtonGroup();
//		bg1.add(r1);
//		bg1.add(r2);

//		rb1 = new JButton("radio value");
//		fr.add(rb1);
//
//		cb1 = new JButton("box value");
//		fr.add(cb1);

		b1.addActionListener(this);
//		rb1.addActionListener(this);
//		cb1.addActionListener(this);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {
			String name = t1.getText();
			long contact = Long.parseLong(t2.getText());
			String address = t3.getText();
			String email = t4.getText();
			String password = t5.getText();
			String gender = r1.isSelected() ? "Male" : "Female"; 
			StringBuilder hobbies = new StringBuilder();

			if (c1.isSelected()) {
				hobbies.append("Sports, ");
			}
			if (c2.isSelected()) {
				hobbies.append("Travelling, ");
			}
			if (c3.isSelected()) {
				hobbies.append("Photography");
			}

			
			if (hobbies.length() > 2) {
				hobbies.setLength(hobbies.length() - 2);
			}

			System.out.println("Name: " + name);
			System.out.println("Contact: " + contact);
			System.out.println("Address: " + address);
			System.out.println("Email: " + email);
			System.out.println("Password: " + password);
			System.out.println("Gender: " + gender);
			System.out.println("Hobbies: " + hobbies);

			try {
				Connection con = SwingTask.getconnection();
				String sql = "insert into data(name, contact, address, email, password, gender, hobbies) values(?,?,?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, name);
				pst.setLong(2, contact);
				pst.setString(3, address);
				pst.setString(4, email);
				pst.setString(5, password);
				pst.setString(6, gender);
				pst.setString(7, hobbies.toString());

				pst.executeUpdate();

				System.out.println("Data inserted successfully.");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				c1.setSelected(false);
				c2.setSelected(false);
				c3.setSelected(false);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
//		 else if (e.getSource() == rb1) {
//			String gender = "";
//			if (r1.isSelected()) {
//				gender = "Male";
//			} else if (r2.isSelected()) {
//				gender = "Female";
//			}
//
//		} else if (e.getSource() == cb1) {
//			String hobbies = "sport";
//			if (c1.isSelected()) {
//				hobbies = "Sports";
//			} else if (c2.isSelected()) {
//				hobbies = "Travelling";
//			} else if (c3.isSelected()) {
//				hobbies = "Photography";
//			}
//
//		}
		new SwingLogin();
	}
}
