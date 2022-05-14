package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.abstraction.ColorDAO;
import ua.synkulych.sort_it.dao.entity.Color;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlColorDAO extends ColorDAO {

  private static final String GET_COLOR_BY_ID = """
      SELECT  colors.value
        FROM  colors
       WHERE  colors.id=?
      """;

  private static final String UPDATE_COLOR_BY_ID = """
      UPDATE  colors
         SET  colors.value=?
       WHERE  colors.id=?
      """;

  private static final String DELETE_COLOR_BY_VALUE = """
      DELETE
        FROM  colors
       WHERE  colors.value=?
      """;

  private static final String INSERT_NEW_COLOR = """
      INSERT
        INTO  colors (colors.id, colors.value)
       VALUE  (NULL, ?)
      """;

  private MysqlColorDAO() {
  }

  private static class SingletonHolder {
    private static final MysqlColorDAO INSTANCE = new MysqlColorDAO();
  }

  public static MysqlColorDAO getInstance() {
    return MysqlColorDAO.SingletonHolder.INSTANCE;
  }

  @Override
  public Color getColorById(int id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_COLOR_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      result.next();
      return new Color(result.getNString("value"));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean updateColorById(int id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_COLOR_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean deleteColorById(String value) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_COLOR_BY_VALUE)) {
      statement.setString(1, value);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean addColor(String value) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_COLOR)) {
      statement.setString(1, value);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
