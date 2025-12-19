# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Appointment is used to create an appointment object, the object holds appointmentID,
a date, and description.
"""
import itertools


class Appointment:

    id_iter = itertools.count()

    # Constructor for appointment date, and description
    def __init__(self, appointment_date, description):

        self.appointment_date = appointment_date
        self.description = description

        self.appointment_id = next(self.id_iter)

    # Getters
    def get_appointment_id(self):
        return self.appointment_id

    def get_date(self):
        return self.appointment_date

    def get_description(self):
        return self.description

    # setDate
    def set_date(self, new_appointment_date):
        self.appointment_date = new_appointment_date

    # setDescription
    def set_description(self, new_description):
        self.description = new_description

    # to string
    def __str__(self):
        return "Appointment ID: {}, Date: {}, Description: {}"\
            .format(self.appointment_id, self.appointment_date, self.description)
