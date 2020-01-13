package be.helha.aemt.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import be.helha.aemt.enums.EnumOfferType;

@Entity
public class Offer extends Element implements Serializable{
	
	private String societyName;
	private String societyMail;
	private String societySector;
	private int societyNum;	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address societyAddress;
	private String functionOffer;
	
	private List<String> skillsNeeded;
	private String noteSupp;
	private String subject;
	private EnumOfferType offerType;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private double amount;
	
	
	
	
	public Offer() {
		
	}


	public Offer(User author, LocalDateTime publishDate, String pathFile, String societyName, String societyMail,
			String societySector, int societyNum, Address societyAddress, String functionOffer,
			List<String> skillsNeeded, String noteSupp, String subject, EnumOfferType offerType,
			LocalDateTime startDate, LocalDateTime endDate, double amount) {
		super(author, publishDate, pathFile);
		this.societyName = societyName;
		this.societyMail = societyMail;
		this.societySector = societySector;
		this.societyNum = societyNum;
		this.societyAddress = societyAddress;
		this.functionOffer = functionOffer;
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

	public int getSocietyNum() {
		return societyNum;
	}

	public void setSocietyNum(int societyNum) {
		this.societyNum = societyNum;
	}

	public String getFunctionOffer() {
		return functionOffer;
	}

	public void setFunctionOffer(String functionOffer) {
		this.functionOffer = functionOffer;
	}

	public List<String> getSkillsNeeded() {
		return skillsNeeded;
	}

	public void setSkillsNeeded(List<String> skillsNeeded) {
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



	





}
	