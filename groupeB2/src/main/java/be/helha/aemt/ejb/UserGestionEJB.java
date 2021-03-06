package be.helha.aemt.ejb;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.ElementDAO;
import be.helha.aemt.dao.UserDAO;
import be.helha.aemt.entities.User;

@Stateless
public class UserGestionEJB {

	@EJB
	private UserDAO ejb;
	
	@EJB
	private ElementDAO elemDAO;
	
	public UserGestionEJB() {
		//ejb = new UserDAO();
	}

	public List<User> query(){
		return ejb.query();
	}

	public User post(User u) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(u.getHashPwd().getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			u.setHashPwd(encoded);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return ejb.postUser(u);
	}

	public User get(User u) {
		return ejb.get(u);
	}

	public User getById(int id) {
		return ejb.findUserById(id);
	}

	public User findById(int id) {
		return ejb.findById(id);
	}

	public User update(User u) {
		return ejb.update(u);
	}

	public User getByLogin(String login) {
		return ejb.findByLogin(login);
	}

	public User removeUser(User user) {
		return ejb.removeUser(user);
	}

	public User removeWithElements(User user) {
		elemDAO.removeElementsFromAuthor(user);
		return ejb.removeUser(user);
	}

}
