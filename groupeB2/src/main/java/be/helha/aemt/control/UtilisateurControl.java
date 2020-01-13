package be.helha.aemt.control;



import java.util.List;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.ejb.UtilisateurGestionEJB;
import be.helha.aemt.entities.Utilisateur;

@Named
@SessionScoped
public class UtilisateurControl implements Serializable{
	
	@EJB
	private UtilisateurGestionEJB bean;
	private Utilisateur utilisateur;
	
	private String name = "Anonymous";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Utilisateur> doList() {
		return bean.findAll();
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
}
