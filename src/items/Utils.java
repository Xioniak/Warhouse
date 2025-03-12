package items;

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
}