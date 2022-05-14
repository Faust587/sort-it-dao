package ua.synkulych.sort_it.dao.entity;

public class Difficult {
  private int height;
  private int weight;
  private int points;
  private int id;

  public Difficult() {}
  public Difficult(int height, int weight, int points) {
    this.height = height;
    this.weight = weight;
    this.points = points;
  }

  public Difficult(int id, int height, int weight, int points) {
    this.id = id;
    this.height = height;
    this.weight = weight;
    this.points = points;
  }

  public int getPoints() {
    return points;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setPoints(int points) {
    this.points = points;
  }
  public int getHeight() {
    return height;
  }
  public void setHeight(int height) {
    this.height = height;
  }
  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }
}
