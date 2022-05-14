package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.DAO;
import ua.synkulych.sort_it.dao.entity.User;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlUserDAO implements DAO<User, String> {

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

  private static final String UPDATE_USER = """
      UPDATE  users
         SET  username=?,
              password=?
       WHERE  users.id=?
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

  @Override
  public Optional<User> get(String username) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_BY_USERNAME_SQL)) {
      statement.setString(1, username);
      ResultSet result = statement.executeQuery();
      return Optional.of(new User(result.getString("username"), result.getString("password")));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public List<User> getAll() {

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
  public User save(User user) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)) {
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getPassword());
      ResultSet result = statement.executeQuery();
      if (result.next()) return user;
      throw new DAOException("Can not save user to the database");
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean update(User user) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
      statement.setString(1, user.getUsername());
      statement.setString(2, user.getPassword());
      statement.setInt(3, user.getId());
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean delete(String username) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
      statement.setString(1, username);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  private MysqlUserDAO() {}

  private static class SingletonHolder {
    private static final MysqlUserDAO INSTANCE = new MysqlUserDAO();
  }

  public static MysqlUserDAO getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
