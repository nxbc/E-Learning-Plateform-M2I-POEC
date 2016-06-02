package com.m2i.elearn.register;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
		try{
		if(!isValidEmail(mailUser)){
			request.getRequestDispatcher("/WEB-INF/RegisterFormResult.jsp").forward(request, response);
			//response.sendError(500,"email invalide");
		}}catch(IllegalStateException e){
			
		}
		LOGGER.info(String.format("Received mail_user=%s password_user=%s",  mailUser, passwordUser));
		// TODO valider ici
		// Si erreur => afficher formulaire + message d'erreur

		UserJPA user = new UserJPA();
		//user.setIdUser(1); sera cr�� automatiquement par la BDD
		user.setMailUser(mailUser);
		user.setPasswordUser(passwordUser);

		
		
		LOGGER.info(" Before Save UserJPA -->"+user);

		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			em.persist(user);
			utx.commit();

			LOGGER.info(String.format("User Saved mailUser=%s" , mailUser));

			//TODO si OK, on repart ?...
			response.sendRedirect(URL_ACCEUIL);

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			LOGGER.log(Level.INFO, "Transaction failed", e);
			// TODO afficher message d'erreur

			try {
				utx.rollback();
				LOGGER.info(String.format("RoolBack %s" , mailUser));
			} catch (IllegalStateException 
					| SecurityException | SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
				// TODO afficher message d'erreur
			}

		}

		LOGGER.info(String.format("Problem... Redirect... %s" , "/")); //TODO a corriger

		// PAS BON, faire une redirection !
		// request.getRequestDispatcher("/filemanager").forward(request, response);
		response.sendRedirect("/RegisterForm");

	}





	private void validationUser( String mailUser ) throws Exception{
		if ( mailUser != null && mailUser.trim().length() != 0 )  {
			if ( !mailUser.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new Exception( "Merci de saisir une adresse mail valide." );
			}
		} 
	}
	private void validationPassword( String passwordUser ) throws Exception{
		if ( passwordUser != null && passwordUser.trim().length() < 8 ) {
			//TODO verifier 
			if ( !passwordUser.matches("(!?=(.*[A-Z]){1,})(?!=(.*[0-9]){1,})")){
				LOGGER.info(passwordUser + "Je suis dans le if");
				throw new Exception( "Le mot de passe doit contenir au moins 8 caract�res avec 1 majuscule et 1 chiffre." );	 
			}
		}
		LOGGER.info(passwordUser + "Je ne suis pas dans le if");
	}

	public static boolean isValidEmail(String email) {
        String emailPattern = "^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }






}