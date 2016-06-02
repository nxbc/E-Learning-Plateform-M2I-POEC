package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Récupération de la session depuis la requête */
	    /* Récupération et destruction de la session en cours */
 
    
		 //response.setContentType("text/html");  
         //PrintWriter out=response.getWriter();  
           
         //request.getRequestDispatcher("/WEB-INF/WelcomePage.jsp").include(request, response);  
         
         //HttpSession session=request.getSession(); 
         //LOGGER.info(String.format("es ce que la session existe : %s ", session.getAttribute("userMail")));
        // session.invalidate();  
         //LOGGER.info(String.format("es ce que la session existe tjr %s ", request.getParameter("session"))); 
         //out.print("You are successfully logged out!");  
           
         //out.close(); 
   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	try{
		if( session.getAttribute("userMail")!=null){
		LOGGER.info(String.format("es ce que la session existe tjr %s ", session.getAttribute("userMail")));
		response.setHeader("Cache-Control","private,no-cache , must-revalidate");
		response.setHeader("Cache-Control","private,no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		session.invalidate();
		 /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward( request, response );

		//request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward(request, response);
		LOGGER.info(String.format("es ce que la session existe tjr %s ", request.getParameter("session"))); 
        //response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/welcome");
        }else{
        	//request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp").forward(request, response);
        }
	}catch(Exception e){
		//response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/login");
	}
	}

}
