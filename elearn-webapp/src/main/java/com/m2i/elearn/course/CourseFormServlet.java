package com.m2i.elearn.course;

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
 * Servlet implementation class CourseForm2
 */
@WebServlet("/courseform")
public class CourseFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(CourseFormServlet.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: Elearning ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/form_course.jsp").forward(request, response);
		LOGGER.info(String.format("Ya qq1 dans le Log"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
		
		String titre = request.getParameter("titre");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String skill= request.getParameter("skill");
		String tag= request.getParameter("tag");
		String required= request.getParameter("required");
				
		LOGGER.info(String.format("Received titre=%s description=%s" , titre, description));
		LOGGER.info(String.format("Received category=%s skill=%s" , category, skill));
		LOGGER.info(String.format("Received titre=%s description=%s" , tag, required));
		
		if(titre.length() <= 10) {
			erreurs.put("erreur", "Titre invalide 10 caractère minimum");
			
		}
		if(erreurs.isEmpty() && description.length() <= 140) {//THIB remplacer 40 caractère par 20 mots
			erreurs.put("erreur", "Description invalide 20 mots minimum");
		}
		
		if(erreurs.isEmpty() && tag.length() <= 3 ){
			erreurs.put("erreur", "Tag invalide 3 caractère minimum");
		}
		
		if (!erreurs.isEmpty()) {
			request.setAttribute("erreurs", erreurs);
			request.getRequestDispatcher("/WEB-INF/form_course.jsp").forward(request, response);
			return;
		};
		
	}
}
