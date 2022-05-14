package ua.synkulych.sort_it.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

  private static final String DRIVER_KEY    = "db.driver";
  private static final String URL_KEY       = "db.url";
  private static final String USERNAME_KEY  = "db.username";
  private static final String PASSWORD_KEY  = "db.password";

  public static Connection openConnection() {
    System.out.println(PropertiesUtil.getProperties(URL_KEY));
    try {
      return DriverManager.getConnection(
        PropertiesUtil.getProperties(URL_KEY),
        PropertiesUtil.getProperties(USERNAME_KEY),
        PropertiesUtil.getProperties(PASSWORD_KEY)
      );
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static void driverLoader() {
    try {
      String driverName = PropertiesUtil.getProperties(DRIVER_KEY);
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
