package be.aemt.helha.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Utilisateur extends Visiteur implements Serializable{
	private String login;
	private String password;
	private String email;
	
	private String groupName;
	
	public Utilisateur(String iP, String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.groupName = "ancien";
	}


	
//	public Utilisateur(String login, String password, String email) {
//		this(login,password,email,null);
//	}

	public Utilisateur() {
		
	}		

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Utilisateur [login=" + login + ", password=" + password + ", email=" + email + ", id=" + getId()
				+  "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
}
