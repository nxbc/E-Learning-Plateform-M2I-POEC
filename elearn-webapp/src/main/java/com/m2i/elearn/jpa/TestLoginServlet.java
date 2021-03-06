package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







/**
 * Servlet implementation class TestLoginServlet
 */
@WebServlet(name = "testlogin", urlPatterns = { "/testlogin" })
public class TestLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(TestLoginServlet.class.getName());

	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		String mailUser = request.getParameter("mailUser");
		String passwordUser = request.getParameter("passwordUser");
		LOGGER.info(String.format("Received mailUser=%s passwordUser=%s ", mailUser, passwordUser));
		
		/*
		UserJPA user = new UserJPA();
		user.setMailUser(id);
		user.setPasswordUser(password);
		
		if(request.getSession().getAttribute("id") != null){
			request.getSession().getAttribute("id");
		}*/
		try{	
		UserJPA user = 
				em.createQuery("SELECT u FROM UserJPA u WHERE u.mailUser = :mailUser AND u.passwordUser= :passwordUser", UserJPA.class)
				.setParameter("mailUser", mailUser).setParameter("passwordUser",passwordUser).getSingleResult();
				LOGGER.info(String.format("Received User=%s", user));
				
				if(user!=null){
					response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/welcome/formateurwelcomepage/${ e.idUser }");
				}else{
					//request.setAttribute("error", "Unknown user, please try again");
		            request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward(request, response);
				}
				
		}catch(NoResultException e){
			LOGGER.log(Level.INFO, "Problem using ELearningDS", e);
			request.getRequestDispatcher("/WEB-INF/ConnectionFormResult.jsp").forward(request, response);
			//response.sendError(500, "Veuillez entrer le bon mot de passe ou identifiant");
			
			return;
		}
	}	

}
