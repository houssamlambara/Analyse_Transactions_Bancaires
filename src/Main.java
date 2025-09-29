import controller.MainController;
import ui.MenuUi;

public class Main {
    public static void main(String[] args) {
        MenuUi menuUi = new MenuUi();
        MainController controller = new MainController(menuUi);

        int choix;
        do {
            choix = menuUi.menuPrincipal();
            switch (choix) {
                case 1 -> controller.menuClients();
                case 2 -> controller.menuComptes();
                case 3 -> controller.menuTransactions();
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }
}
