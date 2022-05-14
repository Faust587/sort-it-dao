package ua.synkulych.sort_it.dao.entity;

public class Color {

  private String value;
  private int id;

  public Color() {}

  public Color (String value) {
    this.value = value;
  }
  public Color (int id, String value) {
    this.value = value;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
