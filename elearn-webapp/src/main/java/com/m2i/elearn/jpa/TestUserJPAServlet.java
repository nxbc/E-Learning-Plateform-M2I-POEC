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
 * Servlet implementation class usersJPAServlet
 */
/**
 * * @author Seb
 *	Servlet de Test, elle permet de controler l'acces à la table MySQL ELearning.Users en effectuant un select
 *  sur les 10 premières lignes de cette table.
 *  Ces lignes sont affichées via la jsp testuserjpa.jsp
 *  
 *  On accede a cette servlet de test par l'adresse "http://localhost:8080/elearn-webapp-0.1/testuserjpa" via le GET.
 *  La methode POST n'est pas implementée car inutile. 
 *
 */
@WebServlet("/testuserjpa")
public class TestUserJPAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(TestUserJPAServlet.class.getName());
	
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManager em = emf.createEntityManager();

	
		TypedQuery<UserJPA> query = 
				em.createNamedQuery("UserJPA.FIND_ALL_ORDER_BY_ID", UserJPA.class)
				  .setFirstResult(0)
				  .setMaxResults(10);	
		
		List<UserJPA> users = query.getResultList();
		LOGGER.info(String.format("Found %d users %n %s", users.size(), users));
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/testuserjpa.jsp")
					.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
