package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;




/**
 * Servlet implementation class FormateurWelcomePageServlet
 */
@WebServlet("/formateurwelcomepage")
public class FormateurWelcomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(FormateurWelcomePageServlet.class.getName());
	
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/formateurwelcomepage.jsp").forward(request, response);

	
		
		String id = request.getParameter("idUser");
		
		UserJPA user = null;
		
		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();

			// userJPA user = em.find(UserJPA.class, Integer.parseInt(id));
			
			// LEFT JOIN FETCH => attention consommation mémoire par jointure
			user = em
					.createQuery("SELECT e FROM UserJPA e "
							+ "	WHERE e.id = :id", UserJPA.class)
					.setParameter("id", id).getSingleResult(); // TODO
																					// TRY
			// on force hibernate a faire la requête => moins de consommation mémoire mais plus de requêtes émises
			user.getIdUser();
			

			// LOGGER.info(String.format("Found %s", user));																// CATCH
																					// NoResultException
			utx.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException | javax.transaction.RollbackException e) {
			LOGGER.log(Level.INFO, "Transaction failed", e);
			// TODO afficher message d'erreur
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
				// TODO afficher message d'erreur
			}

		}
		if(user == null) {
			LOGGER.info(String.format("%s user is null", user));
			response.sendError(404, "Page not found");
			return;
		}
		
		LOGGER.info(String.format("##### HERE #####"));
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/formateurwelcomepage.jsp")
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
