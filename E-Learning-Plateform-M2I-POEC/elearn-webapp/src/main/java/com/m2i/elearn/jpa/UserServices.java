package com.m2i.elearn.jpa;

import java.util.Objects;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 * Servlet implementation class UserServicesServlet
 */

@Stateless
// em.persist(employee); doit etre appeler forcement durant la transaction sinon cela péte 
// on est donc connecté a la BDD  et une ransaction est en cours.

public class UserServices {

	private static final Logger LOGGER = Logger.getLogger(UserServices.class.getName());
	
	
	@PersistenceContext(unitName="ELearningPU")
	private EntityManager em;

	
    public UserJPA findWithMail (String mailUser) {
    	
    	UserJPA user=null;
    	LOGGER.info(String.format("avant le try"));
    	try{user = em.createQuery("SELECT e FROM UserJPA e "+ "	WHERE e.mailUser = :mailUser", UserJPA.class)
					.setParameter("mailUser", mailUser).getSingleResult(); 
    	LOGGER.info(String.format("user with mail %s found", mailUser));
		
    	}catch(NoResultException | NonUniqueResultException ex){
    	 LOGGER.info(String.format("No user with mail %s", mailUser));
    	}
			
			return user;

    }
	/**
	 * SqlException: ConstraintViolations Prénom et Nom Null Employee est null
	 * Perte de connexion à la base et autres exception "Système"
	 * 
	 * @Throws EntityExistException ou PersistenceException si l'employé existe déjà
	 * @Throws NullPointerException si employee est null 
	 */
	public void create(UserJPA user) {
		Objects.requireNonNull(user);
		em.persist(user);
	}
}
