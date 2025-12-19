# Author: Owen Lovell
# Date Updated: 12/13/2025
# Course ID: CS-499-17242-M01

import unittest
from contact_service import *


class TestContactService(unittest.TestCase):
    # Test creation and deletion of contact
    def test_contact_service(self):
        test_instance = ContactList()
        test_instance.add_contact("Bob", "Klein", "3154444444", "123 Noway Lane New York")
        self.assertTrue(test_instance.get_contact("Bob"))
        test_instance.remove_contact("Bob")
        self.assertFalse(test_instance.get_contact("Bob"))


if __name__ == '__main__':
    unittest.main()
