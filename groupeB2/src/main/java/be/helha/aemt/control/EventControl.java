package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.PersistenceContext;

import be.helha.aemt.ejb.EventGestionEJB;
import be.helha.aemt.entities.Event;

@SessionScoped
@Named
public class EventControl implements Serializable {

	@EJB
	private EventGestionEJB bean;
	
	public EventControl() {}
	
	public List<Event> query(){
		return bean.query();
	}
	
	public Event get(Event e) {
		return bean.get(e);
	}
	
	public Event post(Event e) {
		return bean.get(e);
	}
	
	public Event update(Event e) {
		return bean.update(e);
	}
}
