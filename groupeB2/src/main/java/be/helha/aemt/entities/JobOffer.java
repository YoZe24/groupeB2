package be.helha.aemt.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import be.helha.aemt.enums.EnumContractType;

@Entity
public class JobOffer extends Offer {
	
	@Enumerated(EnumType.STRING)
	private EnumContractType typeContract;
	
	public JobOffer() {}
	
	public JobOffer(User author, LocalDateTime publishDate, String pathFile, String societyName, String societyMail,
			String societySector, int societyNum, Address address, String functionOffer, List<String> skillsNeeded,
			String noteSupp, String subject, EnumContractType typeContract) {
		super(author, publishDate, pathFile, societyName, societyMail, societySector, societyNum, address,
				functionOffer, skillsNeeded, noteSupp, subject);
		this.typeContract = typeContract;
	}

	public EnumContractType getTypeContract() {
		return typeContract;
	}

	public void setTypeContract(EnumContractType typeContract) {
		this.typeContract = typeContract;
	}

	@Override
	public String toString() {
		return "JobOffer [typeContract=" + typeContract + super.toString() + "]";
	}
	
	

}
