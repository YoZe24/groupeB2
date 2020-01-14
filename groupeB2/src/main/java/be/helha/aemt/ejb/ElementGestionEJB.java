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
	
	public ElementGestionEJB() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Element> query(){
		return ejb.query();
	}
	
	public Element get(Element a){
		return ejb.get(a);
	}
	
	public Element post(Element a) {
		return ejb.post(a);
	}

}
