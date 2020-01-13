package be.helha.aemt.entities;

import java.util.List;

public class TrainingOffer extends Offer{
	
	private int durationDays;
	private boolean remuneration;
	
	public TrainingOffer() {
		
	}
	
	public TrainingOffer(String societyName, String societyMail, String societySector, int societyNum,
			String localisation, String functionOffer, List<String> skillsNeeded, String noteSupp, String subject,int durationDays, boolean remuneration) {
		super(societyName,societyMail,societySector,societyNum,localisation,functionOffer,skillsNeeded,noteSupp,subject);
	}

	public int getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	public boolean isRemuneration() {
		return remuneration;
	}

	public void setRemuneration(boolean remuneration) {
		this.remuneration = remuneration;
	}

	@Override
	public String toString() {
		return "TrainingOffer [durationDays=" + durationDays + ", remuneration=" + remuneration +super.toString() + "]";
	}
	
	

}
