package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.UtilisateurDAO;
import be.helha.aemt.entities.Utilisateur;

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
