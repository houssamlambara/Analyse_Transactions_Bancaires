package DAO;

import model.Compte;
import model.CompteCourant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {

    private Connection connection;

    public CompteDAO(Connection connection){
        this.connection = connection;
    }

    public void create(Compte compte) throws SQLException{

    }

    public void update(Compte compte) throws SQLException{

    }

    public void delete(String id) throws SQLException{

    }

    public List<Compte> findById(String idClient) throws SQLException {

        return new ArrayList<>();
    }

    public Compte findByNumero(String numero) throws SQLException {

        return null;
    }


}
