package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.abstraction.UserDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlColorDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlDifficultDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlRatingDAO;
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

  public abstract MysqlUserDAO getUserDAO();
  public abstract MysqlColorDAO getColorDAO();
  public abstract MysqlDifficultDAO getDifficultDAO();
  public abstract MysqlRatingDAO getRatingDAO();
}
