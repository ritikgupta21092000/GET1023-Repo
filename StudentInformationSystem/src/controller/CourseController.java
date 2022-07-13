package controller;

import java.util.List;

import dao.CourseDao;
import dao.CourseDaoImplInDatabase;
import model.Course;

public class CourseController {
	CourseDao courseDao = new CourseDaoImplInDatabase();

	public String addNewCourse(Course course) {
		return courseDao.addNewCourse(course);
	}
	
	public Course findCourseById(int courseId) {
		return courseDao.findCourseById(courseId);
	}	

	public List<Course> viewAllCourses() {
		return courseDao.viewAllCourses();
	}
}
