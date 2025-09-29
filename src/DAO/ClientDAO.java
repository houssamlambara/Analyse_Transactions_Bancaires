package DAO;


import config.Connexion;
import model.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Client client) throws SQLException {

    }

    public void update(Client client) throws SQLException {

    }

    public void delete(String id) throws SQLException {

    }

    public Client findById(String id) throws SQLException {

        return null;
    }

    public List<Client> findAll() throws SQLException{

        return new ArrayList<>();
    }

















    }
