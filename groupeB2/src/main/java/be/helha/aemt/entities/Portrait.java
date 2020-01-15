package be.helha.aemt.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class Portrait extends Element {
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private User user;

	private String name;
	private String firstname;
	private String description;
		
	public Portrait(User author, LocalDateTime publishDate, String pathFile, byte[] img, String name, String firstname,
			String description) {
		super(author, publishDate, pathFile, img);
		this.name = name;
		this.firstname = firstname;
		this.description = description;
		this.setConfirmed(true);
	}

	public Portrait(User author, LocalDateTime publishDate, String pathFile) {
		super(author, publishDate, pathFile);
		this.setConfirmed(true);
	}

	public Portrait() {
		this.setConfirmed(true);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Portrait [name=" + name + ", firstname=" + firstname + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Portrait other = (Portrait) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	

}
