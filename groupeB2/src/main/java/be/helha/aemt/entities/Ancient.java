package be.helha.aemt.entities;

import be.helha.aemt.enums.EnumSection;

public class Ancient extends User{
	
	private int degreeYear;
	private EnumSection section;
	private Address address;
	private String extendStudy;
	
	public Ancient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ancient(String name, String firstname, String mail, String login, String hashPwd,String phoneNumber, 
			int degreeYear, EnumSection section, Address address, String extendStudy) {
		super(name, firstname, mail, login, hashPwd, phoneNumber);
		this.degreeYear = degreeYear;
		this.section = section;
		this.address = address;
		this.extendStudy = extendStudy;
		
	}
	
	
}
