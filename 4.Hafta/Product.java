public class Product {
    private String productName;
    private double price;
    private int stock;

    public Product(String productName, double price, int stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public static void displayProductInfo(String productName, double price, int stock) {
        System.out.println("Product: " + productName + ", Price: $" + price + ", Stock: " + stock);
    }

    public static void updateProductInfo(String productName, double price, int stock) {
        System.out.println("Updated Product: " + productName + ", Price: " + price + ", Stock: " + stock);
    }

    public static void checkStock(String productName, int stock) throws OutOfStockException {
        if (stock == 0) {
            throw new OutOfStockException(productName + " is out of stock.");
        } else {
            System.out.println(productName + " stock: " + stock);
        }
    }
}

