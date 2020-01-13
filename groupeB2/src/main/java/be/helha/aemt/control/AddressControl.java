package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.helha.aemt.ejb.AddressGestionEJB;
import be.helha.aemt.entities.Address;

@Named
@SessionScoped
public class AddressControl implements Serializable{
	
	@Inject
	private AddressGestionEJB bean;
	
	//Give a name to the EJB
	private String name = "AddressEJB";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Address> getListAddresses(){
		return bean.query();
	}
	
	public Address getAddress(Address a) {
		return (Address) bean.get(a);
	}
	
	public Address postAddress(Address a) {
		return bean.post(a);
	}
	
	public Address deleteAddress(Address a) {
		return bean.delete(a);
	}
	
	public Address updateAddress(Address oldAddress, Address newAddress) {
		return bean.update(oldAddress, newAddress);
	}
	
}
