package com.m2i.elearn.jpa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestConnection
 */
@WebServlet(name = "testconnection", urlPatterns = { "/testconnection" })
public class TestConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/ConnectionForm.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("mailUser"); 

		String pwd = request.getParameter("passwordUser"); 

		String sql = "SELECT * from users where mail_user ' "+login+" ' and ' "+pwd+" ' "; // les espaces ici sont justes pour la visibilité 

		//int i=0;// pour tester l'existence du user 

		String myusername=""; 
		String mypassword="";

		try{ 

		

		Connection con = DriverManager.getConnection("java:jboss/datasources/ELearningDS"); 

		Statement stmt = con.createStatement(); 

		ResultSet rs = stmt.executeQuery(sql); 

		// si le rs est vide, le programme echappe a partir d'ici 

		while(rs.next()){ 

		// normalement il doit y avoir 1 seul enregistrement, c'est ce ke je suppose

		//i++; // i!=0 il y a donc un user avec ce login 

		myusername = request.getParameter("mailUser"); 
		mypassword = request.getParameter("passwordUser");
		} 

		} 

		catch(Exception ex){ ex.printStackTrace();} 

		//if (i==0){ 

		// login inexistant 
			//response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/testconnection");
		//} 

		//else { 

		if ((mypassword.equals(pwd))&& (myusername.equals(login))){ 

		// les actions s'il est reconnu 
			response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/welcome/connectionform/formateurwelcomepage"); 
		} 

		else { 

		response.sendRedirect("http://localhost:8080/elearn-webapp-0.1/testconnection"); 

		} 

		

		
	
	}

}
