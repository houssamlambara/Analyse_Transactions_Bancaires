package service;

import DAO.TransactionDAO;
import DAO.TransactionDAOImpl;
import enums.TypeTransaction;
import model.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionService {

    private TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
    private final CompteService compteService = new CompteService();


    public void ajouterTransaction(Transaction transaction) throws SQLException {
        transactionDAO.create(transaction);
    }

    public void modifierTransaction(Transaction transaction) throws SQLException {
        transactionDAO.update(transaction);
    }

    public void supprimerTransaction(String id) throws SQLException {
        transactionDAO.delete(id);
    }

    public List<Transaction> findAll() throws SQLException {
        return transactionDAO.findAll();
    }

    public List<Transaction> findByCompte(String idCompte) throws SQLException {
        return transactionDAO.findByCompte(idCompte);
    }

    public List<Transaction> filterByMontant(double montant) throws SQLException{
        return findAll().stream()
                .filter(transaction -> transaction.montant() >= montant)
                .toList();

    }

    public List<Transaction> filterByType(TypeTransaction type) throws SQLException {
        return findAll().stream()
                .filter( transaction -> transaction.type() == type)
                .toList();
    }

    public List<Transaction> filterByDate(LocalDate date) throws SQLException{
        return findAll().stream()
                .filter(transaction -> transaction.date().equals(date))
                .toList();
    }

    public List<Transaction> filterByLieu(String lieu) throws SQLException{
        return findAll().stream()
                .filter(transaction -> transaction.lieu() != null == transaction.lieu().equalsIgnoreCase(lieu))
                        .toList();
    }

    public Map<TypeTransaction, List<Transaction>> groupByType() throws SQLException {
        return findAll().stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    public double totalTransactionsByCompte(String idCompte) throws SQLException {
        return findByCompte(idCompte).stream()
                .mapToDouble(Transaction::montant)
                .sum();
    }

    public double moyenneTransactionsByCompte(String idCompte) throws SQLException {
        return findByCompte(idCompte).stream()
                .mapToDouble(Transaction::montant)
                .average()
                .orElse(0); // si aucune transaction, retourne 0
    }

    public double totalTransactionsByClient(String idClient) throws SQLException {
        return compteService.getByClient(idClient).stream()
                .flatMap(compte -> {
                    try {
                        return findByCompte(compte.getId()).stream();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .mapToDouble(Transaction::montant)
                .sum();
    }

    public double moyenneTransactionsByClient(String idClient) throws SQLException {
        return compteService.getByClient(idClient).stream()
                .flatMap(compte -> {
                    try {
                        return findByCompte(compte.getId()).stream();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .mapToDouble(Transaction::montant)
                .average()
                .orElse(0);
    }
}
