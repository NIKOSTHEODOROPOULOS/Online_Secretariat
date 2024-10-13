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
 * Servlet implementation class Showmprofessor
 */
@WebServlet("/Showprofessor")
public class Showprofessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
    public Showprofessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			out.println("<th>ºÌÔÏ· Í·ËÁ„ÁÙﬁ</th>");
			out.println("<th>œÕœÃ¡ Ã¡»«Ã¡‘œ”</th>");
			out.println("</tr>");

			ResultSet rs = stmt.executeQuery("SELECT  professor.Professor_Name , courses.name FROM courses INNER JOIN professor ON courses.PROFESSOR_ProID = professor.ProID");
		
			while(rs.next()) {
				
				String name = rs.getString("professor.Professor_Name");
				String coursename = rs.getString("courses.name");
				String htmlRow = createHTMLRow(name, coursename);
				out.println(htmlRow);
			
			}
			rs.close();
			
			con.close();
			
		} catch(Exception e) {
			out.println("Database connection problem");
		}
	}
	

	private String createHTMLRow(String name, String coursename) {
			String row = "<tr>";
			row  += "<td>" + name + "</td>";
			row  += "<td>" + coursename + "</td>";
			row +="</tr>";
			return row;
			
		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
