# ⚙️ Apparatus - Device Maintenance Management System 🛠️

The **Apparatus** system is designed to help track and manage maintenance schedules, record incidents, and maintain repair histories for devices within an organization. Below is an overview of the features and functionalities of the project:

## 🧰 Device Management

- **➕ Add, Edit, or Delete Devices:** Administrators can manage devices within the system by adding, editing, or deleting them.
- **📝 Detailed Device Information Management:** Store and manage detailed information about devices, such as name, device code, device type, manufacturer, purchase date, and current status.
- **📍 Device Location Management:** Track the current location of devices within the facility or organization, including relocating devices when necessary.

## 📅 Maintenance Schedule Management

- **📆 Create and Manage Maintenance Schedules:** Set up and manage regular maintenance schedules for devices, including the frequency and type of maintenance to be performed.
- **⏰ Maintenance Reminders and Tracking:** Provide notifications and reminders for upcoming or overdue maintenance tasks.

## 🚨 Incident Reporting and Management

- **🛑 Incident Reporting:** Allow users to report incidents or issues with devices, including a description of the issue, severity level, and occurrence time.
- **🔄 Incident Status Management:** Track the status and progress of reported incidents.

## 🛠️ Repair History Management

- **📋 Record Repair History:** Store information about past repairs, including repair date, type of repair, and costs.
- **💰 Repair Cost Tracking:** Track costs related to repairs and maintenance to analyze expenses and maintenance efficiency.
- **📊 Generate Repair History Reports:** Provide reports on the repair history for individual devices or groups of devices.

## 💬 Social and Support Features

- **🗣️ Forum and Discussion:** Provide a forum or discussion tool where maintenance staff can share experiences and discuss technical issues. This feature integrates Firebase for real-time chat functionality.
- **🎧 Customer Support:** Offer a customer support channel to resolve issues related to devices and maintenance.

---

## 🛠️ Installation and Setup Guide

### 1. 🖥️ System Requirements

- **☕ Java (version 14 or later)**
- **🍃 Spring MVC and Hibernate**
- **🐬 MySQL**
- **🔑 Google Calendar API**
- **🔗 Firebase (for real-time chat integration)**

### 2. 🚀 Project Installation

1. **Clone the project from the repository:**

   ```bash
   git clone https://github.com/your-username/Apparatus.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Apparatus
   ```

3. **Configure Maven dependencies:**

   Ensure all necessary dependencies are listed in your `pom.xml` file, including Spring, Hibernate, MySQL, Google Calendar API, and Firebase libraries.

### 3. 🔧 Environment Configuration

1. **MySQL Database Setup:**
   - Create a MySQL database with the information configured in the `application.properties` file.

2. **Google Calendar API:**
   - Set up Google Calendar API and add the necessary credentials to your environment.

3. **Firebase Setup:**
   - Configure Firebase for real-time chat by adding the `credentials.json` file to your project and updating the `application.properties` accordingly.

### 4. 📂 Database Initialization

1. **Run database migrations to create tables:**

   ```bash
   mvn spring-boot:run
   ```

2. **Initialize the database with sample data (optional):**

   You can populate the database with initial data for testing purposes.

### 5. 🎉 Running the Project

Start the project using Maven:

```bash
mvn spring-boot:run
```

Open your browser and go to `http://localhost:8080/` to access the Apparatus system. 🌐

---

## 🎨 Design and Use Cases

### 📸 Screenshots

Here are some screenshots of the Apparatus interface:

1. **🖥️ Dashboard Overview**:
   ![Dashboard Overview](path_to_image/dashboard.png)

2. **🧰 Device Management**:
   ![Device Management](path_to_image/device_management.png)

3. **📅 Maintenance Scheduling**:
   ![Maintenance Scheduling](path_to_image/maintenance_scheduling.png)

4. **🚨 Incident Reporting**:
   ![Incident Reporting](path_to_image/incident_reporting.png)

5. **🛠️ Repair History**:
   ![Repair History](path_to_image/repair_history.png)

6. **💬 Forum and Real-Time Chat**:
   ![Forum and Real-Time Chat](path_to_image/forum_chat.png)

---

## 📈 Results and Future Enhancements

This section can be expanded with more details as the project progresses, including performance metrics, user feedback, and planned enhancements.

---
