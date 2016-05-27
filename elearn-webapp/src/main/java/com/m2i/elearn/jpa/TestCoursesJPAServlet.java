package com.m2i.elearn.jpa;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class usersJPAServlet
 */
/**
 * * @author Seb
 *	Servlet de Test, elle permet de controler l'acces à la table MySQL ELearning.Courses et ses dependantes, en effectuant un select
 *  sur les 10 premières lignes de chaque table.
 *  Ces lignes sont affichées via la jsp testcoursejpa.jsp
 *  
 *  On accede a cette servlet de test par l'adresse "http://localhost:8080/elearn-webapp-0.1/testcoursejpa" via le GET.
 *  La methode POST n'est pas implementée car inutile. 
 *
 */
@WebServlet("/testcoursejpa")
public class TestCoursesJPAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(TestCoursesJPAServlet.class.getName());
	
	@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManager em = emf.createEntityManager();

	    /* table Course*/
		TypedQuery<CourseJPA> queryCourse = 
				em.createNamedQuery("CourseJPA.FIND_ALL_ORDER_BY_ID", CourseJPA.class)
				  .setFirstResult(0)
				  .setMaxResults(10);	
		
		List<CourseJPA> courses = queryCourse.getResultList();
		LOGGER.info(String.format("Found %d courses %n %s", courses.size(), courses));
		
		request.setAttribute("courses", courses);
		
		/* table Chapter*/
		TypedQuery<ChapterJPA> queryChapter = 
				em.createNamedQuery("ChapterJPA.FIND_ALL_ORDER_BY_ID", ChapterJPA.class)
				  .setFirstResult(0)
				  .setMaxResults(10);	
		
		List<ChapterJPA> chapters = queryChapter.getResultList();
		LOGGER.info(String.format("Found %d chapters %n %s", chapters.size(), chapters));
		
		request.setAttribute("chapters", chapters);
		
		/* table Lecture*/
		TypedQuery<LectureJPA> queryLecture = 
				em.createNamedQuery("LectureJPA.FIND_ALL_ORDER_BY_ID", LectureJPA.class)
				  .setFirstResult(0)
				  .setMaxResults(10);	
		
		List<LectureJPA> lectures = queryLecture.getResultList();
		LOGGER.info(String.format("Found %d lectures %n %s", lectures.size(), lectures));
		
		request.setAttribute("lectures", lectures);
				
		request.getRequestDispatcher("/WEB-INF/testcoursejpa.jsp")
					.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
