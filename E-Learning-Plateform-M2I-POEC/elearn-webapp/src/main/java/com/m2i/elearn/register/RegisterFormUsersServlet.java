package com.m2i.elearn.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class RegisterFormUsersServlet
 */
@WebServlet("/RegisterFormUsersServlet")
public class RegisterFormUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = Logger.getLogger(RegisterFormServlet.class.getName());
    
	private static final String FIND_FIRST_10_EMPLOYEES = "SELECT * FROM employees LIMIT 10";
	
	@Resource(lookup="java:jboss/datasources/ELearningDS")
	private DataSource elearningDS;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
			
		
		try (Connection conn = elearningDS.getConnection()) {
			ResultSet rs = conn.createStatement()
							   .executeQuery(FIND_FIRST_10_EMPLOYEES);
			
			List<User> users = new ArrayList<User>();
			
			while(rs.next()) {
				LOGGER.info(String.format("%d %s %s", rs.getInt("id_user"), rs.getString("mail_user"), rs.getString("password_user")));
				users.add(new User(rs.getInt("id_user"), rs.getString("mail_user"), rs.getString("password_user")));
			}
			
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/registerForm.jsp")
					.forward(request, response);
			
			response.getWriter().append("Served at: ").append(request.getContextPath());	
		} catch(SQLException ex) {
			LOGGER.log(Level.INFO, "Problem using elearningDS", ex);
			response.sendError(500, "Veuillez ré-essayer dans 5 min");
			return;
		}
	}
}