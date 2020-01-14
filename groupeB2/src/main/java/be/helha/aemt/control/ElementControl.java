package be.helha.aemt.control;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.ElementGestionEJB;
import be.helha.aemt.entities.Element;

@SessionScoped
@Named
public class ElementControl implements Serializable {
	
	@EJB
	private ElementGestionEJB bean;
	
	private Element element;
	
	public ElementControl() {
		this.element = new Element();
		this.element.setPublishDate(LocalDateTime.now());
	}

	public List<Element> query(){
		return bean.query();
	}
	
	public Element get(Element e) {
		return bean.get(e);
	}
	
	public Element post(Element e) {
		return bean.post(e);
	}
	
	public Element submitElement() {
		return bean.post(element);
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	
	
}
