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

import com.m2i.elearn.jpa.UsersJPAServlet;
import com.m2i.elearn.jpa.UserJPA;

/**
 * Servlet implementation class UsersJPAServlet
 */
@WebServlet("/UsersJPAServlet")
public class UsersJPAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(UsersJPAServlet.class.getName());
	
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;

	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();
	
	
	TypedQuery<UserJPA> query = 
			em.createNamedQuery("UserJPA.FIND_ALL_ORDER_BY_ID", UserJPA.class)
			  .setFirstResult(0)
			  .setMaxResults(10);	
	
	List<UserJPA> users = query.getResultList();
	LOGGER.info(String.format("Found %d users %n %s", users.size(), users));
	
	request.setAttribute("users",users);
	request.getRequestDispatcher("/WEB-INF/RegisterForm.jsp")
				.forward(request, response);
	

	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}