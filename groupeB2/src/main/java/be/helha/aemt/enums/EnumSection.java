package be.helha.aemt.enums;

public enum EnumSection {
	ASSISTANT_DIRECTION("Assistant de direction"), COMTABILITE("Comptabilit�"),INFORMATIQUE("Informatique de gestion");
	
	private String section;
	
	EnumSection(String section) {
		this.section = section;
	}
		
	public String getSection() {
		return section;
	}

	public String toString() {
		return section;
	}
}
