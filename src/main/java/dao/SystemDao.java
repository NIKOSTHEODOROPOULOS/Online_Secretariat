package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class SystemDao {

	private Connection connection;
	
	public SystemDao() {
        connection = utilities.DbUtil.getConnection();
    }
	
	
	public String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
	public String getSalt(String username) {
		String salt=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT salt FROM mydb.user where Username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	salt=rs.getString("salt");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salt;
	}
	
	public String loginusernameCheck(String username) {
		String answer=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.user where Username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false) 
            { 
            	 answer="There is no user with the username: "+username+", please enter a valid username!";
        	} 
            else 
            { 
            	answer=username;
        	}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }
	
	
	public String signupusernameCheck(String username) {
		String answer=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.user where Username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false) 
            { 
            	 answer="ok";
        	} 
            else 
            { 
            	answer="There is already a user with the username: "+username+", please enter a different username.";
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }
	
		public String passwordCheck(String username,String password) {
		String answer=null;
		try {
            	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.user where (Username=? and hash=?)");
            	preparedStatement.setString(1, username);
            	preparedStatement.setString(2, password);
            	ResultSet rs = preparedStatement.executeQuery();
            	if (rs.next() == false) 
            	{ 
            		answer="Wrong Password!";
            	} 
            	else 
            	{ 
            		answer="You logged in!";
            	}

        	} catch (SQLException e) {
            e.printStackTrace();
        	}
        return answer;
	}

public String getRole(String username) {
	String role=null;
	try {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.student WHERE USER_Username=?");
        preparedStatement.setString(1, username);
        ResultSet rs1 = preparedStatement.executeQuery();
        if (rs1.next()) 
        { 
        	 role="student";
    	} 
        else 
    	{
    		preparedStatement = connection.prepareStatement("SELECT * FROM mydb.professor WHERE USER_Username=?");
    		preparedStatement.setString(1, username);
    		ResultSet rs2 = preparedStatement.executeQuery();
    		
    		if (rs2.next()==true) 
            { 
            	 role="professor";
        	} 
    		else 
        	{
        		 role="secretary";
        		 
        	}
       
    	}
            
    } catch (SQLException e) {
        e.printStackTrace();
    }
	return role;
}


public void Signup(String username, String name, String surname, String role, String password, String salt) 
{
	try {
		
		if(role.equals("student")) 
		{
						
			PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO mydb.user (Username,Role,hash,salt) values (?,?,?,?)");
			preparedStatement1.setString(1, username);
			preparedStatement1.setString(2, role);
			preparedStatement1.setString(3, password);
			preparedStatement1.setString(4, salt);
			preparedStatement1.executeUpdate();
			System.out.println("insert is done");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mydb.student (Name,Surname,USER_Username) values (?,?,?)");

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, username);
			preparedStatement.executeUpdate();
			System.out.println("student insert done");
			
		
		}
		else if (role.equals("professor"))
		{
			PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO mydb.user (Username,Role,hash,salt) values (?,?,?,?)");
			preparedStatement1.setString(1, username);
			preparedStatement1.setString(2, role);
			preparedStatement1.setString(3, password);
			preparedStatement1.setString(4, salt);
			preparedStatement1.executeUpdate();
			System.out.println("insert is done");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mydb.professor (Professor_Name,Professor_Surrname,USER_Username) values (?,?,?)");

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, username);
			preparedStatement.executeUpdate();
			System.out.println("professor insert done");
		}
		else if (role.equals("secretary"))
		{
			PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO mydb.user (Username,Role,hash,salt) values (?,?,?,?)");
			preparedStatement1.setString(1, username);
			preparedStatement1.setString(2, role);
			preparedStatement1.setString(3, password);
			preparedStatement1.setString(4, salt);
			preparedStatement1.executeUpdate();
			System.out.println("insert is done");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mydb.secretary (Name,USER_Username) values (?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			System.out.println("secretary insert done");
		}
		
	}catch(SQLException e) {
        e.printStackTrace();
    }		
}


}
