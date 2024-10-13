package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class CourseServlet
 */
@WebServlet("/Courseservlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    
	private DataSource datasource = null;
	public void init() throws ServletException{
		try {
	
			InitialContext ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch(Exception e) {
			throw new ServletException(e.toString());}
		}
	 public CourseServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title> ¡‘¡Àœ√œ” Ã¡»«Ã¡‘ŸÕ</title></head>");
		out.println("<body>");
		
		
		try {
			Connection con = datasource.getConnection();
			Statement stmt = con.createStatement();
	
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<th>id Ã·ËﬁÏ·ÙÔÚ</th>");
			out.println("<th>ºÌÔÏ·</th>");
			out.println("<th>≈Ó‹ÏÁÌÔ</th>");
			out.println("<th>id  ·ËÁ„ÁÙﬁ</th>");
			out.println("</tr>");

			ResultSet rs = stmt.executeQuery("SELECT * FROM mydb.courses");
		
			while(rs.next()) {
				int code = rs.getInt("CouID");
				String name = rs.getString("Name");
				String semester = rs.getString("Semester");
				int pro_id = rs.getInt("PROFESSOR_ProID");
				String htmlRow = createHTMLRow(code, name, semester,pro_id);
				out.println(htmlRow);
			
			}
			rs.close();
			
			con.close();
			
		} catch(Exception e) {
			out.println("Database connection problem");
		}
	}

	private String createHTMLRow(int code, String name, String semester, int pro_id) {
		String row = "<tr>";
		row  += "<td>" + code + "</td>";
		row  += "<td>" + name + "</td>";
		row  += "<td>" + semester + "</td>";
		row  += "<td>" + pro_id + "</td>";
		row +="</tr>";
		return row;
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

