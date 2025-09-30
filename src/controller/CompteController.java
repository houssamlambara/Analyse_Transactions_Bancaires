package controller;

import DAO.CompteDAO;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import service.CompteService;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CompteController {


    private final CompteService compteService = new CompteService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuComptes() {
        int choix;
        do {
            System.out.println("=== Gestion des Comptes ===");
            System.out.println("1. Créer un compte courant");
            System.out.println("2. Créer un compte épargne");
            System.out.println("3. Modifier un compte");
            System.out.println("4. Supprimer un compte");
            System.out.println("5. Rechercher par client");
            System.out.println("6. Rechercher par numéro");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le \n

            switch (choix) {
                case 1 -> ajouterCompteCourant();
                case 2 -> ajouterCompteEpargne();
                case 3 -> modifierCompte();
                case 4 -> supprimerCompte();
                case 5 -> getByClient();
                case 6 -> getByNumero();
                case 0 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    private void ajouterCompteCourant() {
        String id = UUID.randomUUID().toString();
        String numero = "CPT-" + (int)(Math.random() * 10000);
        System.out.print("Solde : ");
        double solde = scanner.nextDouble();
        System.out.print("Découvert : ");
        double decouvert = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("ID Client : ");
        String idClient = scanner.nextLine();

        CompteCourant compte = new CompteCourant(id, numero, solde, idClient);
        compte.setDecouvert(decouvert);

        try {
            compteService.ajouterCompte(compte);
            System.out.println("Compte courant ajouté !");
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void ajouterCompteEpargne(){
        String id = UUID.randomUUID().toString();
        String numero = "CPT-" + (int)(Math.random() * 10000);
        System.out.println("Solde :");
        double solde = scanner.nextDouble();
        System.out.println("Taux d'interet :");
        double taux = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("ID Client :");
        String idClient = scanner.nextLine();

        CompteEpargne compte = new CompteEpargne(id, numero, solde, idClient, taux);

        try {
            compteService.ajouterCompte(compte);
            System.out.println("Compte epargne ajouter !");
        }catch (SQLException e){
            System.out.println("Erreur :" + e.getMessage());
        }
    }


//    public void ajouterCompte() {
//        try {
//            System.out.println("Type de compte (1=courant, 2=epargne :");
//            int type = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Numero du Compte :");
//            String numero = scanner.nextLine();
//            System.out.println("Solde initial :");
//            double solde = scanner.nextDouble();
//            scanner.nextLine();
//            System.out.println("ID du Client :");
//            String idClient = scanner.nextLine();
//
//            Compte compte = null;
//            if (type == 1){
//                System.out.println("Decouvert autorisé :");
//                double decouvert = scanner.nextDouble();
//                scanner.nextLine();
//                CompteCourant courant = new CompteCourant(UUID.randomUUID().toString(), numero, solde, idClient);
//                courant.setDecouvert(decouvert);
//                compte = compte;
//            }else {
//                System.out.println("Taux d'interet :");
//                double taux = scanner.nextDouble();
//                scanner.nextLine();
//                compte = new CompteEpargne(UUID.randomUUID().toString(), numero, solde, idClient, taux);
//
//            }
//            compteService.ajouterCompte(compte);
//            System.out.println("Compte ajouté avec succes");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void modifierCompte() {
        System.out.println("Entrez l'ID du compte à modifier :");
        String id = scanner.nextLine();

        try {
            Compte compte = compteService.getById(id); // récupère le compte par ID
            if (compte == null) {
                System.out.println("Compte introuvable !");
                return;
            }

            System.out.println("Nouveau solde :");
            double solde = scanner.nextDouble();
            scanner.nextLine();
            compte.setSolde(solde);

            if (compte instanceof CompteCourant courant) {
                System.out.println("Nouveau découvert :");
                double decouvert = scanner.nextDouble();
                scanner.nextLine();
                courant.setDecouvert(decouvert);
            } else if (compte instanceof CompteEpargne epargne) {
                System.out.println("Nouveau taux d'intérêt :");
                double taux = scanner.nextDouble();
                scanner.nextLine();
                epargne.setTauxInteret(taux);
            }

            compteService.modifierCompte(compte);
            System.out.println("Compte modifié avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimerCompte() {
        System.out.println("Entrez L'ID du compte a supprime :");
        String id = scanner.nextLine();
        try {
            compteService.supprimerCompte(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getByClient() {
        System.out.print("Entrez l'ID du client : ");
        String idClient = scanner.nextLine();

        try {
            List<Compte> comptes = compteService.getByClient(idClient);
            if (comptes.isEmpty()) {
                System.out.println("Aucun compte trouvé pour ce client.");
            } else {
                comptes.forEach(c ->
                        System.out.println("ID: " + c.getId() +
                                ", Numéro: " + c.getNumero() +
                                ", Solde: " + c.getSolde() +
                                ", Client: " + c.getIdClient()));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void getByNumero() {
        System.out.print("Entrez le numéro du compte : ");
        String numero = scanner.nextLine();

        try {
            Compte compte = compteService.getByNumero(numero);
            if (compte == null){
                System.out.println("Aucun compte trouvé avec ce numéro.");
            } else {
                System.out.println("ID: " + compte.getId() +
                        ", Numéro: " + compte.getNumero() +
                        ", Solde: " + compte.getSolde() +
                        ", Client: " + compte.getIdClient());
            }
        } catch (SQLException e){
            System.out.println("Erreur : " + e.getMessage());
        }
    }

}
