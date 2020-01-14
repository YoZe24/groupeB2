package be.helha.aemt.control;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.PersistenceContext;

import be.helha.aemt.ejb.EventGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumRole;

@SessionScoped
@Named
public class EventControl implements Serializable {

	@EJB
	private EventGestionEJB bean;

	private Event event;
	private Address address;

	private String startDateStr;
	private String endDateStr;
	
	private Address addressUserTest = new Address("S2", "N2", "C2", "CP2");
	private Address addressEventTest = new Address("TEST", "TEST", "TEST", "TEST");
	private User userTest = new User("userTestName", "userTestFirstName", "userTestMail", "userTestLogin", "testMDP", "00000", addressUserTest, EnumRole.ADMINISTRATOR);
	private LocalDateTime dateTimePublish = LocalDateTime.parse("2020-01-08 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private LocalDateTime startDateTestEvent = LocalDateTime.parse("2020-01-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private LocalDateTime endDateTestEvent = LocalDateTime.parse("2020-01-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	
	private Event eventManual = new Event(userTest, dateTimePublish,"testPathFile",startDateTestEvent, endDateTestEvent, addressEventTest, "testTitle", "testDescription");

	public EventControl() {
		event = new Event();
		//address = new Address();
	}

	public List<Event> query(){
		return bean.query();
	}

	public Event get(Event e) {
		return bean.get(e);
	}

	public Event post(Event e) {
		return bean.post(e);
	}

	public Event update(Event e) {
		return bean.update(e);
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Event submitEvent() {
		event.setStartDate(convertDateStrToLocalDateTime(startDateStr));
		event.setEndDate(convertDateStrToLocalDateTime(endDateStr));
		//event.setAddress(address);
		return post(event);
	}
	
	public Event submitEventManual() {
		return post(eventManual);
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

	private LocalDateTime convertDateStrToLocalDateTime(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}


}
