package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Loginsecretaryservlet")
public class Loginsecretaryservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private DataSource datasource = null;
    public void init() throws ServletException{
        try {
    
            InitialContext ctx = new InitialContext();
            datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
        } catch(Exception e) {
            throw new ServletException(e.toString());}
        }	
    
    public Loginsecretaryservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = datasource.getConnection();
			String username = request.getParameter("uname");
			String password = request.getParameter("psw");
			PreparedStatement preparedStatement =con.prepareStatement("SELECT Username FROM mydb.user WHERE Username=? and password=?");
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,password);
			ResultSet rs = preparedStatement.executeQuery();
			 PreparedStatement preparedStatement1 =con.prepareStatement("SELECT USER_Username FROM mydb.secretary WHERE USER_Username=?");
				preparedStatement1.setString(1,username);
	            ResultSet rs1=preparedStatement1.executeQuery();
	            
	            if(rs.next() && rs1.next()==true) {
					System.out.println("You logged in!");
					String role="secretary";
					HttpSession session = request.getSession(true);
					synchronized(session) 
					{	
						session.setAttribute("username", username);
						session.setAttribute("role", role);
				}{
				request.getRequestDispatcher("homeSecretary.html").forward(request,response);
			} }
			else {
				out.println("<a href=index.html>TRY AGAIN PLEASE!</a>");
			}
			
		} 
			catch(SQLException e) {
			}
		
	}
}
