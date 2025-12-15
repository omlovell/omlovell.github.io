// Author Name: Owen Lovell

// Date: 9/22/2024

// Course ID: CS-320-13376-M01

// Description: Unit tests for the contact service (ContactServiceTest)


package module3B;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class ContactServiceTest {

	/*
	* The following tests exercise the ContactService class.
	* In each test, a new service is created with default values for each field.
	* Then the service is requested to make some change to the list of contacts and the result
	* is tested to ensure the actual field matches what is expected.
	*
	* Because each contact that gets created has a new automatically assigned contactID,
	* and the tests are run based on that contactID, the order in which the tests are run depend
	* on the value of each contactID. Therefore, the @Order annotation is used to keep the tests
	* in a specific order.
	*
	* For evidence that the contactID is correct for each test, uncomment the service.displayContactList();
	* prior to each assertion so that the records are displayed on the console for error checking.
	*/
	
	@Test
	@DisplayName("Test to Update First Name.")
	@Order(1)
	void testUpdateFirstName() {
		ContactService service = new ContactService();
		service.addContact("Dr.", "Crosss", "5555551111", "123 Lollypop Lane");
		service.updateFirstName("Sven","0");
		
		//service.displayContactList();
		assertTrue(service.getContact("0").getFirstName().equals("Sven"));
		
	}
	
	// Finish code here ..........
	// test update last name
	
	@Test
	@DisplayName("Test to Update Last Name.")
	@Order(2)
	void testUpdateLastName() {
		ContactService service = new ContactService();
		service.addContact("Dante", "Rodgers", "123456789", "No way home");
		service.updateLastName("Dodger", "1");
		
		//service.displayContactList();
		assertTrue(service.getContact("1").getLastName().equals("Dodger"));
	}
	
	// test update phone 
	@Test
	@DisplayName("Test to Update Phone.")
	@Order(3)
	void testUpdatePhone() {
		ContactService service = new ContactService();
		service.addContact("OWen", "Lovell", "3155555555", "No home location");
		service.updateNumber("3159355555", "2");
		
		//service.displayContactList();
		assertTrue(service.getContact("2").getNumber().equals("3159355555"));
	}
	
	// test update address.
	@Test
	@DisplayName("Test to Update Address.")
	@Order(4)
	void testUpdateAddress() {
		ContactService service = new ContactService();
		service.addContact("Emily", "Sturgeon", "123456789", "Not Available");
		service.updateAddress("2345 Highway Road", "3");
		
		//service.displayContactList();
		assertTrue(service.getContact("3").getAddress().equals("2345 Highway Road"));
	}
	
	@Test
	@DisplayName("Test to Delete Contact")
	@Order(5)
	void testDeleteContact() {
		ContactService service = new ContactService();
		service.addContact("Tyler", "Happy", "123456789", "Not Available");
		int beforeDelete = service.contactList.size();
		service.deleteContact("1");
		System.out.println("Size before delete:" + beforeDelete);
		int afterDelete = service.contactList.size();
		
		service.displayContactList();
		
		assertTrue(afterDelete < beforeDelete);
	}
	
}