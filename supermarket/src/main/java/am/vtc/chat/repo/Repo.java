package am.vtc.chat.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repo<T> {

  void insert(T t) throws SQLException;

  Optional<T> findById(int id) throws SQLException;

  boolean exist(String unique) throws SQLException;

  Optional<T> findByEmailAndPassword(String email, String password) throws SQLException;

  List<T> findAll(int start, int count) throws SQLException;

  List<T> findAll(int count) throws SQLException;

  long count() throws SQLException;
}
