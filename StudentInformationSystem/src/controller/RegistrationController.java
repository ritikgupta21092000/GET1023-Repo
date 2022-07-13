package controller;

import java.util.Map;

import dao.RegistrationDao;
import dao.RegistrationDaoImplInDatabase;
import model.Course;
import model.Registration;
import model.Student;

public class RegistrationController {
	
	RegistrationDao registrationDao = new RegistrationDaoImplInDatabase();
	StudentController studentController = new StudentController();
	CourseController courseController = new CourseController();
	
	public Map<Student, Course> viewAllRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void registration(Student student, Course course) {
		
	}
	
	public String registrationDB(Registration registration) {
		String message = "";
		
		Student student = studentController.findStudentByRollNo(registration.getRollNo());
		Course course = courseController.findCourseById(registration.getCourseId());
		if (student != null) {
			if (course != null) {
				if (student.getQualification().equals(course.getEligibility())) {
					message = registrationDao.registrationDB(registration);
				} else {
					message = "You are not Eigible";
				}
			} else {
				message = "Course not found!";
			}
		} else {
			message = "Student not found!";
		}
		return message;
	}
}
