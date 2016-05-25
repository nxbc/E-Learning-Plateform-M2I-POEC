package com.m2i.elearn.register;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RegisterFormServlet
 */
@WebServlet("/RegisterForm")
public class RegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(RegisterFormServlet.class.getName());

	private static final String CHAMP_USER = "mail_user";

	private static final String CHAMP_PASS = "password";

	private static final String ATT_ERREURS = "erreurs";

	private static final String ATT_RESULTAT = "resultat";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		request.getRequestDispatcher("/WEB-INF/RegisterForm.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
	
			/* Récupération des champs du formulaire. */
		 	String mail_user = request.getParameter( CHAMP_USER );
	        String password = request.getParameter( CHAMP_PASS );
	       
	        /* Validation du champ email */
	        try {
	            validationUser( mail_user );
	        } catch ( Exception e ) {
	            	erreurs.put( CHAMP_USER, e.getMessage() );
	            }
	        /* Validation du champ password */
	        try {   
	            validationPassword( password );
	        } catch (Exception e) {
	        		erreurs.put( CHAMP_PASS, e.getMessage() );
	        }
	        /* Initialisation du résultat global de la validation. */
	        if ( erreurs.isEmpty() ) {
	            resultat = "Succès de l'inscription.";
	        } else {
	            resultat = "Échec de l'inscription.";
	        }

	        /* Stockage du résultat et des messages d'erreur dans l'objet request */
	        request.setAttribute( ATT_ERREURS, erreurs );
	        request.setAttribute( ATT_RESULTAT, resultat );

	        this.getServletContext().getRequestDispatcher("/WEB-INF/RegisterForm.jsp").forward(request, response);
	}

			private void validationUser( String mail_user ) throws Exception{
				 if ( mail_user != null && mail_user.trim().length() != 0 ) {
				        if ( !mail_user.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				            throw new Exception( "Merci de saisir une adresse mail valide." );
				        }
				    } 
			}
	        private void validationPassword( String password ) throws Exception{
	        	 if ( password != null && password.trim().length() < 8 ) {
	        		 LOGGER.info(password + "Je suis dans le if");
	                throw new Exception( "Le mot de passe doit contenir au moins 8 caractères." );
	            }
	        	 LOGGER.info(password + "Je ne  suis pas dans le if");
	        }
		
	

}
