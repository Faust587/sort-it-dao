package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.abstraction.UserDAO;

public class PostgresqlDAOFactory extends DAOFactory {

  public PostgresqlDAOFactory() {
  }

  @Override
  public UserDAO getUserDAO() {
    return null;
  }

  @Override
  public ColorDAO getColorDAO() {
    return null;
  }

  @Override
  public DifficultDAO getDifficultDAO() {
    return null;
  }

  @Override
  public RatingDAO getRatingDAO() {
    return null;
  }

  private static class SingletonHolder {
    private static final PostgresqlDAOFactory INSTANCE = new PostgresqlDAOFactory();
  }

  public static PostgresqlDAOFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
