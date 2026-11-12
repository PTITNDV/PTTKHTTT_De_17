package vn.edu.ptit.cineman.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database5 {
    private Database5() {}

    public static Connection connect() throws SQLException {
        // Tomcat's web-app class loader may not expose JDBC service providers to DriverManager.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("MySQL JDBC driver is missing from WEB-INF/lib.", ex);
        }
        String url = System.getenv().getOrDefault("CINEMAN_DB_URL",
            "jdbc:mysql://localhost:3306/cineman?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Bangkok");
        String user = System.getenv().getOrDefault("CINEMAN_DB_USER", "root");
        String password = System.getenv().getOrDefault("CINEMAN_DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }
}
