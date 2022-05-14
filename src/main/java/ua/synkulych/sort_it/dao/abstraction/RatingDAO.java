package ua.synkulych.sort_it.dao.abstraction;

import ua.synkulych.sort_it.dao.entity.Rating;

import java.util.ArrayList;

public abstract class RatingDAO {
  public abstract ArrayList<Rating> getRatingListByUserId(int userId);
  public abstract Rating getRatingById(int id);
  public abstract boolean addRating(int userId, int difficult_id);
  public abstract boolean deleteRatingById(int id);
}
