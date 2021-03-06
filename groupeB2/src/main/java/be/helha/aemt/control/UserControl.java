package be.helha.aemt.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import be.helha.aemt.ejb.UserGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumRole;
import be.helha.aemt.enums.EnumSection;

@Named
@SessionScoped
public class UserControl implements Serializable{
	private static final long serialVersionUID = 1L;

	@EJB
	private UserGestionEJB bean;

	private User user;

	private User userCurrent;
	private User singleUser;
	private Boolean adminConnected = false;

	//Variable for confirmation PWD
	private String confirmPwd = "";


	private String messageErrorConfirmPwd = "";


	private Address addressManuel = new Address("S1", "N1", "C1", "CP1");
	private User userManual = new User("A1", "FS1", "M1", "LA1", "91e8c23c79fe019eea9a858d90e4be24dc917988c6fe2e4a55a2339f027b005c", "PN1","2010",EnumSection.COMPTABILITE, addressManuel,EnumRole.ANCIENT);


	private List<User> users= new ArrayList<User>();

	public EnumSection[] getSections() {
		return EnumSection.values();
	}

	public UserControl() {
		user = new User();
	}

	public List<User> getAllUsers(){
		return bean.query();
	}

	public User updateUser(int id) {
		User userToUpdate = bean.getById(id);
		userToUpdate.setConfirmed(true);
		userToUpdate.setGroupName("ancien");
		return userToUpdate;
	}

	public void loadUsers() {
		this.users = getAllUsers();
	}

	public User addUser(User u) {
		return bean.post(u);
	}

	public User removeWithElements() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int userId = Integer.parseInt((String) map.get("idRemoved"));
		User userToRemove = getUserById(userId);
		users.remove(userToRemove);
		return bean.removeWithElements(userToRemove);
	}

	public User submitUserManual() {
		return bean.post(userManual);
	}

	public User getUserByLogin(String login) {
		return bean.getByLogin(login);
	}

	public String submitUser() {
		if(confirmationPwd() == true) {
			bean.post(user);
			return "/index.xhtml";
		}else {
			return "";
		}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean confirmationPwd() {
		if(user.getHashPwd().equals(confirmPwd)) {
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
		users.remove(userToRemove);
	}

	public String convertBoolToString(boolean bool) {
		return bool == false? "Non valid�" : "Valid�";
	}

	public void confirmUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int userId = Integer.parseInt((String) map.get("idConfirmed"));
		User userUpdated = updateUser(userId);
		bean.update(userUpdated);
		users.set(users.indexOf(userUpdated), userUpdated);
	}

	public User getUserCurrent() {
		System.out.println("ELIOT A UN GROS ZIZIIIIIIIIIIIIIIIIIIIIIIIIIII");
		if(userCurrent == null) {
			String login = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			this.userCurrent = getUserByLogin(login);
			System.out.println(userCurrent.toString());
			if(userCurrent.getGroupName().equals("admin")) {
				adminConnected = true;
				System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
			}
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
	public void singleUserDetails() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int userId = Integer.parseInt((String) map.get("idClicked"));
		setSingleUser(bean.getById(userId));
	}

	public User getSingleUser() {
		return singleUser;
	}

	public void setSingleUser(User singleUser) {
		this.singleUser = singleUser;
	}

	public void seeNotConfirmedUsers() {
		List<User> listUserNotConfirmed = new ArrayList<User>();
		for (User user : users) {
			if(!user.isConfirmed()) listUserNotConfirmed.add(user);
		}
		setUsers(listUserNotConfirmed);
	}

	public void seeAllUsers() {
		loadUsers();
	}

	public String changePage() {
		return "singleUser.xhtml";
	}

	public Boolean getUserConnected() {
		return adminConnected;
	}

	public void setUserConnected(Boolean userConnected) {
		this.adminConnected = userConnected;
	}
	
	

}
