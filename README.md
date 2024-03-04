# CRUD Project with Spring Boot

This **CRUD** (Create, Read, Update, Delete) application serves as a user-friendly emoployee Management System. Built using java as a backend and bootstrap as a front-end , it enables efficient management of employees. Perform essential operations - create, view, update, and delete employees with ease.
In CRUD-APP these technologies have been used Spring Boot Web, Spring Data JPA & Hibernate, MySQL Database, Thymeleaf, HTML5 & Bootstrap Spring Data JPA Test.

## 📝Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Technologies](#Technologies)
- [security](#security)
- [Demo](#Demo)
- [Resourses](#Resourses)
## 🚀Features

- Create, Read, Update, and Delete entities.
- RESTful API design.
- Spring Boot for backend development.

Explore the powerful features that make this CRUD project with Spring Boot exceptional:

### 1. **Create**

Perform Create operations seamlessly on entities through a well-designed and intuitive API with 'create' button.

### 2. **Read**

Allows you to easily read employee data.

### 3. **Update**

Modify the information of existing emoloyees using the 'Update' button.

### 4. **Delete**

Remove employee with a single click using the 'Delete' button.

---
## 📖prerequisites
Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed.
- Maven installed.
- Your favorite IDE (e.g., IntelliJ, Eclipse) for code editing.

---
## ✨Getting Started

Step 1: Create a Spring Boot Project
You can use Spring Initializr to generate a basic Spring Boot project. Visit [start.spring.io](https://start.spring.io/;) and configure your project with the following settings:

* Project: Maven Project
* Language: Java
* Spring Boot: Latest stable version
* Packaging: Jar
* Dependencies: Spring Web , Spring Data JPA , devtools , thymeleaf , data-jpa , security , mysql-connector-j and plugin.
Click on "Generate" to download the project zip file.

Step 2: Extract and Import into IDE
Extract the downloaded zip file and import the project into your preferred IDE (IntelliJ IDEA, Eclipse, etc.).

Step 3: Define Entity
Create a simple entity class representing the object you want to manage. For example, if you are building a CRUD application for employees, create a employee class.

Step 4: Create Repository
Create a repository interface for your entity to perform CRUD operations.

Step 5: Create Controller
Create a controller to handle HTTP requests and interact with the repository.

Step 6:Create simple frontend 
You can test your CRUD operations using tools like cURL, Postman, or by creating a simple frontend using bootstrap 5 like m.

Step 7: Run Your Application
Run your Spring Boot application. It will start a server at http://localhost:8080.

---
## 📚Technologies

* IntelliJ IDEA Community Edition 2023.1.3
* mysQL Workbench 8.0 CE
* postman
---
## 🔑security

Step 1: Add Spring Security Dependency.🛡️
In your pom.xml (for Maven) or build.gradle (for Gradle), add the Spring Security dependency

Step 2: Handle User Authentication.🔐
* This App uses in-memory authentication ,this step provide username,password and role For a production application.

Step 3: Secure Endpoints
* By specify which endpoints require authentication and authorization by configuring HttpSecurity.

Step 4: Define User Roles.🗝️
* Define roles that represent different levels of access in your application , For example that apply on my app, you might have roles like ROLE_EMPLOYEE , ROLE_MANAGER and ROLE_ADMIN.
* You can assign roles to users during user authentication.

## 🎥Demo

https://github.com/Mohamad-shosha/CRUD-APP/assets/150439621/4d162eeb-76c0-419f-bf11-a95ec06f5d06

## 🗂️Resourses
* [Spring boot](https://spring.io/why-spring)
* [Spring boot tutorial](https://spring.io/guides/gs/spring-boot)
* [Professor chad darpy](https://luv2code.com/)

