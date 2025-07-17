# Slot Management System (SMS)

## Table of Contents
1. [Project Overview](#project-overview)
2. [System Architecture](#system-architecture)
3. [User Guide](#user-guide)
4. [Admin Module Details](#admin-module-details)
5. [Database Integration](#database-integration)
6. [Technologies Used](#technologies-used)
7. [How to Build and Run](#how-to-build-and-run)
8. [Troubleshooting & FAQ](#troubleshooting--faq)
9. [Credits](#credits)

---

## Project Overview

### Purpose and Scope
The Slot Management System (SMS) is a desktop application designed to streamline the management of classroom slots, timetables, and room reservations in an academic environment. It provides both students and administrators with intuitive tools to view timetables, find available rooms, and manage scheduling efficiently.

### Key Features
- **Student Portal:**
  - View personalized timetables.
  - Find currently empty classrooms.
  - Search for empty rooms at specific times and days.
- **Admin Portal:**
  - Secure login for administrators.
  - Dashboard for managing courses, timetables, and room reservations.
  - Add, edit, or delete courses and timetable entries.
  - Reserve rooms for specific slots.

### Target Users
- University/college students
- Academic administrators and scheduling staff

---

## System Architecture

The application is built using Java Swing for the user interface and connects to a MySQL database for persistent storage. The architecture is modular, with clear separation between UI components and business logic.

**Main Modules:**
- `MainFrame`: Entry point and navigation hub for users.
- `StudentSlotReservation`, `TimetableViewer`: Student-facing features.
- `AdminSlotReservation`, `AdminDashboard`, `CourseEditFrame`, `TimetableEditFrame`, `RoomReservationFrame`: Admin features and management tools.

**High-Level Flow:**
- Users launch the app and are greeted with the main menu.
- Students can view timetables or search for empty rooms.
- Admins log in to access management features.
- All data operations interact with the MySQL backend.

---

## User Guide

### Launching the Application
- Run the application using the provided launcher or by executing the `AppLauncher` class.
- The main window (`MainFrame`) will open, displaying navigation options.

### Main Menu (MainFrame)
- **Find My Timetable:** Opens a form to select department, semester, and section, then displays the timetable.
- **Find Current Empty Slots:** Shows a list of rooms currently not in use.
- **Find Specific Empty Slots:** Lets users search for available rooms at a chosen day and time.
- **Admin Login:** Opens the admin login window.

### Student Features
- **Timetable Viewer:**
  - Select department, semester, and section.
  - View timetable in a tabular format.
- **Empty Slot Search:**
  - For current time: Instantly see available rooms.
  - For custom time: Choose day and time to find available rooms.

### Admin Features
- **Login:**
  - Enter admin credentials to access the dashboard.
- **Dashboard:**
  - Navigate to course management, timetable editing, or room reservation.

---

## Admin Module Details

### Admin Dashboard
- Central hub for all admin operations.
- Access to course, timetable, and room management.

### Course Management (`CourseEditFrame`)
- Add new courses by specifying ID, name, semester, and department.
- Delete existing courses by ID, semester, and department.

### Timetable Management (`TimetableEditFrame`)
- View, add, or remove timetable entries for any department, semester, and section.
- Edit course assignments, rooms, and time slots.

### Room Reservation (`RoomReservationFrame`)
- Reserve rooms for specific days and time slots.
- View and manage existing reservations.

---

## Database Integration

The application uses a MySQL database named `final` with tables such as:
- `Rooms`: List of all rooms.
- `Timetable`: Stores timetable entries (day, slot, room, course, etc.).
- `Courses`: Course details (ID, name, semester, department).
- `Departments`: Department information.
- `ReservedRooms`: Tracks room reservations.

**Database Operations:**
- JDBC is used for all database interactions.
- Queries are parameterized to prevent SQL injection.
- Admin and student actions update or fetch data as needed.

---

## Technologies Used
- **Java (Swing):** For building the desktop GUI.
- **MySQL:** Backend database for persistent storage.
- **JDBC:** Java Database Connectivity for SQL operations.
- **Ant:** Build tool (see `build.xml`).

---

## How to Build and Run

1. **Prerequisites:**
   - Java JDK 8 or higher
   - MySQL server
   - Ant (for building)
2. **Database Setup:**
   - Create a MySQL database named `final`.
   - Import the required tables and sample data (see your DBA or project SQL scripts).
   - Update database credentials in the source code if needed.
3. **Build:**
   - Run `ant clean build` in the project root.
4. **Run:**
   - Execute the application via your IDE or by running the `AppLauncher` class.

---

## Troubleshooting & FAQ
- **Database Connection Errors:**
  - Ensure MySQL is running and credentials are correct.
  - Check that the database schema matches the application's expectations.
- **UI Not Displaying Properly:**
  - Use a supported screen resolution.
  - Ensure all image resources are present in `src/images/`.
- **Login Issues:**
  - Default admin credentials are `admin` / `1234` (change in code for production).

---

## Credits
- Developed by: [Your Name/Team]
- Special thanks to all contributors and testers. 