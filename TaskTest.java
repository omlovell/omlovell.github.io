// Author Name: Owen Lovell

// Date: 9/29/2024

// Course ID: CS-320-13376-M01

// Description: This is the test for the task class. it tests creating and updating task information


package module4;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class TaskTest {

	@Test
	@DisplayName("Test to add new task")
	@Order(1)
	void testTask() {
		Task taskTest = new Task("Dog Walk", "Walk the dog in a circle");
		
		assertTrue(taskTest.getTaskID().equals("0"));
		assertTrue(taskTest.getName().equals("Dog Walk"));
		assertTrue(taskTest.getDescription().equals("Walk the dog in a circle"));
	}
		
		

	@Test
	@DisplayName("Test getTaskID")
	@Order(2)
	void testGetTaskID() {
		Task taskTest = new Task("Locate X", "X is located on the map of the world.");
		
		assertTrue(taskTest.getTaskID().equals("1"));
	}

	@Test
	@DisplayName("Test to get name")
	@Order(3)
	void testGetName() {
		Task taskTest = new Task("What is that?", "The task finds what that is...");
		if("What is that?" == taskTest.getName()) {
			// System.out.println("Test Has Passed.")
		} else {fail("Test failed Name mismatch"); }
	}

	@Test
	@DisplayName("Test to get description")
	@Order(4)
	void testGetDescription() {
		Task taskTest = new Task("Strike out", "The batter has reached three strikes.");
		if("The batter has reached three strikes." == taskTest.getDescription()) {
			// System.out.println("Test Has Passed.")
		} else {fail("Test failed Description mismatch");}
	}

	@Test
	@DisplayName("Test to set name")
	@Order(5)
	void testSetName() {
		Task taskTest = new Task("Ticket Buy", "The ticket will be purchased at the counter.");
		taskTest.setName("Train ticket purchase, and ticket printed, and ticket recieved.");
		if(taskTest.getName().length() > 20) {
			System.out.println("Test 5; Task name length was " + taskTest.getName().length());
			fail("Test failed Name is too long");
		} else {
			// System.out.println("Test Has Passed.")}
		}
	}

	@Test
	@DisplayName("Test to set new description")
	@Order(6)
	void testSetDescription() {
		Task taskTest = new Task("Buy train ticket.", "Ticket was purchased and recieved.");
		taskTest.setDescription("The ticket will be purchased at the counter. Then the ticket will be handed to the customer and the customer shall pay a fee that corelates to the ticket time and disctance.");
		if(taskTest.getDescription().length() > 50) {
			System.out.println("Test 6; Task description length was " + taskTest.getDescription().length());
			fail("Test failed description is too long");
		} else {
			// System.out.println("Test Has Passed.")}
		}

	}

}
