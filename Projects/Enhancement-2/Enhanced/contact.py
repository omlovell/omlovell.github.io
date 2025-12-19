# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Contact is used to create a contact object, the object holds customer information such
as ID, name, number, address
"""
import itertools
from dataclasses import dataclass


@dataclass
class Contact:

    id_iter = itertools.count()

    # Constructor for contact first and last name, phone, and address
    # FIXME: add input validation and checks for all
    def __init__(self, first_name, last_name, phone_num, address):
        self.first_name = first_name
        self.last_name = last_name
        self.phone_num = phone_num
        self.address = address

        self.contact_id = next(self.id_iter)

    # Getters
    def get_contact_id(self):
        return self.contact_id

    def get_fist_name(self):
        return self.first_name

    def get_last_name(self):
        return self.last_name

    def get_phone_num(self):
        return self.phone_num

    def get_address(self):
        return self.address

    # Setters
    def set_first_name(self, new_first_name):
        self.first_name = new_first_name

    def set_last_name(self, new_last_name):
        self.last_name = new_last_name

    def set_phone_num(self, new_phone_num):
        self.phone_num = new_phone_num

    def set_address(self, new_address):
        self.address = new_address

    # to string
    def __str__(self):
        return "Contact ID: {}, First Name: {}, Last Name: {}, Phone {}, Address {}"\
            .format(self.contact_id, self.first_name, self.last_name, self.phone_num, self.address)
