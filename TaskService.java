// Author Name: Owen Lovell

// Date: 9/29/2024

// Course ID: CS-320-13376-M01

// Description: This is the task service, which uses an array list to maintain the list of tasks
// can update name and description as well as add tasks and remove them

package module4;

import java.util.ArrayList;

// needs to add tasks/ delete/ update, name and description

public class TaskService {

	ArrayList<Task> taskList = new ArrayList<Task>();
	
	public void displayTaskList() {
		for(int x = 0; x < taskList.size(); x++) {
			System.out.println("\n TaskID:" + taskList.get(x).getTaskID());
			System.out.println("\n Name:" + taskList.get(x).getName());
			System.out.println("\n Description:" + taskList.get(x).getDescription() + "\n");
		}
	}
	
	// Add new task
	public void addTask(String name, String description) {
		Task task = new Task(name, description);
		taskList.add(task);
	}
	
	// delete task
	public void deleteTask(String taskID) {
		taskList.remove(getTask(taskID));
	}
	
	// update name
	public void updateName(String newName, String taskID) {
		getTask(taskID).setName(newName);
	}
	
	// update description
	public void updateDescription(String newDescription, String taskID) {
		getTask(taskID).setDescription(newDescription);
	}

	// uses a stream to sort threw the task list and get the first occurrence of that taskID
	public Task getTask(String taskID) {
		return taskList.stream()
				.filter(task -> task.getTaskID().equals(taskID))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Invalid taskID"));
			
		}
}
