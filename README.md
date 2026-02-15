# Task Management System (Java + MySQL)

This is a console-based Task Management System developed using Java and MySQL with JDBC.  
The application allows users to manage tasks using basic CRUD operations with persistent database storage.

---

## Features

- Add new tasks  
- View all tasks  
- Mark tasks as completed  
- Delete tasks  
- Data stored permanently using MySQL  
- Menu-driven console interface  

---

## Technologies Used

- Java  
- MySQL  
- JDBC (MySQL Connector/J)  

---

## Database Setup

```sql
CREATE DATABASE task_manager;
USE task_manager;

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    status VARCHAR(50)
);

