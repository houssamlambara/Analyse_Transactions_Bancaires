package DAO;

import model.Client;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {

    void create(Client client) throws SQLException;
    void update(Client client) throws SQLException;
    void delete(String id) throws SQLException;
    Client findById(String id) throws SQLException;
    List<Client> findAll() throws SQLException;
}
