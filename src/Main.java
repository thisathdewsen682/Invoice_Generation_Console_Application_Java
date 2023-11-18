import javax.script.ScriptContext;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnect.connect();


            Scanner sc = new Scanner(System.in);




            //testing code
        /*  Customer customer1 = new Customer();
            customer1.setCustomerName("Thisath");
            customer1.setEmail("thisathdewsen77@gmail.com");
            customer1.setAddress("Gampaha");
            customer1.setContactNumber("0750657812");
            customer1.setDateOfBirth("1999/07/14");
            customer1.setGender("Mail");

            boolean result =  customer1.addNewCustomer(customer1, conn);
            if(result){
                System.out.println("Customer Added Succesfully");
            }*/



        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}