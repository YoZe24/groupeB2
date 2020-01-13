package be.helha.aemt.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Offer {
	
	private String societyName;
	private String societyMail;
	private String societySector;
	private int societyNum;
	//put String to Adress !!
	private String localisation;
	private String functionOffer;
	private List<String> skillsNeeded;
	private String noteSupp;
	private String subject;
	
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

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
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

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((functionOffer == null) ? 0 : functionOffer.hashCode());
		result = prime * result + ((societyName == null) ? 0 : societyName.hashCode());
		result = prime * result + societyNum;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Offer other = (Offer) obj;
		if (functionOffer == null) {
			if (other.functionOffer != null)
				return false;
		} else if (!functionOffer.equals(other.functionOffer))
			return false;
		if (societyName == null) {
			if (other.societyName != null)
				return false;
		} else if (!societyName.equals(other.societyName))
			return false;
		if (societyNum != other.societyNum)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	public Offer() {
		
	}

	public Offer(String societyName, String societyMail, String societySector, int societyNum,
			String localisation, String functionOffer, List<String> skillsNeeded, String noteSupp, String subject) {
		super();
		this.societyName = societyName;
		this.societyMail = societyMail;
		this.societySector = societySector;
		this.societyNum = societyNum;
		this.localisation = localisation;
		this.functionOffer = functionOffer;
		this.skillsNeeded = skillsNeeded;
		this.noteSupp = noteSupp;
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "societyName=" + societyName + ", societyMail=" + societyMail + ", societySector=" + societySector
				+ ", societyNum=" + societyNum + ", localisation=" + localisation + ", functionOffer=" + functionOffer
				+ ", skillsNeeded=" + skillsNeeded + ", noteSupp=" + noteSupp + ", subject=" + subject;
	}
	
}
	