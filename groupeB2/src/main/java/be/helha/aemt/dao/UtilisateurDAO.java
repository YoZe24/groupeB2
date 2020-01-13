package be.helha.aemt.dao;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Utilisateur;

@Stateless
@LocalBean
public class UtilisateurDAO {
	
    @PersistenceContext(name = "groupeB2")
	private EntityManager em;

	
	public UtilisateurDAO() {
		//String = nom du projet par convention
	
	}

	
	public List<Utilisateur> selectAll(){
		Query qSelectAll = em.createQuery("SELECT utilisateur FROM Utilisateur utilisateur");
		return qSelectAll.getResultList();
	}
	
	
}
