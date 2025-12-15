// Author Name: Owen Lovell

// Date: 9/29/2024

// Course ID: CS-320-13376-M01

// Description: This is the task class. It creates and stores task info

package module4;

import java.util.concurrent.atomic.AtomicLong;

// needs to have taskID, name, description

public class Task {

	private final String taskID;
	private String name;
	private String description;
	private static AtomicLong idGenerator = new AtomicLong();
	
	/* CONSTRUCTOR
	* takes name, description as parameters.
	* a new taskID is generated for taskID
	* name and description are checked to fit requirements as not null and of a certain length
	*/
	public Task(String name, String description) {
		
		// creates taskID that is based of when the task is added
		this.taskID = String.valueOf(idGenerator.getAndIncrement());
		
		// name can't be null and can't be longer than 20 characters
		if (name == null || name.isBlank()) {
			this.name = "NULL";
		} else if(name.length() > 20) {
			this.name = name.substring(0,20);
		} else {
			this.name = name;
		}
		
		// description can't be longer than 50 characters and can't be null
		if (description == null || description.isBlank()) {
			this.description = "NULL";
		} else if(description.length() > 50) {
			this.description = description.substring(0,50);
		} else {
			this.description = description;
		}
		
	}
	
	//GETTERS
	public String getTaskID() {
		return taskID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	//SETTERS
	public void setName(String thename) {
		if (thename == null || thename.isBlank()) {
			name = "NULL";
		} else if(thename.length() > 20) {
			name = thename.substring(0,20);
		} else {
			name = thename;
		}
		
	}
	
	public void setDescription(String thedescription) {
		if(thedescription.length() > 50) {
			//System.out.println("Description too long, only first 50 characters kept.");
			description = thedescription.substring(0,50);
		} else if(thedescription == null || thedescription.isBlank()){
			description = "NULL";
		} else {
			description = thedescription;
		}
	}
	
}
