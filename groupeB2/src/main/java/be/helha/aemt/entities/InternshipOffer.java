package be.helha.aemt.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class InternshipOffer extends Offer {

	private int durationDays;

	public InternshipOffer() {}
			
	public InternshipOffer(User author, LocalDateTime publishDate, String pathFile, String societyName, String societyMail,
			String societySector, int societyNum, Address address, String functionOffer, List<String> skillsNeeded,
			String noteSupp, String subject, int durationDays) {
		super(author, publishDate, pathFile, societyName, societyMail, societySector, societyNum, address,
				functionOffer, skillsNeeded, noteSupp, subject);
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
