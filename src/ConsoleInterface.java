import java.sql.Connection;
import java.util.List;
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

        //SHOW MANAGE PRODUCT MENU
        public void manageProducts() {
            while (true) {
                System.out.println("Manage Products Menu:");
                System.out.println("1. Add Product");
                System.out.println("2. Search Product");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("0. Back to Main Menu");

                System.out.print("Enter your choice (0-4): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        searchProduct();
                        break;
                    case 3:
                        updateProduct();
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

        //SUB MENU FEATURES OF MANAGE PRODUCT
        //ADD PRODUCT FEATURES
    private void addProduct() {

        do {
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

            boolean result = product.addProduct(product, conn);
            if (result){
                System.out.println("Product Added Successfully");
            }else{
                System.out.println("Something wrong");
            }
            System.out.print("Do you want to add another product? Yes(y) or No(n): ");
            scanner.nextLine();  // Consume the newline character
            String option = scanner.nextLine();

            if (!option.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to add another product
            }

        } while (true);

            }



        //SUB MENU FEATURES OF MANAGE PRODUCT
        //SEARCH PRODUCT FEATURES



        private void searchProduct(){
            int i = 1;
            String searchTag = "";
            while(true){

                System.out.println("Enter  Product Name or Number You Want to Search");
                if(i == 1){
                    scanner.nextLine();
                }
                searchTag = scanner.nextLine();


                List<Product> foundProducts = Product.searchAndShowProduct(conn, searchTag);


            if (!foundProducts.isEmpty()) {
                System.out.println("Product Information:");
                System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                        "ID", "Name", "Description", "Purchase Price", "Selling Price", "Quantity");

                for (Product product : foundProducts) {
                    System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                            product.getProductId(),
                            product.getProductName(),
                            product.getDescription(),
                            product.getPurchasePrice(),
                            product.getSellingPrice(),
                            product.getQuantity());
                }


            } else {
                System.out.println("Product not found.");
            }

                System.out.println("Do You Want To Search Another Items Yes(y) No(n)");
                //scanner.nextLine();  // Consume the newline character
                String option = scanner.nextLine();
                if (!option.equalsIgnoreCase("y")) {
                    break; // Exit the loop if the user doesn't want to add another product
                }


                i++;



            }

        }

    //SUB MENU FEATURES OF MANAGE PRODUCT
    //UPDATE PRODUCT FEATURES
        public void updateProduct(){
            System.out.println("Update Product:");
            System.out.print("Enter the ID of the product you want to update: ");
            int productId = scanner.nextInt();

            // Check if the product with the given ID exists
            Product existingProduct = Product.getProductById(conn, productId);



            if (existingProduct != null) {
                int id =     existingProduct.getProductId();
                System.out.println("Existing Product Information:");
                System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                        "ID", "Name", "Description", "Purchase Price", "Selling Price", "Quantity");
                System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                        existingProduct.getProductId(),
                        existingProduct.getProductName(),
                        existingProduct.getDescription(),
                        existingProduct.getPurchasePrice(),
                        existingProduct.getSellingPrice(),
                        existingProduct.getQuantity());

                // Get updated information from the user
                System.out.println("Enter new information for the product:");

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

                // Update the product object
                existingProduct.setProductName(name);
                existingProduct.setDescription(desc);
                existingProduct.setQuantity(quantity);
                existingProduct.setPurchasePrice(price);
                existingProduct.setSellingPrice(sellingPrice);

                // Update the product in the database
                boolean result = existingProduct.updateProductById(existingProduct, conn, id);

                if (result) {
                    System.out.println("Product Updated Successfully");
                } else {
                    System.out.println("Something went wrong while updating the product.");
                }

            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
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

