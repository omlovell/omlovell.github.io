// Author Name: Owen Lovell

// Date: 10/06/2024

// Course ID: CS-320-13376-M01

// Description: This is the appointment service test class that adds or deletes an appointment


package module5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {

	@Test
	@DisplayName("Test Add Appointment")
	@Order(1)
	void testAddAppointment() {
		AppointmentService service = new AppointmentService();
		service.addAppointment("10/25/2024", "The doctor is available");
		
		Date testDate = new Date(10/25/2024);
		if(service.getAppointment("0").getAppointmentDate().compareTo(testDate) > 0 || service.getAppointment("0").getAppointmentDate().compareTo(testDate) < 0) {
			System.out.println("The Date was stored as Appointment object as: " + service.getAppointment("0").getAppointmentDate());
			fail("Date doesn't match the string, something went wrong creating the date");
		}
		assertTrue(service.getAppointment("0").getDescription().equals("The doctor is available"));
	}

	@Test
	@DisplayName("Test Delete Appointment")
	@Order(2)
	void testDeleteAppointment() {
		AppointmentService service = new AppointmentService();
		service.addAppointment("10/24/2024", "Help is on the way");
		service.addAppointment("10/26/2024", "Routine check up");
		
		int listSizeBefore = service.appointmentList.size();
		System.out.println("List size 1 =" + listSizeBefore);
		service.deleteAppointment("1");
		int listSizeAfter = service.appointmentList.size();
		System.out.println("List size 2 =" + listSizeAfter);
		
		assertTrue(listSizeBefore > listSizeAfter);

	}

}
