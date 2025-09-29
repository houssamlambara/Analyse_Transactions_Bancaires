package DAO;

import model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class TransactionDAO {

    private Connection connection;

    public TransactionDAO(Connection connection){
        this.connection = connection;
    }

    public void create(Transaction transaction) throws SQLException{

    }

    public void update() throws SQLException{

    }

    public void delete() throws SQLException{

    }

    public List<Transaction> chercherParCompte(String idCompte) throws SQLException {

        return new ArrayList<>();
    }

    public List<Transaction> findAll () throws SQLException{

        return new ArrayList<>();
    }


}
