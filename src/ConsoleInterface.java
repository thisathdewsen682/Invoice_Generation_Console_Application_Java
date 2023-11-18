import java.sql.Connection;
import java.util.Scanner;

public class ConsoleInterface {

        private final Scanner scanner;
        private final Connection conn;
        public ConsoleInterface() {
            this.scanner = new Scanner(System.in);
            this.conn =  DBConnect.connect();
        }

        public void showMenu() {
            System.out.println("Main Menu:");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Customers");
            System.out.println("3. Invoice Generation");
            System.out.println("4. Admin Tasks");
            System.out.println("0. Exit");
        }

        public void manageProducts() {
            while (true) {
                System.out.println("Manage Products Menu:");
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Search Product");
                System.out.println("4. Delete Product");
                System.out.println("0. Back to Main Menu");

                System.out.print("Enter your choice (0-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        //updateProduct();
                        break;
                    case 3:
                       // searchProduct();
                        break;
                    case 4:
                       // deleteProduct();
                        break;
                    case 0:
                        return; // Back to the main menu
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 4.");
                }
            }
        }
    private void addProduct() {
        System.out.println("Adding Product:");


        System.out.print("Enter Product Name: ");
        String name = scanner.next();

        System.out.print("Enter Product Price: ");
        float price = scanner.nextFloat();

        System.out.print("Enter Product Description: ");
        scanner.nextLine();
        String desc = scanner.nextLine();



        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Selling Price: ");
        float sellingPrice = scanner.nextFloat();


        Product product = new Product();

        product.setProductName(name);
        product.setDescription(desc);
        product.setQuantity(quantity);
        product.setPurchasePrice(price);
        product.setSellingPrice(sellingPrice);

        boolean result = product.addProduct(product , conn);



    }

        public void manageCustomers() {
            // Add functionality for managing customers
            System.out.println("Managing Customers");
        }

        public void generateInvoice() {
            // Add functionality for invoice generation
            System.out.println("Generating Invoice");
        }

        public void adminTasks() {
            // Add functionality for admin tasks
            System.out.println("Admin Tasks");
        }

        public void startSystem() {
            while (true) {
                showMenu();
                System.out.print("Enter your choice (0-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageProducts();
                        break;
                    case 2:
                        manageCustomers();
                        break;
                    case 3:
                        generateInvoice();
                        break;
                    case 4:
                        adminTasks();
                        break;
                    case 0:
                        System.out.println("Exiting the system. Goodbye!");
                        scanner.close();
                        //conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 4.");
                }
            }
        }


    }

