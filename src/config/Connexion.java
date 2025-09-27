package config;

import java.sql.*;
import java.sql.DriverManager;

public class Connexion {

    private String url = "jdbc:mysql://localhost:3306/transactions_bancaires";
    private String username = "root";
    private String password = "root";

    public static Connexion instance = null;
    private Connection connection = null;

    public static Connexion getInstance() {
        if (instance == null)
            instance = new Connexion();
        return instance;
    }

    private Connexion() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
