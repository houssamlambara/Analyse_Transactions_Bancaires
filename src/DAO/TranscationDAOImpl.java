package DAO;

import model.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TranscationDAOImpl implements TransactionDAO{
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
