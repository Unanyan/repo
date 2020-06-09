package am.vtc.chat.service.impl;

import am.vtc.chat.exception.DatabaseException;
import am.vtc.chat.model.Shipper;
import am.vtc.chat.repo.Repo;
import am.vtc.chat.repo.impl.ShipperRepoSql;
import am.vtc.chat.service.Service;

import java.sql.SQLException;
import java.util.Optional;

public class ShipperServiceImpl implements Service<Shipper> {

  private final Repo shipperRepo;

  public ShipperServiceImpl() {
    this.shipperRepo = new ShipperRepoSql();
  }

  @Override
  public boolean exist(String email) throws DatabaseException {
    try {
      return this.shipperRepo.exist(email);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void save(Shipper shipper) throws DatabaseException {
    try {
      if (shipper.getId() > 0) {
        //todo update
      } else {
        this.shipperRepo.insert(shipper);
      }
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public Optional<Shipper> getEntity(String email, String password) throws DatabaseException {
    try {
      return this.shipperRepo.findByEmailAndPassword(email, password);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public Optional<Shipper> findById(int id) throws SQLException {
    return this.shipperRepo.findById(id);
  }
}
