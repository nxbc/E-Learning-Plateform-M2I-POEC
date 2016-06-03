package com.m2i.elearn.course;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.mail.MessagingException;
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

import com.m2i.elearn.jpa.CourseJPA;

/**
 * Servlet implementation class CourseForm2
 */
@WebServlet("/courseform")
public class CourseFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(CourseFormServlet.class.getName());
	private static final String URL_HOME = "http://localhost:8080/elearn-webapp-0.1/welcome";
	
	@PersistenceUnit(unitName = "ELearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: Elearning ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/form_course.jsp").forward(request, response);
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
		
		CourseJPA course = new CourseJPA();
		course.setTitleCourse(titre);
		course.setDescriptCourse(description);
		course.setCategoryCourse(category);
		course.setSkillsCourse(skill);
		course.setTagsCourse(tag);
		
		if(required.isEmpty()) {
			course.setRequiredCourse("Aucun pré-requis");
		} else {
			course.setRequiredCourse(required);
		}
		
		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			em.persist(course);
			utx.commit();
			LOGGER.info(String.format("Course saved =%s" , titre));

			response.sendRedirect(URL_HOME);
			return;

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e){
			response.sendError(500, "Server error");

			try {
				utx.rollback();
				LOGGER.info(String.format("RollBack %s" , titre));
			} catch (IllegalStateException 	| SecurityException | SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
				response.sendError(500, "Server error");
			}
		}
	}
}
