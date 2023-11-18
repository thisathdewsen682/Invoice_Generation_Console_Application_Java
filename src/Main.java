import javax.script.ScriptContext;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ConsoleInterface systemManager = new ConsoleInterface();
            systemManager.startSystem();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}