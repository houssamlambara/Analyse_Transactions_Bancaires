package DAO;

import config.Connexion;
import enums.TypeTransaction;
import model.Compte;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO{

    private Connection connection;

    public TransactionDAOImpl() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public void create(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction (id, date, montant, type, lieu, idCompte) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaction.id());
            stmt.setDate(2, java.sql.Date.valueOf(transaction.date()));
            stmt.setDouble(3, transaction.montant());
            stmt.setString(4, transaction.type().name());
            stmt.setString(5, transaction.lieu());
            stmt.setString(6, transaction.idCompte());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Transaction transaction) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public List<Transaction> findAll() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction ORDER BY date DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getString("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getDouble("montant"),
                        TypeTransaction.valueOf(rs.getString("type")),
                        rs.getString("lieu"),
                        rs.getString("idCompte")
                ));
            }
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByCompte(String idCompte) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE idCompte = ? ORDER BY date DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idCompte);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new Transaction(
                            rs.getString("id"),
                            rs.getDate("date").toLocalDate(),
                            rs.getDouble("montant"),
                            TypeTransaction.valueOf(rs.getString("type")),
                            rs.getString("lieu"),
                            rs.getString("idCompte")
                    ));
                }
            }
        }
        return transactions;
    }
}
