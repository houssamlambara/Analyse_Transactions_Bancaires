package controller;

import java.util.Scanner;

public class MainController {

    private Scanner scanner = new Scanner(System.in);

    public MainController() {
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Menu Principal Gestion Bancaire ===");
            System.out.println("1. Gestion des clients");
            System.out.println("2. Gestion des comptes");
            System.out.println("3. Gestion des transactions");
            System.out.println("4. Rapports");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    menuClients();
                    break;
                case 2:
                    menuComptes();
                    break;
                case 3:
                    menuTransactions();
                    break;
                case 4:
                    menuRapports();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé l'application !");
                    return; // quitte la méthode start()
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    private void menuClients() {
        System.out.println("\n--- Menu Gestion des Clients ---");
        System.out.println("1. Créer un client");
        System.out.println("2. Lister les clients");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();
        switch (choix) {
            case 1:
                System.out.println("Création client...");
                break;
            case 2:
                System.out.println("Liste des clients...");
                break;
            case 0:
                System.out.println("Retour au menu principal...");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    private void menuComptes() {
        System.out.println("\n--- Menu Gestion des Comptes ---");
        System.out.println("1. Créer un compte");
        System.out.println("2. Lister les comptes");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();
        switch (choix) {
            case 1:
                System.out.println("Création compte...");
                break;
            case 2:
                System.out.println("Liste des comptes...");
                break;
            case 0:
                System.out.println("Retour au menu principal...");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    private void menuTransactions() {
        System.out.println("\n--- Menu Gestion des Transactions ---");
        System.out.println("1. Effectuer un versement");
        System.out.println("2. Effectuer un retrait");
        System.out.println("3. Effectuer un virement");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();
        switch (choix) {
            case 1:
                System.out.println("Versement...");
                break;
            case 2:
                System.out.println("Retrait...");
                break;
            case 3:
                System.out.println("Virement...");
                break;
            case 0:
                System.out.println("Retour au menu principal...");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    private void menuRapports() {
        System.out.println("\n--- Menu Rapports ---");
        System.out.println("1. Rapport des transactions d’un compte");
        System.out.println("2. Rapport global des clients et comptes");
        System.out.println("0. Retour");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();
        switch (choix) {
            case 1:
                System.out.println("Rapport compte...");
                break;
            case 2:
                System.out.println("Rapport global...");
                break;
            case 0:
                System.out.println("Retour au menu principal...");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }
}
