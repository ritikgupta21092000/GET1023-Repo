package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.OracleConnection;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentDaoImplDatabase implements StudentDao {
	
	Connection conn;
	PreparedStatement ps;
	
	public StudentDaoImplDatabase() {
		conn = OracleConnection.getConnection();
	}

	@Override
	public String addNewStudent(Student student) {
		int count = 0;
		String message = "";
		
		String sql = "insert into tbl_students values(seq_stud.nextval,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setDate(2, Date.valueOf(student.getDateOfBirth()));
			ps.setString(3, student.getQualification().name());
			ps.setString(4, student.getPhoneNo());
			ps.setString(5, student.getEmail());
			ps.setString(6, student.getAddress());
			
			count = ps.executeUpdate();
			
			if (count > 0) {
				message = "New Student Added Successfully";
			} else {
				message = "Error Occured!";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public void updateStudentProfile(Student student) {
		int count = 0;
		String message = "";
		String sql = "update tbl_students set phoneNo=? where rollno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getPhoneNo());
			ps.setInt(2, student.getRollNo());
			count = ps.executeUpdate();
			if (count > 0) {
				message = "Student Updated Successfully";
			} else {
				message = "Student is not Updated";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(message);

	}

	@Override
	public boolean registration(Registration registration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student findStudentByRollNo(int rollNo) {
		String sql = "select * from tbl_students where rollno=?";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rollNo);
			rs = ps.executeQuery();
			
			Student st = null;
			if (rs.next()) {
				st = new Student();
				st.setRollNo(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setDateOfBirth(rs.getDate(3).toLocalDate());
				st.setQualification(Qualification.valueOf(rs.getString(4)));
				st.setPhoneNo(rs.getString(5));
				st.setEmail(rs.getString(6));
				st.setAddress(rs.getString(7));
			}
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Student> viewAllStudents() {
		String sql = "select * from tbl_students";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			//1. Fetch each row data
			//2. Wrap in a student object
			//3. Add student object in collection
			//4. Return the collection
			List<Student> students = new ArrayList<Student>();
			Student st = null;
			while (rs.next()) {
				st = new Student();
				st.setRollNo(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setDateOfBirth(rs.getDate(3).toLocalDate());
				st.setQualification(Qualification.valueOf(rs.getString(4)));
				st.setPhoneNo(rs.getString(5));
				st.setEmail(rs.getString(6));
				st.setAddress(rs.getString(7));
				students.add(st);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
