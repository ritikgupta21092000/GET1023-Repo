package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.print.PrintService;

import connection.OracleConnection;
import model.Course;
import model.Registration;
import model.Student;

public class RegistrationDaoImplInDatabase implements RegistrationDao {
	
	Connection conn;
	PreparedStatement ps;
	
	public RegistrationDaoImplInDatabase() {
		conn = OracleConnection.getConnection();
	}

	@Override
	public Map<Student, Course> viewAllRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registration(Student student, Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String registrationDB(Registration registration) {
		String message = "";
		String sql = "insert into tbl_registration values(seq_reg.nextval, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(registration.getRegistrationDate()));
			ps.setInt(2, registration.getRollNo());
			ps.setInt(3, registration.getCourseId());
			
			int count = ps.executeUpdate();
			message = count > 0 ? "Registration Successfull" : "Error Occured";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

}
