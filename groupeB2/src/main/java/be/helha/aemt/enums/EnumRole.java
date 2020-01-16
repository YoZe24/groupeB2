package be.helha.aemt.enums;

public enum EnumRole {
	ADMINISTRATOR("admin"),MODERATOR("Modérateur"),ANCIENT("ancien"),VISITOR("visiteur");

	public String role;
	
	EnumRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		return role;
	}
}
