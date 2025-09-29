package controller;

import model.Client;
import java.util.Scanner;
import service.ClientService;
import service.CompteService;
import service.TransactionService;
import ui.MenuUi;

import java.sql.SQLException;

public class MainController {

    private final MenuUi menu;
    private final ClientService clientService = new ClientService();
    private final CompteService compteService = new CompteService();
    private final TransactionService transactionService = new TransactionService();

    public MainController(MenuUi menu) {
        this.menu = menu;
    }

    public void menuClients() {
        int choix;
        do {
            choix = menu.menuClients();
            switch (choix) {
                case 1 -> ajouterClient();
                case 2 -> modifierClient();
                case 3 -> supprimerClient();
                case 4 -> System.out.println("Afficher tout les clients"); // appeler clientService.getAll(...)
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    public void menuComptes() {
        int choix;
        do {
            choix = menu.menuComptes();
            switch (choix) {
                case 1 -> System.out.println("Créer compte Courant");
                case 2 -> System.out.println("Créer compte Epargne");
                case 3 -> System.out.println("Afficher tout les Compte");
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    public void menuTransactions() {
        int choix;
        do {
            choix = menu.menuTransactions();
            switch (choix) {
                case 1 -> System.out.println("Ajouter transaction");
                case 2 -> System.out.println("Afficher transact33333ions d’un compte");
                case 3 -> System.out.println("Afficher transactions d’un client");
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    public void ajouterClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID : ");
        String id = scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        Client client = new Client(id, nom, email);

        try {
            clientService.ajouterClient(client);
            System.out.println("Client ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l’ajout : " + e.getMessage());
        }
    }

    public void modifierClient() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Entrer l'ID du client à modifier :");
            String id = scanner.nextLine().trim();

            System.out.println("Entrer le nouveau nom du client :");
            String nom = scanner.nextLine();

            System.out.println("Entrer le nouvel email du client :");
            String email = scanner.nextLine();

            Client client = new Client(id, nom, email); // créer l'objet client avec les nouvelles infos
            clientService.modifierClient(client);       // appeler le service pour mettre à jour

            System.out.println("Client mis à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    private void supprimerClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Supprimer un client ===");
        System.out.print("Entrez l'ID du client à supprimer : ");
        String id = scanner.nextLine().trim();

        try {
            clientService.supprimerClient(id);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }
}
