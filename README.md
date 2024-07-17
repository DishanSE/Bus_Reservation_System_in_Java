# Bus Reservation System

## Introduction

In the competitive landscape of the transportation industry, bus companies must adopt efficient and scalable software solutions to manage reservations and provide superior customer experiences. This project implements a bus seat reservation system for XYZ Pvt Ltd, leveraging independent data structures to optimize performance and maintainability. The system supports essential operations such as customer and bus registration, seat reservation and cancellation, and real-time notifications.

## Features

1. **Customer Registration**:
   - Register new customers with details such as name, mobile number, email ID, city, and age.

2. **Bus Registration**:
   - Register new buses with details such as bus number, total seats, starting point, ending point, starting time, and fare.

3. **Bus Search**:
   - Search for available buses based on starting and ending points.

4. **Seat Reservation**:
   - Reserve seats for customers and send notification messages upon successful reservation.

5. **Reservation Cancellation**:
   - Cancel reservations and notify the next customer in the queue for the seat.

6. **Request New Seat**:
   - Allow reserved customers to request a new seat, placing them in a queue.

7. **Display Reservations**:
   - Display all current reservations.

## Data Structures Used

- **Linked List**:
  - Used for managing customer registrations in a sequential manner.
  
- **HashMap**:
  - Used for storing and retrieving bus and reservation details efficiently.

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/BusReservationSystem.git
    ```
2. Navigate to the project directory:
    ```bash
    cd BusReservationSystem
    ```
3. Compile the Java files:
    ```bash
    javac *.java
    ```
4. Run the application:
    ```bash
    java Main
    ```

## Usage

1. Register customers and buses using the provided methods.
2. Search for available buses based on starting and ending points.
3. Reserve and cancel seats for customers, with notifications sent for each action.
4. View all current reservations and manage seat requests.

## Example Test Cases

### Test Case 1: Registering a New Customer
- **Input**: Customer name: John Doe, Mobile number: 1234567890, Email ID: john@example.com, City: New York, Age: 30
- **Expected Outcome**: Customer registered successfully and added to the list.

### Test Case 2: Registering a New Bus
- **Input**: Bus number: NY123, Total seats: 40, Starting point: New York, Ending point: Washington, Starting time: 09:00 AM, Fare: $50
- **Expected Outcome**: Bus registered successfully and added to the list.

### Test Case 3: Reserving a Seat
- **Input**: Customer ID: 1, Bus ID: NY123, Seat number: 5
- **Expected Outcome**: Seat reserved successfully, and notification sent to the customer.

### Test Case 4: Canceling a Reservation
- **Input**: Reservation ID: 1
- **Expected Outcome**: Reservation canceled successfully, and notification sent to the customer. Next customer in queue notified about the available seat.




Thank you for using our Bus Reservation System. We hope it enhances the efficiency and effectiveness of your transportation business!
