import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productId;

    private String productName;

    private String description;

    private float purchasePrice;
    private float sellingPrice;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }



    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean addProduct(Product product, Connection conn ) {
        String insertSQL = "INSERT INTO product (product_name, description, purchase_price, selling_price, quantity) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getPurchasePrice());
            statement.setFloat(4, product.getSellingPrice());
            statement.setInt(5, product.getQuantity());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateProductById(Product product, Connection conn, int id) {
        String updateSQL = "UPDATE product SET product_name = ?, description = ?, purchase_price = ?, selling_price = ?, quantity = ? WHERE product_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getPurchasePrice());
            statement.setFloat(4, product.getSellingPrice());
            statement.setInt(5, product.getQuantity());
            statement.setInt(6, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean deleteProduct(Connection conn, int id) {
        String deleteSQL = "DELETE FROM product WHERE product_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(deleteSQL)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Product> searchAndShowProduct(Connection conn, String searchTag) {
        List<Product> products = new ArrayList<>();
        String searchSql = "SELECT * FROM product WHERE product_name = ? OR product_id =? ";

        try (PreparedStatement statement = conn.prepareStatement(searchSql)) {
            statement.setString(1, searchTag);
            statement.setString(2, searchTag);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(Integer.parseInt(resultSet.getString("product_id")));
                    product.setProductName(resultSet.getString("product_name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPurchasePrice(Float.parseFloat(resultSet.getString("purchase_price")));
                    product.setSellingPrice(Float.parseFloat(resultSet.getString("selling_price")));
                    product.setQuantity(Integer.parseInt(resultSet.getString("quantity")));

                    products.add(product);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    //GET PRODUCT BY ID

    public static Product getProductById(Connection conn, int productId){
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1,productId);

                try (ResultSet resultSet = statement.executeQuery()){

                    if (resultSet.next()){
                        Product product = new Product();
                        product.setProductId(resultSet.getInt("product_id"));
                        product.setProductName(resultSet.getString("product_name"));
                        product.setDescription(resultSet.getString("description"));
                        product.setPurchasePrice(resultSet.getFloat("purchase_price"));
                        product.setSellingPrice(resultSet.getFloat("selling_price"));
                        product.setQuantity(resultSet.getInt("quantity"));
                        return product;
                    }
                }

        }catch (SQLException e){

            System.out.println(e.getMessage());
        }


        return null;
    }


}


