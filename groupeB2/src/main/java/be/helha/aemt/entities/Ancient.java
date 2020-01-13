package be.helha.aemt.entities;

import javax.persistence.Entity;

import be.helha.aemt.enums.EnumSection;

@Entity
public class Ancient extends User{
	
	private int degreeYear;
	private EnumSection section;
	private String extendStudy;
	
	public Ancient() {
		super();
	}
	
	public Ancient(String name, String firstname, String mail, String login, String hashPwd,String phoneNumber,Address address, 
			int degreeYear, EnumSection section, String extendStudy) {
		super(name, firstname, mail, login, hashPwd, phoneNumber,address);
		this.degreeYear = degreeYear;
		this.section = section;
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
		a.setDegreeYear(a.getDegreeYear());
		a.setExtendStudy(a.getExtendStudy());
		a.setSection(a.getSection());
		return a;
	}
	
	public void update(Ancient a) {
		super.update(a);
		setDegreeYear(a.getDegreeYear());
		setExtendStudy(a.getExtendStudy());
		setSection(a.getSection());
	}

	@Override
	public String toString() {
		return super.toString() + "\nAncient [degreeYear=" + degreeYear + ", section=" + section + ", extendStudy="
				+ extendStudy + "]";
	}
	
	
}
