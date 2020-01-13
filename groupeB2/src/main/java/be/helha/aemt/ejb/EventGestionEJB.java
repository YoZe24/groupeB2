package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.EventDAO;
import be.helha.aemt.entities.Event;

@Stateless
public class EventGestionEJB {

	@EJB
	private EventDAO ejb;
	
	public EventGestionEJB() {}
	
	public List<Event> query(){
		return ejb.query();
	}
	
	public Event get(Event e) {
		return ejb.get(e);
	}
	
	public Event post(Event e) {
		return ejb.post(e);
	}
	
	public Event delete(Event e) {
		return ejb.delete(e);
	}
	
	public Event update(Event e) {
		return ejb.update(e);
	}
}
