package be.helha.aemt.entities;

import be.helha.aemt.enums.EnumSection;

public class Ancient extends User{
	
	private int degreeYear;
	private EnumSection section;
	private Address address;
	private String extendStudy;
	
	public Ancient() {
		super();
	}
	
	public Ancient(String name, String firstname, String mail, String login, String hashPwd,String phoneNumber, 
			int degreeYear, EnumSection section, Address address, String extendStudy) {
		super(name, firstname, mail, login, hashPwd, phoneNumber);
		this.degreeYear = degreeYear;
		this.section = section;
		this.address = address;
		this.extendStudy = extendStudy;
	}

	public int getDegreeYear() {
		return degreeYear;
	}

	public void setDegreeYear(int degreeYear) {
		this.degreeYear = degreeYear;
	}

	public EnumSection getSection() {
		return section;
	}

	public void setSection(EnumSection section) {
		this.section = section;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getExtendStudy() {
		return extendStudy;
	}

	public void setExtendStudy(String extendStudy) {
		this.extendStudy = extendStudy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + degreeYear;
		result = prime * result + ((extendStudy == null) ? 0 : extendStudy.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ancient other = (Ancient) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (degreeYear != other.degreeYear)
			return false;
		if (extendStudy == null) {
			if (other.extendStudy != null)
				return false;
		} else if (!extendStudy.equals(other.extendStudy))
			return false;
		if (section != other.section)
			return false;
		return true;
	}
	
	public Ancient clone() {
		Ancient a = (Ancient) super.clone();
		a.setDegreeYear(degreeYear);
		a.setAddress(address);
		a.setExtendStudy(extendStudy);
		a.setSection(section);
		return a;
	}
	
	public void update(Ancient a) {
		super.update(a);
		setDegreeYear(degreeYear);
		setAddress(address);
		setExtendStudy(extendStudy);
		setSection(section);
	}

	@Override
	public String toString() {
		return "Ancient [degreeYear=" + degreeYear + ", section=" + section + ", address=" + address + ", extendStudy="
				+ extendStudy + "]";
	}
	
	
}
