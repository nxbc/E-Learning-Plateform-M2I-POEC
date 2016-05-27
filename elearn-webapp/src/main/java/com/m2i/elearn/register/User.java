package com.m2i.elearn.register;

public class User {

	private int idUser;
	private String mailUser;
	private String passwordUser;
	
	public User(int id_user, String mail_user, String password_user) {
		super();
		this.idUser = id_user;
		this.mailUser = mail_user;
		this.passwordUser = password_user;
	}
	public int getIdUser() {
		return idUser;
	}
	public String getMailUser() {
		return mailUser;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	
}
