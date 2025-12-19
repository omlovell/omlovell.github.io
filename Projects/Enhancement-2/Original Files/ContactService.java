// Author Name: Owen Lovell

// Date: 9/22/2024

// Course ID: CS-320-13376-M01

// Description: This is the contact service. Maintains a list of contacts 
// and has capabilities for adding and deleting contacts, as well as updating first
// name, last name, phone number, and address.
// Partially copied from teacher hints to get started

package module3B;

import java.util.ArrayList;

public class ContactService {

	//Start with Array List of contacts to hold list of contacts
	ArrayList<Contact> contactList = new ArrayList<Contact>();
	
	//Display full list of contacts to the console for error checking
	public void displayContactList() {
		for(int counter = 0; counter < contactList.size(); counter++) {
			System.out.println("\t Contact ID:" + contactList.get(counter).getContactID());
			System.out.println("\t First Name:" + contactList.get(counter).getFirstName());
			System.out.println("\t Last Name:" + contactList.get(counter).getLastName());
			System.out.println("\t Phone Number:" + contactList.get(counter).getNumber());
			System.out.println("\t Address:" + contactList.get(counter).getAddress() + "\n");
			
		}	
	}
	
	// Adds new contact using the Contact constructor, then assigns new contact to list
	public void addContact(String firstName, String lastName, String number, String address) {
		// Create new contact
		Contact contact = new Contact(firstName, lastName, number, address);
		contactList.add(contact);
	}

	
	// Finish creating the code here .........
	
	// Gets contact 
	public Contact getContact(String contactID) {
		return contactList.stream()
			.filter(contact -> contact.getContactID().equals(contactID))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Invalid contactID"));
		
	}
	
	// Deletes contacts using contact ID. 
	public void deleteContact(String contactID) {
		contactList.remove(getContact(contactID));
	}
	
	// the Following updater's are  firstName, lastName, Number, and Address
	public void updateFirstName(String newfirstName, String contactID) {
		getContact(contactID).setFirstName(newfirstName);
	}
	
	public void updateLastName(String newLastName, String contactID) {
		getContact(contactID).setLastName(newLastName);
			}
	
	public void updateNumber(String newNumber, String contactID) {
		getContact(contactID).setNumber(newNumber);	
	}
	
	public void updateAddress(String newAddress, String contactID) {
		getContact(contactID).setAddress(newAddress);
	}

	
	
}