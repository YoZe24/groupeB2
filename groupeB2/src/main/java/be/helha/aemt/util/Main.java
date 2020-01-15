package be.helha.aemt.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import be.helha.aemt.control.UserControl;
import be.helha.aemt.dao.AddressDAO;
import be.helha.aemt.dao.UserDAO;
import be.helha.aemt.ejb.UserGestionEJB;
import be.helha.aemt.entities.Address;
import be.helha.aemt.entities.Event;
import be.helha.aemt.entities.User;
import be.helha.aemt.enums.EnumRole;
import be.helha.aemt.enums.EnumSection;


public class Main {


	public static void main(String[] args) {

		System.out.println(new File("").getAbsolutePath()+"\\");
		
		Address addressUserTest = new Address("S2", "N2", "C2", "CP2");
		Address addressEventTest = new Address("TEST", "TEST", "TEST", "TEST");
		User userTest = new User("userTestName", "userTestFirstName", "userTestMail", "userTestLogin", "testMDP", "00000", addressUserTest, EnumRole.ADMINISTRATOR);
		LocalDateTime dateTimePublish = LocalDateTime.parse("2020-01-08 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime startDateTestEvent = LocalDateTime.parse("2020-01-09 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime endDateTestEvent = LocalDateTime.parse("2020-01-10 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		Event eventManual = new Event(userTest, dateTimePublish,"testPathFile",startDateTestEvent, endDateTestEvent, addressEventTest, "testTitle", "testDescription");
	
		System.out.println(eventManual.toString());
	}
	
}
