# SmartQuizGenerator

A comprehensive Quiz Management System built with **Java**, **Maven**, and **MySQL**. Create, manage, and take quizzes with role-based dashboards for teachers and students.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [User Roles](#user-roles)
- [Database Configuration](#database-configuration)
- [Build & Deployment](#build--deployment)
- [File Descriptions](#file-descriptions)

---

## ✨ Features

- **User Authentication**: Secure login system with password hashing
- **Role-Based Access**: Separate dashboards for teachers and students
- **Quiz Management**: Create, edit, and manage quizzes (Teacher only)
- **Quiz Taking**: Interactive quiz interface for students with real-time feedback
- **Session Management**: Track user sessions and activity
- **Input Validation**: Comprehensive validation for all user inputs
- **Database Integration**: MySQL backend for persistent data storage
- **Logging**: SLF4J-based logging for debugging and monitoring

---

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java 11 |
| **Build Tool** | Maven 3.x |
| **Database** | MySQL 8.0+ |
| **JDBC Driver** | MySQL Connector/J (com.mysql.cj.jdbc) |
| **Logging** | SLF4J with Logback |
| **GUI Framework** | Java Swing (NetBeans) |
| **IDE** | NetBeans 25 |

---

## 📁 Project Structure

```
SmartQuizGenerator/
├── src/main/java/com/quizapp/
│   ├── Main.java                    # Entry point (run this!)
│   ├── DBConnection.java            # Database connectivity
│   ├── LoginFrame.java              # Login GUI
│   ├── PasswordUtils.java           # Password hashing & validation
│   ├── QuizTakingFrame.java         # Quiz interface for students
│   ├── SessionManager.java          # Session & user tracking
│   ├── SmartQuizGenerator.java      # Core business logic
│   ├── StudentFrame.java            # Student dashboard
│   ├── TeacherFrame.java            # Teacher dashboard
│   └── Validator.java               # Input validation
├── pom.xml                          # Maven configuration
├── target/                          # Compiled JAR (after build)
└── README.md                        # This file
```

---

## ⚙️ Prerequisites

Before running the application, ensure you have:

1. **Java Development Kit (JDK) 11 or higher**
   - Download: https://www.oracle.com/java/technologies/javase-downloads.html
   - Verify: `java -version`

2. **Apache Maven 3.6.0 or higher**
   - Download: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **MySQL Server 8.0 or higher**
   - Download: https://dev.mysql.com/downloads/mysql/
   - Verify: `mysql --version`

4. **MySQL JDBC Driver** (included in `pom.xml`)
   - Automatically downloaded by Maven

---

## 📦 Installation & Setup

### Step 1: Clone/Extract Project

```bash
cd C:\Users\arnav\OneDrive\Desktop\Classes\2nd Year\Sem 4\Java\Class\SmartQuizGenerator
```

### Step 2: Create MySQL Database

Open MySQL CLI and run:

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS quiz_db;
USE quiz_db;

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('teacher', 'student') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create quizzes table
CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Create questions table
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    question_text VARCHAR(500) NOT NULL,
    question_type ENUM('multiple_choice', 'true_false', 'short_answer') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

-- Create answers table
CREATE TABLE answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL,
    answer_text VARCHAR(500) NOT NULL,
    is_correct BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

-- Create quiz attempts table
CREATE TABLE quiz_attempts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score INT,
    attempted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
```

### Step 3: Configure Database Credentials

Edit `src/main/java/com/quizapp/DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/quiz_db";
private static final String USER = "root";           // Your MySQL username
private static final String PASSWORD = "1234";      // Your MySQL password
```

### Step 4: Build with Maven

```bash
mvn clean install
```

Expected output:
```
BUILD SUCCESS
Total time: ~4 seconds
```

---

## 🚀 Running the Application

### Option 1: From Command Line

```bash
java -cp target/SmartQuizGenerator-1.0.jar com.quizapp.Main
```

### Option 2: From NetBeans

1. Open project in NetBeans
2. Right-click → **Run**
3. Or press `F6`

### Option 3: Run Compiled JAR

```bash
cd target/
java -jar SmartQuizGenerator-1.0.jar
```

---

## 👥 User Roles

### Teacher
- ✅ Create new quizzes
- ✅ Add questions and answers
- ✅ View student attempts
- ✅ Edit/delete own quizzes
- ✅ Track student performance

### Student
- ✅ View available quizzes
- ✅ Take quizzes
- ✅ View past attempts
- ✅ See score and feedback
- ❌ Cannot create/edit quizzes

---

## 🗄️ Database Configuration

### Connection Details

```
Host: localhost
Port: 3306
Database: quiz_db
Username: root
Password: 1234 (default, change as needed)
```

### Testing Connection

```bash
mysql -h localhost -u root -p quiz_db
```

If successful, you'll see the MySQL prompt: `mysql>`

---

## 🔨 Build & Deployment

### Clean Build

```bash
mvn clean install
```

### Skip Tests (faster build)

```bash
mvn clean install -DskipTests
```

### Create Executable JAR

```bash
mvn package
```

The JAR will be at: `target/SmartQuizGenerator-1.0.jar`

### Deploy to Production

1. Update database credentials for production environment
2. Build with `mvn clean install`
3. Copy JAR to deployment server
4. Run: `java -jar SmartQuizGenerator-1.0.jar`

---

## 📄 File Descriptions

| File | Purpose |
|------|---------|
| **Main.java** | Application entry point; initializes login screen |
| **DBConnection.java** | Manages database connections; handles JDBC setup |
| **LoginFrame.java** | GUI for user login; validates credentials |
| **PasswordUtils.java** | Hashes and verifies passwords securely |
| **SessionManager.java** | Tracks active sessions and user data |
| **SmartQuizGenerator.java** | Core business logic for quiz operations |
| **StudentFrame.java** | Dashboard GUI for students |
| **TeacherFrame.java** | Dashboard GUI for teachers |
| **QuizTakingFrame.java** | Interactive quiz UI for students |
| **Validator.java** | Validates user input across the application |

---

## 🐛 Troubleshooting

### Issue: "Cannot load MySQL driver"
**Solution**: Ensure MySQL Connector/J is in the classpath
```bash
mvn dependency:tree | grep mysql
```

### Issue: "Access denied for user 'root'@'localhost'"
**Solution**: Verify MySQL credentials in `DBConnection.java`
```bash
mysql -h localhost -u root -p
```

### Issue: "Database quiz_db not found"
**Solution**: Run the SQL setup script (see Step 2 in Installation)

### Issue: "JAVA_HOME not set"
**Solution**: 
```bash
set JAVA_HOME=C:\Program Files\Java\jdk-11
mvn clean install
```

---

## 📝 License

This project is for educational purposes.

---

## 👨‍💻 Author

**Arnav**  
Computer Science Student, 2nd Year Sem 4  
NetBeans + MySQL Implementation

---

## 🔗 Quick Links

- [Java Documentation](https://docs.oracle.com/en/java/javase/11/)
- [Maven Guide](https://maven.apache.org/guides/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)

---

**Last Updated**: March 26, 2026

For issues or questions, verify your database setup and check application logs for detailed error messages.
