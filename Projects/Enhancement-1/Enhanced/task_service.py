# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Task List Class is creating objects of tasks and adding them to a list
"""

from task import *

task_list = []


class TaskList:

    def add_task(self, name, description):
        new_task = Task(name, description)
        task_list.append(new_task)

    # removes first instance of task with that name
    # should switch to id
    def remove_task(self, task_name):
        task_list.remove(task_name)

    # get specific appointment for testing etc
    def get_task(self, name):
        try:
            index = task_list.index(name)
            print("Found at index: ")
            print(index)
            return index
        except ValueError:
            print("Task Not Found")

    # to string
    def print_task_list(self):
        print('\n'.join(map(str, task_list)))
