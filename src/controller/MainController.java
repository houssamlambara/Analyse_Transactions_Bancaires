package controller;

import service.ClientService;
import service.CompteService;
import service.TransactionService;
import ui.MenuUi;

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
                case 1 -> System.out.println("Ajouter client");   // appeler clientService.ajouterClient(...)
                case 2 -> System.out.println("Modifier client");  // appeler clientService.modifierClient(...)
                case 3 -> System.out.println("Supprimer client"); // appeler clientService.supprimerClient(...)
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
                case 2 -> System.out.println("Afficher transactions d’un compte");
                case 3 -> System.out.println("Afficher transactions d’un client");
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }
}
