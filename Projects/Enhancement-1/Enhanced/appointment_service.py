# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Appointment Service is used to create an appointment object, the object is then placed in a list
"""


from appointment import *

appointment_list = []


class AppointmentList:

    def add_appointment(self, name, description):
        new_appointment = Appointment(name, description)  # This is why 'AppointmentID is in list
        appointment_list.append(new_appointment)

    # this removes the first instance of name in the list
    def remove_appointment(self, name):
        appointment_list.remove(name)

    # get specific appointment for testing etc
    def get_appointment(self, name):
        try:
            index = appointment_list.index(name)
            print("Found at index: ")
            print(index)
            return index
        except ValueError:
            print("Appointment Not Found")

    # function to print the list to a string
    def print_appointments(self):
        print('\n'.join(map(str, appointment_list)))
