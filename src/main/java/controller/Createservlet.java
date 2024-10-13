package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SystemDao;

/**
 * Servlet implementation class Createservlet
 */
@WebServlet("/Createservlet")
public class Createservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Createservlet() {
        super();
        dao=new SystemDao();
        
    }
private SystemDao dao;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salt=dao.getAlphaNumericString(16);
		String nusername=request.getParameter("newusername");
		String name=request.getParameter("newname");
		String surname=request.getParameter("newsurname");
		String password=request.getParameter("newpassword1")+salt;
		System.out.println("pass = "+password);
		String role=request.getParameter("role");
		System.out.println("Role= "+role);
		System.out.println(nusername);
		String usernamevalidation=dao.signupusernameCheck(nusername);
		if (usernamevalidation=="ok") 
		{		MessageDigest digest;
			try {
					digest = MessageDigest.getInstance("SHA-1");
					byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
					password=dao.bytesToHex(encodedhash);
					password =""+password;
					System.out.println("pass = "+password);
					HttpSession session = request.getSession();
					dao.Signup(nusername, name, surname, role, password, salt);
					synchronized(session) 
					{
						
						session.setAttribute("username", nusername);
						session.setAttribute("name",name);
						session.setAttribute("surname", surname);
						session.setAttribute("role", role);

						if (role.equals("secretary")) 
						{
							request.setAttribute("role", "secretary");
							request.setAttribute("username",nusername);
							RequestDispatcher dispatcher =request.getRequestDispatcher("homeSecretary.html");
							dispatcher.forward(request, response);
						}
						else if (role.equals("student")) 
						{
//							System.out.println("role= "+role);
							request.setAttribute("role", "student");
							request.setAttribute("username",nusername);
							RequestDispatcher dispatcher =request.getRequestDispatcher("student.jsp");
							dispatcher.forward(request, response);
						}
						else if (role.equals("professor"))
						{
							request.setAttribute("role", "professor");
							request.setAttribute("username",nusername);
							RequestDispatcher dispatcher =request.getRequestDispatcher("Professor.jsp");
							dispatcher.forward(request, response);
						}
					}	
				}
			catch (NoSuchAlgorithmException e) 
					{
						e.printStackTrace();
					}
		
		
	}
		else {
			request.setAttribute("message", usernamevalidation);
			request.setAttribute("user", nusername);
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}	
	}
}


