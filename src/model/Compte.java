package model;

public abstract sealed class Compte permits CompteCourant, CompteEpargne {

    private final String id;
    private final String numero;
    private Double solde;
    private final String idClient;

    public Compte(String id, String numero, double solde, String idClient) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
        this.idClient = idClient;
    }

    public String getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public String getIdClient() {
        return idClient;
    }
}
