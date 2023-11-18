import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection connect(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/invoice_generation_system","root","");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
