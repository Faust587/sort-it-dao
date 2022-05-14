package ua.synkulych.sort_it;

import ua.synkulych.sort_it.dao.abstraction.UserDAO;
import ua.synkulych.sort_it.dao.entity.User;
import ua.synkulych.sort_it.dao.factory.DAOFactory;
import ua.synkulych.sort_it.dao.factory.MysqlDAOFactory;

public class ProgramRunner {

  public static void main(String[] args) {
    User user = DAOFactory.getDAOFactory("MYSQL").getUserDAO().getUserByUsername("Faust");
    System.out.println(user.getPassword() + " | " +  user.getUsername());
  }
}
