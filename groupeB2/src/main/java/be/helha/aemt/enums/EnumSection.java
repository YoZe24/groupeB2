package be.helha.aemt.enums;

public enum EnumSection {
	ECONOMIQUE("Economique"),AGRONOMIE("Agronomie"),ART_APPLIQUES("Art appliqués"),SOCIAL("Social"),SANTE("Sante"),PEDAGOGIQUE("Pedagogique"),TECHNIQUE("Technique");
	
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
