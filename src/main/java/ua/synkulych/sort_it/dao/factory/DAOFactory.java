package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.impl.mysql.MysqlUserDAO;

public abstract class DAOFactory {

  public static final String POSTGRESQL = "POSTGRESQL";
  public static final String MYSQL = "MYSQL";

  public static DAOFactory getDAOFactory(String factoryName) {
    return switch (factoryName) {
      case POSTGRESQL -> PostgresqlDAOFactory.getInstance();
      case MYSQL -> MysqlDAOFactory.getInstance();
      default -> null;
    };
  }

  public abstract MysqlUserDAO getUserByUsername();
}
