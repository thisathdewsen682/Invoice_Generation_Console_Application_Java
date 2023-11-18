import java.sql.Connection;
import java.sql.PreparedStatement;

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

    public boolean addNewCustomer(Customer customer, Connection conn){
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

    //UPDATE CUSTOMER BY ID

        


    }




}
