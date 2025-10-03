package controller;

import ui.MenuUi;

public class MainController {

    private final MenuUi menu;
    private final ClientController clientController = new ClientController();
    private final CompteController compteController = new CompteController();
    private final TransactionController transactionController = new TransactionController();
    private final RapportController rapportController = new RapportController();


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
                case 4 -> rapportController.menuRapports();
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }
}
