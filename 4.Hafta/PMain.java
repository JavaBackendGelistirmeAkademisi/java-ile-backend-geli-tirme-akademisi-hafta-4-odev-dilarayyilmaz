import java.io.*;
import java.util.Scanner;

public class PMain {

    public static final String FILE_NAME = "tasks.txt";

    static class InvalidException extends Exception {
        public InvalidException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the E-Commerce Platform.");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        try {
            validateUsername(username);
        } catch (InvalidException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();  

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an operation:");
            System.out.println("1- Product Info");
            System.out.println("2- Update Product");
            System.out.println("3- Check Stock");
            System.out.println("4- Customer Info");
            System.out.println("5- Place an Order");
            System.out.println("6- Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> displayProductInfo();
                case 2 -> updateProduct(scanner);
                case 3 -> checkStock();
                case 4 -> displayCustomerInfo();
                case 5 -> placeOrder(scanner);
                case 6 -> {
                    System.out.println("Logging out...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void validateUsername(String username) throws InvalidException {
        if (username.length() < 5) {
            throw new InvalidException("Username must be at least 5 characters long.");
        }
    }

    public static void displayProductInfo() {
        Product.displayProductInfo("Laptop", 58.20, 15);
        Product.displayProductInfo("Bag", 64.80, 10);
    }

    public static void updateProduct(Scanner scanner) {
        System.out.println("Available Products:");
        Product.displayProductInfo("Laptop", 58.20, 15);
        Product.displayProductInfo("Bag", 64.80, 10);

        System.out.print("Select product to update (1 - Laptop, 2 - Bag): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            Product.updateProductInfo("Laptop", 50.30, 80);
        } else if (choice == 2) {
            Product.updateProductInfo("Bag", 58.36, 20);
        } else {
            System.out.println("Invalid input.");
        }
    }

    public static void checkStock() {
        try {
            Product.checkStock("Laptop", 80);
            Product.checkStock("Bag", 20);
        } catch (OutOfStockException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayCustomerInfo() {
        new Customer("C001", "John Doe", "john.doe@example.com");
        new Customer("C002", "Jane Smith", "jane.smith@example.com");
    }

    public static void placeOrder(Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        double totalPrice = price * quantity;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("Product: " + productName);
            writer.newLine();
            writer.write("Price: " + price);
            writer.newLine();
            writer.write("Quantity: " + quantity);
            writer.newLine();
            System.out.println("Order placed. Total price: " + totalPrice);
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }
}

