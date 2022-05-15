package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.DAO;
import ua.synkulych.sort_it.dao.entity.Difficult;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlDifficultDAO implements DAO<Difficult, Integer> {

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

  @Override
  public Optional<Difficult> get(Integer id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_DIFFICULT_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      result.next();
      return Optional.of(new Difficult(result.getInt("id"), result.getInt("height"), result.getInt("weight"), result.getInt("points")));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public List<Difficult> getAll() {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL_DIFFICULT)) {
      ResultSet result = statement.executeQuery();
      ArrayList<Difficult> difficultList = new ArrayList<>();
      while (result.next()) {
        difficultList.add(new Difficult(result.getInt("id"), result.getInt("height"), result.getInt("weight"), result.getInt("points")));
      }
      return difficultList;
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public Integer save(Difficult difficult) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_DIFFICULT, Statement.RETURN_GENERATED_KEYS)) {
      statement.setInt(1, difficult.getHeight());
      statement.setInt(2, difficult.getWeight());
      statement.setInt(3, difficult.getPoints());
      statement.executeQuery();
      ResultSet result = statement.getGeneratedKeys();
      if (result.next()) return result.getInt(1);
      throw new DAOException("Can not save difficult to database");
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean update(Difficult difficult) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_DIFFICULT)) {
      statement.setInt(1, difficult.getHeight());
      statement.setInt(2, difficult.getWeight());
      statement.setInt(3, difficult.getPoints());
      statement.setInt(4, difficult.getId());
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean delete(Integer id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_DIFFICULT_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  private static class SingletonHolder {
    private static final MysqlDifficultDAO INSTANCE = new MysqlDifficultDAO();
  }

  public static MysqlDifficultDAO getInstance() {
    return MysqlDifficultDAO.SingletonHolder.INSTANCE;
  }
}
