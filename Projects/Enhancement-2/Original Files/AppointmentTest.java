// Author Name: Owen Lovell

// Date: 10/06/2024

// Course ID: CS-320-13376-M01

// Description: This is the Appointment Test class that tests the creation of an appointment and 
// the different parts of the appointment object

package module5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppointmentTest {

	// invalid date
	@Test
	@DisplayName("Invalid Date, date not valid for specific calender.")
	void testDateThatisInvalid() {
		Date test = new Date(10/31/2024);
		Appointment appt = new Appointment("10/69/2024", "IS the Date valid");
		if(appt.getAppointmentDate().after(test) == true) {
			fail("Date is in the past");
		}
	}
	
	// date in the past
	@Test
	@DisplayName("Date is before today (10/6/2024).")
	void testDateBeforeToday() {
		Date today = new Date(10/6/2024);
		Appointment appt = new Appointment("10/1/2024", "IS the Date before the 6th");
		if(appt.getAppointmentDate().before(today) == true) {
			fail("Date is in the past");
		}
	}
	
	// description over 50
	@Test
	@DisplayName("Description over 50 characters")
	void testDescriptionWithOverFiftyCharacters() {
		Appointment appt = new Appointment("10/30/2024", "This was a test to see if the patient has normal vision or advanced vision");
		if(appt.getDescription().length() > 50) {
			fail("Description is over 50 characters");
		}
	}
	
	// description null
	@Test
	@DisplayName("Date is cannot be null")
	void testDateNULL() {
		Appointment appt = new Appointment(null, "Treat a null case date");
		if(appt.getAppointmentDate() == null) {
			fail("Date is null");
		}
	}
	
	// date null
	@Test
	@DisplayName("Description is cannot be null")
	void testDescriptionNULL() {
		Appointment appt = new Appointment("10/25/2024", null);
		if(appt.getDescription() == null) {
			fail("Description is null");
		}
	}
	
	

}
