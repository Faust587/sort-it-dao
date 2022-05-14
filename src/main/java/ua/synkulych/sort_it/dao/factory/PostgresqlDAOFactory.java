package ua.synkulych.sort_it.dao.factory;

import ua.synkulych.sort_it.dao.impl.mysql.MysqlUserDAO;

public class PostgresqlDAOFactory extends DAOFactory {

  public PostgresqlDAOFactory() {
  }

  @Override
  public MysqlUserDAO getUserByUsername() {
    return null;
  }

  private static class SingletonHolder {
    private static final PostgresqlDAOFactory INSTANCE = new PostgresqlDAOFactory();
  }

  public static PostgresqlDAOFactory getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
