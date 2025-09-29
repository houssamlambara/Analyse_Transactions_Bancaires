package DAO;

import model.Compte;
import java.sql.SQLException;
import java.util.List;

public interface CompteDAO {

    void create(Compte compte) throws SQLException;
    void update(Compte compte) throws SQLException;
    void delete(String id) throws SQLException;
    List<Compte> findByClient(String idClient) throws SQLException;
    Compte findByNumero(String numero) throws SQLException;
}
