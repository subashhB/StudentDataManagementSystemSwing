package com.nist.swingpractical;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;

public class StudentForm extends JFrame {

	public JPanel contentPane;
	public JTextField textFieldFirstName;
	public JTextField textFieldLastName;
	public JLabel idLabel;
	public JLabel lblFirstName;
	public JLabel lblLastName;
	public JRadioButton rdbtnMale;
	public JRadioButton rdbtnFemale;
	public JRadioButton rdbtnOther;
	public JLabel lblGender; 
	public JLabel lblLevel;
	public JComboBox comboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm frame = new StudentForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idLabel = new JLabel("");
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLabel.setBounds(381, 14, 45, 13);
		contentPane.add(idLabel);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(20, 43, 106, 13);
		contentPane.add(lblLastName);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(20, 12, 106, 13);
		contentPane.add(lblFirstName);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMale.setBounds(127, 76, 55, 21);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnFemale.setBounds(198, 76, 85, 21);
		contentPane.add(rdbtnFemale);
		
		rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnOther.setBounds(285, 76, 103, 21);
		contentPane.add(rdbtnOther);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldFirstName.setBounds(136, 10, 138, 19);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		ButtonGroup obj = new ButtonGroup();
		obj.add(rdbtnMale);
		obj.add(rdbtnFemale);
		obj.add(rdbtnOther);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(136, 40, 138, 19);
		contentPane.add(textFieldLastName);
		
		lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(20, 80, 106, 13);
		contentPane.add(lblGender);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"S.E.E Graduated", "+2 Graduated", "Bachelors", "Masters"}));
		comboBox.setBounds(136, 99, 147, 21);
		contentPane.add(comboBox);
		
		lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLevel.setBounds(20, 103, 106, 13);
		contentPane.add(lblLevel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = textFieldFirstName.getText();
				String lastName = textFieldLastName.getText();
				String selectedItem = (String)comboBox.getSelectedItem();
				String gender = "";
				if(rdbtnFemale.isSelected()) {
					gender = "Female";
				}
				else if (rdbtnMale.isSelected()) {
					gender = "Male";
				}
				else if(rdbtnOther.isSelected()) {
					gender = "Others";
				}
				
//				String subjects=" ";
//				if(chckbxHTML.isSelected()) {
//					subjects = subjects + "HTML /";
//				}
//				
//				if(chckbxCss.isSelected()) {
//					subjects = subjects + "CSS /";
//				}
//				
//				if(chckbxJavascript.isSelected()) {
//					subjects = subjects + "JS /";
//				}
				
				Student student = new StudentImpl();
				StudentFormDAO studentFormDAO = new StudentFormDAO();
				studentFormDAO.setFirstName(firstName);
				studentFormDAO.setLastName(lastName);
				studentFormDAO.setGender(gender);
				studentFormDAO.setLevel(selectedItem);
//				studentFormDAO.setCourse(subjects);
				String stdId = idLabel.getText();
				if(stdId == null || stdId.isEmpty()) {
					student.saveStudentInfo(studentFormDAO);
				}else {
					studentFormDAO.setId(Integer.parseInt(stdId));
					student.updateStudentInfo(studentFormDAO);
				}
				
				dispose();
				new StudentDetails().setVisible(true);
						
			}
			
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(41, 194, 85, 21);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentDetails().setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(222, 196, 85, 21);
		contentPane.add(btnCancel);
		
		
	}
}
