package ua.synkulych.sort_it.dao.abstraction;

import ua.synkulych.sort_it.dao.entity.Color;

public abstract class ColorDAO {

  public abstract Color getColorById(int id);
  public abstract boolean updateColorById(int id);
  public abstract boolean deleteColorById(String value);
  public abstract boolean addColor(String value);
}
