package controller;

import ui.MenuUi;

public class MainController {

    private final MenuUi menu;
    private final ClientController clientController = new ClientController();
    private final CompteController compteController = new CompteController();
    private final TransactionController transactionController = new TransactionController();


    public MainController(MenuUi menu) {
        this.menu = menu;
    }

    public void demarrer() {
        int choix;
        do {
            choix = menu.menuPrincipal();
            switch (choix) {
                case 1 -> clientController.menuClients();
                case 2 -> compteController.menuComptes();
                case 3 -> transactionController.menuTransactions();
                case 0 -> System.out.println("Au revoir !");
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
                case 2 -> System.out.println("Afficher transacions d’un compte");
                case 3 -> System.out.println("Afficher transactions d’un client");
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }
}
