package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class Secretaryservlet
 */
@WebServlet("/Secretaryservlet")
public class Secretaryservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;   
	
	public void init() throws ServletException{
		try {
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());}
		}
   
    public Secretaryservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<head><title>INSERT COURSE</title></head>");
		
			try {
				Connection con = datasource.getConnection();
				PreparedStatement preparedStatement1 = con.prepareStatement("INSERT INTO mydb.courses (Name, semester, PROFESSOR_ProID) values (?, ?, ?)");
				String Name1 = request.getParameter("Name");
				int semester1 = Integer.parseInt(request.getParameter("Semester"));
				int PROFESSOR_ProID1 = Integer.parseInt(request.getParameter("PROFESSOR_ProID"));
				preparedStatement1.setString(1, Name1);
				preparedStatement1.setInt(2, semester1);
				preparedStatement1.setInt(3, PROFESSOR_ProID1);
				preparedStatement1.executeUpdate();
				System.out.println("insert is done");
				con.close();
				request.getRequestDispatcher("homeSecretary.html").forward(request,response);
			} catch(Exception e) {
				out.println("Database connection problem");
			}
		

	}
	


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	
}
}
		

