import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private String customerName;
    private String email;
    private String address;
    private String contactNumber;
    private String dateOfBirth;

    private String gender;

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

    //UPDATE CUSTOMER BY ID
    public boolean updateCustomerById(Customer customer, Connection conn, int id) {

        String updateSQL = "UPDATE customer SET customer_name = ?, email = ?, address = ?, contact_no = ?, dob = ?, gender = ? WHERE customer_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getContactNumber());
            statement.setString(5, customer.getDateOfBirth());
            statement.setString(6, customer.getGender());
            statement.setInt(7, id); // Assuming the customer_id is a string; adjust accordingly if it's another data type

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //SEARCH AND VIEW CUSTOMER BY TAG METHOD

    public static void searchAndShowCustomer(Connection conn, String searchTag) throws SQLException {

        String searchSql = "SELECT * FROM customer WHERE customer_name = ? OR customer_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(searchSql)) {

            statement.setString(1, searchTag);
            statement.setString(2, searchTag);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Customer Information:");
                    System.out.println("ID\tName\t\tEmail\t\tAddress\t\tContact\t\tDOB\t\tGender");

                    do {
                        System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                                resultSet.getString("customer_id"),
                                resultSet.getString("customer_name"),
                                resultSet.getString("email"),
                                resultSet.getString("address"),
                                resultSet.getString("contact_no"),
                                resultSet.getString("dob"),
                                resultSet.getString("gender"));
                    } while (resultSet.next());


                } else {
                    System.out.println("Customer not found.");


                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());


            }


        }


    }


    //DELETE CUSTOMER

    public static boolean  deleteCustomer( Connection conn,  int id) throws SQLException {

        try{
            String deleteSql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement statement = conn.prepareStatement(deleteSql);
            statement.setInt(1,id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;




        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;

        }



    }
































}





