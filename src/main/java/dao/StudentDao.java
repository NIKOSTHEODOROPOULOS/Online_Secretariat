package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;
import utilities.DbUtil;

public class StudentDao {

	private Connection connection;
	
	public StudentDao() {
        connection = DbUtil.getConnection();
    }
	
	public int getStudentRegistrationNumber(String username) {
		int registrationNumber=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT RegNum FROM mydb.student where USER_Username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	registrationNumber=rs.getInt("RegNum");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationNumber;
	}
	
	
	
	
	public Students GetStudentDetails (String username) {
		Students student=new Students();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from mydb.student where USER_Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				student.setRegistrationNumber(rs.getInt("RegNum"));
            	student.setUsername(rs.getString("USER_Username"));
            	student.setName(rs.getString("Name"));
            	student.setSurname(rs.getString("Surname"));
            	student.setRole("student");
            	//String htmlRow = createHTMLRow(code, name, surName, dept);
				//out.println(htmlRow);
            	System.out.println("PRINTING THE STUDENT: "+ student.toString());
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return student;
	}
	
	public Students GetGrades(int RegNum)
	{
		Students student=new Students();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from mydb.Grade where STUDENT_RegNum=?");
			preparedStatement.setInt(1, RegNum);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				student.SetGrade(rs.getInt("Grade"));
            	student.Setcourseid(rs.getInt("COURSES_CouID"));
            	student.setRole("student");
            	System.out.println("PRINTING THE STUDENT: ");
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return student;
	}

	}

	
	

