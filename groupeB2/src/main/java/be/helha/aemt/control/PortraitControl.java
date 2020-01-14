package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.PortraitEJB;
import be.helha.aemt.entities.Portrait;

@Named
@SessionScoped
public class PortraitControl implements Serializable {
	
	@EJB
	private PortraitEJB bean;
	
	public List<Portrait> query(){
		return bean.query();
	}
	
	public Portrait get(Portrait p) {
		return bean.get(p);
	}
	
	public Portrait post(Portrait p) {
		return bean.post(p);
	}
	
	public Portrait delete(Portrait p) {
		return bean.delete(p);
	}
	
	public Portrait update(Portrait p) {
		return bean.update(p);
	}
}
