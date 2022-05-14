package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.abstraction.UserDAO;
import ua.synkulych.sort_it.dao.entity.User;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO extends UserDAO {

  private static final String GET_BY_USERNAME_SQL = """
      SELECT  users.username,
              users.password
        FROM  users
       WHERE  users.username=?
      """;

  private static final String GET_ALL_USERS = """
      SELECT  users.username,
              users.password
        FROM  users
      """;

  private static final String UPDATE_USER_USERNAME = """
      UPDATE  users
         SET  username=?
       WHERE  username=? AND password=?
      """;

  private static final String UPDATE_USER_PASSWORD = """
      UPDATE  users
         SET  password=?
       WHERE  username=? AND password=?
      """;

  private static final String DELETE_USER = """
      DELETE
        FROM  users
       WHERE  username=? AND password=?
      """;

  private static final String INSERT_NEW_USER = """
      INSERT
        INTO  users (users.id, users.username, users.password)
       VALUE  (NULL, ?, ?)
      """;

  private MysqlUserDAO() {
  }

  private static class SingletonHolder {
    private static final MysqlUserDAO INSTANCE = new MysqlUserDAO();
  }

  public static MysqlUserDAO getInstance() {
    return SingletonHolder.INSTANCE;
  }

  @Override
  public List<User> getUserList() {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
      ResultSet result = statement.executeQuery();
      ArrayList<User> users = new ArrayList<>();
      while (result.next()) {
        users.add(new User(result.getString("username"), result.getString("password")));
      }
      return users;
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public User getUserByUsername(String username) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_USERNAME_SQL)) {
      statement.setString(1, username);
      ResultSet result = statement.executeQuery();
      result.next();
      return new User(result.getString("username"), result.getString("password"));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean updateUsername(String username, String password, String value) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_USER_USERNAME)) {
      statement.setString(1, value);
      statement.setString(2, username);
      statement.setString(3, password);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean updatePassword(String username, String password, String value) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
      statement.setString(1, value);
      statement.setString(2, username);
      statement.setString(3, password);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean deleteUser(String username, String password) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean addNewUser(String username, String password) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }
}
