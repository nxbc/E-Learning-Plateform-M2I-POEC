package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.logging.Level;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UpdateCourseServlet
 */
@WebServlet("/createcourse")
public class CreateCourseServlet extends HttpServlet {
	
	@PersistenceUnit(unitName = "ELearningPU")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction utx;
	
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CreateCourseServlet.class.getName());

	
	/**
	 * DoGet inutile
	 */
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 *  @author Seb
	 *  - Recupère les parametres pour la creation d'un cours dans le lien HTTP
	 *  - Recupère l'idUser du Formateur identifié actuellemnt
	 *  - Sauvegarde le cours dans la base Course
	 *  
	 *  -> reste a definir les évènements a déclencher quand il y a des erreur (suivre les TO DO)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		String titleCourse = data.get("titleCourse").getAsString();
		String descriptCourse = data.get("descriptCourse").getAsString();
		String skillsCourse = data.get("skillsCourse").getAsString();
		
		LOGGER.info(String.format("***JSON recup DATA titleCourse:%s descriptCourse:%s skillsCourse:%s", titleCourse,descriptCourse,skillsCourse));
		
		
		String titre = request.getParameter("titre");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String skill = request.getParameter("skill");
		String tag = request.getParameter("tag");
		String required = request.getParameter("required");


		//TODO verifier les entrees
		if (!validateParameter()){
			//TODO gerer 'validateParameter' l'erreur (+saut vers jsp)
		}

		int idUser = 0;
		retreiveIdUser(idUser);
		
		if (idUser == 0){
			//TODO gerer 'retreiveIdUser' l'erreur (+saut vers jsp)
		}
			
		
		//creation du JPA + identifiants
		CourseJPA course = new CourseJPA();
		course.setIdUser(idUser);
		//course.setIdCourse(idCourse); //pas besoin car gere en automatique sur mySQL (+1 auto)
		

		//+ champs de Courses
		course.setTitleCourse(titre);
		course.setDescriptCourse(description);
		course.setCategoryCourse(category);
		course.setSkillsCourse(skill);
		course.setTagsCourse(tag);
		course.setRequiredCourse(required);

		LOGGER.info(" avant sauvegarde -->"+course);
		
		try {
			utx.begin();
			EntityManager em = emf.createEntityManager();
			em.joinTransaction();
			em.persist(course);
			utx.commit();
			
			LOGGER.info(String.format("Course titre=%s user=%s saved" , titre, idUser));
			
			//TODO si OK, on repart ?...
			//response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/acceuil");
			
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			LOGGER.log(Level.INFO, "Transaction failed", e);
			// TODO afficher message d'erreur
		
			try {
				utx.rollback();
				LOGGER.info(String.format("RoolBack %s" , titre));
			} catch (IllegalStateException 
					| SecurityException | SystemException e1) {
				LOGGER.log(Level.INFO, "Transaction rollback failed", e1);
				// TODO afficher message d'erreur
			}
			
		}

		LOGGER.info(String.format("Problem... Redirect... %s" , "/"));

		// PAS BON, faire une redirection !
		// request.getRequestDispatcher("/filemanager").forward(request, response);
		response.sendRedirect("/");
	}

	private void retreiveIdUser(int idUser) {
		// TODO recuperer l'idUser du user (login doit etre ok) 
		idUser = 1; //TODO pour l'instant on force le idUser manuellement (il doit exister dans la base ELearning.User 
		//return null
	}

	private boolean validateParameter() {
		// TODO mettre les contraintes sur les parametres recus via l'URL
		return true;
	}

}
