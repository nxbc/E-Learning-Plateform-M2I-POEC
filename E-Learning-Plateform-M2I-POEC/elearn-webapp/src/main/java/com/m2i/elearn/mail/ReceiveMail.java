package com.m2i.elearn.mail;

import java.io.IOException;
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

import com.m2i.elearn.jpa.UserJPA;

/**
 * Servlet implementation class ReceiveMail
 */
@WebServlet("/receivemail")
public class ReceiveMail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;

	private static final Logger LOGGER = Logger.getLogger(ReceiveMail.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO generer une clé id  
		String mailUser = request.getParameter("id");
		String confirmedKeyUser = request.getParameter("key");

		//TODO test avant que la servlet creation de mail soit OK
		mailUser="a@b.fr";
		confirmedKeyUser="123456789";


		
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

		//TODO refactoriser (attention a "reponse")
		if (userF == null){
			if (userE.isConfirmedUser()){
				LOGGER.info(String.format("%s already confirmed, 404", userE.getMailUser()));
				response.sendError(404, "Page not found");
				return;
			} 
			updateUserConfirmed(userE);
			
		} else {
			if (userF.isConfirmedUser()){
				LOGGER.info(String.format("%s already confirmed, 404", userF.getMailUser()));
				response.sendError(404, "Page not found");
				return;
			}
			updateUserConfirmed(userF);
		}
		
		


		response.getWriter().append("ReceiveMail Confirmed ").append(request.getContextPath());
	}


	private void updateUserConfirmed(UserJPA user) {

		// TODO a controler, user controlé comme non deja confirmed, mettre a jour la BDD confirmed passe a VRAI 
		
		LOGGER.info(String.format("%s confirmed a faire", user.getMailUser())); 



	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}





}