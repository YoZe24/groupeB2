package be.helha.aemt.enums;

public enum EnumOfferType {

	//INTERNSHIP, CDD, CDI, TRAINING;
	INTERNSHIP("Stage"), CDD("CDD"), CDI("CDI"), TRAINING("Formation continue");
	
	private String type;
	
	EnumOfferType(String type) {
		this.type = type;
	}
		
	public String getType() {
		return type;
	}

	public String toString() {
		return type;
	}
}

