package be.helha.aemt.control;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	
	public EventControl() {
		this.event = new Event();
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
	
	public Event submitEvent() {
		Address a = new Address("testEvent", "1", "2", "3");
		Address aUser = new Address("testEventUser", "1", "2", "3");
		User u = new User("test", "test", "test@gmail.com", "test", "test", "test", aUser, EnumRole.ANCIENT);
		this.event = new Event(u, LocalDateTime.now(), "pathFile", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), a, "titleEvent2", "descEvent");
		return bean.post(event);
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
