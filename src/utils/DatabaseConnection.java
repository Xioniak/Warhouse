package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
  private static Connection conn;

  private DatabaseConnection() {
  }

  public static Connection getConnection() {
    if (conn == null) {
      try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warhouse", "root", "");
        System.out.println("Connected to database");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return conn;
  }

  public static void closeConnection() {
    if (conn != null) {
      try {
        conn.close();
        System.out.println("Connection closed");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}