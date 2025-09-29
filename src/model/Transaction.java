package model;

import enums.TypeTransaction;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private LocalDateTime date;
    private double montant;
    private TypeTransaction type;
    private String lieu;
    private String idCompte;

    public Transaction(String id, LocalDateTime date, Double montant, TypeTransaction type, String lieu, String idCompte) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.lieu = lieu;
        this.idCompte = idCompte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }
}

