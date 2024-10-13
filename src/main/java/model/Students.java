package model;

public class Students extends Users {
	private int registrationNumber=0;
	private int grade=0;
	private int courseid=0;
	
	public void setRegistrationNumber(int i) {
		this.registrationNumber=i;
	}
	
	public int getRegistrationNumber() {
		return this.registrationNumber;
	}	
	public int Getgrade() {
		return this.grade;
	}
	public void SetGrade(int i) {
		this.grade=i;
	}
	
	public int getcourseid()
{
		return this.courseid;}
	public void Setcourseid(int i) {
		this.courseid=i;
	}

}

