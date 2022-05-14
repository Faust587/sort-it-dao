package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.abstraction.UserDAO;

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

  public abstract UserDAO getUserDAO();
  public abstract ColorDAO getColorDAO();
  public abstract DifficultDAO getDifficultDAO();
  public abstract RatingDAO getRatingDAO();
}
