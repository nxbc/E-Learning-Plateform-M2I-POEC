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

import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;

import com.m2i.elearn.jpa.UserJPA;





/**
 * Servlet implementation class RegisterFormServlet
 */
@WebServlet("/RegisterForm")
public class RegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(RegisterFormServlet.class.getName());

	@PersistenceUnit(unitName = "ELearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;

	private static final String mail_user = "mailUser";
	private static final String password_user = "passwordUser";
	private static final String ATT_ERREURS = "erreurs";

	private static final String URL_ACCEUIL = "http://localhost:8080/elearn-webapp-0.1/welcome";


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/RegisterForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> erreurs = new HashMap<String, String>();

		String mailUser = request.getParameter("mailUser");
		String passwordUser = request.getParameter("passwordUser");

		LOGGER.info(String.format("Received mail_user=%s password_user=%s",  mailUser, passwordUser));
		// TODO valider ici
		// Si erreur => afficher formulaire + message d'erreur

		//		try {   
		//			CheckingExistingMailUser(mailUser);
		//		} catch (Exception e) {
		//			erreurs.put( mail_user, e.getMessage() );
		//		}

		EntityManager em2 = emf.createEntityManager();
		UserJPA userF = null;

		String mailUserF = "F"+ mailUser;
		LOGGER.info(String.format("Check mailUser %s exist ?", mailUserF));

		try{ // Cl� Formateur
			userF =	em2.createQuery("SELECT u FROM UserJPA u WHERE u.mailUser = :mailUser", UserJPA.class)
					.setParameter("mailUser", mailUserF).getSingleResult();
		} catch (NoResultException e){
			//OK le mail de l'utilisateur n'existe pas en base
		};

		if (userF != null){
			erreurs.put( mail_user, "Adresse mail d�j� choisie par un utilisateur.");
			request.setAttribute( ATT_ERREURS, erreurs );
			request.getRequestDispatcher("/WEB-INF/RegisterForm.jsp").forward(request, response);
			return;
		}



		UserJPA user = new UserJPA();
		//user.setIdUser(1); sera cr�� automatiquement par la BDD
		user.setMailUser("F"+mailUser);
		
		String passwordUserCrypt = BCrypt.hashpw(passwordUser, BCrypt.gensalt(12));
		user.setPasswordUser(passwordUserCrypt);
		LOGGER.info(String.format(" User %s, password %s, passwordBcrypt %s",mailUser,passwordUser,passwordUserCrypt));

		//TODO crypter la cl�
		String md5 = DigestUtils.md5Hex( mailUser );
		user.setConfirmedKeyUser(md5);
		LOGGER.info(" User/key "+mailUser + "/key:"+md5);

		Mail mailConfirm = null;

		/* Validation du champ email */
		try {
			validationMailUser( mailUser );
		} catch ( Exception e ) {
			erreurs.put( mail_user, e.getMessage() );
		}
		/* Validation du champ password */
		try {   
			validationPasswordUser( passwordUser );
		} catch (Exception e) {
			erreurs.put( password_user, e.getMessage() );
		}


		/* Stockage du r�sultat et des messages d'erreur dans l'objet request */
		request.setAttribute( ATT_ERREURS, erreurs );


		if (!erreurs.isEmpty()){
			request.getRequestDispatcher("/WEB-INF/RegisterForm.jsp").forward(request, response);
			return;
		};

		LOGGER.info(" Before Save UserJPA -->"+user);

		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			em.persist(user);


			mailConfirm = new Mail(mailUser,
					"Valider Inscription Formateur",
					"Bonjour, veuillez cliquer sur le lien suivant pour confirmer votre inscription. Cordialement \n "
							+ "http://localhost:8080/elearn-webapp-0.1/receivemail?id="+mailUser
							+ "&key="+user.getConfirmedKeyUser());

			mailConfirm.sendMail();

			utx.commit();

			LOGGER.info(String.format("User Saved mailUser=%s" , mailUser));

			//TODO si OK, on repart ?...
			response.sendRedirect(URL_ACCEUIL);
			return;

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException | MessagingException e) {
			LOGGER.log(Level.INFO, "Transaction failed", e);
			// TODO afficher message d'erreur

			try {
				utx.rollback();
				LOGGER.info(String.format("RoolBack %s" , mailUser));
			} catch (IllegalStateException 	| SecurityException | SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
				// TODO afficher message d'erreur
			}

		}

		LOGGER.info(String.format("Problem... Redirect... %s" , "/")); //TODO a corriger

		// PAS BON, faire une redirection !
		// request.getRequestDispatcher("/filemanager").forward(request, response);
		response.sendRedirect("/RegisterForm");


	}
	private void validationMailUser( String mailUser )throws Exception{
		if (mailUser == null){
			throw new Exception( "Merci de saisir une adresse mail valide." );
		}
		if ( !mailUser.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			throw new Exception( "Merci de saisir une adresse mail valide." );
		}
	} 

	private void validationPasswordUser( String passwordUser ) throws Exception{

		boolean Error = false;
		String Message = "";
		if ( passwordUser != null && passwordUser.trim().length() < 8 ) {
			Error=true;
			Message += " 8 caract�res";
		}
		if ( !passwordUser.matches("[^A-Z]*[A-Z]+[^A-Z]*")){
			Error=true;
			Message += " 1 majuscule";	
		}	
		if ( !passwordUser.matches("[^0-9]*[0-9]+[^0-9]*")){
			Error=true;
			Message += "  1 chiffre";
		}
		if (Error){
			throw new Exception( "Le mot de passe doit contenir au moins:"+ Message  );
		}	
	}

}

