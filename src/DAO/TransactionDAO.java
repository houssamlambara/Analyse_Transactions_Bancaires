package DAO;

import model.Transaction;
import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO {

    void create(Transaction transaction) throws SQLException;
    void update(Transaction transaction) throws SQLException;
    void delete(String id) throws SQLException;
    List<Transaction> findByCompte(String idCompte) throws SQLException;
    List<Transaction> findAll() throws SQLException;
}
