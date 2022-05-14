package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.impl.mysql.MysqlUserDAO;

public class MysqlDAOFactory extends DAOFactory {

  public MysqlDAOFactory() {
  }

  @Override
  public MysqlUserDAO getUserByUsername() {
    return MysqlUserDAO.getInstance();
  }

  private static class SingletonHolder {
    private static final MysqlDAOFactory INSTANCE = new MysqlDAOFactory();
  }

  public static MysqlDAOFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
