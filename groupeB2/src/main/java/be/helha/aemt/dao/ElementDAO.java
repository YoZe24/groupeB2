package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Element;
import be.helha.aemt.entities.User;

@Stateless
@LocalBean
public class ElementDAO {
	
	@EJB
	private UserDAO userDAO;

	@PersistenceContext(name ="groupeB2")	
	private EntityManager em;
	
	public ElementDAO() {}
	
	public List<Element> query(){
		Query query = em.createQuery("select element from Element element");
		List<Element> elements = query.getResultList();
		return elements.size() == 0 ? null : elements;	
	}
	
	public List<Element> getElementsByAuthor(User user){
		Query query = em.createQuery("Select element from Element element "
				+ " JOIN element.author a"
				+ " where a.login = ?1");
		query.setParameter(1, user.getLogin());
		List<Element> elements = query.getResultList();
		return elements.size()==0?null:elements;
	}
	
	public boolean removeElementsFromAuthor(User user) {
		Query query = em.createQuery("Select element from Element element "
				+ " JOIN element.author a"
				+ " where a.login = ?1");
		query.setParameter(1, user.getLogin());
		List<Element> elements = query.getResultList();
		if(elements.size() == 0) return false;
		for (Element element : elements) {
			em.remove(element);
		}
		return true;
	}
	
	public Element get(Element e) {
		Query query = em.createQuery("select element from Element element "
				+ " JOIN element.author a"
				+ " where a = :varAuthor "
				+ " and element.pathFile = :varPathFile");
		query.setParameter("varAuthor", e.getAuthor());
		query.setParameter("varPathFile", e.getPathFile());
		List<Element> elements = query.getResultList();
		return elements.size() == 0 ? null : elements.get(0);
	}
	
	public Element post(Element e) {
		Element elementFound = get(e);
		if(elementFound != null) 
			return elementFound;
		
		User user = userDAO.findUserByEmail(e.getAuthor());
		if(user != null)
			e.setAuthor(user);
		
		em.merge(e);
		
		return e;
	}
	
	public Element delete(Element e) {
		Element elementFound = get(e);
		if(elementFound == null) 
			return null;
		
		em.remove(elementFound);
		return elementFound;
	}
	
	public Element getById(Element e) {
		Query query = em.createQuery("select element from Element element "
				+ " where element.id = ?1 ");
		query.setParameter(1, e.getId());
		List<Element> elements = query.getResultList();
		return elements.size() == 0 ? null : elements.get(0);
	}
	
	public Element deleteById(Element e) {
		if(getById(e) == null)return null;
		if(!em.contains(e))e = em.merge(e);
		em.remove(e);
		return e;
	}
	
	public Element update(Element newElement) {
		Element elementFound = get(newElement);
		if(elementFound == null) 
			return null;
		newElement.setId(elementFound.getId());
		return em.merge(newElement);
	}

}
