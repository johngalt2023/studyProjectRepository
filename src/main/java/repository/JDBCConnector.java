package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/cleverservletproject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Exception during create connection with database");
            throw new SQLException();
        }
    }
}
