package MainAppJDBC.Database;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection = null;
    private final static String ADRESS = "jdbc:mysql://localhost";
    private final static String DATABASE = "\"j1b?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String USER = "localhost";
    private final static String PASSWORD = "";
    private final static String PORT = "3306";
    private final static String DRIVER = "com.mysql.jdbc.Driver";

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getFormattedUrl(){
        return ADRESS + ":" + PORT + "/" + DATABASE;
    }

    public static void loadConnection(){
        try {
            connection = DriverManager.getConnection(getFormattedUrl(), USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if (connection == null){
            System.out.println("Tworzę połączenie");
            loadDriver();
            loadConnection();
        }
        System.out.println(connection);
        return connection;
    }
}
