package ua.synkulych.sort_it.dao.abstraction;

import ua.synkulych.sort_it.dao.entity.Difficult;

import java.util.ArrayList;

public abstract class DifficultDAO {

  public abstract Difficult getDifficultById(int id);
  public abstract ArrayList<Difficult> getDifficultList();
  public abstract boolean updateDifficultById(int id, int height, int weight, int points);
  public abstract boolean deleteDifficultById(int id);
  public abstract boolean addDifficultById(int height, int weight, int points);
}
