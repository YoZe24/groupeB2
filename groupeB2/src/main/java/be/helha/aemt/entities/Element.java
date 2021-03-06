package be.helha.aemt.entities;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;



@Entity
public class Element implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private User author;

	private LocalDateTime publishDate;
	private String pathFile;

	private boolean confirmed;
	
	@Lob
	private byte[] img;

	public Element(User author, LocalDateTime publishDate, String pathFile, byte[] img) {
		this();
		this.author = author;
		this.publishDate = publishDate;
		this.pathFile = pathFile;
		this.img = img;
	}

	public Element(User author, LocalDateTime publishDate, String pathFile) {
		this();
		this.author = author;
		this.publishDate = publishDate;
		this.pathFile = pathFile;
	}

	public Element() {
		this.author = new User();
		this.publishDate = LocalDateTime.now();
		this.confirmed = false;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public void setImgWithPath(String path) {
		File file = new File(path);
		byte[] picInBytes = new byte[(int) file.length()];
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(picInBytes);
			fileInputStream.close();
			this.img = picInBytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setToVoid() {
		this.author.setToVoid();
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

	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Element clone() {
		return new Element(author, publishDate, pathFile);
	}

	public String convertDateIntoString(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dateTimeString = date.format(formatter);
//		String dateTimeString = date.format(DateTimeFormatter.RFC_1123_DATE_TIME);
		return dateTimeString;
	}
	
	public String getRealClassName() {
		return this.getClass().getSimpleName();
	}
	

	@Override
	public String toString() {
		return "Element [id=" + id + ", author=" + author + ", publishDate=" + publishDate + ", pathFile=" + pathFile + ", confirmed=" + confirmed
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
