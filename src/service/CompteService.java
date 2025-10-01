package service;

import DAO.CompteDAO;
import DAO.CompteDAOImpl;
import model.Client;
import model.Compte;
import config.Connexion;

import java.sql.*;
import java.util.List;

public class CompteService {

    private CompteDAOImpl compteDAO = new CompteDAOImpl();


    public void ajouterCompte(Compte compte) throws SQLException {
        compteDAO.create(compte);
    }

    public void modifierCompte(Compte compte) throws SQLException {
        compteDAO.update(compte);
    }

    public void supprimerCompte(String id) throws SQLException {
        compteDAO.delete(id);
    }

    public List<Compte> getAll() throws SQLException{
        return compteDAO.findAll();
    }

    public List<Compte> getByClient(String idClient) throws SQLException {
        return compteDAO.findByClient(idClient);
    }

    public Compte getByNumero(String numero) throws SQLException {
        return compteDAO.findByNumero(numero);
    }

    public Compte getById(String id) throws SQLException {
        return compteDAO.findById(id);
    }

}
