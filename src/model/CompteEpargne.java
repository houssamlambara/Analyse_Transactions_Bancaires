package model;

public final class CompteEpargne extends Compte{
    private Double tauxInteret;

    public CompteEpargne(String id, String numero, Double solde, String idClient, double tauxInteret) {
        super(id, numero, solde, idClient);
        this.tauxInteret = tauxInteret;
    }

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
