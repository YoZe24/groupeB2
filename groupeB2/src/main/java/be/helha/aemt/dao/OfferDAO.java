package be.helha.aemt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Offer;

@PersistenceContext(name = "groupeB2")
public class OfferDAO {

	private EntityManager em;
	
	public OfferDAO () {
		
	}
	
	public List<Offer>query(){
		Query query = em.createQuery("SELECT offer from Offer offer");
		return query.getResultList();
	}
	
	
	
	
	
}
