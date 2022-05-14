package ua.synkulych.sort_it.dao.impl.mysql;

import ua.synkulych.sort_it.dao.abstraction.RatingDAO;
import ua.synkulych.sort_it.dao.entity.Rating;
import ua.synkulych.sort_it.dao.exception.DAOException;
import ua.synkulych.sort_it.dao.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MysqlRatingDAO extends RatingDAO {

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

  private MysqlRatingDAO() {
  }

  @Override
  public ArrayList<Rating> getRatingListByUserId(int userId) {

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
  public Rating getRatingById(int id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(GET_RATING_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      result.next();
      return new Rating(result.getInt("user_id"), result.getInt("difficult_id"));
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean addRating(int userId, int difficult_id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_NEW_RATING)) {
      statement.setInt(1, userId);
      statement.setInt(2, difficult_id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public boolean deleteRatingById(int id) {

    try (Connection connection = ConnectionManager.openConnection();
         PreparedStatement statement = connection.prepareStatement(DELETE_RATING_BY_ID)) {
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      return result.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  private static class SingletonHolder {
    private static final MysqlRatingDAO INSTANCE = new MysqlRatingDAO();
  }

  public static MysqlRatingDAO getInstance() {
    return MysqlRatingDAO.SingletonHolder.INSTANCE;
  }


}
