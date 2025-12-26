# University Management System (SIS)

![Project Status](https://img.shields.io/badge/Status-Completed-success)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue)
![MSSQL](https://img.shields.io/badge/MS_SQL_Server-Database-red)

## 📌 Project Overview
The **Student Information System (SIS)** is a centralized, web-based desktop application designed to digitize and automate academic and administrative operations at **Ain Shams University**. 

[cite_start]Developed using the **Scrum Framework** over 8 Sprints, this system streamlines four critical domains: Facilities Management, Curriculum Delivery, Staff Administration, and Community Engagement[cite: 2, 60, 160].

## 🚀 Key Features

### 1. Facilities Module (Infrastructure)
* [cite_start]**Conflict-Free Booking:** Algorithms to prevent double-booking of classrooms and labs[cite: 165].
* [cite_start]**Maintenance Reporting:** Ticket-based system for students/staff to report issues (e.g., broken projectors) and for Admins to resolve them[cite: 773].
* [cite_start]**EAV Model:** Implemented Entity-Attribute-Value architecture to dynamically add room attributes (e.g., "Has AC") without altering the database schema[cite: 111].

### 2. Curriculum Module (Academic Core)
* [cite_start]**Self-Service Enrollment:** Students can register for courses, drop classes, and view available electives[cite: 319].
* [cite_start]**Automated GPA Calculation:** Database triggers instantly recalculate GPA upon grading, removing the need for complex application-side loops[cite: 117].
* [cite_start]**Transcript Generation:** One-click academic history generation[cite: 74].

### 3. Staff Module (Administration)
* [cite_start]**Grading System:** Secure interface for professors to input grades, which automatically updates student records[cite: 445].
* [cite_start]**Analytics Dashboard:** Visualizations of grade distributions (Bar Charts) for course performance analysis[cite: 446].
* [cite_start]**Payroll & Leave:** Management of financial benefits and leave requests[cite: 170].

### 4. Community Module
* [cite_start]**Parent Portal:** Secure access for parents to view their child's grades and attendance[cite: 33].
* [cite_start]**Secure Messaging:** Direct communication channel between parents and teachers[cite: 765].
* [cite_start]**LMS Integration:** File upload/download capabilities for course materials[cite: 601].

---

## 🛠️ Technical Architecture

[cite_start]This project strictly adheres to the **DAO (Data Access Object) Design Pattern** to decouple the UI from the database logic, ensuring maintainability and scalability[cite: 84].

### Tech Stack
* [cite_start]**Frontend:** JavaFX (Modular UI with CSS styling)[cite: 64].
* [cite_start]**Backend:** Java (JDK 17+)[cite: 65].
* [cite_start]**Database:** Microsoft SQL Server (T-SQL)[cite: 66].
* [cite_start]**Build Tool:** Maven[cite: 67].
* [cite_start]**Version Control:** Git (Feature Branch Workflow)[cite: 139].

### Database Logic (Automation)
We utilized **SQL Triggers** to handle critical logic atomically:
* [cite_start]`trg_AutoCalculateGPA`: Automatically updates student GPA when a grade is inserted/updated[cite: 471].
* [cite_start]`trg_AutoUpdateFeesOnEnrollment`: Adds course fees to the student's ledger immediately upon registration[cite: 356].

### Security
* [cite_start]**Password Hashing:** SHA-256 encryption for all user passwords[cite: 674].
* [cite_start]**Input Validation:** Strict validation for emails and strong passwords[cite: 208].
* [cite_start]**Role-Based Access Control:** Distinct views for Admin, Student, Teacher, and Parent roles[cite: 206].

---

## 📸 Screenshots

| Login Portal | Admin Dashboard |
|:---:|:---:|
| ![Login](images/login_screen.png) | ![Admin](images/admin_dashboard.png) |
| *Secure Role-Based Login* | *User & Facility Management* |

| Student Dashboard | Teacher Analytics |
|:---:|:---:|
| ![Student](images/student_dashboard.png) | ![Teacher](images/teacher_dashboard.png) |
| *Course Registration & GPA* | *Grade Distribution & LMS* |

---

## ⚙️ Installation & Setup

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/Fadij1/Student_Information_System-SIS-.git](https://github.com/Fadij1/Student_Information_System-SIS-.git)
    ```
2.  **Database Setup**
    * Install **Microsoft SQL Server**.
    * Run the `scheme.sql` script located in the `/sql` folder to create tables, triggers, and the EAV structure.
    * Update `DatabaseManager.java` with your local server credentials.
3.  **Build & Run**
    * Open the project in IntelliJ IDEA or Eclipse.
    * Reload Maven Dependencies (`pom.xml`).
    * Run `MainApp.java`.

---

## 📅 Agile Methodology

[cite_start]This project was executed using **Scrum** over **8 Sprints**[cite: 976]:

1.  **Foundation & Security:** Login, Registration, DB Setup.
2.  **Student Core:** Enrollment, Dropping Courses.
3.  **Grading & Facilities:** GPA Triggers, Room Booking.
4.  **Finance & LMS:** Payments, File Uploads.
5.  **Profiles:** Secure updates, Password Hashing.
6.  **Parents & Maintenance:** Parent Portal, Issue Reporting.
7.  **Resources:** Asset Allocation, Final LMS features.
8.  **Stabilization:** UI Polish, Bug Fixes, Final Delivery.

[cite_start]**Velocity:** Average of **18.25 Story Points** per sprint[cite: 981].

---

## 👥 Team 25

| Name | ID 
|:---|:---
| **Michael Nagi** | 22P0101
| **Fady John** | 22P0051
| **Farid Fahmy** | 22P0046

**Course:** CSE233 - Agile Software Engineering  
**University:** Ain Shams University, Faculty of Engineering
