package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.ElementDAO;
import be.helha.aemt.entities.Element;

@Stateless
public class ElementGestionEJB {

	
	@EJB
	private ElementDAO ejb;
	
	public ElementGestionEJB() {}
	
	public List<Element> query(){
		return ejb.query();
	}
	
	public Element post(Element e) {
		return ejb.post(e);
	}
}
