package com.nist.swingpractical;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;

public class StudentDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Student student = new StudentImpl();
	private JTextField searchBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetails frame = new StudentDetails();
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
	public StudentDetails() {
		setTitle("Student Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Student");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Student");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentForm().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 10, 416, 171);
		String[] columnName = {"ID", "First Name","Last Name","Gender","Level","Course"};
		DefaultTableModel tableModel = new DefaultTableModel(columnName, 0);
		table.setModel(tableModel);
		loadTableData();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 30, 416, 151);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(10, 210, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				Object id = tableModel.getValueAt(row, 0);
				Object firstName = tableModel.getValueAt(row, 1);
				Object lastName = tableModel.getValueAt(row, 2);
				Object gender = tableModel.getValueAt(row, 3);
				Object level = tableModel.getValueAt(row, 4);
				Object course = tableModel.getValueAt(row, 5);
				StudentForm stdForm = new StudentForm();
				stdForm.idLabel.setText(id.toString());
				stdForm.textFieldFirstName.setText(firstName.toString());
				stdForm.textFieldLastName.setText(lastName.toString());
				if(gender.toString().equals("Male")) {
					stdForm.rdbtnMale.setSelected(true);
				}else if(gender.toString().equals("Female")) {
					stdForm.rdbtnFemale.setSelected(true);
				}else {
					stdForm.rdbtnOther.setSelected(true);
				}
				stdForm.comboBox.setSelectedItem(level);
				dispose();
				stdForm.setVisible(true);
			}
		});
		btnEdit.setBounds(172, 210, 85, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				Object id = tableModel.getValueAt(row, 0);
				int status = JOptionPane.showConfirmDialog(StudentDetails.this, "Click Yes to Confirm", "Do you want to delete this Data?", JOptionPane.YES_NO_OPTION);
				if(status == 0) {
					student.deleteStudentData(Integer.parseInt(id.toString()));
					loadTableData();
				}
				else {
					loadTableData();
				}
			}
		});
		btnDelete.setBounds(324, 210, 85, 21);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setBounds(268, 7, 45, 13);
		contentPane.add(lblNewLabel);
		
		searchBox = new JTextField();
		searchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String searchName = searchBox.getText();	
				if(searchName == null || searchName.isEmpty()) {
					loadTableData();
					}
				else {
					searchStudentData(searchName);
				}
				}
		});
		searchBox.setBounds(313, 4, 96, 19);
		contentPane.add(searchBox);
		searchBox.setColumns(10);
	}
	public void loadTableData() {
		List<StudentFormDAO> stdList = student.getStudents();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(StudentFormDAO std: stdList) {
			tableModel.addRow(new Object[] {
					std.getId(),std.getFirstName(),std.getLastName(),std.getGender(), std.getLevel(), std.getCourse()
			});
		}
		
	}
	
	public void searchStudentData(String name) {
		List<StudentFormDAO> stdList = student.searchStudent(name);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for(StudentFormDAO std: stdList) {
			tableModel.addRow(new Object[] {
					std.getId(),std.getFirstName(),std.getLastName(),std.getGender(), std.getLevel(), std.getCourse()
			});
		}
		
	}
}
