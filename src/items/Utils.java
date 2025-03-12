package items;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class Utils {
  /**
   * Pobiera dane o pistoletach z bazy danych.
   * @return lista danych o pistoletach
   */
  public static List<String> getPistolsData() {
    List<String> pistolsData = new ArrayList<>();
    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warhouse", "root", "");

      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM pistol");

      while (resultSet.next()) {
        String name = resultSet.getString("name");
        boolean hasSilencer = resultSet.getBoolean("hasSilencer");
        pistolsData.add(name + " " + hasSilencer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pistolsData;
  }

  public static boolean registerUser(String username, String password) {
    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warhouse", "root", "");

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
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warhouse", "root", "");

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

  public static void titleDivider(String title) {
    System.out.println("--------| " + title + " |--------");
  }

  public void optionDivider() {
      System.out.println("-----------------------------");
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