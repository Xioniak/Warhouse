package utils;

import items.Utils;

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
        Utils.print("Connected to database");
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
        Utils.print("Connection closed");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}