package be.helha.aemt.entities;

import be.helha.aemt.enums.EnumRole;

public class Admin extends User {

	private EnumRole enumRole;

	public Admin() {
		super();
	}

	public Admin(String name, String firstname, String mail, String login, String hashPwd,String phoneNumber, EnumRole enumRole) {
		super(name, firstname, mail, login, hashPwd, phoneNumber);
		this.enumRole = enumRole;
	}

	public EnumRole getEnumRole() {
		return enumRole;
	}

	public void setEnumRole(EnumRole enumRole) {
		this.enumRole = enumRole;
	}
	
	
}
