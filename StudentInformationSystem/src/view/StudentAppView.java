package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.CourseController;
import controller.RegistrationController;
import controller.StudentController;
import model.Course;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentAppView {
	public static void main(String[] args) {

		StudentController studentController = new StudentController();
		CourseController courseController = new CourseController();
		RegistrationController registrationController = new RegistrationController();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. Student \n 2. Admin \n 3. Exit");
			int userType = scanner.nextInt();

			if (userType == 1) {
				String choice = "y";
				do {
					System.out.println(
							"1. Sign Up \n 2. Update Phone Number \n 3. View All Courses \n 4. Register for Course \n 5. Sign Out");
					int option = scanner.nextInt();
					switch (option) {
					case 1:
						System.out.println("Enter Name, Date of Birth (DD/MM/YYYY), Phone Number, Email, Address");
						String name = scanner.next();
						String dateOfBirth = scanner.next();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
						String phoneNumber = scanner.next();
						String email = scanner.next();
						String address = scanner.next();

						System.out.println("1.Master 2.Graduate 3.Intermediate 4.Matric");
						int q = scanner.nextInt();
						Qualification qualification = null;
						if (q == 1)
							qualification = Qualification.Master;
						if (q == 2)
							qualification = Qualification.Graduate;
						if (q == 3)
							qualification = Qualification.Intermediate;
						if (q == 4)
							qualification = Qualification.Matric;

						Student student = new Student(name, dob, qualification, phoneNumber, email, address);

						String message = studentController.addNewStudent(student);
						System.out.println(message);
						break;

					default:
						break;
					}

					System.out.println("Continue (y/n)");
					choice = scanner.next();

				} while (choice.equalsIgnoreCase("y"));

			} else if (userType == 2) {
				System.out.println("1. View All Students \n 2.Update Student \n 3.Find Student By ID \n 4. Add Course \n 5. View All Courses \n 6. Registration for Course \n 7. Find By Course ID");
				int option = scanner.nextInt();
				switch (option) {
				case 1:
					List<Student> students;
					students = studentController.viewAllStudents();
					Iterator<Student> studentIterator = students.iterator();
					while (studentIterator.hasNext()) {
						Student studentObj = studentIterator.next();
						System.out.println(studentObj.getRollNo() + " " + studentObj.getName() + " "
								+ studentObj.getDateOfBirth() + " " + studentObj.getQualification());

					}
					break;
				case 2:
					System.out.println("Enter Student Roll No to be Updated: ");
					int rollNo = scanner.nextInt();
					System.out.println("Enter phone number: ");
					String phoneNo = scanner.next();
					break;

				case 3:
					System.out.println("Enter Roll No of student: ");
					int studentId = scanner.nextInt();
					Student filteredStudent = studentController.findStudentByRollNo(studentId);
					System.out.println("RollNo: " + filteredStudent.getRollNo() + ", Name is: "
							+ filteredStudent.getName() + ", DOB: " + filteredStudent.getDateOfBirth());
					break;
					
				case 4:
					System.out.println("Enter Course Name, Duration, Fee, Eligibility");
					String courseName = scanner.next();
					int duration = scanner.nextInt();
					double fee = scanner.nextDouble();

					System.out.println("1.Master 2.Graduate 3.Intermediate 4.Matric");
					int q = scanner.nextInt();
					Qualification eligibility = null;
					if (q == 1)
						eligibility = Qualification.Master;
					if (q == 2)
						eligibility = Qualification.Graduate;
					if (q == 3)
						eligibility = Qualification.Intermediate;
					if (q == 4)
						eligibility = Qualification.Matric;

					Course course = new Course(courseName, duration, fee, eligibility);
					String message = courseController.addNewCourse(course);
					System.out.println(message);
					break;
				case 5:
					List<Course> courses;
					courses = courseController.viewAllCourses();
					Iterator<Course> courseIterator = courses.iterator();
					while (courseIterator.hasNext()) {
						Course courseObj = courseIterator.next();
						System.out.println((courseObj.getCourseId()) + " " + courseObj.getCourseName() + " "
								+ courseObj.getDuration() + " " + courseObj.getEligibility());

					}
					break;
				case 6:
					System.out.println("Enter Roll no and course id and registration date: ");
					int rollno = scanner.nextInt();
					int courseid = scanner.nextInt();
					String regDate = scanner.next();
					DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate regDate2 = LocalDate.parse(regDate, formatter2);
					Registration registration = new Registration(regDate2, courseid, rollno);
					String message1 = registrationController.registrationDB(registration);
					System.out.println(message1);
					break;
				case 7:
					System.out.println("Enter Course ID: ");
					int courseID = scanner.nextInt();
					Course filteredCourse = courseController.findCourseById(courseID);
					System.out.println("COURSE ID: " + filteredCourse.getCourseId() + ", Course Name is: "
							+ filteredCourse.getCourseName());
					break;
				default:
					break;
				}
			} else {
				System.exit(0);
			}
		}
	}
}
