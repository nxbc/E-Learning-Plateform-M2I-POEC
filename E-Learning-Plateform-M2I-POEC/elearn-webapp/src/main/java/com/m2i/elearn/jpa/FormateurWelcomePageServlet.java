package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;





/**
 * Servlet implementation class FormateurWelcomePageServlet
 */
@WebServlet(name = "formateurwelcomepage", urlPatterns = { "/welcome/formateurwelcomepage/*" })
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
	
	//@Inject
	//private UserServices usersService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOGGER.info(String.format("request.getContextPath() %s", request.getContextPath()));
		LOGGER.info(String.format("request.getServletPath() %s", request.getServletPath()));
		LOGGER.info(String.format("request.getPathInfo() %s", request.getPathInfo()));
		
		LOGGER.info("es ce que la session existe "+request.getParameter("session"));
		HttpSession mysession = request.getSession();
		LOGGER.info(String.format("es ce que la session existe %s ", request.getParameter("session")));
		/* Récupération de l'objet depuis la session */
		String mailUser=(String) mysession.getAttribute( "userMail" );
		
		//String mailUser = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
		LOGGER.info(String.format("mailUser : %s", mailUser));
		
		//UserJPA user = usersService.findWithMail(mailUser);//TODO a reflechir
		
	
		if(mailUser == null) {
			LOGGER.info(String.format("%s user is null", mailUser));
			response.sendError(404, "Page not found");
			return;
		}
		LOGGER.info(String.format("##### HERE user found #####"));
		request.setAttribute("userMail", mailUser);
		request.getRequestDispatcher("/WEB-INF/formateurwelcomepage.jsp")
					.forward(request, response);
	
	}

	/**
	 * Validates an email address.
	 * @param email
	 * @return true if email is valid, false otherwise
	 */
	public static boolean isValidEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
