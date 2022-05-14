package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.abstraction.UserDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlColorDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlDifficultDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlRatingDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlUserDAO;

public class MysqlDAOFactory extends DAOFactory {

  public MysqlDAOFactory() {
  }

  @Override
  public UserDAO getUserDAO() {
    return MysqlUserDAO.getInstance();
  }

  @Override
  public ColorDAO getColorDAO() {
    return MysqlColorDAO.getInstance();
  }

  @Override
  public DifficultDAO getDifficultDAO() {
    return MysqlDifficultDAO.getInstance();
  }

  @Override
  public RatingDAO getRatingDAO() {
    return MysqlRatingDAO.getInstance();
  }

  private static class SingletonHolder {
    private static final MysqlDAOFactory INSTANCE = new MysqlDAOFactory();
  }

  public static MysqlDAOFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
