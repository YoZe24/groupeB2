package be.helha.aemt.util;

import be.helha.aemt.control.UserControl;
import be.helha.aemt.dao.UserDAO;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Ancien;
import be.helha.aemt.enums.EnumSection;

public class Main {


	public static void main(String[] args) {
		
		//UserControl userControl = new UserControl();
		
		UserDAO userDAO = new UserDAO();
		
		Ancien ancien = new Ancien("A1", "FS1", "M1", "LA1", "91e8c23c79fe019eea9a858d90e4be24dc917988c6fe2e4a55a2339f027b005c", "PN1", 2020, EnumSection.ECONOMIQUE, new Address(), "");
		System.out.println(ancien.toString());
//		userControl.addUser(ancien);
		userDAO.postUser(ancien);
	}

}
