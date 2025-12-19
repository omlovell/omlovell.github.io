// Author Name: Owen Lovell

// Date: 9/29/2024

// Course ID: CS-320-13376-M01

// Description: this is the task service, it tests adding tasks to the task service
// by adding, deleting, and updating information of a task.

package module4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class TaskServiceTest {

	@Test
	@DisplayName("Test Add Task")
	@Order(1)
	void testAddTask() {
		TaskService service = new TaskService();
		service.addTask("Enter", "The door was opened");
		
		assertTrue(service.getTask("0").getName().equals("Enter"));
		assertTrue(service.getTask("0").getDescription().equals("The door was opened"));
	}

	@Test
	@DisplayName("Test Delete Task")
	@Order(4)
	void testDeleteTask() {
		TaskService service = new TaskService();
		service.addTask("Open", "The door was opened");
		service.addTask("Close", "The door was closed");
		
		int listSizeBefore = service.taskList.size();
		System.out.println("List size 1 =" + listSizeBefore);
		service.deleteTask("1");
		int listSizeAfter = service.taskList.size();
		System.out.println("List size 2 =" + listSizeAfter);
		
		assertTrue(listSizeBefore > listSizeAfter);

	}

	@Test
	@DisplayName("Test Update Name")
	@Order(2)
	void testUpdateName() {
		TaskService service = new TaskService();
		service.addTask("NEWTASK", "This task no work");
		
		service.updateName("Start Record", "1");
		//System.out.println("Updated name =" + service.getTask("1").getName());
		assertTrue(service.getTask("1").getName().equals("Start Record"));
		
	}

	@Test
	@DisplayName("Test Update Description")
	@Order(3)
	void testUpdateDescription() {
		TaskService service = new TaskService();
		service.addTask("Old Task", "NO NO NO NO");
		
		service.updateDescription("Create a work of art", "2");
		//System.out.println("Updated name =" + service.getTask("2").getDescription());
		
		assertTrue(service.getTask("2").getDescription().equals("Create a work of art"));
	}

}


// NEED ORDERING

// double check assertions 

// 