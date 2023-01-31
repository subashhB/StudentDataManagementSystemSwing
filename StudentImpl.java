package com.nist.swingpractical;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements Student{
	PreparedStatement ps = null;
	
	@Override
	public void saveStudentInfo(StudentFormDAO studentFormDAO) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO studentinfo(firstName,lastName,gender,level,course) VALUES(?,?,?,?,?)";
		try {
		ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
		ps.setString(1, studentFormDAO.getFirstName());
		ps.setString(2, studentFormDAO.getLastName());
		ps.setString(3, studentFormDAO.getGender());
		ps.setString(4, studentFormDAO.getLevel());
		ps.setString(5, studentFormDAO.getCourse());
		ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<StudentFormDAO> getStudents() {
		List<StudentFormDAO> studentList = new ArrayList<>();
		String sql = "SELECT * FROM studentinfo";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StudentFormDAO student = new StudentFormDAO();
				student.setId(rs.getInt("ID"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setGender(rs.getString("gender"));
				student.setLevel(rs.getString("level"));
				student.setCourse(rs.getString("course"));
				studentList.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList;
	}

	@Override
	public void deleteStudentData(int id) {
		String sql = "DELETE FROM studentinfo where ID = ?";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<StudentFormDAO> searchStudent(String name) {
		List<StudentFormDAO> studentList = new ArrayList<>();
		String sql = "SELECT * FROM studentinfo WHERE firstName like ?";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StudentFormDAO student = new StudentFormDAO();
				student.setId(rs.getInt("ID"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setGender(rs.getString("gender"));
				student.setLevel(rs.getString("level"));
				student.setCourse(rs.getString("course"));
				studentList.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList;
	}
	
	@Override
	public void updateStudentInfo(StudentFormDAO studentFormDAO) {
		// TODO Auto-generated method stub
		String sql = "UPDATE studentinfo SET firstName=?, lastName=?, gender=?, level=? WHERE ID=?";
		try {
			ps = DatabaseConnectivity.getConnection().prepareStatement(sql);
			ps.setString(1, studentFormDAO.getFirstName());
			ps.setString(2, studentFormDAO.getLastName());
			ps.setString(3, studentFormDAO.getGender());
			ps.setString(4, studentFormDAO.getLevel());
			ps.setInt(5, studentFormDAO.getId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}

}
