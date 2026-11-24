package final_project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER_NAME = "c##dbsoft";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public static void main(String[] args) throws SQLException {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to Oracle as " + connection.getMetaData().getUserName());
        }
    }
}
