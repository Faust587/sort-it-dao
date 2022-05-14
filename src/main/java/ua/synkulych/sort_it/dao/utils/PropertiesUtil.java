package ua.synkulych.sort_it.dao.utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

  private static final Properties PROPERTIES = new Properties();

  static {
    loadProperties();
  }

  public PropertiesUtil() {}

  public static String getProperties(String key) {
    return PROPERTIES.getProperty(key);
  }

  private static void loadProperties() {
    try ( var inputStream = Properties.class.getClassLoader().getResourceAsStream("application.properties")) {
      PROPERTIES.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
