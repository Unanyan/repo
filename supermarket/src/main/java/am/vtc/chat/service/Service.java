package am.vtc.chat.service;

import am.vtc.chat.exception.DatabaseException;

import java.sql.SQLException;
import java.util.Optional;

public interface Service<T> {

  boolean exist(String unique) throws DatabaseException;

  void save(T t) throws DatabaseException;

  Optional<T> getEntity(String email, String password) throws DatabaseException;

  Optional<T> findById(int id) throws SQLException;
}
