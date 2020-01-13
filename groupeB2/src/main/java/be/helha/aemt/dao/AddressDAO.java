package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.helha.aemt.entities.Address;


@Stateless
@LocalBean
public class AddressDAO {

	@PersistenceContext(name = "groupeB2")
	private EntityManager em;
	
	public AddressDAO() {}
	
	public List<Address> query(){
		Query query = em.createQuery("select address from Address address");
		return query.getResultList().size() != 0? (ArrayList<Address>) query.getResultList() : null;
	}
	
	public Address get(Address a){
		Query query = em.createQuery("select address from Address address "
				+ "where address.street = :varStreet and address.num = :varNum "
				+ "and address.city = :varCity and address.cp = :varCp");
		query.setParameter("varStreet", a.getStreet());
		query.setParameter("varNum", a.getNum());
		query.setParameter("varCity", a.getCity());
		query.setParameter("varCp", a.getCp());
		return query.getResultList().size() != 0? (Address) query.getResultList().get(0) : null;
	}
	
	public Address post(Address a) {
		Address addressFound = get(a);
		if(addressFound != null) return addressFound;
		em.persist(a);
		return a;
	}
	
	public Address delete(Address a) {
		Address addressFound = (Address) get(a);
		if(addressFound == null) return null;
		em.remove(addressFound);
		return addressFound;
	}
	
	public Address update(Address newAddress) {
		Address addressFound = get(newAddress);
		if(addressFound == null) return null;
		newAddress.setId(addressFound.getId());
		return em.merge(newAddress);
	}
}
