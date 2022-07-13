package view;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.CourseDao;
import dao.CourseDaoImplInMemory;
import dao.RegistrationDao;
import dao.RegistrationDaoImplInMemory;
import dao.StudentDao;
import dao.StudentDaoImplInMemory;
import model.Course;
import model.Qualification;
import model.Student;


public class Main {
	public static void main(String[] args) {
		StudentDao dao = new StudentDaoImplInMemory();
		CourseDao courseDao = new CourseDaoImplInMemory();
		RegistrationDao registrationDao = new RegistrationDaoImplInMemory();
		Scanner scanner = new Scanner(System.in);
		
		int rollNo;
		
		Student student1 = new Student("Ritik", LocalDate.of(2000, 9, 21), Qualification.Graduate, "7666004769", "abc@abc.com", "Mumbai");
		Student student2 = new Student("Ritesh", LocalDate.of(2000, 5, 16), Qualification.Intermediate, "7643004769", "abc@abc.com", "Mumbai");
		Student student3 = new Student("Shivam", LocalDate.of(2000, 9, 15), Qualification.Graduate, "8766004769", "abc@abc.com", "Mumbai");
		Student student4 = new Student("Rohan", LocalDate.of(2000, 1, 14), Qualification.Matric, "9666004769", "abc@abc.com", "Mumbai");
		Student student5 = new Student("Ritika", LocalDate.of(2000, 12, 10), Qualification.Master, "7666004880", "abc@abc.com", "Mumbai");
		
		Course course1 = new Course("Java", 6, 4000, Qualification.Graduate);
		Course course2 = new Course("Python", 3, 5000, Qualification.Graduate);
		Course course3 = new Course("JavaScript", 7, 6000, Qualification.Matric);
		Course course4 = new Course("React", 4, 4500, Qualification.Master);
		
		dao.addNewStudent(student1);
		dao.addNewStudent(student2);
		dao.addNewStudent(student3);
		dao.addNewStudent(student4);
		dao.addNewStudent(student5);
		
		courseDao.addNewCourse(course1);
		courseDao.addNewCourse(course2);
		courseDao.addNewCourse(course3);
		courseDao.addNewCourse(course4);
		
		System.out.println("Enter Student Roll No for registration: ");
		rollNo = scanner.nextInt();
		System.out.println("Enter Course Id: ");
		int courseId = scanner.nextInt();
		
		Student stud1 = dao.findStudentByRollNo(rollNo);
		
		Course co1 = courseDao.findCourseById(courseId);
		
		
		if (stud1 != null) {
			if (co1 != null) {
				registrationDao.registration(stud1, co1);
				System.out.println("Registration Successfull");
			} else {
				System.out.println("Course Not Found!");
			}
		} else {
			System.out.println("Student Not Found!");
		}
		
		System.out.println("View All Registrations: ");
		Map<Student, Course> registrations = registrationDao.viewAllRegistrations();
		
		for(Map.Entry<Student, Course> r:registrations.entrySet()) {
			System.out.println("Inside");
			Student s = r.getKey();
			Course c = r.getValue();
			System.out.println(s.getRollNo() + " " + s.getName() + " " + c.getCourseId() + " " + c.getCourseName());
		}
		
		System.out.println("View All Courses: ");
		List<Course> courses = courseDao.viewAllCourses();
		Iterator<Course> coursesIterator = courses.iterator();
		
		while (coursesIterator.hasNext()) {
			Course courseObj = coursesIterator.next();
			System.out.println(courseObj.getCourseId() + " " + courseObj.getCourseName());
		}
		
		
		System.out.println("Enter RollNo: ");
		rollNo = scanner.nextInt();
		
		Student filteredStudent = dao.findStudentByRollNo(rollNo);
		if (filteredStudent != null) {
			System.out.println("Student of RollNo " + rollNo + " is " + filteredStudent.getName());
			//Updating the profile
			System.out.println("Enter New Name: ");
			String name = scanner.next();
			filteredStudent.setName(name);
			dao.updateStudentProfile(filteredStudent);
		} else {
			System.out.println("Student not found!");
		}
		
		List<Student> students = dao.viewAllStudents();
		
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			Student student = iterator.next();
			System.out.println(student.getRollNo() + " " + student.getName() + " " + student.getQualification() + " " + student.getDateOfBirth());
		}
		
		scanner.close();
	}
}
