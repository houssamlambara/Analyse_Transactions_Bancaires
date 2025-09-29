package DAO;

import model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public void create(Client client) throws SQLException {

    }

    @Override
    public void update(Client client) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public Client findById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Client> findAll() throws SQLException {
        return List.of();
    }
}
