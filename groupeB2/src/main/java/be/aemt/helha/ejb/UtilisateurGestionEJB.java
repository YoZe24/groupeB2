package be.aemt.helha.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.aemt.helha.dao.UtilisateurDAO;
import be.aemt.helha.entities.Utilisateur;

@Stateless
public class UtilisateurGestionEJB {
	
	@EJB
	private UtilisateurDAO ejb;

	public UtilisateurGestionEJB() {
		
	}
	
	public List<Utilisateur> findAll(){
		return ejb.selectAll();
	}
}
