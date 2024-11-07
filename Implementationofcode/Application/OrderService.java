
package Application;

import Domain.Customer;
import Domain.Book;
import Domain.Order;

public class OrderService {
    public void placeOrder(Order order) {
        System.out.println("Order placed successfully!");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Book: " + order.getBook().getTitle());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total Price: $" + order.calculateTotal());
    }
}
