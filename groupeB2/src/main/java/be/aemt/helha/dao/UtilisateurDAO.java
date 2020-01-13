package be.aemt.helha.dao;

import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import com.mysql.jdbc.Util;

import be.aemt.helha.entities.Utilisateur;

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
