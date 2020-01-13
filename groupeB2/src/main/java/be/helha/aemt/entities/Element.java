package be.helha.aemt.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class Element implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User author;
	
	private DateTime publishDate;
	private String pathFile;	

	public Element(User author, DateTime publishDate, String pathFile) {
		this.author = author;
		this.publishDate = publishDate;
		this.pathFile = pathFile;
	}

	public Element() {
		
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(DateTime publishDate) {
		this.publishDate = publishDate;
	}


	@Override
	public String toString() {
		return "Element [id=" + id + ", author=" + author + ", publishDate=" + publishDate + ", pathFile=" + pathFile
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
