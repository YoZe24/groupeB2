package be.helha.aemt.enums;

public enum EnumContractType {
	CDI("Cdi"), CDD("Cdd");
	
	private String contractType;
	
	EnumContractType(String ct) {
		this.contractType = ct;
	}
	
	public String getContractType() {
		return contractType;
	}
	
}
