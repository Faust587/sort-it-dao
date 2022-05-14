package ua.synkulych.sort_it;

import ua.synkulych.sort_it.dao.entity.User;
import ua.synkulych.sort_it.dao.factory.DAOFactory;

import java.util.Optional;

public class ProgramRunner {

  public static void main(String[] args) {
    Optional<User> user = DAOFactory.getDAOFactory("MYSQL").getUserDAO().get("Faust");
    System.out.println(user.get().getUsername() + " | " +  user.get().getPassword());
  }
}
