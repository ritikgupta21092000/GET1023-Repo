package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Registration;
import model.Student;

public class StudentDaoImplInMemory implements StudentDao {
	
	public static List<Student> students = new ArrayList<Student>();

	@Override
	public String addNewStudent(Student student) {
		students.add(student);
		return null;
	}

	@Override
	public void updateStudentProfile(Student student) {
		Student student1 = findStudentByRollNo(student.getRollNo());
		int index;
		index = students.indexOf(student1);
		students.set(index, student);
	}

	@Override
	public boolean registration(Registration registration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student findStudentByRollNo(int rollNo) {
		return students.stream().filter(st -> st.getRollNo() == rollNo).findFirst().orElse(null);
	}

	@Override
	public List<Student> viewAllStudents() {
		return students.stream().collect(Collectors.toList());
	}
	
	

}
