# University Management System (SIS)

![Project Status](https://img.shields.io/badge/Status-Completed-success)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue)
![MSSQL](https://img.shields.io/badge/MS_SQL_Server-Database-red)

## 📌 Project Overview
The **Student Information System (SIS)** is a centralized, web-based desktop application designed to digitize and automate academic and administrative operations at **Ain Shams University**. 

Developed using the **Scrum Framework** over 8 Sprints, this system streamlines four critical domains: Facilities Management, Curriculum Delivery, Staff Administration, and Community Engagement.

## 🚀 Key Features

### 1. Facilities Module (Infrastructure)
* **Conflict-Free Booking:** Algorithms to prevent double-booking of classrooms and labs.
* **Maintenance Reporting:** Ticket-based system for students/staff to report issues (e.g., broken projectors) and for Admins to resolve them.
* **EAV Model:** Implemented Entity-Attribute-Value architecture to dynamically add room attributes (e.g., "Has AC") without altering the database schema.

### 2. Curriculum Module (Academic Core)
* **Self-Service Enrollment:** Students can register for courses, drop classes, and view available electives.
* **Automated GPA Calculation:** Database triggers instantly recalculate GPA upon grading, removing the need for complex application-side loops.
* **Transcript Generation:** One-click academic history generation.

### 3. Staff Module (Administration)
* **Grading System:** Secure interface for professors to input grades, which automatically updates student records.
* **Analytics Dashboard:** Visualizations of grade distributions (Bar Charts) for course performance analysis.
* **Payroll & Leave:** Management of financial benefits and leave requests.

### 4. Community Module
* **Parent Portal:** Secure access for parents to view their child's grades and attendance.
* **Secure Messaging:** Direct communication channel between parents and teachers.
* **LMS Integration:** File upload/download capabilities for course materials.

---

## 🛠️ Technical Architecture

This project strictly adheres to the **DAO (Data Access Object) Design Pattern** to decouple the UI from the database logic, ensuring maintainability and scalability.

### Tech Stack
* **Frontend:** JavaFX (Modular UI with CSS styling).
* **Backend:** Java (JDK 17+).
* **Database:** Microsoft SQL Server (T-SQL).
* **Build Tool:** Maven.
* **Version Control:** Git (Feature Branch Workflow).

### Database Logic (Automation)
We utilized **SQL Triggers** to handle critical logic atomically:
* `trg_AutoCalculateGPA`: Automatically updates student GPA when a grade is inserted/updated.
* `trg_AutoUpdateFeesOnEnrollment`: Adds course fees to the student's ledger immediately upon registration.

### Security
* **Password Hashing:** SHA-256 encryption for all user passwords.
* **Input Validation:** Strict validation for emails and strong passwords.
* **Role-Based Access Control:** Distinct views for Admin, Student, Teacher, and Parent roles.

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

This project was executed using **Scrum** over **8 Sprints**:

1.  **Foundation & Security:** Login, Registration, DB Setup.
2.  **Student Core:** Enrollment, Dropping Courses.
3.  **Grading & Facilities:** GPA Triggers, Room Booking.
4.  **Finance & LMS:** Payments, File Uploads.
5.  **Profiles:** Secure updates, Password Hashing.
6.  **Parents & Maintenance:** Parent Portal, Issue Reporting.
7.  **Resources:** Asset Allocation, Final LMS features.
8.  **Stabilization:** UI Polish, Bug Fixes, Final Delivery.

**Velocity:** Average of **18.25 Story Points** per sprint.

---

## 👥 Team 25

|    Name |    ID 
|:---|:---
| **Michael Nagi** | 22P0101
| **Fady John** | 22P0051
| **Farid Fahmy** | 22P0046

**Course:** CSE233 - Agile Software Engineering  
**University:** Ain Shams University, Faculty of Engineering
