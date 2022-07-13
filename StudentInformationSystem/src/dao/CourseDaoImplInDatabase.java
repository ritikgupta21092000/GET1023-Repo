package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.OracleConnection;
import model.Course;
import model.Qualification;
import model.Student;

public class CourseDaoImplInDatabase implements CourseDao {
	
	Connection conn;
	PreparedStatement ps;
	
	public CourseDaoImplInDatabase() {
		conn = OracleConnection.getConnection();
	}

	@Override
	public String addNewCourse(Course course) {
		String message = "";
		String sql = "insert into tbl_courses values(seq_crs.nextval, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setInt(2,  course.getDuration());
			ps.setDouble(3, course.getFee());
			ps.setString(4, course.getEligibility().name());
			
			int count = ps.executeUpdate();
			
			if (count > 0) {
				message = "Course Added Successfully";
			} else {
				message = "Error Occured";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
	}

	@Override
	public Course findCourseById(int courseId) {
		String sql = "select * from tbl_courses where courseId=?";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			
			Course c = null;
			if (rs.next()) {
				c = new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				c.setDuration(rs.getInt(3));
				c.setFee(rs.getDouble(4));
				c.setEligibility(Qualification.valueOf(rs.getString(5)));
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

	@Override
	public List<Course> viewAllCourses() {
		String sql = "select * from tbl_courses";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			//1. Fetch each row data
			//2. Wrap in a student object
			//3. Add student object in collection
			//4. Return the collection
			List<Course> courses = new ArrayList<Course>();
			Course c = null;
			while (rs.next()) {
				c = new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				c.setDuration(rs.getInt(3));
				c.setFee(rs.getDouble(4));
				c.setEligibility(Qualification.valueOf(rs.getString(5)));
				courses.add(c);
			}
			return courses;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
