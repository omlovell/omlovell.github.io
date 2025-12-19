# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Task test class makes sure that in creating the task there is no errors in making the object
"""


from task import *
import unittest


class TestTaskInformation(unittest.TestCase):
    
    def test_task_parameter_length(self):
        bad_instance = Task("NameLongerThan20Characters", "DescriptionLongerThanThirtyCharacters")
        self.assertLessEqual(len(bad_instance.task_name), 20)
        self.assertLessEqual(len(bad_instance.description), 30)

    
    def test_task_null(self):
        bad_instance = Task(None, None)
        self.assertTrue(bad_instance.task_id is not None)
        self.assertTrue(bad_instance.task_name is not None)
        self.assertTrue(bad_instance.description is not None)


if __name__ == '__main__':
    unittest.main()
