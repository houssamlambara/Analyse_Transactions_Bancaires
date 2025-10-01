package DAO;

import com.mysql.cj.protocol.Resultset;
import config.Connexion;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAOImpl implements CompteDAO{

    private Connection connection;


    public CompteDAOImpl() {
        this.connection = Connexion.getInstance().getConnection();
    }

    @Override
    public void create(Compte compte) throws SQLException {
        String sql = "INSERT INTO compte(id, numero, solde, idClient, typeCompte, decouvert, tauxInteret) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, compte.getId());
            stmt.setString(2, compte.getNumero());
            stmt.setDouble(3, compte.getSolde());
            stmt.setString(4, compte.getIdClient());

            if (compte instanceof CompteCourant courant){
                stmt.setString(5, "courant");
                stmt.setDouble(6, courant.getDecouvert());
                stmt.setNull(7, Types.DOUBLE);
        } else if (compte instanceof CompteEpargne epargne) {
                stmt.setString(5,"epargne");
                stmt.setNull(6, Types.DOUBLE);
                stmt.setDouble(7, epargne.getTauxInteret());
        }
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Compte compte) throws SQLException {
        if (compte instanceof CompteCourant courant) {
            String sql = "UPDATE compte SET solde = ?, decouvert = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDouble(1, compte.getSolde());
                stmt.setDouble(2, courant.getDecouvert());
                stmt.setString(3, compte.getId());
                stmt.executeUpdate();
            }
        } else if (compte instanceof CompteEpargne epargne) {
            String sql = "UPDATE compte SET solde = ?, tauxInteret = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDouble(1, compte.getSolde());
                stmt.setDouble(2, epargne.getTauxInteret());
                stmt.setString(3, compte.getId());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM compte where id =?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            int rows = stmt.executeUpdate();
            if(rows >0){
                System.out.println("Compte supprimé avec succes !");
            }else {
                System.out.println("Aucun compte trouvé avec cet ID !");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compte> findAll() throws SQLException{
        String sql = "SELECT * FROM compte";
        List<Compte> comptes = new ArrayList<>();
        try (Statement stmt = connection.createStatement()){
            ResultSet resultat = stmt.executeQuery(sql);
            while (resultat.next()){
                comptes.add(mapToCompte(resultat));
            }
        }
        return comptes;
    }

    @Override
    public List<Compte> findByClient(String idClient) throws SQLException {
            String sql = "SELECT * FROM compte where idclient =?";
            List<Compte> comptes = new ArrayList<>();

            try (PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setString(1, idClient);
                ResultSet resultat = stmt.executeQuery();

                while (resultat.next()) {
                    comptes.add(mapToCompte(resultat));
                }
            }
        return comptes;
    }

    @Override
    public Compte findByNumero(String numero) throws SQLException {
        String sql = "SELECT * FROM compte WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, numero);
            ResultSet resultat = stmt.executeQuery();

            if (resultat.next()){
                return mapToCompte(resultat);
            }
        }
        return null;
    }

    @Override
    public Compte findById(String id) throws SQLException {
        String sql = "SELECT * FROM compte WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet resultat = stmt.executeQuery();

            if (resultat.next()) {
                String numero = resultat.getString("numero");
                double solde = resultat.getDouble("solde");
                String idClient = resultat.getString("idClient");
                String typeCompte = resultat.getString("typeCompte");

                if ("courant".equalsIgnoreCase(typeCompte)) {
                    double decouvert = resultat.getDouble("decouvert");
                    CompteCourant courant = new CompteCourant(id, numero, solde, idClient);
                    courant.setDecouvert(decouvert);
                    return courant;
                } else if ("epargne".equalsIgnoreCase(typeCompte)) {
                    double tauxInteret = resultat.getDouble("tauxInteret");
                    return new CompteEpargne(id, numero, solde, idClient, tauxInteret);
                }
            }
            return null;
        }
    }

    private Compte mapToCompte(ResultSet resultat) throws SQLException {
        String id = resultat.getString("id");
        String numero = resultat.getString("numero");
        double solde = resultat.getDouble("solde");
        String idClient = resultat.getString("idClient");
        String type = resultat.getString("typeCompte");

        if ("courant".equals(type)) {
            double decouvert = resultat.getDouble("decouvert");
            CompteCourant c = new CompteCourant(id, numero, solde, idClient);
            c.setDecouvert(decouvert);
            return c;
        } else if ("epargne".equals(type)) {
            double taux = resultat.getDouble("tauxInteret");
            return new CompteEpargne(id, numero, solde, idClient, taux);
        }
        return null;
    }

}
