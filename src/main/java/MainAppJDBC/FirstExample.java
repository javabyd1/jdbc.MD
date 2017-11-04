package MainAppJDBC;


import java.sql.*;
import java.util.Scanner;

public class FirstExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/j1b ? useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("Wybierz opcję");
            System.out.println("1.Wyświetl wszystkie adresy.");
            System.out.println("2.Dodaj adres.");
            System.out.println("3.Wyjdź.");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    String sql;
                    sql = "SELECT id_adresu, numer_domu, miasto, ulica FROM adresy";
                    ResultSet rs = stmt.executeQuery(sql);
                    //STEP 5: Extract data from result set
                    while (rs.next()) {
                        //Retrieve by column name
                        int id = rs.getInt("id_adresu");
                        String road = rs.getString("ulica");
                        String houseNumber = rs.getString("numer_domu");
                        String city = rs.getString("miasto");
                        //Display values
                        System.out.print("ID: " + id);
                        System.out.print(", Ulica: " + road);
                        System.out.print(", Numer domu: " + houseNumber);
                        System.out.println(", Miasto: " + city);
                    }
                    //STEP 6: Clean-up environment
                    rs.close();
                    stmt.close();
                    conn.close();
                    break;
                }
                case "2": {
                    String road = "";
                    System.out.println("Podaj id adresu:");
                    int idAdresu = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Podaj ulicę:");
                    road = scanner.nextLine();
                    System.out.println("Podaj numer domu:");
                    String houseNumber = scanner.nextLine();
                    System.out.println("Podaj miasto:");
                    String city = scanner.nextLine();

                    String sql2 = "INSERT INTO adresy (id_adresu, ulica, numer_domu, miasto)" + "VALUES (" + +idAdresu + ", '"+ road + "', '" + houseNumber + "', '" + city + "')";
                    int result = stmt.executeUpdate(sql2);
                    System.out.println("Dodano do bazy danych!");
                    break;
                }
                case "3": {
                    System.exit(0);
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        System.out.println("Goodbye!");
    }}//end main
}//end FirstExample

