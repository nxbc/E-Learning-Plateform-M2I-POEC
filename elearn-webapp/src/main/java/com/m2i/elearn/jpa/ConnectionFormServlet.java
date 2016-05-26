package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;


/**
 * Servlet implementation class ConnectionFormServlet
 */
@WebServlet(name = "connectionform", urlPatterns = { "/welcome/connectionform" })
public class ConnectionFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(ConnectionFormServlet.class.getName());

	@PersistenceUnit(unitName = "ElearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//EntityManager em = emf.createEntityManager();
		/*String id = (request.getPathInfo() == null ? null : request.getPathInfo().substring(1));
		
		if(! isValidEmailAddress(id)) {
			LOGGER.info(String.format("%s is invalid", id));
			response.sendError(404, "Page not found");
			return;
		}
		EntityManager em = emf.createEntityManager();
		UserJPA user = em.find(UserJPA.class, EmailValidator.getInstance().isValid(id));
		if(user == null) {
			LOGGER.info(String.format("user %s not found", id));
			response.sendError(404, "Page not found");
			return;			
		}*/
		/*String sqlString = "SELECT * FROM user_info WHERE mail_user = ? AND password_user = ?";
		PreparedStatement ps = con.prepareStatement(sqlString);
		ps.setString(1, mailUser);
		ps.setString(2, passwordUser);
		ResultSet rs = ps.executeQuery();*/
		/*EntityManager em = emf.createEntityManager();
		TypedQuery<UserJPA> query = 
				em.createNamedQuery("UserJPA.FIND_ALL_ORDER_BY_ID", UserJPA.class)
				  .setFirstResult(0)
				  .setMaxResults(10);	
		
		List<UserJPA> users = query.getResultList();
		request.setAttribute("user", users);*/
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = (request.getPathInfo() == null ? null : request.getPathInfo().substring(0));
		LOGGER.info(String.format("Received id=%s", id));
		
		if(! isValidEmailAddress(id)) {
			LOGGER.info(String.format("%s is invalid", id));
			response.sendError(404, "Page not found");
			return;
		}
		doGet(request, response);
	}

	/**
	 * Validates if id is an email.
	 * @param id
	 * @return true if id is an email, false otherwise
	 */
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}

}
