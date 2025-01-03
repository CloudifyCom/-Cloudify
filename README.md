# Project Documentation: !Cloudify Airline Services

## 1. Project Overview

### Purpose and Objectives
The goal of this project is to develop a web application for an airline company, providing functionalities such as flight search, booking, check-in, and real-time flight tracking.

### Problem Statement
The project addresses inefficiencies in disconnected systems that make it challenging for users to check in or stay updated about flight statuses. The solution employs a microservices architecture to enhance reliability, scalability, and responsiveness.

### Key Features
- Flight search by destination and dates.
- Flight booking and payment processing.
- Real-time flight status tracking.
- Weather and delay prediction.
- Notifications for reservations and flight updates.

## 2. System Architecture

### Overview of Microservices
1. **User Service**: User registration, login, and authentication.
2. **Flight Search Service**: Search for flights based on destination and dates.
3. **Flight Booking Service**: Handle flight reservations and payment processing.
4. **Inventory Service**: Manage available seats on flights.
5. **Payment Service**: Process payments and refunds.
6. **Loyalty Program Service**: Manage loyalty points and rewards.
7. **Notification Service**: Send SMS and email updates for bookings and flight statuses.
8. **Flight Status Service**: Monitor real-time flight statuses.
9. **Weather and Delay Prediction Service**: Predict delays based on weather conditions.

### Architectural Diagram
*(Add your architectural diagram here in Markdown or as a linked image)*

## 3. Technology Stack
- **Backend**: Java with KumuluzEE
- **Database**: PostgreSQL
- **Frontend**: Angular or React
- **Containerization and Orchestration**: Kubernetes
- **Version Control**: GitHub ([Repository Link](https://github.com/ilijagavrilovic/-Cloudify.git))

## 4. Setup and Deployment

### Prerequisites
- Java Development Kit (JDK 11 or later)
- PostgreSQL installed and running
- Docker and Kubernetes
- Git

### Installation Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/ilijagavrilovic/-Cloudify.git
   ```
2. Set up PostgreSQL and create required databases.
3. Navigate to each microservice directory and build the service:
   ```bash
   mvn clean install
   ```
4. Deploy services to Kubernetes:
   ```bash
   java -jar api/target/api-1.0-SNAPSHOT.jar
   ```

### Deployment Instructions
Follow Kubernetes best practices to deploy microservices. Use `kubectl` commands to monitor pods and logs.

## 5. Microservices Details

### Example: User Service
**Purpose**: Handles user authentication and registration.
**Dependencies**: PostgreSQL for user data storage.
**Endpoints**:
- `POST /register`: Register a new user.
- `POST /login`: Authenticate a user.

*(Repeat for other services)*

## 6. Usage

### User Interaction Examples
- **Flight Search**: Enter destination and dates to get available flights.
- **Booking**: Select a flight and make a payment.
- **Real-Time Tracking**: Monitor your flight status through the dashboard.

### Key Use Cases
1. User enters login credentials to access the dashboard.
2. Searches for flights and selects a preferred option.
3. Completes the booking process.