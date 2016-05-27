package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = emf.createEntityManager();
		
		String id = request.getParameter("mailUser");
		String password = request.getParameter("passwordUser");
		
		TypedQuery<UserJPA> query = 
				em.createNamedQuery("SELECT * FROM users WHERE mail_user ' "+id+"' AND password_user '"+password+"'", UserJPA.class)
				.setParameter("i",id)
				.setParameter("password",password);
		List<UserJPA> user = query.getResultList();
		LOGGER.info(String.format("Received User=%s", user));
	/*
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home");
        }
        else {
            request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }*/

		// verification du couple de getResultList
		/*if(user!=null){
			response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/welcome/connectionform/formateurwelcomepage");
		}else{
			request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward(request, response);
		}*/
    }
	

}
