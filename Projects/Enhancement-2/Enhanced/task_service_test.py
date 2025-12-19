# Author: Owen Lovell
# Date Updated: 12/13/2025
# Course ID: CS-499-17242-M01

import unittest
from task_service import *


class TestTaskService(unittest.TestCase):
    # Test creation and deletion of Task
    def test_task_service(self):
        test_instance = TaskList()
        test_instance.add_task("Start Engine", "Klein performs magic to start the device")
        self.assertTrue(test_instance.get_task("Start Engine"))
        test_instance.remove_task("Start Engine")
        self.assertFalse(test_instance.get_task("Start Engine"))


if __name__ == '__main__':
    unittest.main()