package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.helha.aemt.ejb.OfferGestionEJB;
import be.helha.aemt.entities.Offer;

@Named
@SessionScoped
public class OfferControl implements Serializable{

	@Inject
	private OfferGestionEJB bean;
	private Offer offer;
	private String name = "OfferEJB";
	
	public OfferControl() {
		offer = new Offer();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Offer> getListOffers(){
		return bean.query();
	}
	public Offer getOffer(Offer o) {
		return bean.get(o);
	}
	public Offer postOffer(Offer o) {
		return bean.post(o);
	}
	public Offer submitOffer() {
		return bean.post(offer);
	}
	public Offer deleteOffer(Offer o) {
		return bean.delete(o);
	}
	public Offer updateOffer(Offer o) {
		return bean.update(o);
	}
	public Offer updateOfferAvailable(Offer o) {
		return bean.updateStatut(o);
	}
	public List<Offer> getListJobOffers(){
		return bean.getAllJobOffer();
	}
	public List<Offer> getListInternshipOffers(){
		return bean.getAllInternshipOffer();
	}
	public List<Offer> getListTrainingOffers(){
		return bean.getAllTrainingOffer();
	}
	public Offer getOfferById(int id) {
		return bean.getById(id);
	}

}
