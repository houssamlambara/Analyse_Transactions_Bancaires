package DAO;

import config.Connexion;
import model.Compte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAOImpl implements CompteDAO{

    private Connection connection;


    public CompteDAOImpl() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public void create(Compte compte) throws SQLException {

    }

    @Override
    public void update(Compte compte) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public List<Compte> findByClient(String idClient) throws SQLException {
        return List.of();
    }

    @Override
    public Compte findByNumero(String numero) throws SQLException {
        return null;
    }
}
