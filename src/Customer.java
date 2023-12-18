import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String customerName;
    private String email;
    private String address;
    private String contactNumber;
    private String dateOfBirth;

    private String gender;

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //ADD NEW CUSTOMER

    public boolean addNewCustomer(Customer customer, Connection conn) {

        String insertSQL = "INSERT INTO customer (customer_name, email, address, contact_no, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getContactNumber());
            statement.setString(5, customer.getDateOfBirth());
            statement.setString(6, customer.getGender());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Customer getCustomerById(Connection conn, int custoemrId){
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1,custoemrId);

            try (ResultSet resultSet = statement.executeQuery()){

                if (resultSet.next()){
                    Customer customer = new Customer();
                    customer.setId(String.valueOf(resultSet.getInt("customer_id")));
                    customer.setCustomerName(resultSet.getString("customer_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));
                    customer.setContactNumber(resultSet.getString("contact_no"));
                    customer.setGender(resultSet.getString("gender"));
                    return customer;
                }
            }

        }catch (SQLException e){

            System.out.println(e.getMessage());
        }


        return null;
    }

    //UPDATE CUSTOMER BY ID
    public boolean updateCustomerById(Customer customer, Connection conn, String id) {

        String updateSQL = "UPDATE customer SET customer_name = ?, email = ?, address = ?, contact_no = ?, dob = ?, gender = ? WHERE customer_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getContactNumber());
            statement.setString(5, customer.getDateOfBirth());
            statement.setString(6, customer.getGender());
            statement.setString(7, id); // Assuming the customer_id is a string; adjust accordingly if it's another data type

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //SEARCH AND VIEW CUSTOMER BY TAG METHOD

    public static List<Customer> searchAndShowCustomer(Connection conn, String searchTag) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String searchSql = "SELECT * FROM customer WHERE customer_name = ? OR customer_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(searchSql)) {

            statement.setString(1, searchTag);
            statement.setString(2, searchTag);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(String.valueOf(Integer.parseInt(resultSet.getString("customer_id"))));
                    customer.setCustomerName(resultSet.getString("customer_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));

                    customer.setContactNumber(resultSet.getString("contact_no"));

                    customer.setDateOfBirth(resultSet.getString("dob"));

                    customer.setGender(resultSet.getString("gender"));
                    customers.add(customer);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            } catch (SQLException e) {
                System.out.println(e.getMessage());


            }


        return customers;
    }




    //DELETE CUSTOMER

    public static boolean  deleteCustomer( Connection conn, String id) throws SQLException {

        try{
            String deleteSql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement statement = conn.prepareStatement(deleteSql);
            statement.setString(1,id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;


        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;

        }



    }
































}





