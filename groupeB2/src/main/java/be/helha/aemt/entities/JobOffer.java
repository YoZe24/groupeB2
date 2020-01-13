package be.helha.aemt.entities;

import java.util.List;

import be.helha.aemt.enums.EnumContractType;

public class JobOffer extends Offer {
	
	private EnumContractType typeContract;
	
	public JobOffer() {}
	
	public JobOffer(String societyName, String societyMail, String societySector, int societyNum,
			String localisation, String functionOffer, List<String> skillsNeeded, String noteSupp, String subject,EnumContractType typeContract) {
		super(societyName,societyMail,societySector,societyNum,localisation,functionOffer,skillsNeeded,noteSupp,subject);
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
