package controller;

import enums.TypeTransaction;
import model.Compte;
import model.Transaction;
import service.CompteService;
import service.TransactionService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TransactionController {


    private final TransactionService transactionService = new TransactionService();
    private final CompteService compteService = new CompteService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuTransactions() {
        int choix;
        do {
            System.out.println("=== Gestion des Transactions ===");
            System.out.println("1. Faire un versement");
            System.out.println("2. Faire un retrait");
            System.out.println("3. Faire un virement");
            System.out.println("4. Afficher toutes les transactions");
            System.out.println("5. Afficher les transactions par compte");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> versement();
                case 2 -> retrait();
                case 3 -> virement();
                case 4 -> afficherToutesTransactions();
                case 5 -> afficherTransactionsParCompte();
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    public void versement() {
        System.out.print("ID Compte : ");
        String idCompte = scanner.nextLine();

        System.out.print("Montant du versement : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();

        try {
            Compte compte = compteService.getById(idCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé !");
                return;
            }

            // Mise à jour du solde
            compte.setSolde(compte.getSolde() + montant);
            compteService.modifierCompte(compte);

            // Enregistrement de la transaction
            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDate.now(),
                    montant,
                    TypeTransaction.VERSEMENT,
                    lieu,
                    idCompte
            );
            transactionService.ajouterTransaction(transaction);

            System.out.println("Versement effectué avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors du versement : " + e.getMessage());
        }
    }

    public void retrait() {
        System.out.print("ID Compte : ");
        String idCompte = scanner.nextLine();

        System.out.print("Montant du retrait : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();

        try {
            Compte compte = compteService.getById(idCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé !");
                return;
            }

            if (compte.getSolde() < montant) {
                System.out.println("Solde insuffisant !");
                return;
            }

            // Mise à jour du solde
            compte.setSolde(compte.getSolde() - montant);
            compteService.modifierCompte(compte);

            // Enregistrement de la transaction
            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDate.now(),
                    montant,
                    TypeTransaction.RETRAIT,
                    lieu,
                    idCompte
            );
            transactionService.ajouterTransaction(transaction);

            System.out.println("Retrait effectué avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors du retrait : " + e.getMessage());
        }
    }

    public void virement() {
        System.out.print("ID Compte source : ");
        String idCompteSource = scanner.nextLine();

        System.out.print("ID Compte destinataire : ");
        String idCompteDest = scanner.nextLine();

        System.out.print("Montant du virement : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();

        try {
            Compte compteSource = compteService.getById(idCompteSource);
            Compte compteDest = compteService.getById(idCompteDest);

            if (compteSource == null || compteDest == null) {
                System.out.println("Un des comptes est introuvable !");
                return;
            }

            if (compteSource.getSolde() < montant) {
                System.out.println("Solde insuffisant dans le compte source !");
                return;
            }

            // Mise à jour des soldes
            compteSource.setSolde(compteSource.getSolde() - montant);
            compteDest.setSolde(compteDest.getSolde() + montant);
            compteService.modifierCompte(compteSource);
            compteService.modifierCompte(compteDest);

            // Enregistrement des transactions (débit et crédit)
            Transaction debit = new Transaction(
                    UUID.randomUUID().toString() + "-Debit",
                    LocalDate.now(),
                    montant,
                    TypeTransaction.VIREMENT,
                    lieu,
                    idCompteSource
            );
            Transaction credit = new Transaction(
                    UUID.randomUUID().toString() + "-Credit",
                    LocalDate.now(),
                    montant,
                    TypeTransaction.VIREMENT,
                    lieu,
                    idCompteDest
            );

            transactionService.ajouterTransaction(debit);
            transactionService.ajouterTransaction(credit);

            System.out.println("Virement effectué avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors du virement : " + e.getMessage());
        }
    }

    private void afficherToutesTransactions() {
        try {
            List<Transaction> transactions = transactionService.findAll();
            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction trouvée !");
                return;
            }

            System.out.println("=== Liste des transactions ===");
            for (Transaction t : transactions) {
                System.out.println("ID : " + t.id() +
                        " | Date : " + t.date() +
                        " | Montant : " + t.montant() +
                        " | Type : " + t.type() +
                        " | Lieu : " + t.lieu() +
                        " | Compte : " + t.idCompte());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des transactions : " + e.getMessage());
        }
    }

    private void afficherTransactionsParCompte() {
        System.out.print("ID Compte : ");
        String idCompte = scanner.nextLine();

        try {
            List<Transaction> transactions = transactionService.findByCompte(idCompte);
            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction trouvée pour ce compte !");
                return;
            }

            System.out.println("=== Transactions du compte " + idCompte + " ===");
            for (Transaction t : transactions) {
                System.out.println("ID : " + t.id() +
                        " | Date : " + t.date() +
                        " | Montant : " + t.montant() +
                        " | Type : " + t.type() +
                        " | Lieu : " + t.lieu());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des transactions : " + e.getMessage());
        }
    }

    public void ajouterTransaction() { }
    public void modifierTransaction() { }
    public void supprimerTransaction() { }
    public void filterByMontant() { }
    public void filterByType() { }
    public void filterByDate() { }
    public void filterByLieu() { }
}
