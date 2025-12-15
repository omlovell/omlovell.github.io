// Author Name: Owen Lovell

// Date: 10/06/2024

// Course ID: CS-320-13376-M01

// Description: This is the Appointment class that creates an appointment object that holds
// a appointmentID, a date, and description.

package module5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Appointment {
	
	private final String appointmentID;
	private Date appointmentDate;
	private String description;
	private static AtomicLong idGenerator = new AtomicLong();
	
	public Appointment(String dateOfAppointment, String description) {
		this.appointmentID = String.valueOf(idGenerator.getAndIncrement());
		 
		// DATE - day of today set to check if appointment date is in the past
		// fist if appointment is null then todays date will be auto assigned
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY"); 
		Date todaysDate = new Date(10/6/2024);	// FIXME: date is hardcoded
		
		if(dateOfAppointment == null) {
			this.appointmentDate = todaysDate;
		} else {
			try {
				Date temp = formatter.parse(dateOfAppointment);
				if(temp.before(todaysDate) == true) { 
					System.out.println("Date in the past!");
				} else {
					this.appointmentDate = temp;
				}
			} catch (ParseException e) {
				System.out.println("String DATE unable to be converted into date.");
				e.printStackTrace();
			}
		}
				
		// DESCRIPTION
		if(description == null || description.isBlank()){
			this.description = "NULL";
		} else if(description.length() > 50) {
			//System.out.println("Description too long, only first 50 characters kept.");
			this.description = description.substring(0,50);
		} else {
			this.description = description;
		}
	}
	
	// Getters
	public String getAppointmentID() {
		return appointmentID;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	// Setters
	
	public void setAppointmentDate(String newDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
		Date todaysDate = new Date(10/6/2024);
		try {
			Date temp = formatter.parse(newDate);
			if(temp.before(todaysDate) == true) { 
				System.out.println("Date in the past!");
			} else {
				this.appointmentDate = temp;
			}
		} catch (ParseException e) {
			System.out.println("String DATE unable to be converted into date.");
			e.printStackTrace();
		}
	}
	
	public void setDescription(String newDescription) {
		if(newDescription.length() > 50) {
			//System.out.println("Description too long, only first 50 characters kept.");
			description = newDescription.substring(0,50);
		} else if(newDescription == null || newDescription.isBlank()){
			description = "NULL";
		} else {
			description = newDescription;
		}
	}

}
