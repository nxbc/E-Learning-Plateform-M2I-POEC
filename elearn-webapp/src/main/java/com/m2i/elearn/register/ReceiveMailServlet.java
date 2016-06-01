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
public class ReceiveMailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;

	private static final Logger LOGGER = Logger.getLogger(ReceiveMailServlet.class.getName());

	
	/**
	 * @author Seb
	 * 
	 * Le "Formateur" a recu un mail dans sa boite mail. Ce dernier contient un lien de confirmation de creation de compte.
	 * En cliquant sur ce lien, on arrive sur cette Servlet.
	 * Il faut dans le lien : l'id ("id"=mailUser) et la clé ("key"=confirmedKeyUser)
	 * Si le User est deja enregistré : erreur 404 (message detaillé dans la log)
	 * Sinon, une page lui indique qu'il est a present enregistré
	 * 
	 * information : la confirmation d'un "Eleve" est déja en place
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		String mailUser = request.getParameter("id");
		String confirmedKeyUser = request.getParameter("key");

		EntityManager em = emf.createEntityManager();
		
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

		request.getRequestDispatcher("/WEB-INF/RegisterConfirmedByMail.jsp").forward(request, response);
		
	}

	private void updateUserConfirmed(UserJPA user, HttpServletResponse response) {

		LOGGER.info(String.format("%s confirmed en cours...", user.getMailUser()));
		
		EntityManager em = emf.createEntityManager();
		UserJPA userLu = em.find(UserJPA.class, user.getIdUser());
		
		userLu.setConfirmedUser(true);
		
		try {
			utx.begin();
			
			em.joinTransaction();
			em.persist(userLu);
			utx.commit();

			LOGGER.info(String.format("User-confirmed update to true  mailUser=%s" , userLu.getMailUser()));

			return;

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			LOGGER.log(Level.INFO, "Transaction failed", e);
			
			e.printStackTrace();
			
			try {
				utx.rollback();
				LOGGER.info(String.format("RoolBack %s" , userLu.getMailUser()));
				
			} catch (SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
			
				e1.printStackTrace();
			}

		}
		
		// dans tous les cas anormaux , on met une erreur serveur 
		try {
			response.sendError(500, "Server Problem");
		} catch (IOException e) {
			 
			e.printStackTrace();
		}

	}

}
