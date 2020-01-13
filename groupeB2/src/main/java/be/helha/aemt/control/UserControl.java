package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.UserGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.User;

@Named
@SessionScoped
public class UserControl implements Serializable{
	private static final long serialVersionUID = 1L;


	@EJB
	private UserGestionEJB bean;
	
	private User user;
	private Address address;
	
	public UserControl() {
		//bean = new UserGestionEJB();
		user = new User();
		address = new Address();
		user.setAddress(address);
	}

	public List<User> getAllUsers(){
		return bean.query();
	}
	
	public User addUser(User u) {
		return bean.post(u);
	}
	
	public User submitUser() {
		return bean.post(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
