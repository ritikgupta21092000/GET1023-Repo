package model;

public class Course {
	private int courseId;
	private String courseName;
	private int duration;
	private double fee;
	private Qualification eligibility;

	public Course() {
		
	}
	public Course(String courseName, int duration, double fee2, Qualification eligibility) {
		this.courseName = courseName;
		this.duration = duration;
		this.fee = fee2;
		this.eligibility = eligibility;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Qualification getEligibility() {
		return eligibility;
	}

	public void setEligibility(Qualification eligibility) {
		this.eligibility = eligibility;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	

}
