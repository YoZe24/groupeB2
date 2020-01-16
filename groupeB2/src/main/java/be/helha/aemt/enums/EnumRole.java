package be.helha.aemt.enums;

public enum EnumRole {
	ADMINISTRATOR("Admin"),MODERATOR("Modérateur"),ANCIENT("Ancien"),VISITOR("visiteur");

	public String role;
	
	EnumRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		return role;
	}
}
