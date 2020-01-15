package be.helha.aemt.control;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.OfferGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Offer;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumOfferType;
import be.helha.aemt.enums.EnumRole;

@Named
@SessionScoped
public class OfferControl implements Serializable {

	@EJB
	private OfferGestionEJB bean;
	private Offer offer;
	private String name = "OfferEJB";
	private EnumOfferType typeOfferChoose = EnumOfferType.TRAINING;
	private List<Offer> listOfferLoad = new ArrayList<>();
	

	public OfferControl() {
		offer = new Offer();
	}
	public EnumOfferType[] getOfferTypes() {
		return EnumOfferType.values();
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
		List<String> skills = new ArrayList<>();
		Address a = new Address("testOffer", "offerNum", "offerCity", "offerCp");
		Address aUser = new Address("testOfferUser", "userNum", "userCity", "userCp");
		User u = new User("testOffer", "testOffer", "testOffer@gmail.com", "testoffer", "testOffer", "testOffer", aUser, EnumRole.ANCIENT);
		this.offer = new Offer(u, LocalDateTime.now(), "pathFile","SocietyTest", "societyMail","societySector",1,a,"functionOffer",true, skills,"noteSupp","subject",EnumOfferType.CDD,LocalDateTime.now(),LocalDateTime.now(),200.0);
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
	public void loadListOffer (EnumOfferType type) {
		this.listOfferLoad = getAllByOfferType(EnumOfferType.CDD);
	}
	
	public List<Offer> getAllByOfferType(EnumOfferType type){
		return bean.getAllByOfferType(type);
	}
	public Offer getOfferById(int id) {
		return bean.getById(id);
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public EnumOfferType getTypeOfferChoose() {
		return typeOfferChoose;
	}
	public void setTypeOfferChoose(EnumOfferType typeOfferChoose) {
		this.typeOfferChoose = typeOfferChoose;
	}
	public List<Offer> getListOfferLoad() {
		return listOfferLoad;
	}
	public void setListOfferLoad(List<Offer> listOfferLoad) {
		this.listOfferLoad = listOfferLoad;
	}

}
