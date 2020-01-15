package be.helha.aemt.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Query;

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

	private User userCurrent;

	//Variable for confirmation PWD
	private String confirmPwd = "";


	private String messageErrorConfirmPwd = "";


	private Address addressManuel = new Address("S1", "N1", "C1", "CP1");
	private User userManual = new User("A1", "FS1", "M1", "LA1", "91e8c23c79fe019eea9a858d90e4be24dc917988c6fe2e4a55a2339f027b005c", "PN1", addressManuel,EnumRole.ANCIENT);


	private List<User> users= new ArrayList<User>();


	public UserControl() {
		user = new User();
	}

	public List<User> getAllUsers(){
		return bean.query();
	}

	public User updateUser(int id) {
		User userToUpdate = bean.getById(id);
		userToUpdate.setConfirmed(true);
		return userToUpdate;
	}

	public void loadUsers() {
		this.users = getAllUsers();
	}

	public User addUser(User u) {
		return bean.post(u);
	}

	public User submitUserManual() {
		return bean.post(userManual);
	}

	public User getUserByLogin(String login) {
		return bean.getByLogin(login);
	}

	public User submitUser() {
		//if(confirmationPwd() == true) {
			return bean.post(user);
		//}else {
		//	return null;
		//}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean confirmationPwd() {
		if(user.getHashPwd() == confirmPwd) {
			return true;
		}else {
			messageErrorConfirmPwd = "Le mot de passe ne correspond pas";
			return false;

		}
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public User removeUser(User user) {
		return bean.removeUser(user);
	}
	
	public void removeUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int userId = Integer.parseInt((String) map.get("idRemoved"));
		User userToRemove = getUserById(userId);
		removeUser(userToRemove);
	}

	public String convertBoolToString(boolean bool) {
		return bool == false? "Non validé" : "Validé";
	}

	public void confirmUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int userId = Integer.parseInt((String) map.get("idConfirmed"));		
		User userUpdated = updateUser(userId);
		bean.update(userUpdated);
	}

	public User getUserCurrent() {
		if(userCurrent == null) {
			String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			this.userCurrent = getUserByLogin(login);
			if(userCurrent == null) userCurrent = new User();
		}
		return userCurrent;
	}

	public void setUserCurrent(User userCurrent) {
		this.userCurrent = userCurrent;
	}
	
	public User getUserById(int id) {
		return bean.getById(id);
	}


}
