# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Task Service is used to create an task object, the object is then placed in a dictionary
where the tasks are placed together
"""

from task import Task
from collections import defaultdict


class TaskDict:
    def __init__(self):
        # key is name, of task value is task objects including name and description
        self.tasks = defaultdict(list)

    def add_task(self, name, task: Task):
        if name not in self.tasks:
            self.tasks[name] = []
        self.tasks[name].append(task)

    # removing a task based off the object
    def remove_task(self, name, task: Task) -> bool:
        if name not in self.tasks:
            return False

        try:
            self.tasks[name].remove(Task)
            if not self.tasks[name]:
                del self.tasks[name]
            return True
        except ValueError:
            return False

    def get_tasks(self, name):
        return self.tasks.get(name, [])
