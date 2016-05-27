package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
		
		String id = request.getParameter("mailUser");
		String password = request.getParameter("passwordUser");
		LOGGER.info(String.format("Received mailUser=%s passwordUser=%s ", id,password));
		/*
		UserJPA user = new UserJPA();
		user.setMailUser(id);
		user.setPasswordUser(password);
		
		if(request.getSession().getAttribute("id") != null){
			request.getSession().getAttribute("id");
		}*/
		Query query = 
				em.createNamedQuery("SELECT * from users where mail_user ' "+id+" ' and ' "+password+" '", UserJPA.class);
		LOGGER.info(String.format("Received query=%s ", query));
		
	}

}
