package ui;

import java.util.Scanner;

public class MenuUi {

    private final Scanner scanner = new Scanner(System.in);

    public int menuPrincipal() {
        System.out.println("==== Al Baraka Banque ====");
        System.out.println("1. Gestion des clients");
        System.out.println("2. Gestion des comptes");
        System.out.println("3. Gestion des transactions");
        System.out.println("4. Rapports");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }
}
