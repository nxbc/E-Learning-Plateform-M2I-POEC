package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;





/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;
	
	@Inject
	private UserServices usersService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String mailUser = request.getParameter("mailUser");
//		String passwordUser = request.getParameter("passwordUser");
//		boolean confirmedUser = Boolean.parseBoolean(request.getParameter("confirmedUser")); 
//		
//		UserJPA user = usersService.findWithMail(mailUser);
//		
//		LOGGER.info(String.format("Found %s user", user));
//
//		EntityManager em = emf.createEntityManager();
//		UserJPA userjpa = 
//				em.createQuery("SELECT confirmedUser FROM UserJPA WHERE confirmedUser.mailUser = :mailUser AND confirmedUser.passwordUser= :passwordUser", UserJPA.class)
//				.setParameter("mailUser", mailUser).setParameter("passwordUser",passwordUser).getSingleResult();
//				LOGGER.info(String.format("Received User = %s", userjpa));
//				LOGGER.info(String.format("Received mailUser=%s passwordUser=%s confirmedUser=%s ", mailUser, passwordUser,confirmedUser));
		request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	 
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = emf.createEntityManager();
		UserJPA user = null;
		
		String mailUser = "F"+request.getParameter("mailUser");
		String passwordUser = request.getParameter("passwordUser");	
		
		//boolean confirmedUser = Boolean.valueOf("confirmedUser");
		//String confirmedUser = request.getParameter("confirmedUser");
		//boolean confirmedUser = Boolean.parseBoolean(request.getParameter("confirmedUser")); 
		//BCrypt.checkpw(candidate, hashedValue);
		//LOGGER.info(String.format("Received mailUser=%s passwordUser=%s confirmedUser=%s ", mailUser, passwordUser,confirmedUser));
		/*
		UserJPA user = 
				em.createQuery("SELECT u FROM UserJPA u WHERE u.mailUser = :mailUser AND u.passwordUser= :passwordUser", UserJPA.class)
				.setParameter("mailUser", mailUser).setParameter("passwordUser",passwordUser).getSingleResult();
				
		LOGGER.info(String.format("Received User=%s", user));
		*/
		try{	
			user = 
					em.createQuery("SELECT u FROM UserJPA u WHERE u.mailUser = :mailUser", UserJPA.class).setParameter("mailUser", mailUser).getSingleResult();
					LOGGER.info(String.format("Received User=%s", user));
					
					LOGGER.info(String.format("Le pass clair = %s", passwordUser));
					LOGGER.info(String.format("Le pass crypt� = %s", user.getPasswordUser()));
					
					if(BCrypt.checkpw(passwordUser, user.getPasswordUser())){

						LOGGER.info(String.format("Login Servlet user is not null"));
						HttpSession mysession = request.getSession(false);
						LOGGER.info(String.format("Session avant verification"));
						if (mysession == null) {
							LOGGER.info(String.format("Session Not Yet Created"));
						    mysession = request.getSession();
						} else {
							LOGGER.info(String.format("Session Already Exist"));
						}
						LOGGER.info(String.format("avant set attribut"));
						mysession.setAttribute("userMail", mailUser);
						LOGGER.info(String.format("apr�s set attribut : %s ", mysession.getAttribute("userMail")));
						
						/* R�cup�ration de l'objet depuis la session */
						mysession.getAttribute("userMail");
						
						//request.setAttribute("session",mysession);
					//	request.getRequestDispatcher("/WEB-INF/formateurwelcomepage.jsp").forward(request, response);
						response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/welcome/formateurwelcomepage/");
					}else{
						//request.setAttribute("error", "Unknown user, please try again");
			            request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward(request, response);
					}
					
			}catch(NoResultException e){
				
				LOGGER.log(Level.INFO, "Problem using ELearningDS", e);
				request.getRequestDispatcher("/WEB-INF/ConnectionFormResult.jsp").forward(request, response);
				response.sendError(500, "Please enter a correct mail or password.");
				
				return;
			}
		
	/*
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home");
        }
        else {
            request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
*/			/*
		// verification du couple de getResultList
		if(user!=null){
			response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/welcome/connectionform/formateurwelcomepage");
		}else{
			//request.setAttribute("error", "Unknown user, please try again");
            request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward(request, response);
		}*/
    }
	

}
