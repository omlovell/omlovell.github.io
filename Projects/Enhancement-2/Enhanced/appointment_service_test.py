# Author: Owen Lovell
# Date Updated: 12/13/2025
# Course ID: CS-499-17242-M01

import unittest
from appointment_service import *


class TestAppointmentService(unittest.TestCase):
    # Test to check creation and deletion of appointment
    def test_appointment_create(self):
        test_instance = AppointmentList()
        test_instance.add_appointment("1/1/2025", "testing functionality of service")
        self.assertTrue(test_instance.get_appointment("1/1/2025"))
        test_instance.remove_appointment("1/1/2025")
        self.assertFalse(test_instance.get_appointment("1/1/2025"))


if __name__ == '__main__':
    unittest.main()
