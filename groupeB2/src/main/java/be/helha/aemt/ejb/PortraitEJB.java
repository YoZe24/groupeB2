package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.PortraitDAO;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.Portrait;

@Stateless
public class PortraitEJB {

	@EJB
	private PortraitDAO ejb;
	
	public PortraitEJB() {}
	
	public List<Portrait> query(){
		return ejb.query();
	}
	
	public Portrait get(Portrait p) {
		return ejb.get(p);
	}
	
	public Portrait post(Portrait p) {
		return ejb.post(p);
	}
	
	public Portrait delete(Portrait p) {
		return ejb.delete(p);
	}
	
	public Portrait update(Portrait p) {
		return ejb.update(p);
	}
}