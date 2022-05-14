package ua.synkulych.sort_it.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <Entity, Key> {
  Optional<Entity> get(Key key);
  List<Entity> getAll();
  Entity save(Entity entity);
  boolean update(Entity entity);
  boolean delete(Key key);
}
