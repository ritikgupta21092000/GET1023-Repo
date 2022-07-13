package controller;

import java.util.List;

import dao.StudentDao;
import dao.StudentDaoImplDatabase;
import exception.PhoneException;
import model.Registration;
import model.Student;

public class StudentController {
	
	StudentDao studentDao = new StudentDaoImplDatabase();
	
	public String addNewStudent(Student student) {
		if (student.getPhoneNo().length() != 10) {
			try {
				throw new PhoneException("Invalid Phone Number");
			} catch (PhoneException e) {
				return e.getMessage();
			}
		}
		String message = studentDao.addNewStudent(student);
		return message;
	}

	public void updateStudentProfile(Student student) {

	}

	public boolean registration(Registration registration) {
		return false;
	}

	public Student findStudentByRollNo(int rollNo) {
		return studentDao.findStudentByRollNo(rollNo);
	}

	public List<Student> viewAllStudents() {
		return studentDao.viewAllStudents();
		
	}

}
