import controller.MainController;
import ui.MenuUi;

public class Main {
    public static void main(String[] args) {
        MenuUi menuUi = new MenuUi();
        MainController controller = new MainController(menuUi);

        controller.demarrer();
    }
}
