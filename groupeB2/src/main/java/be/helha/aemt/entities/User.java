package be.helha.aemt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import be.helha.aemt.enums.EnumRole;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@NotNull(message="Veuillez entrez un nom")
 	private String name;
 	//@NotNull(message="Veuillez entrez un pr�nom")
	private String firstname;
	//@NotNull(message="Veuillez entrez un mail")
	private String mail;
	//@NotNull(message="Veuillez entrez un login")
	private String login;
	//@NotNull(message="Veuillez entrez un mot de passe")
	private String hashPwd;
	//@NotNull(message="Veuillez entrez un num�ro de t�l�phone")
	private String phoneNumber;

	private String groupName;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address address;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Element> elements;

	@Enumerated(EnumType.STRING)
	private EnumRole role;

	public User() {
		this.address = new Address();
		this.groupName = "ancien";
	};

	public User(String name, String firstname, String mail, String login, String hashPwd, String phoneNumber,
			Address address,EnumRole role) {
		this();
		this.name = name;
		this.firstname = firstname;
		this.mail = mail;
		this.login = login;
		this.hashPwd = hashPwd;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
	}

	public User( String name, String firstname, String mail, String login, String hashPwd, String phoneNumber,
			Address address, EnumRole role, List<Element> elements) {
		this(name,firstname,mail,login,hashPwd,phoneNumber,address,role);
//		this.id = id;
		this.elements = elements;
	}

	public void setToVoid() {
		this.name = "";
		this.firstname = "";
		this.mail = "";
		this.login = "";
		this.hashPwd = "";
		this.phoneNumber = "";
		this.address.setToVoid();
	}


	public EnumRole getRole() {
		return role;
	}

	public void setRole(EnumRole role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHashPwd() {
		return hashPwd;
	}

	public void setHashPwd(String hashPwd) {
		this.hashPwd = hashPwd;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public boolean addElement(Element e) {
		if(elements.contains(e)) return false;
		if(e == null) return false;
		elements.add(e); return true;
	}

	public User clone() {
		return new User(name, firstname, mail, login, hashPwd, phoneNumber,address,role,elements);
	}

	public void update(User u) {
		setName(name);
		setFirstname(firstname);
		setMail(mail);
		setLogin(login);
		setHashPwd(hashPwd);
		setPhoneNumber(phoneNumber);
		setAddress(address);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	// Equals only based on User's login
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", firstname=" + firstname + ", mail=" + mail + ", login=" + login
				+ ", hashPwd=" + hashPwd + ", phoneNumber=" + phoneNumber + "]";
	}

}
