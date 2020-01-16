package be.helha.aemt.control;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.aemt.ejb.OfferGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.Offer;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumOfferType;
import be.helha.aemt.enums.EnumRole;
import be.helha.aemt.enums.EnumSection;

@Named
@SessionScoped
public class OfferControl implements Serializable {

	@EJB
	private OfferGestionEJB bean;
	private Offer offer;
	private String name = "OfferEJB";
	private EnumOfferType typeOfferChoose = null;
	private List<Offer> listOfferLoad = new ArrayList<>();
	
	private Part pdf;
	
	private String startDateStr = "";
	private String endDateStr = "";
	
	private boolean offerTypeIsOk;

	public OfferControl() {
		offer = new Offer();
		offer.setOfferType(EnumOfferType.CDD);
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
	
	public Offer addOffer(User u) {
		if(this.offer.getOfferType() != EnumOfferType.CDD) {
			this.offer.setStartDate(convertDateStrToLocalDateTime(startDateStr));
			this.offer.setEndDate(convertDateStrToLocalDateTime(endDateStr));
		}
		
		byte[] pdfBytes = new byte[(int) pdf.getSize()];
		try {
			pdf.getInputStream().read(pdfBytes);
			this.offer.setImg(pdfBytes);
		}catch (Exception e) {
		}
		
		this.offer.setAuthor(u);
		
		System.out.println(offer);
		return postOffer(offer);
	}
	
	public Offer submitOffer() {
		List<String> skills = new ArrayList<>();
		Address a = new Address("testOffer", "offerNum", "offerCity", "offerCp");
		Address aUser = new Address("testOfferUser", "userNum", "userCity", "userCp");
		User u = new User("testOffer", "testOffer", "testOffer@gmail.com", "testoffer", "testOffer", "testOffer","testOffer",EnumSection.ECONOMIQUE, aUser, EnumRole.ANCIENT);
		//this.offer = new Offer(u, LocalDateTime.now(), "pathFile","SocietyTest", "societyMail","societySector",1,a,"functionOffer",true, skills,"noteSupp","subject",EnumOfferType.CDD,LocalDateTime.now(),LocalDateTime.now(),200.0);
		//User u = new User("testOffer", "testOffer", "testOffer@gmail.com", "testoffer", "testOffer", "testOffer","testOffer","testOffer", aUser, EnumRole.ANCIENT);
		this.offer = new Offer(u, LocalDateTime.now(), "pathFile","SocietyTest", "societyMail","societySector","03",a,"functionOffer",true, "Java,Mysql","noteSupp","subject",EnumOfferType.CDD,LocalDateTime.now(),LocalDateTime.now(),200.0);
		return bean.post(offer);
	}
	
	public void removeOffer() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int offerId = Integer.parseInt((String) map.get("idRemoved"));
		
		Offer offerToRemove = getOfferById(offerId);
		removeOffer(offerToRemove);
	}
	
	public Offer removeOffer(Offer offer) {
		return bean.delete(offer);
	}
	
	
	public void confirmOffer() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int offerId = Integer.parseInt((String) map.get("idConfirmed"));
		Offer offerUpdated = updateOffer(offerId);
		bean.update(offerUpdated);
	}
	
	public Offer updateOffer(int id) {
		Offer offerToUpdate = bean.getById(id);
		offerToUpdate.setConfirmed(true);
		return offerToUpdate;
	}
	
	public boolean renderDate() {
		return offer.getOfferType() != EnumOfferType.CDD;
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
	
	public void loadListOffer() {
		this.listOfferLoad = getAllByOffer();
	}
	
	public void loadListOffer (EnumOfferType type) {
		System.out.println("Type choisis: " + typeOfferChoose);
		this.listOfferLoad = getAllByOfferType(type);
	}
	public List<Offer> getAllByOfferType(EnumOfferType type){
		return bean.getAllByOfferType(type);
	}
	
	public List<Offer> getAllByOffer(){
		return bean.query();
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
	
	public boolean isOfferTypeIsOk() {
		return renderDate();
	}
	public void setOfferTypeIsOk(boolean offerTypeIsOk) {
		this.offerTypeIsOk = offerTypeIsOk;
	}
	
	private LocalDateTime convertDateStrToLocalDateTime(String dateStr) {
		if(dateStr == null || dateStr.length() == 0) return LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public Part getPdf() {
		return pdf;
	}
	public void setPdf(Part pdf) {
		this.pdf = pdf;
	}

	
	
}
