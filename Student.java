package com.nist.swingpractical;

import java.util.List;

public interface Student {
	public void saveStudentInfo(StudentFormDAO studentFormDAO);
	public List<StudentFormDAO> getStudents();
	public void deleteStudentData(int id);
	public List<StudentFormDAO> searchStudent(String name);
	public void updateStudentInfo(StudentFormDAO studentFormDAO);
}
