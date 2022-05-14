package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.abstraction.DifficultDAO;
import ua.synkulych.sort_it.dao.entity.Difficult;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MysqlDifficultDAO extends DifficultDAO {

  private static final String GET_DIFFICULT_BY_ID = """
      SELECT  difficult.height,
              difficult.weight,
              difficult.points
        FROM  difficult
       WHERE  difficult.id=?
      """;

  private static final String GET_ALL_DIFFICULT = """
      SELECT  difficult.height,
              difficult.weight,
              difficult.points
        FROM  difficult
      """;

  private static final String UPDATE_DIFFICULT = """
      UPDATE  difficult
         SET  difficult.height=?,
              difficult.weight=?,
              difficult.points=?
       WHERE  difficult.id=?
      """;

  private static final String DELETE_DIFFICULT_BY_ID = """
      DELETE
        FROM  difficult
       WHERE  difficult.id=?
      """;

  private static final String INSERT_NEW_DIFFICULT = """
      INSERT
        INTO  difficult (height, weight, points)
       VALUE  (?, ?, ?)
      """;

  private MysqlDifficultDAO() {
  }

  private static class SingletonHolder {
    private static final MysqlDifficultDAO INSTANCE = new MysqlDifficultDAO();
  }

  public static MysqlDifficultDAO getInstance() {
    return MysqlDifficultDAO.SingletonHolder.INSTANCE;
  }

  @Override
  public Difficult getDifficultById(int id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_DIFFICULT_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      result.next();
      return new Difficult(result.getInt("height"), result.getInt("weight"), result.getInt("points"));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public ArrayList<Difficult> getDifficultList() {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL_DIFFICULT)) {
      ResultSet result = statement.executeQuery();
      ArrayList<Difficult> difficultList = new ArrayList<>();
      while (result.next()) {
        difficultList.add(new Difficult(result.getInt("height"), result.getInt("weight"), result.getInt("points")));
      }
      return difficultList;
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean updateDifficultById(int id, int height, int weight, int points) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_DIFFICULT)) {
      statement.setInt(1, height);
      statement.setInt(2, weight);
      statement.setInt(3, points);
      statement.setInt(4, id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean deleteDifficultById(int id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_DIFFICULT_BY_ID)) {
       statement.setInt(1, id);
       ResultSet result = statement.executeQuery();
       return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean addDifficultById(int height, int weight, int points) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_DIFFICULT)) {
      statement.setInt(1, height);
      statement.setInt(2, weight);
      statement.setInt(3, points);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }
}
