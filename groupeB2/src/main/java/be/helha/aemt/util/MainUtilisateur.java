package be.helha.aemt.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import be.helha.aemt.dao.UtilisateurDAO;
import be.helha.aemt.entities.Utilisateur;
import be.helha.aemt.entities.Visiteur;

public class MainUtilisateur {
	public static void main(String args[]) {
	
		Utilisateur u2 = new Utilisateur("L2", "P2", "E2",null);
	
		
		Utilisateur uHeritage = new Utilisateur("192.168.12.08","LH","MH","EH");
		Visiteur v1 = new Visiteur("192.168.12.07");
		
		
		UtilisateurDAO uDAO = new UtilisateurDAO();			

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gHenneboJPA");
//		EntityManager em = emf.createEntityManager();
//		
//		EntityTransaction tx = em.getTransaction();
//		
//		tx.begin();
//		
//		em.persist(uHeritage);
//		em.persist(v1);
//		
//		tx.commit();
//		emf.close();
//		
		
//		
	}
		
}
