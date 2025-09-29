package DAO;

import config.Connexion;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    private Connection connection;


    public ClientDAOImpl() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO client(id, nom, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.id());
            stmt.setString(2, client.nom());
            stmt.setString(3, client.email());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Client client) throws SQLException {
        String sql = "UPDATE client SET nom = ?, email = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.nom());
            stmt.setString(2, client.email());
            stmt.setString(3, client.id());
            int rows = stmt.executeUpdate();
            if (rows > 0){
                System.out.println("Client mis a jour avec succes !");
            }else {
                System.out.println("Aucun client trouvé avec cette ID !");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE from client where id = ?";
        try (PreparedStatement stmt =connection.prepareStatement(sql)){
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            if(rows >0){
                System.out.println("Client supprimer avec succes !");
            }else {
                System.out.println("Aucun client trouvé avec cet ID !");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client findById(String id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet resultat = stmt.executeQuery()) {
                if (resultat.next()) {
                    return new Client(
                            resultat.getString("id"),
                            resultat.getString("nom"),
                            resultat.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultat = stmt.executeQuery()) {
            while (resultat.next()) {
                clients.add(new Client(
                        resultat.getString("id"),
                        resultat.getString("nom"),
                        resultat.getString("email")
                ));
            }
        }
        return clients;
    }

    @Override
    public Client findByName(String nom) throws SQLException {
        String sql = "SELECT * FROM client WHERE UPPER(nom) = UPPER(?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nom);
            try (ResultSet resultat = stmt.executeQuery()) {
                if (resultat.next()) {
                    return new Client(
                            resultat.getString("id"),
                            resultat.getString("nom"),
                            resultat.getString("email")
                    );
                }
            }
        }
        return null;
    }
}
