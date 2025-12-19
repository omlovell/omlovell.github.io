# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Task is used to create a task object, which holds task id, name, and description
"""

import itertools


class Task:

    id_iter = itertools.count()

    # Constructor for task name and description
    # FIXME: Add validation and checks
    def __init__(self, task_name, description):
        self.task_name = task_name
        self.description = description

        self.task_id = next(self.id_iter)

    # Getters
    def get_task_name(self):
        return self.task_name

    def get_description(self):
        return self.description

    # Setters
    def set_task_name(self, new_task_name):
        self.task_name = new_task_name

    def set_description(self, new_description):
        self.description = new_description

    # to string
    def __str__(self):
        return "Task ID: {}, Name: {}, Description: {}"\
            .format(self.task_id, self.task_name, self.description)
