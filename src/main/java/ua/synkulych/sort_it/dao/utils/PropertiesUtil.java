package ua.synkulych.sort_it.dao.utils;

import java.io.IOException;
import java.io.InputStream;
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

  public static void loadProperties() {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    try (InputStream stream = loader.getResourceAsStream("application.properties")) {
      PROPERTIES.load(stream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
