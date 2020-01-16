package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.ElementDAO;
import be.helha.aemt.entities.Element;
import be.helha.aemt.entities.User;

@Stateless
public class ElementGestionEJB {

	@EJB
	private ElementDAO ejb;

	public ElementGestionEJB() {}

	public List<Element> query(){
		return ejb.query();
	}

	public List<Element> getElementsFromUser(User user){
		return ejb.getElementsByAuthor(user);
	}
	
	public boolean removeElementsFromAuthor(User user) {
		return ejb.removeElementsFromAuthor(user);
	}
	
	public Element get(Element a){
		return ejb.get(a);
	}

	public Element post(Element a) {
		return ejb.post(a);
	}
	
	public Element remove(Element e) {
		return ejb.delete(e);
	}

}
