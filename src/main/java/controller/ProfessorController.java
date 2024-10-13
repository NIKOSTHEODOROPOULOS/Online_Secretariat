package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProfessorDao;

@WebServlet("/professor")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private ProfessorDao professorDao;   
	 private model.Professor Professor;
	 
    public ProfessorController() {
        super();
        professorDao = new ProfessorDao();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String role=(String) session.getAttribute("role");
		Professor= professorDao.GetProfessorDetails(username);
		System.out.println("USERNAME= "+ username + "  ROLE= "+role + "Professor NAME = "+ Professor.getName());
		session.setAttribute("name", Professor.getName());
		System.out.println("NAME +  " +Professor.getName());
		

				
		if(action.equalsIgnoreCase("viewProfessorDetails")) {
			request.setAttribute("action","viewProfessorDetails");
					
			System.out.println("Name= "+ Professor.getName());
			session.setAttribute("surname", Professor.getSurname());
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
		}
	}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
