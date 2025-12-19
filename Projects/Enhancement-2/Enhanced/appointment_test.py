# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Appointment Class is tested to ensure creation caused no errors
"""

from appointment import *
import unittest


class TestAppointmentInformation(unittest.TestCase):
    def test_appointment_parameter_length(self):
        bad_instance = Appointment("11/11/11110",
                                   "Needs to be less than fifty characters. less that 50 chars not equal to")
        self.assertEqual(len(bad_instance.appointment_date), 10)
        self.assertLessEqual(len(bad_instance.description), 50)

    def test_appointment_null(self):
        bad_instance = Appointment(None, None)
        self.assertTrue(bad_instance.appointment_id is not None)
        self.assertTrue(bad_instance.appointment_date is not None)
        self.assertTrue(bad_instance.description is not None)

    # FIXME: add test for creation of correct appointment


if __name__ == '__main__':
    unittest.main()
