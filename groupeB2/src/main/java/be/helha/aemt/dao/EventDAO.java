package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Element;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.User;

@Stateless
@LocalBean
public class EventDAO {

	@PersistenceContext(name = "groupeB2")
	private EntityManager em;

	@EJB
	private UserDAO userDAO;

	public EventDAO() {}

	public List<Event> query(){
		Query query = em.createQuery("select event from Event event");
		return query.getResultList();
		//return query.getResultList().size() != 0?(ArrayList<Event>) query.getResultList() : null;
	}

	public Event get(Event e) {
		Query query = em.createQuery("select event from Event event where "
				+ " event.title = :varTitle"
				+ " and event.startDate = :varStartDate"
				+ " and event.endDate = :varEndDate"
				+ " and event.address = :varAddress");
		query.setParameter("varTitle", e.getTitle());
		query.setParameter("varStartDate", e.getStartDate());
		query.setParameter("varEndDate", e.getEndDate());
		query.setParameter("varAddress", e.getAddress());
		List<Event> events = query.getResultList();
		return events.size() == 0 ? null : events.get(0);
	}

	public Event findByTitle(String title) {
		Query query = em.createQuery("select event from Event event where "
				+ " event.title = :varTitle");
		query.setParameter("varTitle", title);
		return query.getResultList().size() != 0 ? (Event) query.getResultList().get(0) : null;

	}

	public Event post(Event e) {
		Event eventFound = findByTitle(e.getTitle());
		if(eventFound != null) return eventFound;

		User userFinded = userDAO.findUserByEmail(e.getAuthor());
		if(userFinded != null)
			e.setAuthor(userFinded);

		Address addressFromEventFinded = userDAO.findAddress(e.getAddress());
		if(addressFromEventFinded != null)
			e.setAddress(addressFromEventFinded);

		em.persist(e);
		return e;
	}

	public Event delete(Event e) {
		Event eventFound = get(e);
		if(eventFound == null) return null;
		em.remove(eventFound);
		return eventFound;
	}

	public Event update(Event e) {
		Event eventFound = get(e);
		if(eventFound == null) return null;
		e.setId(eventFound.getId());
		return em.merge(e);

	}
}
