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

	public User postUser(User u) {
		User userFinded = findUserByEmail(u);
		if(userFinded != null)
			return null;

		em.persist(u);

		return u;
	}

	public User findUserByEmail(User u) {
		Query query = em.createQuery("Select user from User user where user.mail = ?1");
		query.setParameter(1, u.getMail());
		List<User> users = query.getResultList();
		return users.size() == 0 ? null : users.get(0);
	}


}
