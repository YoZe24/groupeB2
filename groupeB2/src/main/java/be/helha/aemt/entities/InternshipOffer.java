package be.helha.aemt.entities;

import java.util.List;

import javax.persistence.Entity;


public class InternshipOffer extends Offer {
	
	private int durationDays;

	public InternshipOffer() {}
	
	public InternshipOffer(String societyName, String societyMail, String societySector, int societyNum,
			String localisation, String functionOffer, List<String> skillsNeeded, String noteSupp, String subject,int durationDays) {
		super(societyName,societyMail,societySector,societyNum,localisation,functionOffer,skillsNeeded,noteSupp,subject);
		this.durationDays = durationDays;
	}
	
	public int getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	@Override
	public String toString() {
		return "InternshipOffer [durationDays=" + durationDays +super.toString() + "]";
	}
	

}
