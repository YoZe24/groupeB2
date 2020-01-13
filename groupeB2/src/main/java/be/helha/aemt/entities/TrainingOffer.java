package be.helha.aemt.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class TrainingOffer extends Offer{
	
	private int durationDays;
	private boolean isRemunerate;
	private double amount;
	
	public TrainingOffer() {
		
	}
	
	public TrainingOffer(User author, LocalDateTime publishDate, String pathFile, String societyName, String societyMail,
			String societySector, int societyNum, Address address, String functionOffer, List<String> skillsNeeded,
			String noteSupp, String subject, int durationDays, boolean isRemunerate, double amount) {
		super(author, publishDate, pathFile, societyName, societyMail, societySector, societyNum, address,
				functionOffer, skillsNeeded, noteSupp, subject);
		this.durationDays = durationDays;
		this.isRemunerate = isRemunerate;
		this.amount = amount;
	}

	public boolean isRemunerate() {
		return isRemunerate;
	}

	public void setRemunerate(boolean isRemunerate) {
		this.isRemunerate = isRemunerate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	@Override
	public String toString() {
		return "TrainingOffer [durationDays=" + durationDays + ", isRemunerate=" + isRemunerate + ", amount=" + amount
				+ "]";
	}

}
