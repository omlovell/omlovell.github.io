# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Contact Object is added to a list of contacts
"""

from contact import *

contact_list = []


class ContactList:

    def add_contact(self, first_name, last_name, phone, address):
        new_contact = Contact(first_name, last_name, phone, address)
        contact_list.append(new_contact)

    # this removes the first instance of name in the list
    # since contact has first and last names it should be removed by ID, same names for some people
    def remove_contact(self, id_string):
        contact_list.remove(id_string)

    # get specific appointment for testing etc
    def get_contact(self, name):
        try:
            index = contact_list.index(name)
            print("Found at index: ")
            print(index)
            return index
        except ValueError:
            print("Contact Not Found")

    # function to print the list to string
    def print_contacts(self):
        print('\n'.join(map(str, contact_list)))
