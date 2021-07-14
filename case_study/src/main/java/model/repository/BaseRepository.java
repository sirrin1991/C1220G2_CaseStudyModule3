package model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private Connection connection;
    private final String jdbcURL = "jdbc:mysql://localhost:3306/furama_resort";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "12345678";

    public BaseRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
