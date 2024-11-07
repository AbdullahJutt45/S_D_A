package Domain;


public class Order {
    private Customer customer;
    private Book book;
    private int quantity;

    public Order(Customer customer, Book book, int quantity) {
        this.customer = customer;
        this.book = book;
        this.quantity = quantity;
    }

    // Getter methods
    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateTotal() {
        return book.getPrice() * quantity;
    }
}
