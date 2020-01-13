package be.helha.aemt.entities;

import javax.persistence.Entity;

import be.helha.aemt.enums.EnumRole;

@Entity
public class Admin extends User {

	private EnumRole enumRole;

	public Admin() {
		super();
	}

	public Admin(String name, String firstname, String mail, String login, String hashPwd, String phoneNumber,
			Address address, EnumRole enumRole) {
		super(name, firstname, mail, login, hashPwd, phoneNumber, address);
		this.enumRole = enumRole;
	}

	public EnumRole getEnumRole() {
		return enumRole;
	}

	public void setEnumRole(EnumRole enumRole) {
		this.enumRole = enumRole;
	}

	public void update(Admin a) {
		super.update(a);
		setEnumRole(a.getEnumRole());
	}

	public Admin clone() {
		Admin a = (Admin) super.clone();
		a.enumRole = enumRole;
		return a;
	}

	@Override
	public String toString() {
		return super.toString() + "\nAdmin [enumRole=" + enumRole + "]";
	}




}
