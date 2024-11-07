# Online Book Store

This is an Online Book Store application where customers can browse and purchase books. The system includes various components like books, customers, and orders, and follows the principles of object-oriented design to ensure modularity, flexibility, and maintainability.

## Project Structure

- **UI Layer**: Manages the user interface and interactions.
- **Domain Layer**: Handles the core business logic (e.g., managing books, orders).
- **Infrastructure Layer**: Handles database connections and data persistence.
- **Service Layer**: Manages order processing logic.

## Use Case Diagrams

### 1. **Use Case: Place Order**
   - **Actor**: Customer
   - **Description**: A customer selects a book, provides their details (name, email), and specifies the quantity. The system calculates the total cost and processes the order.

   **Steps**:
   1. Customer selects a book.
   2. Customer enters name, email, and quantity.
   3. System calculates the total price.
   4. Order is processed, and a confirmation message is displayed.

   This is represented in the **Use Case Diagram**, where the **Customer** interacts with the **Order Processing System** to complete the order.

### 2. **Use Case: View Books**
   - **Actor**: Customer
   - **Description**: Customers can view available books, including the title and price.

   This use case is connected to the **Browse Books** feature in the system.

## Class Diagram

The class diagram depicts the structure of the Online Book Store system. Key classes include:

- **Book**: Represents a book, containing properties like title and price.
- **Customer**: Represents the customer, containing personal information (name, email).
- **Order**: Represents an order, including the `Customer`, `Book`, and `quantity`.
- **OrderService**: Manages the order processing logic, including calculating the total price.
- **DatabaseConnection**: Handles database interactions (e.g., storing customer and order details).

## Code Walkthrough: Use Case - Place Order

### **Use Case: Place Order**
In the `OnlineBookStoreUI` class, I have implemented the "Place Order" use case, where the user inputs their details and order information. The `OrderService` class is responsible for calculating the total price, and the system communicates with the database via the `DatabaseConnection` class to store the order.

# Online Book Store

This project is an **Online Book Store** application where customers can browse books, make orders, and view order details. It implements key **Object-Oriented Design Principles** including **GRASP** principles to maintain good design and flexibility.

## Key GRASP Principle Applied: **Controller**

In this project, we applied the **Controller** principle from **GRASP** to manage the flow of user input and interaction with the system. Here's how this principle is used and why it's important for our Online Book Store project.

### **GRASP Controller Principle**

The **Controller** pattern assigns the responsibility of handling system events (such as user interactions) to a class, often referred to as the **Controller**. This class is responsible for managing the flow of control between the user interface and the underlying business logic. 

The **Controller** acts as an intermediary between the UI (view) and the core functionality of the application (model). By using the **Controller** principle, we can ensure the following:

1. **Separation of Concerns**: The **Controller** isolates the user interface (UI) from the business logic, making the application easier to maintain and extend.
2. **Centralized Event Handling**: It consolidates the handling of events like button clicks, form submissions, and other user actions.
3. **Modularity**: The **Controller** is modular, so it's easier to change the UI without affecting the underlying business logic, and vice versa.

### **How the Controller Principle is Applied in Our Project**

In the **Online Book Store** application, the **OnlineBookStoreUI** class acts as the **Controller**.

#### **Controller Class: OnlineBookStoreUI**

The `OnlineBookStoreUI` class is responsible for:
- Capturing user input (like customer name, email, book selection, and quantity).


- Validating the inputs and ensuring that they are correct (for example, checking if the quantity is a valid number).
- Calling appropriate business logic in the `OrderService` to process the order.
- Managing the flow of control between the user interface (UI) and the core logic (order processing and total calculation).

https://github.com/user-attachments/assets/18695395-4341-49a6-9b2e-d012b68af1db

Uploading freecompress-Screen Recording 2024-11-08 040735.mp4…


