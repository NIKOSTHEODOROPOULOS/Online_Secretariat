package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

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

import dao.SystemDao;

/**
 * Servlet implementation class LoginProfessorServlet
 */
@WebServlet("/Loginprofessorservlet")
public class Loginprofessorservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemDao dao;
	private DataSource datasource = null;
    public void init() throws ServletException{
        try {
    
            InitialContext ctx = new InitialContext();
            datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
        } catch(Exception e) {
            throw new ServletException(e.toString());}
        }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginprofessorservlet() {
        super();
        dao=new SystemDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		MessageDigest digest;
		try {
		
			Connection con = datasource.getConnection();
			String username = request.getParameter("uname");
			String password = request.getParameter("psw");
			//password=dao.getSalt(username);
			//System.out.println("pass="+password);
			//digest = MessageDigest.getInstance("SHA-1");
			//byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			//password=dao.bytesToHex(encodedhash);
			//System.out.println(password);
			PreparedStatement preparedStatement =con.prepareStatement("SELECT Username FROM mydb.user WHERE Username=? and password=?");
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,password);
			//System.out.println("salt="+password);
			ResultSet rs = preparedStatement.executeQuery();
			PreparedStatement preparedStatement1 =con.prepareStatement("SELECT User_Username FROM mydb.professor WHERE User_Username=?");
			preparedStatement1.setString(1,username);
            ResultSet rs1=preparedStatement1.executeQuery();
            
			if(rs.next() && rs1.next()==true) {
				System.out.println("You logged in!");
				String role="professor";
				HttpSession session = request.getSession(true);

				synchronized(session) 
				{	
					session.setAttribute("username", username);
					session.setAttribute("role", role);
			}
				request.getRequestDispatcher("Professor.jsp").forward(request,response);
				 }
			else {
				System.out.println("You not logged in!"+password);
				out.println("<a href=index.html>TRY AGAIN PLEASE!</a>");
			}
			
		} 
			catch(SQLException e) {
					}
	}
}
