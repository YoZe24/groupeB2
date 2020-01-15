package be.helha.aemt.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import be.helha.aemt.enums.EnumOfferType;

@Entity
public class Offer extends Element implements Serializable{
	
	private String societyName;
	private String societyMail;
	private String societySector;
	private String societyNum;	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address societyAddress;
	private String functionOffer;
	private Boolean available;
	private String skillsNeeded;
	private String noteSupp;
	private String subject;
	
	@Enumerated(EnumType.STRING)
	private EnumOfferType offerType;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private double amount;
	
	
	public Offer() {
		this.societyAddress = new Address();
	}

	public Offer(User author, LocalDateTime publishDate, String pathFile, String societyName, String societyMail,
			String societySector, String societyNum, Address societyAddress, String functionOffer, Boolean available,
			String skillsNeeded, String noteSupp, String subject, EnumOfferType offerType,
			LocalDateTime startDate, LocalDateTime endDate, double amount) {
		super(author, publishDate, pathFile);
		this.societyName = societyName;
		this.societyMail = societyMail;
		this.societySector = societySector;
		this.societyNum = societyNum;
		this.societyAddress = societyAddress;
		this.functionOffer = functionOffer;
		this.available = available;
		this.skillsNeeded = skillsNeeded;
		this.noteSupp = noteSupp;
		this.subject = subject;
		this.offerType = offerType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public EnumOfferType getOfferType() {
		return offerType;
	}
	public void setOfferType(EnumOfferType offerType) {
		this.offerType = offerType;
	}
	public Address getSocietyAddress() {
		return societyAddress;
	}
	public void setSocietyAddress(Address societyAddress) {
		this.societyAddress = societyAddress;
	}
	public String getSocietyName() {
		return societyName;
	}
	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}
	public String getSocietyMail() {
		return societyMail;
	}
	public void setSocietyMail(String societyMail) {
		this.societyMail = societyMail;
	}
	public String getSocietySector() {
		return societySector;
	}
	public void setSocietySector(String societySector) {
		this.societySector = societySector;
	}
	public String getSocietyNum() {
		return societyNum;
	}
	public void setSocietyNum(String societyNum) {
		this.societyNum = societyNum;
	}
	public String getFunctionOffer() {
		return functionOffer;
	}
	public void setFunctionOffer(String functionOffer) {
		this.functionOffer = functionOffer;
	}
	public String getSkillsNeeded() {
		return skillsNeeded;
	}
	public void setSkillsNeeded(String skillsNeeded) {
		this.skillsNeeded = skillsNeeded;
	}
	public String getNoteSupp() {
		return noteSupp;
	}
	public void setNoteSupp(String noteSupp) {
		this.noteSupp = noteSupp;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
	public Offer clone() {
		Element el = super.clone();
		return new Offer(el.getAuthor(), el.getPublishDate(), el.getPathFile(), societyName, societyMail, societySector, societyNum, societyAddress, functionOffer, available, skillsNeeded, noteSupp, subject, offerType, startDate, endDate, amount);
	}
	
	public void update(Offer o) {
		setAuthor(o.getAuthor());
		setPublishDate(o.getPublishDate());
		setPathFile(o.getPathFile());
		setSocietyName(o.getSocietyName());
		setSocietyMail(o.getSocietyMail());
		setSocietySector(o.getSocietySector());
		setSocietyNum(o.getSocietyNum());
		setSocietyAddress(o.getSocietyAddress());
		setFunctionOffer(o.getFunctionOffer());
		setSkillsNeeded(o.getSkillsNeeded());
		setNoteSupp(o.getNoteSupp());
		setSubject(o.getSubject());
		setOfferType(o.getOfferType());
		setStartDate(o.getStartDate());
		setEndDate(o.getEndDate());
		setAmount(o.getAmount());
	}
	
	public void updateStatut (Offer o) {
		if(o.getAvailable() == true) {
			o.setAvailable(false);
		}else if (o.getAvailable() == false) {
			o.setAvailable(true);
		}
	}

	@Override
	public String toString() {
		return "Offer [societyName=" + societyName + ", societyMail=" + societyMail + ", societySector=" + societySector
				+ ", societyNum=" + societyNum + ", societyAddress=" + societyAddress + ", functionOffer="
				+ functionOffer + ", skillsNeeded=" + skillsNeeded + ", noteSupp=" + noteSupp + ", subject=" + subject
				+ ", offerType=" + offerType + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + super.toString() + "]";
	}
	
	



	





}
	