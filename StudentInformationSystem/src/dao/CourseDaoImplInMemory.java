package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.Course;

public class CourseDaoImplInMemory implements CourseDao {
	
	public static Set<Course> courses = new HashSet<Course>(); 

	@Override
	public String addNewCourse(Course course) {
		courses.add(course);
		return "Course Added!";
	}

	@Override
	public List<Course> viewAllCourses() {
		return courses.stream().collect(Collectors.toList());
	}

	@Override
	public Course findCourseById(int courseId) {
		return courses.stream().filter(c -> c.getCourseId() == courseId).findAny().orElse(null);
	}

}
