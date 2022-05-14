package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.abstraction.UserDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlColorDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlDifficultDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlRatingDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlUserDAO;

public class PostgresqlDAOFactory extends DAOFactory {

  public PostgresqlDAOFactory() {
  }

  @Override
  public MysqlUserDAO getUserDAO() {
    return null;
  }

  @Override
  public MysqlColorDAO getColorDAO() {
    return null;
  }

  @Override
  public MysqlDifficultDAO getDifficultDAO() {
    return null;
  }

  @Override
  public MysqlRatingDAO getRatingDAO() {
    return null;
  }

  private static class SingletonHolder {
    private static final PostgresqlDAOFactory INSTANCE = new PostgresqlDAOFactory();
  }

  public static PostgresqlDAOFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
