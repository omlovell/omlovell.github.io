// Author Name: Owen Lovell

// Date: 9/22/2024

// Course ID: CS-320-13376-M01

// Description: Unit tests for the contact class (ContactTest)

package module3B;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

	/*
	* The following tests exercise the Contact class.
	* The first 5 tests to make sure the field does not become longer than the constraint
	* (10 characters for first and last name, exactly 10 characters for phone number,
	* and 30 characters for the address).
	* The last 4 tests ensure that each field is not null.
	* ContactID is not tested for being not null because there isn't a way to create
	* a contact with a null contactID. Likewise it is not tested for being non-updateable
	* because there is no way to update it.
	*/
	
	@Test
	@DisplayName("Contact ID cannot have more than 10 characters")
	void testContactIDWithMoreThanTenCharacters() {
		Contact contact = new Contact("FirstName","LastName","PhoneNumbr","Address");
		if(contact.getContactID().length() > 10) {
			fail("Contact ID has more than 10 characters.");
		}
	}

	@Test
	@DisplayName("Contact First Name cannot have more than 10 characters")
	void testContactFirstNameWithMoreThanTenCharacters() {
		Contact contact = new Contact("OllyOllyOxenFree","LastName","PhoneNumbr","Address");
		if(contact.getFirstName().length() > 10) {
			fail("First Name has more than 10 characters.");
		}
	}

	// Finish Code 
	
	// last name no more than 10 characters
	@Test
	@DisplayName("Contact Last Name cannot have more than 10 characters")
	void testContactLastNameWithMoreThanTenCharacters() {
		Contact contact = new Contact("Owen","Lovellllllllll","PhoneNumbr","Address");
		if(contact.getLastName().length() > 10) {
			fail("Last Name has more than 10 characters.");
		}
	}
	
	// number HAS exactly 10 characters
	@Test
	@DisplayName("Contact Number must be 10 characters long")
	void testContactNumberNotTenCharacters() {
		Contact contact = new Contact("Bobby","Love","PhoneNumbers","Address");
		if(contact.getNumber().length() > 10 || contact.getNumber().length() < 10) {
			fail("Number is not exactly 10 characters");
		}
	}
	
	// Address is no more than 30 chracters
	@Test
	@DisplayName("Contact Address cannot have more than 30 characters")
	void testContactAddressWithMoreThanThirtyCharacters() {
		Contact contact = new Contact("Owen","Lovellllllllll","PhoneNumbr","Address");
		if(contact.getAddress().length() > 30) {
			fail("Address has more than 30 characters.");
		}
	}
	
	// first name is not null
	@Test
	@DisplayName("Contact First Name cannot be null")
	void testContactFirstNameNULL() {
		Contact contact = new Contact(null,"LastName","PhoneNumbr","Address");
		if(contact.getFirstName() == null) {
			fail("First Name is null.");
		}
	}
	
	// last name is not null
	@Test
	@DisplayName("Contact Last Name cannot be null")
	void testContactLastNameNULL() {
		Contact contact = new Contact("Owen",null ,"PhoneNumbr","Address");
		if(contact.getLastName() == null ) {
			fail("Last Name is null");
		}
	}
	
	
	// number not null
	@Test
	@DisplayName("Contact Number must not be null")
	void testContactNumberNULL() {
		Contact contact = new Contact("Bobby","Love",null,"Address");
		if(contact.getNumber() == null ) {
			fail("Number is null");
		}
	}
	
	
	// address is not null
	@Test
	@DisplayName("Contact Address cannot be null")
	void testContactAddressNULL() {
		Contact contact = new Contact("Emily","Lovell","1234567890",null);
		if(contact.getAddress() == null) {
			fail("Address is null");
		}
	}
	
}