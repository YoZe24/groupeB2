package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Address;

@PersistenceContext(name = "groupeB2")
public class AddressDAO {

	private EntityManager em;
	
	public AddressDAO() {}
	
	public List<Address> query(){
		Query query = em.createQuery("select address from Address address");
		return query.getResultList();
	}
	
	public List<Address> get(Address a){
		Query query = em.createQuery("select address from Address address "
				+ "where address.street = :varStreet and address.num = :varNum "
				+ "and address.city = :varCity and address.cp = :varCp");
		query.setParameter("varStreet", a.getStreet());
		query.setParameter("varNum", a.getNum());
		query.setParameter("varCity", a.getCity());
		query.setParameter("varCp", a.getCp());
		return query.getResultList() != null ? query.getResultList():null;
	}
	
	public Address post(Address a) {
		Address addressFound = (Address) get(a);
		if(addressFound != null) return addressFound;
		
		Query query = em.createQuery("insert");
		return new Address();
	}
}
