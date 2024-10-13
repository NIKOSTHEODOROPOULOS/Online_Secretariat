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

@WebServlet("/Studentgrades")
public class GradingStundent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;  
	public void init() throws ServletException{
		try {
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());}
		}
   
    public GradingStundent() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<head><title>INSERT GRADE</title></head>");
		
			try {
				Connection con = datasource.getConnection();
				PreparedStatement preparedStatement1 = con.prepareStatement("INSERT INTO mydb.grades (Grade,COURSES_CouID, STUDENT_RegNum ) values (?, ?, ?)");
				int grade1 = Integer.parseInt(request.getParameter("grade"));
				int CouID1 =Integer.parseInt(request.getParameter("CouID"));
				int RegNum1 = Integer.parseInt(request.getParameter("RegNum"));
				preparedStatement1.setInt(2, CouID1);
				preparedStatement1.setInt(3, RegNum1);
				preparedStatement1.setInt(1, grade1);
				preparedStatement1.executeUpdate();
				System.out.println("insert is done");
				con.close();
				request.getRequestDispatcher("homeSecretary.html").forward(request,response);
			} catch(Exception e) {
				out.println("Database connection problem");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
