package be.helha.aemt.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class Portrait extends Element {
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	private String description;
	
	
	public Portrait(User author, DateTime publishDate, String pathFile, User user, String description) {
		super(author, publishDate, pathFile);
		this.user = user;
		this.description = description;
	}

	public Portrait(User author, DateTime publishDate, String pathFile) {
		super(author, publishDate, pathFile);
	}

	public Portrait() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Portrait [user=" + user + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
