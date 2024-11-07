package UI;

import Application.OrderService;
import Domain.Book;
import Domain.Customer;
import Domain.Order;
import Infrasturcture.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineBookStoreUI extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField quantityField;
    private JComboBox<String> bookComboBox;
    private JLabel totalLabel;
    private OrderService orderService;

    public OnlineBookStoreUI() {
        // Set up the main frame
        setTitle("Online Book Store");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize OrderService instance
        orderService = new OrderService();

        // Create and arrange UI components
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Lighter background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 12, 12, 12); // Increase spacing

        // Title Label with improved styling
        JLabel titleLabel = new JLabel("Online Book Store", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Modern font
        titleLabel.setForeground(new Color(50, 150, 255)); // Blue color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Customer Name Label and Field
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Use clean font
        nameLabel.setForeground(new Color(50, 50, 50)); // Dark gray
        panel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Light border
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Customer Email Label and Field
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel emailLabel = new JLabel("Customer Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(50, 50, 50));
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Book Selection ComboBox
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel bookLabel = new JLabel("Book:");
        bookLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bookLabel.setForeground(new Color(50, 50, 50));
        panel.add(bookLabel, gbc);

        bookComboBox = new JComboBox<>(new String[]{"Java Programming - $50.0", "Python Programming - $40.0"});
        bookComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        bookComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        panel.add(bookComboBox, gbc);

        // Quantity Label and Field
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityLabel.setForeground(new Color(50, 50, 50));
        panel.add(quantityLabel, gbc);

        quantityField = new JTextField(5);
        quantityField.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        // Place Order Button with modern style
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBackground(new Color(76, 175, 80)); // Green color
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        placeOrderButton.setFocusPainted(false);
        placeOrderButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        placeOrderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        placeOrderButton.addActionListener(new PlaceOrderButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(placeOrderButton, gbc);

        // Total Label with larger font and style
        totalLabel = new JLabel("Total: $0.0", JLabel.CENTER);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Modern font
        totalLabel.setForeground(new Color(50, 150, 255)); // Blue color
        gbc.gridy = 6;
        panel.add(totalLabel, gbc);

        add(panel);
    }

    // Handles the order placement logic when the "Place Order" button is clicked
    private class PlaceOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            placeOrder();
        }
    }
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
    
        // Validate customer info
        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and email.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Create customer, book, and order objects based on user input
        Book book;
        if (selectedBook.contains("Java Programming")) {
            book = new Book("Java Programming", 50.0);
        } else {
            book = new Book("Python Programming", 40.0);
        }
    
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
    
        // Stylish Confirmation Message with Thank You note
        String confirmationMessage = String.format("Thank you, %s!\nYour order has been placed successfully.\nTotal Price: $%.2f", name, total);
        JOptionPane.showMessageDialog(this, confirmationMessage, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
    
        // Add a stylish Thank You popup
        String thankYouMessage = "<html><div style='text-align:center;'><h2>Thank You for Your Purchase!</h2><p>We appreciate your order, and we hope you enjoy your book!</p></div></html>";
        JOptionPane.showMessageDialog(this, thankYouMessage, "Thank You!", JOptionPane.INFORMATION_MESSAGE);
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new OnlineBookStoreUI().setVisible(true));
}
}
