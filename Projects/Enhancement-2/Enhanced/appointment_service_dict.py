# Author: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: The Appointment Service is used to create an appointment object, the object is then placed in a dictionary
where the appointments are grouped by name of type of appointment
"""


from appointment import Appointment
from collections import defaultdict


class AppointmentDict:
    def __init__(self):
        # key is name, of appointment value is appointment objects including date and description
        self.appointments = defaultdict(list)

    def add_appointment(self, name, appointment: Appointment):
        if name not in self.appointments:
            self.appointments[name] = []
        self.appointments[name].append(appointment)

    # removing an appointment based off th object,
    def remove_appointment(self, name, appointment: Appointment) -> bool:
        if name not in self.appointments:
            return False

        try:
            self.appointments[name].remove(appointment)
            if not self.appointments[name]:
                del self.appointments[name]
            return True
        except ValueError:
            return False

    def get_appointments(self, name):
        return self.appointments.get(name, [])
