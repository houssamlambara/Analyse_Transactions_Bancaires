package ui;

import java.util.Scanner;

public class MenuUi {
    Scanner scanner = new Scanner(System.in);

    public Integer menuClients() {
        System.out.println("=== Gestion des Clients ===");
        System.out.println("1. Créer un client");
        System.out.println("2. Lister les clients");
        System.out.println("3. Rechercher un client");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public Integer menuComptes() {
        System.out.println("=== Gestion des Comptes ===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Lister les comptes");
        System.out.println("3. Compte solde max/min");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public Integer menuTransactions() {
        System.out.println("=== Gestion des Transactions ===");
        System.out.println("1. Enregistrer un versement");
        System.out.println("2. Enregistrer un retrait");
        System.out.println("3. Enregistrer un virement");
        System.out.println("4. Historique transactions");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public Integer menuRapports() {
        System.out.println("=== Rapports ===");
        System.out.println("1. Top 5 clients par solde");
        System.out.println("2. Rapport mensuel");
        System.out.println("3. Transactions suspectes");
        System.out.println("4. Comptes inactifs");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }
}
