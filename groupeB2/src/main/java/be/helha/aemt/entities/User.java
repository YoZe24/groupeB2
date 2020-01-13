package be.helha.aemt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String firstname;
	private String mail;
	private String login;
	private String hashPwd;
	private String phoneNumber;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address address;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Element> elements;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address address;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Element> elements;

	public User() {
	};

	public User(String name, String firstname, String mail, String login, String hashPwd, String phoneNumber,
			Address address) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.mail = mail;
		this.login = login;
		this.hashPwd = hashPwd;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public User(int id, String name, String firstname, String mail, String login, String hashPwd, String phoneNumber,
			Address address, List<Element> elements) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.mail = mail;
		this.login = login;
		this.hashPwd = hashPwd;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.elements = elements;
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
		return new User(name, firstname, mail, login, hashPwd, phoneNumber, address);
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
