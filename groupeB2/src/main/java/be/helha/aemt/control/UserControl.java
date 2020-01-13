package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.UserGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumRole;

@Named
@SessionScoped
public class UserControl implements Serializable{
	private static final long serialVersionUID = 1L;


	@EJB
	private UserGestionEJB bean;
	
	private User user;
	private Address address;
	
	//private Address a = new Address("S1", "N1", "C1", "CP1");
	//private User user = new User("A1", "FS1", "M1", "LA1", "91e8c23c79fe019eea9a858d90e4be24dc917988c6fe2e4a55a2339f027b005c", "PN1", a,EnumRole.ANCIENT);

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
