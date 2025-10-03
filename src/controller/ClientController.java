package controller;

import model.Client;
import service.ClientService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientController {

    private final ClientService clientService = new ClientService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuClients() {
        int choix;
        do {
            System.out.println("=== Gestion des Clients ===");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Modifier un client");
            System.out.println("3. Supprimer un client");
            System.out.println("4. Rechercher un client par ID");
            System.out.println("5. Rechercher un client par nom");
            System.out.println("6. Afficher tous les clients");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterClient();
                case 2 -> modifierClient();
                case 3 -> supprimerClient();
                case 4 -> rechercherClientParId();
                case 5 -> rechercherClientParNom();
                case 6 -> afficherTousLesClients();
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    public void ajouterClient() {
        try {

        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        String id = UUID.randomUUID().toString();

        Client client = new Client(id, nom, email);

            clientService.ajouterClient(client);
            System.out.println("Client ajouté avec succès !");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre valide !");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
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

            Client client = new Client(id, nom, email);
            clientService.modifierClient(client);

            System.out.println("Client mis à jour avec succès !");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre valide !");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }

    private void supprimerClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du client à supprimer : ");
        String id = scanner.nextLine().trim();

        try {
            clientService.supprimerClient(id);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    private void rechercherClientParId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du client : ");
        String id = scanner.nextLine().trim();
        try {
            Client client = clientService.getClientById(id);
            if (client != null) {
                System.out.println("ID : " + client.id() + " | Nom : " + client.nom() + " | Email : " + client.email());
            } else {
                System.out.println("Client non trouvé !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void rechercherClientParNom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du client : ");
        String nom = scanner.nextLine().trim();

        try {
            Client client = clientService.getClientByName(nom);
            if (client != null) {
                System.out.println("Client trouvé :");
                System.out.println("ID : " + client.id() + " | Nom : " + client.nom() + " | Email : " + client.email());
            } else {
                System.out.println("Client non trouvé !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
    }

    private void afficherTousLesClients() {
        try {
            List<Client> clients = clientService.findAll();
            if (clients.isEmpty()) {
                System.out.println("Aucun client trouvé !");
            } else {
                System.out.println("=== Liste des clients ===");
                for (Client client : clients) {
                    System.out.println("ID : " + client.id()
                            + " | Nom : " + client.nom()
                            + " | Email : " + client.email());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des clients : " + e.getMessage());
        }
    }
}
