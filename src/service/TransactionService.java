package service;

import DAO.TransactionDAO;
import DAO.TransactionDAOImpl;
import enums.TypeTransaction;
import model.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

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

    public List<Transaction> filterByMontant(double montant) throws SQLException{
        return transactionDAO.findAll();
    }

    public List<Transaction> filterByType(TypeTransaction type) throws SQLException {
        return transactionDAO.findAll();
    }

    public List<Transaction> filterByDate(LocalDate date) throws SQLException{
        return transactionDAO.findAll();
    }

    public List<Transaction> filterByLieu(String lieu) throws SQLException{
        return transactionDAO.findAll();
    }

}
