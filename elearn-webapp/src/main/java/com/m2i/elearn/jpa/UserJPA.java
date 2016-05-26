package com.m2i.elearn.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Seb
 * Classe permettant l'accès a la table ELearning.users
 * 
 */
@Entity
@Table(name="users")
@NamedQueries(value={
		@NamedQuery(name="UserJPA.FIND_ALL_ORDER_BY_ID",
				    query="SELECT e FROM UserJPA e ORDER BY e.idUser")		
})
public class UserJPA {
	
	@Id
	@NotNull
	@Column(name="id_user")
	private Integer idUser;
	
	@NotNull
	@Column(name="mail_user")
	private String mailUser;
	
	@NotNull
	@Column(name="password_user")
	private String passwordUser;
	
	@Column(name="confirmed_user")
	private boolean confirmedUser = false;
	
	@Column(name="confirmed_key_user")
	private String confirmedKeyUser;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getMailUser() {
		return mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public boolean isConfirmedUser() {
		return confirmedUser;
	}

	public void setConfirmedUser(boolean confirmedUser) {
		this.confirmedUser = confirmedUser;
	}

	public String getConfirmedKeyUser() {
		return confirmedKeyUser;
	}

	public void setConfirmedKeyUser(String confirmedKeyUser) {
		this.confirmedKeyUser = confirmedKeyUser;
	}

	@Override
	public String toString() {
		return "UserJPA [idUser=" + idUser + ", mailUser=" + mailUser + ", passwordUser=" + passwordUser
				+ ", confirmedUser=" + confirmedUser + ", confirmedKeyUser=" + confirmedKeyUser + "]";
	}

	
	
	
}
