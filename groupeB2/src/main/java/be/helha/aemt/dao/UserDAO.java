package be.helha.aemt.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.User;

@Stateless
@LocalBean
public class UserDAO implements Serializable {
	
	@PersistenceContext(name = "groupeB2")
	private EntityManager em;
	//private EntityManagerFactory emf;
	
//	public UserDAO() {
//		//emf = Persistence.createEntityManagerFactory("groupeB2");
//		//em = emf.createEntityManager();
//	}

	public List<User> query(){
		Query query = em.createQuery("SELECT user from User user");
		return query.getResultList(); 
	}
	
	public User postUser(User user) {
		User userFinded = findUserByEmail(user);
		if(userFinded != null)
			return null;
		
		Address addressFinded = findAddress(user.getAddress());
		if(addressFinded != null)
			user.setAddress(addressFinded);
		
		em.persist(user);
		
		return user;
	}
	
	public User findUserByEmail(User u) {
		Query query = em.createQuery("Select user from User user where user.mail = ?1");
		query.setParameter(1, u.getMail());
		List<User> users = query.getResultList();
		return users.size() == 0 ? null : users.get(0);
	}
	
	public Address findAddress(Address a) {
		Query query = em.createQuery("Select a from Address a where a.city = ?1 and a.cp = ?2 and a.num = ?3 and a.street = ?4");
		query.setParameter(1, a.getCity());
		query.setParameter(2, a.getCp());
		query.setParameter(3, a.getNum());
		query.setParameter(4, a.getStreet());
		List<Address> addresses = query.getResultList();
		return addresses.size() == 0 ? null : addresses.get(0);
	}
	
}

