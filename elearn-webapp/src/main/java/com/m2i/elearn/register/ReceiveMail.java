package com.m2i.elearn.register;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.m2i.elearn.jpa.UserJPA;

/**
 * Servlet implementation class ReceiveMail
 */
@WebServlet("/receivemail")
public class ReceiveMail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String URL_ACCEUIL = "http://localhost:8080/elearn-webapp-0.1/welcome";

	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;

	private static final Logger LOGGER = Logger.getLogger(ReceiveMail.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO generer une clé id  
		String mailUser = request.getParameter("id");
		String confirmedKeyUser = request.getParameter("key");

		EntityManager em = emf.createEntityManager();
		Map<String, String> erreurs = new HashMap<String, String>();

		UserJPA userF = null;
		UserJPA userE = null;

		String mailUserF = "F"+ mailUser;
		LOGGER.info(String.format("mailUser %s key<%s>", mailUserF,confirmedKeyUser));

		try{ // Clé Formateur ?
			userF =	em.createQuery("SELECT u FROM UserJPA u WHERE u.mailUser = :mailUser AND u.confirmedKeyUser= :confirmedKeyUser", UserJPA.class)
					.setParameter("mailUser", mailUserF).setParameter("confirmedKeyUser",confirmedKeyUser).getSingleResult();
		} catch (NoResultException e){

			try{ // clé Eleve ?
				String mailUserE = "E"+ mailUser;
				LOGGER.info(String.format("mailUser %s key<%s>", mailUserE,confirmedKeyUser));
				userE = em.createQuery("SELECT u FROM UserJPA u WHERE u.mailUser = :mailUser AND u.confirmedKeyUser= :confirmedKeyUser", UserJPA.class)
						.setParameter("mailUser", mailUserE).setParameter("confirmedKeyUser",confirmedKeyUser).getSingleResult();
			}catch (NoResultException e2){
				//TODO Cette clé n'est pas valide
				LOGGER.info(String.format("%s key:%s does NOT exist on Serveur", mailUser,confirmedKeyUser));
				response.sendError(404, "Page not found");
				return;
			}
		};

		//On gère un Eleve
		if (userF == null){
			if (userE.isConfirmedUser()){
				LOGGER.info(String.format("%s already confirmed, 404", userE.getMailUser()));
				response.sendError(404, "Page not found");
				return;
			} 
			updateUserConfirmed(userE, response);

		} else {//On gère un Formateur
			if (userF.isConfirmedUser()){
				LOGGER.info(String.format("%s already confirmed, 404", userF.getMailUser()));
				response.sendError(404, "Page not found");
				return;
			}
			updateUserConfirmed(userF, response);
		}

		response.sendRedirect(URL_ACCEUIL);
		
	}

	private void updateUserConfirmed(UserJPA user, HttpServletResponse response) {

		// TODO a controler, user controlé comme non deja confirmed, mettre a jour la BDD confirmed passe a VRAI 

		LOGGER.info(String.format("%s confirmed en cours...", user.getMailUser()));
		
		EntityManager em = emf.createEntityManager();
		UserJPA userLu = em.find(UserJPA.class, user.getIdUser());
		
		userLu.setConfirmedUser(true);
		
		
		

		try {
			utx.begin();
			//EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			em.persist(userLu);
			utx.commit();

			LOGGER.info(String.format("User-confirmed update to true  mailUser=%s" , userLu.getMailUser()));

			//TODO si OK, on repart ?...
			response.sendRedirect(URL_ACCEUIL);
			return;

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException | IOException e) {
			LOGGER.log(Level.INFO, "Transaction failed", e);
			// TODO afficher message d'erreur
			e.printStackTrace();
			

			try {
				utx.rollback();
				LOGGER.info(String.format("RoolBack %s" , userLu.getMailUser()));
				
			} catch (SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
				// TODO afficher message d'erreur
				e1.printStackTrace();
			}

		}
		//TODO si KO ?
		try {
			response.sendError(500, "Server Problem");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}





}
