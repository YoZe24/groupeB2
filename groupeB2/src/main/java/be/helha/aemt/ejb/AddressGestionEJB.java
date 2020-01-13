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
	
	public List<Address> get(Address a){
		return ejb.get(a);
	}
	
	public Address post(Address a) {
		return ejb.post(a);
	}
	
	public Address delete(Address a) {
		return ejb.delete(a);
	}
	
	public Address update(Address oldAddress, Address newAddress) {
		return ejb.update(oldAddress,newAddress);
	}
}
