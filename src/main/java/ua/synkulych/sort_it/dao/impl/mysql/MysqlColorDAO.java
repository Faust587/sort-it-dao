package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.DAO;
import ua.synkulych.sort_it.dao.entity.Color;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlColorDAO implements DAO<Color, Integer> {

  private static final String GET_COLOR_BY_ID = """
      SELECT  *
        FROM  colors
       WHERE  colors.id=?
      """;
  private static final String GET_ALL_COLORS = """
      SELECT  *
        FROM  colors
      """;

  private static final String UPDATE_COLOR_BY_ID = """
      UPDATE  colors
         SET  colors.value=?
       WHERE  colors.id=?
      """;

  private static final String DELETE_COLOR_BY_ID = """
      DELETE
        FROM  colors
       WHERE  colors.id=?
      """;

  private static final String INSERT_NEW_COLOR = """
      INSERT
        INTO  colors (colors.id, colors.value)
       VALUE  (NULL, ?)
      """;

  @Override
  public Optional<Color> get(Integer id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_COLOR_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      result.next();
      return Optional.of(new Color(result.getInt("id"), result.getString("value")));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public List<Color> getAll() {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL_COLORS)) {
      ResultSet result = statement.executeQuery();
      ArrayList<Color> colorList = new ArrayList<>();
      while (result.next()) {
        colorList.add(new Color(result.getInt("id"), result.getString("value")));
      }
      return colorList;
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public Color save(Color color) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_COLOR)) {
      statement.setString(1, color.getValue());
      ResultSet result = statement.executeQuery();
      if (result.next()) return color;
      throw new DAOException("Can not add color to the database");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean update(Color color) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_COLOR_BY_ID)) {
      statement.setInt(1, color.getId());
      statement.setString(1, color.getValue());
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean delete(Integer id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_COLOR_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  private MysqlColorDAO() {
  }


  private static class SingletonHolder {
    private static final MysqlColorDAO INSTANCE = new MysqlColorDAO();
  }

  public static MysqlColorDAO getInstance() {
    return MysqlColorDAO.SingletonHolder.INSTANCE;
  }
}
