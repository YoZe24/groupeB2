package be.helha.aemt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Offer;
import be.helha.aemt.enums.EnumOfferType;

@PersistenceContext(name = "groupeB2")
public class OfferDAO {

	private EntityManager em;
	
	public OfferDAO () {
		
	}
	
	public List<Offer>query(){
		Query query = em.createQuery("SELECT offer from Offer offer");
		return query.getResultList();
	}
	
	public Offer get(Offer o){
		Query query = em.createQuery("select offer from Offer offer "
				+ "where offer.societyName = :varSocietyName and offer.societyNum = :varSocietyNum "
				+ "and offer.offerType = :varOfferType and offer.functionOffer = :varFunctionOffer");
		query.setParameter("varSocietyName", o.getSocietyName());
		query.setParameter("varSocietyNum", o.getSocietyNum());
		query.setParameter("varOfferType", o.getOfferType());
		query.setParameter("varFunctionOffer", o.getFunctionOffer());
		List<Offer> offers = query.getResultList();
		return offers.size() == 0 ? null : offers.get(0);
	}
	
	public Offer post(Offer o) {
		Offer offerFound = (Offer) get(o);
		if(offerFound != null) return offerFound;
		em.persist(o);
		return o;
	}
	
	public Offer delete(Offer o) {
		Offer offerFound = (Offer) get(o);
		if(offerFound == null) return null;
		em.remove(offerFound);
		return offerFound;
	}
	
	public Offer updateOffer(Offer offerToUpdate) {
		Offer offerToUpdateFinded = (Offer) get(offerToUpdate);
		Offer offerToReturn = null;
		if(offerToUpdateFinded != null) {
			offerToUpdate.update(offerToUpdateFinded);
			offerToReturn = em.merge(offerToUpdate);
		}
		return offerToReturn;
	}
	
	public Offer updateStatut(Offer offerToUpdateAvailable) {
		Offer offerToUpdateFinded = (Offer) get(offerToUpdateAvailable);
		Offer offerToReturn = null;
		if(offerToUpdateFinded != null) {
			offerToUpdateAvailable.updateStatut(offerToUpdateFinded);
			offerToReturn = em.merge(offerToUpdateAvailable);
		}
		return offerToReturn;
	}
	
	
	//Recup all Training
	public List<Offer> getAllTrainingOffer(){
		Query query = em.createQuery("SELECT offer from Offer offer"
				+"WHERE offer.offerType = :varOfferType");
		query.setParameter("varOfferType", "TRAINING");
		List<Offer> offers = query.getResultList();
		return offers.size() == 0 ? null : offers;
	}
	
	//Recup all Internship
	public List<Offer> getAllInternshipOffer(){
		Query query = em.createQuery("SELECT offer from Offer offer"
				+"WHERE offer.offerType = :varOfferType");
		query.setParameter("varOfferType", "INTERNSHIP");
		List<Offer> offers = query.getResultList();
		return offers.size() == 0 ? null : offers;
	}
	
	//Recup all Job
	public List<Offer> getAllJobOffer(){
		Query query = em.createQuery("SELECT offer from Offer offer"
				+"WHERE offer.offerType = :varOfferType OR offer.offerType = :varOfferTypeCDI");
		query.setParameter("varOfferTypeCDD", "CDD");
		query.setParameter("varOfferTypeCDI", "CDI");
		List<Offer> offers = query.getResultList();
		return offers.size() == 0 ? null : offers;
	}
	
	public Offer getById(int id){
		Query query = em.createQuery("SELECT offer from Offer offer"
				+"WHERE offer.id = :id");
		query.setParameter("id", id);
		return (Offer) query.getSingleResult();
	}
	
	
	
	
	
	
	
	
}
