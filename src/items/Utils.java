package items;

import utils.DatabaseConnection;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class Utils {
  public static String userLogin;

  public static String getUserLogin() {
    return userLogin;
  }

  public static void setUserLogin(String login) {
    userLogin = login;
  }

  public static void print(String value) {
    System.out.println(value);
  }

  public static String getProducts() {
    Utils.print("Brand | Model | Price | Amount");
    Connection conn = DatabaseConnection.getConnection();

    try {
      Statement statement = conn.createStatement();
      ResultSet dbProducts = statement.executeQuery("SELECT name, brand, price, amount FROM products");
      StringBuilder productsData = new StringBuilder();
      while (true) {
        assert dbProducts != null;
        if (!dbProducts.next()) break;
        String name = dbProducts.getString("name");
        String brand = dbProducts.getString("brand");
        String price = dbProducts.getString("price");
        String amount = dbProducts.getString("amount");
        productsData.append("- ").append(brand).append(" | ").append(name).append(" | ").append(price).append("$ | ").append(amount).append("pcs. \n");
      }
      return productsData.toString();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getWeapon() {
    Utils.print("Brand | Model | Caliber | Price | Amount");
    Connection conn = DatabaseConnection.getConnection();

    try {
      Statement statement = conn.createStatement();
      ResultSet dbWeapon = statement.executeQuery("SELECT brand, model, caliber, price, amount, type FROM weapons");
      StringBuilder weaponData = new StringBuilder();
      while (true) {
        assert dbWeapon != null;
        if (!dbWeapon.next()) break;
        String brand = dbWeapon.getString("brand");
        String model = dbWeapon.getString("model");
        String caliber = dbWeapon.getString("caliber");
        String price = dbWeapon.getString("price");
        String amount = dbWeapon.getString("amount");
        String type = dbWeapon.getString("type");
        if (type.equals("ICBM")) {
          weaponData.append("- ").append(brand).append(" ").append(type).append(" | ").append(model).append(" | ").append(caliber).append(" | ").append(price).append("$ | ").append(amount).append(" missile silos\n");
        } else {
          weaponData.append("- ").append(brand).append(" | ").append(model).append(" | ").append(caliber).append(" | ").append(price).append("$ | ").append(amount).append("pcs.\n");
        }
      }
      return weaponData.toString();
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
    setUserLogin(username);
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
    Utils.print(transactions != null ? transactions : "No transactions found");
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
    Utils.print("--------| " + title + " |--------");
  }

  public static void optionDivider() {
      Utils.print("---------------------------");
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