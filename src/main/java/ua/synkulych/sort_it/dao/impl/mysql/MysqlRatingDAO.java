package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.DAO;
import ua.synkulych.sort_it.dao.entity.Rating;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlRatingDAO implements DAO<Rating, Integer> {

  private static final String GET_ALL_RATINGS = """
      SELECT rating.difficult_id,
             rating.user_id
      FROM rating
      """;

  private static final String GET_RATING_BY_ID = """
      SELECT rating.difficult_id,
             rating.user_id
        FROM rating
       WHERE rating.id=?
      """;

  private static final String INSERT_NEW_RATING = """
      INSERT
        INTO  rating (user_id, difficult_id)
       VALUE  (?, ?)
      """;

  private static final String DELETE_RATING_BY_ID = """
      DELETE
        FROM  rating
       WHERE  rating.id=?
      """;

  private static final String UPDATE_RATING = """
      UPDATE  rating
         SET  rating.user_id=?,
              rating.difficult_id=?
       WHERE  rating.id=?
      """;

  @Override
  public Optional<Rating> get(Integer id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_RATING_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      result.next();
      return Optional.of(new Rating(result.getInt("user_id"), result.getInt("difficult_id")));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public List<Rating> getAll() {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_ALL_RATINGS)) {
      ResultSet result = statement.executeQuery();
      ArrayList<Rating> ratingList = new ArrayList<>();
      while (result.next()) {
        ratingList.add(new Rating(result.getInt("user_id"), result.getInt("difficult_id")));
      }
      return ratingList;
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public Rating save(Rating rating) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_RATING)) {
      statement.setInt(1, rating.getUserId());
      statement.setInt(2, rating.getDifficultId());
      ResultSet result = statement.executeQuery();
      if (result.next()) return rating;
      throw new DAOException("Can not save rating to the database");
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean update(Rating rating) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(UPDATE_RATING)) {
      statement.setInt(1, rating.getUserId());
      statement.setInt(2, rating.getDifficultId());
      statement.setInt(3, rating.getId());
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean delete(Integer id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_RATING_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  private MysqlRatingDAO() {
  }

  private static class SingletonHolder {
    private static final MysqlRatingDAO INSTANCE = new MysqlRatingDAO();
  }

  public static MysqlRatingDAO getInstance() {
    return MysqlRatingDAO.SingletonHolder.INSTANCE;
  }


}
