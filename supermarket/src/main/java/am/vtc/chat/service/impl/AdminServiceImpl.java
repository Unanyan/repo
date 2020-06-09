package am.vtc.chat.service.impl;

import am.vtc.chat.exception.DatabaseException;
import am.vtc.chat.model.Admin;
import am.vtc.chat.repo.Repo;
import am.vtc.chat.repo.impl.AdminRepoSql;
import am.vtc.chat.service.Service;

import java.sql.SQLException;
import java.util.Optional;

public class AdminServiceImpl implements Service<Admin> {
    private final Repo adminRepo;

    public AdminServiceImpl() {
        this.adminRepo = new AdminRepoSql();
    }
    @Override
    public boolean exist(String email) throws DatabaseException {
        try {
            return this.adminRepo.exist(email);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void save(Admin admin) throws DatabaseException {
        try {
            if (admin.getId() > 0) {
                //todo update
            } else {
                this.adminRepo.insert(admin);
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Admin> getEntity(String email, String password) throws DatabaseException {
        try {
            return this.adminRepo.findByEmailAndPassword(email, password);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Admin> findById(int id) throws SQLException {
        return Optional.empty();
    }
}
