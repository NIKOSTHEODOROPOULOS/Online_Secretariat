package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.*;
import model.*;


@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   private StudentDao studentDao;   
   private Students student;
   
    
    public StudentController() {
        super();
        studentDao = new StudentDao();
        
    }

	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String role=(String) session.getAttribute("role");
		student= studentDao.GetStudentDetails(username);
		System.out.println("USERNAME= "+ username + "  ROLE= "+role + "STUDENT NAME = "+ student.getName());
		session.setAttribute("name", student.getName());
		System.out.println("NAME +  " +student.getName());
		

				
		if(action.equalsIgnoreCase("viewStudentDetails")) {
			request.setAttribute("action","viewStudentDetails");
					
			System.out.println("Name= "+student.getName());
			session.setAttribute("surname", student.getSurname());
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
		} if (action.equalsIgnoreCase("ViewStudentGrades")){
			request.setAttribute("action","viewStudentGrades");
			System.out.println("Name= "+student.getName());
			session.setAttribute("grade", student.Getgrade());
			session.setAttribute("courseid", student.getcourseid());
		
		}		
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	
	
}


