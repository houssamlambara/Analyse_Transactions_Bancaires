package service;

import DAO.ClientDAO;
import DAO.ClientDAOImpl;
import model.Client;
import config.Connexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDAOImpl clientDAO = new ClientDAOImpl();


    public void ajouterClient(Client client) throws SQLException {
        clientDAO.create(client);
    }

    public void modifierClient(Client client) throws SQLException {
        clientDAO.update(client);
    }

    public void supprimerClient(String id) throws SQLException {
        clientDAO.delete(id);
    }

    public Client getClientById(String id) throws SQLException {
        return clientDAO.findById(id);
    }

    public Client getClientByName(String nom) throws SQLException {
        return clientDAO.findByName(nom); // 🔹 maintenant ça utilise bien findByName
    }

    public List<Client> getAll() throws SQLException {
        return clientDAO.findAll();
    }
}
