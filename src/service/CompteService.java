package service;

import DAO.CompteDAO;
import DAO.CompteDAOImpl;
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

    public List<Compte> getComptesParClient(String idClient) throws SQLException {
        return compteDAO.findByClient(idClient);
    }

    public Compte getCompteParNumero(String numero) throws SQLException {
        return compteDAO.findByNumero(numero);
    }
}
