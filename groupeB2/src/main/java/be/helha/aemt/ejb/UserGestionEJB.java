package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.UserDAO;
import be.helha.aemt.entities.User;

@Stateless
public class UserGestionEJB {
	
	@EJB
	private UserDAO ejb;
	
	public UserGestionEJB() {
		//ejb = new UserDAO();
	}
	
	public List<User> query(){
		return ejb.query();
	}
	
	public User post(User u) {
		return ejb.postUser(u);
	}

}
