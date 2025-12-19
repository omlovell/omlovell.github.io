# Author Name: Owen Lovell
# Date Updated: 12/8/2025
# Course ID: CS-499-17242-M01

"""
Description: This script contains the test driver(runner code) for Appointment, Contact, and Task.
Creating objects that hold information
for each of them and using their class methods
"""
import appointment_service
from appointment import Appointment
from appointment_service import AppointmentList
from contact import Contact
from task import Task

print("Appointment.py driver test")

firstAppointment = Appointment("12/5/2025", "Check up for basic things")
secondAppointment = Appointment("12/8/2025", "Inspect all aspects of vehicle")
thirdAppointment = Appointment("12/25/2025", "Free work for christmas")

print("info before .. . ")
print(firstAppointment)
print(secondAppointment)
print(thirdAppointment)
print("\n")

firstAppointment.set_date("12/6/2025")
secondAppointment.set_date("12/9/2025")
thirdAppointment.set_date("12/24/2025")
firstAppointment.set_description("Planning and inspection needed before work")
fourthAppointment = Appointment("1/1/2026", "Start new year planning")

print("info after ...")
print(firstAppointment)
print(secondAppointment)
print(thirdAppointment)
print(fourthAppointment)
print("\n")

print("contact.py driver test")
firstContact = Contact("Bob", "Bullet", "315-555-5555", "101 Dog St. NY")
secondContact = Contact("Cat", "Emery", "609-456-8976", "191 Peach Lane NY")
thirdContact = Contact("Jacob", "Catnip", "315-894-6137", "9 Water Bear NY")

print("info before ... ")
print(firstContact)
print(secondContact)
print(thirdContact)
print("\n")

firstContact.set_first_name("Bobby")
secondContact.set_address("..removed")
thirdContact.set_last_name("Katniss")
firstContact.set_phone_num("315-965-9444")
fourthContact = Contact("Nomad", "Celtic", "444-444-5555", "Always Moving")

print("info after ...")
print(firstContact)
print(secondContact)
print(thirdContact)
print(fourthContact)
print("\n")

print("task.py driver test")
task1 = Task("Fix Drain", "Replace broken parts and seals with new ones")
task2 = Task("Create List", "Detail a bulleted list of objectives")
task3 = Task("Enter Code", "Take customer info and enter given code")

print("info before ... ")
print(task1)
print(task2)
print(task3)
print("\n")

task1.set_task_name("Drainage Leak")
task3.set_description("Use provided code to enter in keypad")
task4 = Task("Dog Walk", "Walk dog for given amount of time")

print("info after ...")
print(task1)
print(task2)
print(task3)
print(task4)
print("\n")

print("testing appointment list ...")

temp_appointment_service = AppointmentList()  # create instance of appointment service for testing purpose

appointment_service.AppointmentList.add_appointment(temp_appointment_service, firstAppointment.get_date(),
                                                    firstAppointment.description)
appointment_service.AppointmentList.add_appointment(temp_appointment_service, secondAppointment.get_date(),
                                                    secondAppointment.description)
appointment_service.AppointmentList.print_appointments(temp_appointment_service)
print("\n Service works as should just earlier in test the appointments were created already")
print(firstAppointment)
print(secondAppointment)
