package ua.synkulych.sort_it.dao.entity;

public class User {

  private String username;
  private String password;
  private Integer id;

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(Integer id, String username, String password) {
    this.id=id;
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
}
