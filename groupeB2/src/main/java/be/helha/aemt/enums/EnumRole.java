package be.helha.aemt.enums;

public enum EnumRole {
	ADMINISTRATOR("Admin"),MODERATOR("Mod�rateur"),ANCIENT("Ancien");

	public String role;
	
	EnumRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		return role;
	}
}
