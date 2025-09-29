package model;

public final class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String id, String numero, double solde, String idClient ){
        super(id, numero, solde, idClient);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
