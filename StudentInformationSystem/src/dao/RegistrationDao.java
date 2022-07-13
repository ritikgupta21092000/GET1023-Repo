package dao;

import java.util.Map;

import model.Course;
import model.Registration;
import model.Student;

public interface RegistrationDao {
	Map<Student, Course> viewAllRegistrations();
	void registration(Student student, Course course);
	public String registrationDB(Registration registration);
}
