package be.helha.aemt.control;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.ElementGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Element;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.Offer;
import be.helha.aemt.entities.Portrait;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumRole;
import be.helha.aemt.enums.EnumSection;

@Named
@SessionScoped
public class ElementControl implements Serializable {

	@EJB
	private ElementGestionEJB bean;

	private Element element;

	public ElementControl() {
		this.element = new Element();
	}

	private Address addressUserTest = new Address("S2", "N2", "C2", "CP2");
	private Address addressEventTest = new Address("TEST", "TEST", "TEST", "TEST");

	private User userTest = new User("userTestName", "userTestFirstName", "userTestMail", "userTestLogin", "testMDP", "00000", "2010", EnumSection.ASSISTANT_DIRECTION, addressUserTest, EnumRole.ADMINISTRATOR);
	private LocalDateTime dateTimePublish = LocalDateTime.parse("2020-01-08 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private LocalDateTime startDateTestEvent = LocalDateTime.parse("2020-01-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private LocalDateTime endDateTestEvent = LocalDateTime.parse("2020-01-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	private Event eventManual = new Event(userTest, dateTimePublish,"testPathFile",startDateTestEvent, endDateTestEvent, addressEventTest, "testTitle", "testDescription");


	public List<Element> query(){
		return bean.query();
	}

	public Element get(Element e) {
		return bean.get(e);
	}

	public Element post(Element e) {
		return bean.post(e);
	}

	public Element remove(Element e) {
		return bean.remove(e);
	}

	public boolean removeElementFromAuthor(User user) {
		return bean.removeElementsFromAuthor(user);
	}

//	public Element removeFromForm(Element e) {
//
//	}

	public List<Element> getElementsFromUser(User user){
		return bean.getElementsFromUser(user);
	}

	public Element submitElement() {
		return bean.post(element);
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element submitElementManual() {
		System.out.println(eventManual.toString());
		return post(eventManual);
	}

	public String convertBoolToString(boolean bool) {
		return !bool ? "Pas valid�" : "Valid�";
	}

	public String getTitleOrNull(Element e) {
		if(e instanceof Event) {
			return ((Event)e).getTitle();
		}return "/";
	}

	public String getFunctionOfferOrNull(Element e) {
		if(e instanceof Offer) {
			return ((Offer)e).getFunctionOffer();
		}return "/";
	}

	public String getOfferTypeOrNull(Element e) {
		if(e instanceof Offer) {
			return ((Offer)e).getOfferType().getType();
		}return "/";
	}

	public String getNameOrNull(Element e) {
		if(e instanceof Portrait) {
			return ((Portrait)e).getName();
		}return "/";
	}

	public String getFirstnameOrNull(Element e) {
		if(e instanceof Portrait) {
			return ((Portrait)e).getFirstname();
		}return "/";
	}
}
