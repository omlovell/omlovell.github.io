// Author Name: Owen Lovell

// Date: 10/06/2024

// Course ID: CS-320-13376-M01

// Description: This is the appointment service class that adds or deletes an appointment

package module5;

import java.util.ArrayList;

public class AppointmentService {

	ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	// Add new Appointment
	public void addAppointment(String name, String description) {
		Appointment appt = new Appointment(name, description);
		appointmentList.add(appt);
	}
		
	// delete appointment
	public void deleteAppointment(String appointmentID) {
		appointmentList.remove(getAppointment(appointmentID));
	}
	
	public Appointment getAppointment(String appointmentID) {
		return appointmentList.stream()
			.filter(appointment -> appointment.getAppointmentID().equals(appointmentID))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Couldn't find Appointment with that AppointmentID"));
		
	}
	
}
