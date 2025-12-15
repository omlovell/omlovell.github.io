// Author Name: Owen Lovell

// Date: 9/22/2024

// Course ID: CS-320-13376-M01

// Description: This is the contact class. it creates and stores contact info. see constructor for more info


package module3B;

import java.util.concurrent.atomic.AtomicLong;

public class Contact {
	private final String contactID;
	private String firstName;
	private String lastName;
	private String Number;
	private String Address;
	private static AtomicLong idGenerator = new AtomicLong();
	
	// Constructor
	
	/*
	* The constructor takes first name, last name, phone number, and address as parameters.
	* The first thing it does is generates a new ID for the contactID field.
	*
	* First name and last name are checked for null condition or blank fields. If either of
	* those conditions exist, fill in the field with the phrase "NULL" so that something exists
	* to protect data integrity while making it clear it is a placeholder.
	* In both cases, if the first or last name is greater than 10 characters, truncate it
	* so that only the first 10 characters are used.
	*
	* For the number field, if the phone number is not exactly 10 characters then fill it with
	* the placeholder '5555555555'.
	*
	* Address is like first and last names. If it is blank or null, set it to "NULL".
	* If it is more than 30 characters, truncate to the first 30 characters.
	*/
	
	public Contact(String firstName, String lastName, String number, String address) {
		
		//CONTACTID
		//Contact ID is generated when the constructor is called. It is set as a final variable
		// and has no other getter or setter so there should be no way to change it.
		// the idGenerator is static to prevent duplicates across all contacts.
		this.contactID = String.valueOf(idGenerator.getAndIncrement());
		
		//FIRSTNAME
		if (firstName == null || firstName.isBlank()) {
			this.firstName = "NULL";
		//if first name is longer than 10 char, grab first 10 chars	
		} else if(firstName.length() > 10) {
			this.firstName = firstName.substring(0,10);
		} else {
			this.firstName = firstName;
		}
		
		//LASTNAME
		if (lastName == null || lastName.isBlank()) {
			this.lastName = "NULL";
		//if first name is longer than 10 char, grab first 10 chars	
		} else if(lastName.length() > 10) {
			this.lastName = lastName.substring(0,10);
		} else {
			this.lastName = lastName;
		}
		
		//NUMBER
		if(number == null || number.isBlank() || number.length() != 10) {
			this.Number = "5555555555"; // FIXME number hardcoded -- try random 10 digit numbers for phone
		} else {
			this.Number = number;
		}
		
		//ADDRESS
		if(address == null || address.isBlank()) {
			this.Address = "NULL";
		} else if(address.length() > 30) {
			this.Address = address.substring(0,30);
		} else {
			this.Address = address;
		}
	}
		
	//GETTERS
	public String getContactID() {
		return contactID;
	}
	
	// Finish code for 'Getters' .........
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNumber() {
		return Number;
	}

	public String getAddress() {
		return Address;
	}
	
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public void setAddress(String address) {
		this.Address = address;
	}
	public void setLastName(String name) {
		this.lastName = name;
	}
	public void setNumber(String number) {
		this.Number = number;
	}
}