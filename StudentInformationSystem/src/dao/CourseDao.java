package dao;

import java.util.List;

import model.Course;

public interface CourseDao {
	String addNewCourse(Course course);
	Course findCourseById(int courseId);
	List<Course> viewAllCourses();
}
