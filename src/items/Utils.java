package items;

import utils.DatabaseConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Utils {
  /**
   * Pobiera dane o pistoletach z bazy danych.
   * @return lista danych o pistoletach
   */
  public static List<String> getPistolsData() {
    List<String> pistolsData = new ArrayList<>();
     try {
       Connection conn = DatabaseConnection.getConnection();

       Statement statement = conn.createStatement();
       ResultSet dbPistolData = statement.executeQuery("SELECT * FROM pistol");
       while (true) {
         assert dbPistolData != null;
         if (!dbPistolData.next()) break;
         String name = dbPistolData.getString("name");
         boolean hasSilencer = dbPistolData.getBoolean("hasSilencer");
         pistolsData.add(name + " " + hasSilencer);
       }
       return pistolsData;
     } catch (SQLException e) {
       e.printStackTrace();
     }
    return null;
  }

  public static boolean registerUser(String username, String password) {
    try {
      Connection conn = DatabaseConnection.getConnection();

      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT login FROM user WHERE login = '" + username + "'");

      if (resultSet.next()) {
        return false;
      } else {
        String password_hash = toSHA256(password);
        if (password_hash == null) {
          return false;
        } else {
          statement.executeUpdate("INSERT INTO user (login, pass_hash) VALUES ('" + username + "', '" + password_hash + "')");
          return true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean loginUser(String username, String password) {
    try {
      Connection conn = DatabaseConnection.getConnection();

      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE login = '" + username + "'");

      if (!resultSet.next()) {
          return false;
      } else {
        String password_hash = toSHA256(password);
        if (password_hash == null) {
            return false;
        } else {
            return resultSet.getString("pass_hash").equals(password_hash);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void displayUserTransactions(String username) {
    String transactions = getUserTransactions(username);
    titleDivider("TRANSACTIONS");
    System.out.println(transactions != null ? transactions : "No transactions found");
  }

  public static String getUserTransactions(String username) {
    String queryUser = "SELECT id FROM `user` WHERE login = ?";
    String queryTransactions = "SELECT * FROM `user_transactions` WHERE user_id = ?";
    StringBuilder composedResult = new StringBuilder();
    Connection conn = DatabaseConnection.getConnection();

    try (PreparedStatement userStmt = conn.prepareStatement(queryUser);
         PreparedStatement transactionStmt = conn.prepareStatement(queryTransactions)) {

      // Get user ID
      userStmt.setString(1, username);
      try (ResultSet userIdResultSet = userStmt.executeQuery()) {
        if (!userIdResultSet.next()) {
          return null; // User not found
        }
        int userId = userIdResultSet.getInt("id");

        // Get transactions
        transactionStmt.setInt(1, userId);
        try (ResultSet resultSet = transactionStmt.executeQuery()) {
          int i = 0;
          while (resultSet.next()) {
            i++;
            String paymentMethod = resultSet.getString("payment_method");
            float price = resultSet.getFloat("price");
            Date order_date = resultSet.getDate("order_date");
            composedResult.append("[").append(i).append("] ")
                    .append(paymentMethod).append(" ")
                    .append(price).append(" ").append(order_date).append("\n");
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return !composedResult.isEmpty() ? composedResult.toString() : null;
  }

  public static void titleDivider(String title) {
    System.out.println("--------| " + title + " |--------");
  }

  public static void optionDivider() {
      System.out.println("---------------------------");
  }

  public static String toSHA256(String data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");

      byte[] hashBytes = digest.digest(data.getBytes());

      StringBuilder hexString = new StringBuilder();
      for (byte b : hashBytes) {
        hexString.append(String.format("%02x", b));
      }

      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }
}