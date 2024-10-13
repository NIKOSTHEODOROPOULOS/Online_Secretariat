package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class GradesServlet
 */
@WebServlet("/GradesServlet")
public class GradesServlet extends HttpServlet {
	private DataSource datasource = null;

	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());}
		}
	 public GradesServlet() {
	        super();
	        
	    }
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title> Student Grades</title></head>");
		out.println("<body>");
		
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
	
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<th>Grade</th>");
			out.println("<th>Course ID</th>");
			out.println("<th>Student ID</th>");
			out.println("</tr>");
			String stuID = request.getParameter("stuID");
			int sID = Integer.parseInt(stuID);
			PreparedStatement preparedStatement =con.prepareStatement("SELECT * FROM mydb.grades WHERE STUDENT_RegNum=?");
			preparedStatement.setInt(1,sID);
            ResultSet rs = preparedStatement.executeQuery();

			
			while(rs.next()) {
				int grade = rs.getInt("Grade");
				int cou_id = rs.getInt("COURSES_CouID");
				int stu_id = rs.getInt("STUDENT_RegNum");
				String htmlRow = createHTMLRow(grade, cou_id, stu_id);
				out.println(htmlRow);
			
			}
			rs.close();
			
			con.close();
			
		} catch(Exception e) {
			out.println("Database connection problem");
		}
	}

	private String createHTMLRow(int grade, int cou_id, int stu_id) {
			String row = "<tr>";
			row  += "<td>" + grade + "</td>";
			row  += "<td>" + cou_id + "</td>";
			row  += "<td>" + stu_id + "</td>";
			row +="</tr>";
			return row;
			
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
