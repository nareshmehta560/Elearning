# Softwaretechnik-II Project


### E-Learning Platform
This project is an e-learning platform similar to Udemy. It allows users to create and take courses online.

## Table of Contents
* Introduction
* Features
* Login Details
* Problems Encountered
* Note


### Introduction

The E-Learning Platform is designed to facilitate online education by enabling instructors to create courses, while students can buy the course and can enroll the course. 
This project follows an agile development approach with a clear branching strategy and extensive use of GitLab issues and boards.

### Features
* Register and login
* User Authentication and Autherization using **Spring Security**
* Search for courses/category
* **Paypal (sandbox)** integration for payment

### Login Details
**User with Admin right**<br>
* Username: userTest<br>
* Password: testuser<br>

**User**
* Username: kim
* Password: testuser<br>

Use these credentials to log in to the application after starting it.

### Problems Encountered

The cart functionality is incomplete. When a course is added and you click on "checkout," it works fine. However, if you navigate away after adding a course, the item will not be visible in the cart anymore, even though it is still present in the database.

### Note
add to cart button can not be seen in the course details. It is only visible after user is logged in.