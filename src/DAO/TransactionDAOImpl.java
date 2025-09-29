package DAO;

import config.Connexion;
import model.Compte;
import model.Transaction;

import java.sql.*;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO{

    private Connection connection;

    public TransactionDAOImpl() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public void create(Transaction transaction) throws SQLException {

    }

    @Override
    public void update(Transaction transaction) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public List<Transaction> findByCompte(String idCompte) throws SQLException {
        return List.of();
    }

    @Override
    public List<Transaction> findAll() throws SQLException {
        return List.of();
    }
}
