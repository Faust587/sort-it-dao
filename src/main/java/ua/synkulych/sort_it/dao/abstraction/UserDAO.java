package ua.synkulych.sort_it.dao.abstraction;

import ua.synkulych.sort_it.dao.entity.User;

import java.util.List;

public abstract class UserDAO {

  public abstract List<User> getUserList();
  public abstract User getUserByUsername(String username);

  public abstract boolean updateUsername(String username, String password, String value);

  public abstract boolean updatePassword(String username, String password, String value);

  public abstract boolean deleteUser(String username, String password);

  public abstract boolean addNewUser(String username, String password);
}
