package sample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private static Connection conn = null;
  static{
    final String url = "jdbc:postgresql://localhost/cinema";
    final String user = "postgres";
    final String password = "admin";
    try {
      conn = DriverManager.getConnection(url, user, password);
      System.out.println("Connected to the PostgreSQL server successfully.");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static Connection getConnection() {
    return conn;
  }
}
