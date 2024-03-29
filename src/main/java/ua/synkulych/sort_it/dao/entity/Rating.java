package ua.synkulych.sort_it.dao.entity;

public class Rating {

  private int userId;
  private int difficultId;
  private int id;

  public Rating() {}
  public Rating(int userId, int difficultId) {
    this.userId = userId;
    this.difficultId = difficultId;
  }

  public Rating(int id, int userId, int difficultId) {
    this.id=id;
    this.userId = userId;
    this.difficultId = difficultId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getDifficultId() {
    return difficultId;
  }

  public void setDifficultId(int difficultId) {
    this.difficultId = difficultId;
  }
}
