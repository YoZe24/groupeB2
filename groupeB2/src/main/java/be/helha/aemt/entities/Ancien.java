package be.helha.aemt.entities;

import javax.persistence.Entity;

import be.helha.aemt.enums.EnumSection;

@Entity
public class Ancien extends User{
	
	private int degreeYear;
	private EnumSection section;
	private String extendStudy;
	
	public Ancien() {
		super();
	}
	
	public Ancien(String name, String firstname, String mail, String login, String hashPwd,String phoneNumber, 
			int degreeYear, EnumSection section, Address address, String extendStudy) {
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
	public String toString() {
		return "Ancien [degreeYear=" + degreeYear + ", section=" + section + ", extendStudy=" + extendStudy + "]";
	}

	
//	public void update(Ancien a) {
//		super.update(a);
//		setDegreeYear(degreeYear);
//		setAddress(address);
//		setExtendStudy(extendStudy);
//		setSection(section);
//	}
	
	
}
