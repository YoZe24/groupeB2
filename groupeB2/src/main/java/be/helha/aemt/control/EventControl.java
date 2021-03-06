package be.helha.aemt.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import be.helha.aemt.ejb.EventGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumRole;
import be.helha.aemt.enums.EnumSection;

@ApplicationScoped
@Named
public class EventControl implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8412325343163651921L;

	@EJB
	private EventGestionEJB bean;

	private Part img;

	private Event event;
	private Event eventManual2;
	private Address address;

	private String startDateStr = "";
	private String endDateStr = "";

	Address aUser = new Address("testEventUser", "1", "2", "3");
	User u = new User("test", "test", "test@gmail.com", "test", "test", "test","test",EnumSection.ASSISTANT_DIRECTION, aUser, EnumRole.ANCIENT);


	private Address addressUserTest = new Address("S2", "N2", "C2", "CP2");
	private Address addressEventTest = new Address("TEST", "TEST", "TEST", "TEST");

	private User userTest = new User("userTestName", "userTestFirstName", "userTestMail", "userTestLogin", "testMDP", "00000","2010",EnumSection.ASSISTANT_DIRECTION, addressUserTest, EnumRole.ADMINISTRATOR);
	private LocalDateTime dateTimePublish = LocalDateTime.parse("2020-01-08 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private LocalDateTime startDateTestEvent = LocalDateTime.parse("2020-01-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private LocalDateTime endDateTestEvent = LocalDateTime.parse("2020-01-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

	private Event eventManual = new Event(userTest, dateTimePublish,"testPathFile",startDateTestEvent, endDateTestEvent, addressEventTest, "testTitle", "testDescription");

	private List<Event> events = new ArrayList<Event>();

	public EventControl() {
		event = new Event();
		eventManual = new Event();
		address = new Address();
	}

	public List<Event> query(){
		return bean.query();
	}


	public Event get(Event e) {
		return bean.get(e);
	}

	public Event getByTitle(String title) {
		return bean.getByTitle(title);
	}

	public byte[] getImgBytes(String title) {
		return getByTitle(title).getImg();
	}

	public Event post(Event e) {
		return bean.post(e);
	}

	public Event update(Event e) {
		return bean.update(e);
	}

	public List<Event> getAllEvents(){
		return bean.query();
	}

	public void loadEvents(){
		this.events = getAllEvents();
	}

	public void download() throws IOException {
       FacesContext facesContext = FacesContext.getCurrentInstance();
       ExternalContext externalContext = facesContext.getExternalContext();
       externalContext.responseReset();
       externalContext.setResponseContentType("application/pdf");
       externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "file.pdf" + "\"");
       OutputStream outputStream = externalContext.getResponseOutputStream();
       outputStream.write(getImgBytes("titleEvent3"));
       facesContext.responseComplete();
	}

	public String addEvent(User user) {
		this.event.setStartDate(convertDateStrToLocalDateTime(startDateStr));
		this.event.setEndDate(convertDateStrToLocalDateTime(endDateStr));


		byte[] picBytes = new byte[(int) img.getSize()];
		try {
			img.getInputStream().read(picBytes);
			this.event.setImg(picBytes);
		}catch (Exception e) {
		}

		this.event.setAuthor(user);
		post(event);
		
		return "/index.xhtml";
	}

	public Event submitEvent() {
		Address a = new Address("testEvent", "1", "2", "3");
		this.eventManual2 = new Event(u, LocalDateTime.now(), "pathFile", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), a, "titleEvent3", "descEvent");
		this.eventManual2.setImgWithPath("C:\\Users\\eliot\\Desktop\\cv\\CV_ANGLAIS.pdf");
		return bean.post(eventManual2);
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		System.out.println(event);
		this.event = event;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Event submitEvent2() {
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

	public Part getImg() {
		return img;
	}

	public void setImg(Part img) {
		System.out.println(img.getSize());
		this.img = img;
	}

	public Event getEventManual2() {
		return eventManual2;
	}

	public void setEventManual2(Event eventManual2) {
		this.eventManual2 = eventManual2;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	private LocalDateTime convertDateStrToLocalDateTime(String dateStr) {
		if(dateStr == null || dateStr.length() == 0) return LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
		return dateTime;
	}




	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event getEventById(int id) {
		return bean.getById(id);
	}

	public void removeEvent() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int eventId = Integer.parseInt((String) map.get("idRemoved"));

		Event eventToRemove = getEventById(eventId);
		removeEvent(eventToRemove);
		events.remove(eventToRemove);
	}

	public Event removeEvent(Event event) {
		return bean.delete(event);
	}

	public void confirmEvent() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		int eventId = Integer.parseInt((String) map.get("idConfirmed"));
		Event eventUpdated = updateEvent(eventId);
		bean.update(eventUpdated);
		events.set(events.indexOf(eventUpdated),eventUpdated);
	}

	public Event updateEvent(int id) {
		Event eventToUpdate = bean.getById(id);
		eventToUpdate.setConfirmed(true);
		return eventToUpdate;
	}
	
	public void seeAllEvents() {
		loadListOffer();
	}
	
	public void loadListOffer() {
		this.events = getAllEvents();
	}




}
