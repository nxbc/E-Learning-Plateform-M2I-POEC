package com.m2i.elearn.jpa;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ConnectionUserJPAServlet
 */
@WebServlet(name = "connectionuserjpa", urlPatterns = { "/connectionuserjpa" })
public class ConnectionUserJPAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
		@PersistenceUnit(unitName="ELearningPU")
	private EntityManagerFactory emf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionUserJPAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
