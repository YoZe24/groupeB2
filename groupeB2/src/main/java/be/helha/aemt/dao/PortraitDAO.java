package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Offer;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.Portrait;
import be.helha.aemt.entities.User;

@Stateless
@LocalBean
public class PortraitDAO {

	@PersistenceContext(name = "groupeB2")
	private EntityManager em;

	@EJB
	private UserDAO userDAO;

	public PortraitDAO() {}

	public List<Portrait> query(){
		Query query = em.createQuery("select portrait from Portrait portrait");
		List<Portrait> portraits = query.getResultList();
		return portraits.size() != 0? portraits: null;
	}

	public Portrait getById(int id){
		Query query = em.createQuery("SELECT portrait from Portrait portrait WHERE portrait.id = :id");
		query.setParameter("id", id);
		return query.getResultList().size() != 0?(Portrait) query.getResultList().get(0) : null;
	}

	public Portrait get(Portrait p) {
		Query query = em.createQuery("select portrait from Portrait portrait where "
				+ " portrait.firstname = :firstname"
				+ " and portrait.name = :name"
				+ " and portrait.description = :varDescription");
		query.setParameter("varDescription", p.getDescription());
		query.setParameter("firstname", p.getFirstname());
		query.setParameter("name", p.getName());
		return query.getResultList().size() != 0 ? (Portrait) query.getResultList().get(0) : null;
	}

	public Portrait post(Portrait p) {
//		Portrait portraitFound = get(p);
//		if(portraitFound != null) return portraitFound;
//
		User user = userDAO.findUserByEmail(p.getAuthor());
		if(user != null) {
			p.setAuthor(user);
		}

		em.persist(p);
		return p;
	}

	public Portrait delete(Portrait p) {
		Portrait portraitFound = get(p);
		if(portraitFound == null) return null;
		em.remove(portraitFound);
		return portraitFound;
	}

	public Portrait update(Portrait p) {
		Portrait portraitFound = get(p);
		if(portraitFound == null) return null;
		p.setId(portraitFound.getId());
		return em.merge(p);
	}

	public Portrait findById(int id) {
		Query query = em.createQuery("select portrait from Portrait portrait where portrait.id = :varId");
		query.setParameter("varId", id);
		return query.getResultList().size() != 0?(Portrait) query.getResultList().get(0) : null;
	}
}
