package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.AddressDAO;
import be.helha.aemt.entities.Address;
@Stateless
public class AddressGestionEJB {
	
	@EJB
	private AddressDAO ejb;
	
	public AddressGestionEJB() {}
	
	public List<Address> query(){
		return ejb.query();
	}
}
