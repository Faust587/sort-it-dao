package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.abstraction.UserDAO;
import ua.synkulych.sort_it.dao.entity.User;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlColorDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlDifficultDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlRatingDAO;
import ua.synkulych.sort_it.dao.impl.mysql.MysqlUserDAO;

public class MysqlDAOFactory extends DAOFactory {

  public MysqlDAOFactory() {
  }

  @Override
  public MysqlUserDAO getUserDAO() {
    return MysqlUserDAO.getInstance();
  }

  @Override
  public MysqlColorDAO getColorDAO() {
    return MysqlColorDAO.getInstance();
  }

  @Override
  public MysqlDifficultDAO getDifficultDAO() {
    return MysqlDifficultDAO.getInstance();
  }

  @Override
  public MysqlRatingDAO getRatingDAO() {
    return MysqlRatingDAO.getInstance();
  }

  private static class SingletonHolder {
    private static final MysqlDAOFactory INSTANCE = new MysqlDAOFactory();
  }

  public static MysqlDAOFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
