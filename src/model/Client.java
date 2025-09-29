package model;

public class Client {
    private String id;
    private String nom;
    private String email;


    public Client(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
