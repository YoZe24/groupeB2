package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.OfferDAO;
import be.helha.aemt.entities.Offer;
import be.helha.aemt.enums.EnumOfferType;

@Stateless
public class OfferGestionEJB {

	@EJB
	private OfferDAO ejb;
	
	public OfferGestionEJB() {}
	
	public List<Offer> query(){
		return ejb.query();
	}
	
	public Offer get(Offer o) {
		return ejb.get(o);
	}
	
	public Offer post(Offer o) {
		return ejb.post(o);
	}
	
	public Offer delete(Offer o) {
		return ejb.delete(o);
	}
	
	public Offer update(Offer o) {
		return ejb.updateOffer(o);
	}
	
	public Offer updateStatut(Offer o) {
		return ejb.updateStatut(o);
	}
	
	public List<Offer> getAllByOfferType(EnumOfferType type){
		return ejb.getAllByOfferType(type);
	}
	
/*	public List<Offer> getAllJobOffer(){
		return ejb.getAllJobOffer();
	}
	
	public List<Offer> getAllTrainingOffer(){
		return ejb.getAllTrainingOffer();
	}
	
	public List<Offer> getAllInternshipOffer(){
		return ejb.getAllInternshipOffer();
	}*/
	
	public Offer getById(int id) {
		return ejb.getById(id);
	}
	
}
