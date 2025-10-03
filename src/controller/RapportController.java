package controller;

import service.RapportService;
import enums.TypeTransaction;
import model.Client;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RapportController {

    private final RapportService rapportService = new RapportService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuRapports() {
        int choix = 0;
        do {
            try {
            System.out.println("\n===== MENU RAPPORTS =====");
            System.out.println("1. Top 5 des clients par solde");
            System.out.println("2. Rapport mensuel (transactions par type)");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> afficherTop5Clients();
                case 2 -> afficherRapportMensuel();
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer uniquement des chiffres !");
            scanner.nextLine();
        }
        } while (choix != 0);
    }

    private void afficherTop5Clients() {
        try {
            var top5 = rapportService.top5ClientsParSolde();
            System.out.println("\n--- Top 5 clients par solde ---");
            for (var entry : top5) {
                Client client = entry.getKey();
                double solde = entry.getValue();
                System.out.println(client.nom() + " (" + client.email() + ") - Solde total : " + solde);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la génération du rapport : " + e.getMessage());
        }
    }

    private void afficherRapportMensuel() {
        try {
            System.out.print("Entrez le mois (1-12) : ");
            int mois = scanner.nextInt();
            System.out.print("Entrez l'année : ");
            int annee = scanner.nextInt();

            var rapport = rapportService.rapportMensuel(mois, annee);

            System.out.println("\n--- Rapport du mois " + mois + "/" + annee + " ---");
            for (Map.Entry<TypeTransaction, Map<String, Double>> entry : rapport.entrySet()) {
                System.out.println(entry.getKey() + " -> Nombre : " + entry.getValue().get("nombre")
                        + ", Total : " + entry.getValue().get("total"));
            }
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer uniquement des chiffres !");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la génération du rapport : " + e.getMessage());
        }
    }
}
