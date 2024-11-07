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

#### Code for Placing Order

```java
private void placeOrder() {
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String selectedBook = (String) bookComboBox.getSelectedItem();
    int quantity;

    // Parse and validate quantity
    try {
        quantity = Integer.parseInt(quantityField.getText().trim());
        if (quantity <= 0) throw new NumberFormatException();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid quantity greater than 0.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Create Customer and Book objects based on user input
    Book book = new Book(selectedBook, 50.0);
    Customer customer = new Customer(name, email);
    Order order = new Order(customer, book, quantity);

    // Database connection and order placement
    DatabaseConnection dbConnection = new DatabaseConnection();
    dbConnection.connect();
    orderService.placeOrder(order);
    dbConnection.disconnect();

    // Calculate and display the total price
    double total = order.calculateTotal();
    totalLabel.setText("Total: $" + total);
    
    // Show a confirmation message
    String confirmationMessage = String.format("Thank you, %s! Your order has been placed successfully. Total Price: $%.2f", name, total);
    JOptionPane.showMessageDialog(this, confirmationMessage, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
}
