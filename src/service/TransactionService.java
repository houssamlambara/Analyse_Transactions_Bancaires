package service;

import DAO.TransactionDAO;
import DAO.TransactionDAOImpl;
import model.Transaction;

import java.sql.*;

public class TransactionService {

    private TransactionDAOImpl transactionDAO = new TransactionDAOImpl();


    public void ajouterTransaction(Transaction transaction) throws SQLException {
        transactionDAO.create(transaction);
    }

    public void modifierTransaction(Transaction transaction) throws SQLException {
        transactionDAO.update(transaction);
    }

    public void supprimerTransaction(String id) throws SQLException {
        transactionDAO.delete(id);
    }
}
