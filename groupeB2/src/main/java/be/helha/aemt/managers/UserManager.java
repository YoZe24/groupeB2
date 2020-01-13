package be.helha.aemt.managers;

import java.util.List;

import be.helha.aemt.entities.User;

public class UserManager {
	private List<User> listUsers;

	
	
	public UserManager(List<User> listUsers) {
		super();
		this.listUsers = listUsers;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	
	
	
}
