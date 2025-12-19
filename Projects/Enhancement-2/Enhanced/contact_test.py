# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

from contact import *
import unittest


class TestContact(unittest.TestCase):
    # The first test takes a complete bad instance of contact, it fails if the length
    # is incorrect
    def test_contact_parameter_length(self):
        bad_instance = Contact("Testing12345", "Testing12345", "Testing12345", "Testing12345")
        self.assertLessEqual(len(bad_instance.first_name), 10)
        self.assertLessEqual(len(bad_instance.last_name), 10)
        self.assertEquals(len(bad_instance.phone_num), 10)
        self.assertLess(len(bad_instance.address), 30)

    # Second test checks for null ('None' for Python) values of contact, fails if any are present
    def test_contact_null(self):
        bad_instance = Contact("", "", "", "")
        self.assertTrue(bad_instance.contact_id is not None)
        self.assertTrue(bad_instance.first_name is not None)
        self.assertTrue(bad_instance.last_name is not None)
        self.assertTrue(bad_instance.phone_num is not None)
        self.assertTrue(bad_instance.address is not None)

    # Third Test checks for creation of a contact, making sure what was created is actually there


if __name__ == '__main__':
    unittest.main()
