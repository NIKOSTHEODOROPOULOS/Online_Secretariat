package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;
import utilities.DbUtil;

public class ProfessorDao {
private Connection connection;
	
	public ProfessorDao() {
        connection = DbUtil.getConnection();
    }
	
	public int getProfassorRegistrationNumber(String username) {
		int registrationNumber=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ProID FROM mydb.professor where USER_Username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	registrationNumber=rs.getInt("ProID");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationNumber;
	}
	
	
	
	
	public Professor GetProfessorDetails (String username) {
		Professor professor=new Professor();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from mydb.professor where USER_Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				professor.setRegistrationNumber(rs.getInt("ProID"));
				professor.setUsername(rs.getString("USER_Username"));
				professor.setName(rs.getString("Professor_Name"));
				professor.setSurname(rs.getString("Professor_Surrname"));
				professor.setRole("professor");
            	//String htmlRow = createHTMLRow(code, name, surName, dept);
				//out.println(htmlRow);
            	System.out.println("PRINTING THE PROFESSOR: "+professor.toString());
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return professor;
	}

	
}
