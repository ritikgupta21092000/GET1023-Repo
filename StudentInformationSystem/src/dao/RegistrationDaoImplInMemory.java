package dao;

import java.util.HashMap;
import java.util.Map;

import model.Course;
import model.Registration;
import model.Student;

public class RegistrationDaoImplInMemory implements RegistrationDao {
	public static Map<Student, Course> registrations = new HashMap<Student, Course>();

	@Override
	public void registration(Student student, Course course) {
		registrations.put(student, course);
	}

	@Override
	public Map<Student, Course> viewAllRegistrations() {
		return registrations;
	}

	@Override
	public String registrationDB(Registration registration) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
