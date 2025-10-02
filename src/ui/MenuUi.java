package ui;

import java.util.Scanner;

public class MenuUi {

    private final Scanner scanner = new Scanner(System.in);

    public int menuPrincipal() {
        System.out.println("==== Al Baraka Banque ====");
        System.out.println("1. Gestion des clients");
        System.out.println("2. Gestion des comptes");
        System.out.println("3. Gestion des transactions");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public Integer menuClients() {
        System.out.println("=== Gestion des Clients ===");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Modifier un client");
        System.out.println("3. Supprimer un client");
        System.out.println("4. Rechercher un client par ID");
        System.out.println("5. Rechercher un client par nom");
        System.out.println("6. Afficher tous les clients");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public Integer menuComptes() {
        System.out.println("=== Gestion des Comptes ===");
        System.out.println("1. Créer un compte courant");
        System.out.println("2. Créer un compte épargne");
        System.out.println("3. Modifier un compte");
        System.out.println("4. Supprimer un compte");
        System.out.println("5. Rechercher par client");
        System.out.println("6. Rechercher par numéro");
        System.out.println("7. Compte avec solde max");
        System.out.println("8. Compte avec solde min");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public Integer menuTransactions() {
        System.out.println("=== Gestion des Transactions ===");
        System.out.println("1. Faire un versement");
        System.out.println("2. Faire un retrait");
        System.out.println("3. Faire un virement");
        System.out.println("4. Afficher toutes les transactions");
        System.out.println("5. Afficher les transactions par compte");
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
