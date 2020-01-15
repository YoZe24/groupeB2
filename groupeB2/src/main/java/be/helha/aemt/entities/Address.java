package be.helha.aemt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@NotNull(message="Veuillez entrez une rue")
	private String street;
	//@NotNull(message="Veuillez entrez un numéro")
	private String num;
	//@NotNull(message="Veuillez entrez une ville")
	private String city;
	//@NotNull(message="Veuillez entrez un code postal")
	private String cp;
	
	public Address() {}
	
	public Address(String street, String num, String city, String cp) {
		super();
		this.street = street;
		this.num = num;
		this.city = city;
		this.cp = cp;
	}
	
	public void setToVoid() {
		this.street = "";
		this.num = "";
		this.city = "";
		this.cp = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + id;
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	//Equals based on all attributs
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (id != other.id)
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	
	public void update(Address a) {
		setStreet(a.getStreet());
		setNum(a.getNum());
		setCity(a.getCity());
		setCp(a.getCp());
	}
	
	public Address clone() {
		return new Address(street, num, city, cp);
	}

	@Override
	public String toString() {
		return "Adresse :"+ " " + street + " N°" + num + ", " + cp + ", " + city;
	}
	
	

	
}
