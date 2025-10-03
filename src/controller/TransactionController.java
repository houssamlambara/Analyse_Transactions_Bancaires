package controller;

import enums.TypeTransaction;
import model.Compte;
import model.Transaction;
import service.CompteService;
import service.TransactionService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class TransactionController {


    private final TransactionService transactionService = new TransactionService();
    private final CompteService compteService = new CompteService();
    private final Scanner scanner = new Scanner(System.in);

    public void menuTransactions() {
        int choix;
        do {
            System.out.println("=== Gestion des Transactions ===");
            System.out.println("1. Faire un versement");
            System.out.println("2. Faire un retrait");
            System.out.println("3. Faire un virement");
            System.out.println("4. Afficher toutes les transactions");
            System.out.println("5. Afficher les transactions par compte");
            System.out.println("6. Filtrer les transactions");
            System.out.println("7. Regrouper les transactions par type");
            System.out.println("8. Total/Moyenne des transactions par compte ou client");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> versement();
                case 2 -> retrait();
                case 3 -> virement();
                case 4 -> afficherToutesTransactions();
                case 5 -> afficherTransactionsParCompte();
                case 6 -> filterTransactions();
                case 7 -> groupTransactionsByType();
                case 8 -> calculerTotalMoyenne();
                case 0 -> System.out.println("Retour au menu principal...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    public void versement() {
        try {
            System.out.print("ID Compte : ");
            String idCompte = scanner.nextLine();

            System.out.print("Montant du versement : ");
            double montant = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Lieu : ");
            String lieu = scanner.nextLine();

            if (montant <= 0) {
                System.out.println("Le montant doit être positif !");
                return;
            }

            Compte compte = compteService.getById(idCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé !");
                return;
            }

            compte.setSolde(compte.getSolde() + montant);
            compteService.modifierCompte(compte);

            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDate.now(),
                    montant,
                    TypeTransaction.VERSEMENT,
                    lieu,
                    idCompte
            );
            transactionService.ajouterTransaction(transaction);

            System.out.println("Versement effectué avec succès !");
        }
        catch (java.util.InputMismatchException e) {
            System.out.println("Veuillez entrer uniquement un nombre valide !");
            scanner.nextLine();
        }
        catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }


    public void retrait() {
        try {
            System.out.print("ID Compte : ");
            String idCompte = scanner.nextLine();

            System.out.print("Montant du retrait : ");
            double montant = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Lieu : ");
            String lieu = scanner.nextLine();

            if (montant <= 0) {
                System.out.println("Le montant doit être positif !");
                return;
            }

            Compte compte = compteService.getById(idCompte);
            if (compte == null) {
                System.out.println("Compte non trouvé !");
                return;
            }

            if (compte.getSolde() < montant) {
                System.out.println("Solde insuffisant !");
                return;
            }

            compte.setSolde(compte.getSolde() - montant);
            compteService.modifierCompte(compte);

            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDate.now(),
                    montant,
                    TypeTransaction.RETRAIT,
                    lieu,
                    idCompte
            );
            transactionService.ajouterTransaction(transaction);

            System.out.println("Retrait effectué avec succès !");

        } catch (java.util.InputMismatchException e) {
            System.out.println("Veuillez entrer uniquement un nombre valide !");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }

    public void virement() {
        try {
            System.out.print("ID Compte source : ");
            String idCompteSource = scanner.nextLine();

            System.out.print("ID Compte destinataire : ");
            String idCompteDest = scanner.nextLine();

            System.out.print("Montant du virement : ");
            double montant = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Lieu : ");
            String lieu = scanner.nextLine();

            if (montant <= 0) {
                System.out.println("Le montant doit être positif !");
                return;
            }

            Compte compteSource = compteService.getById(idCompteSource);
            Compte compteDest = compteService.getById(idCompteDest);

            if (compteSource == null || compteDest == null) {
                System.out.println("Un des comptes est introuvable !");
                return;
            }

            if (compteSource.getSolde() < montant) {
                System.out.println("Solde insuffisant dans le compte source !");
                return;
            }

            // Mise à jour des soldes
            compteSource.setSolde(compteSource.getSolde() - montant);
            compteDest.setSolde(compteDest.getSolde() + montant);
            compteService.modifierCompte(compteSource);
            compteService.modifierCompte(compteDest);

            // Enregistrement des transactions
            Transaction debit = new Transaction(
                    UUID.randomUUID().toString() + "-Debit",
                    LocalDate.now(),
                    montant,
                    TypeTransaction.VIREMENT,
                    lieu,
                    idCompteSource
            );
            Transaction credit = new Transaction(
                    UUID.randomUUID().toString() + "-Credit",
                    LocalDate.now(),
                    montant,
                    TypeTransaction.VIREMENT,
                    lieu,
                    idCompteDest
            );

            transactionService.ajouterTransaction(debit);
            transactionService.ajouterTransaction(credit);

            System.out.println("Virement effectué avec succès !");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Veuillez entrer uniquement un nombre valide !");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }

    private void afficherToutesTransactions() {
        try {
            List<Transaction> transactions = transactionService.findAll();
            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction trouvée !");
                return;
            }

            System.out.println("=== Liste des transactions ===");
            for (Transaction t : transactions) {
                System.out.println("ID : " + t.id() +
                        " | Date : " + t.date() +
                        " | Montant : " + t.montant() +
                        " | Type : " + t.type() +
                        " | Lieu : " + t.lieu() +
                        " | Compte : " + t.idCompte());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des transactions : " + e.getMessage());
        }
    }

    private void afficherTransactionsParCompte() {
        System.out.print("ID Compte : ");
        String idCompte = scanner.nextLine();

        try {
            List<Transaction> transactions = transactionService.findByCompte(idCompte);
            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction trouvée pour ce compte !");
                return;
            }

            System.out.println("=== Transactions du compte " + idCompte + " ===");
            for (Transaction t : transactions) {
                System.out.println("ID : " + t.id() +
                        " | Date : " + t.date() +
                        " | Montant : " + t.montant() +
                        " | Type : " + t.type() +
                        " | Lieu : " + t.lieu());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des transactions : " + e.getMessage());
        }
    }

    private void filterTransactions() {
        try {
            System.out.println("=== Filtrer les Transactions ===");
            System.out.println("1. Par montant");
            System.out.println("2. Par type");
            System.out.println("3. Par date");
            System.out.println("4. Par lieu");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            List<Transaction> result = null;

            switch (choix) {
                case 1 -> {
                    System.out.print("Montant minimum : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine();
                    result = transactionService.filterByMontant(montant);
                }
                case 2 -> {
                    System.out.print("Type (VERSEMENT/RETRAIT/VIREMENT) : ");
                    String type = scanner.nextLine().toUpperCase();
                    result = transactionService.filterByType(TypeTransaction.valueOf(type));
                }
                case 3 -> {
                    System.out.print("Date (YYYY-MM-DD) : ");
                    String dateStr = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateStr);
                    result = transactionService.filterByDate(date);
                }
                case 4 -> {
                    System.out.print("Lieu : ");
                    String lieu = scanner.nextLine();
                    result = transactionService.filterByLieu(lieu);
                }
                default -> System.out.println("Choix invalide !");
            }

            if (result != null && !result.isEmpty()) {
                result.forEach(System.out::println);
            } else {
                System.out.println("Aucune transaction trouvée !");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre valide !");
            scanner.nextLine();
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Format de date invalide !");
        } catch (SQLException e) {
            System.out.println("Erreur base de données : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }


    private void groupTransactionsByType() {
        try {
            Map<TypeTransaction, List<Transaction>> grouped = transactionService.groupByType();

            if (grouped.isEmpty()) {
                System.out.println("Aucune transaction trouvée !");
                return;
            }

            // Parcours de chaque type
            for (TypeTransaction type : grouped.keySet()) {
                System.out.println("\n=== " + type + " ===");
                List<Transaction> list = grouped.get(type);
                for (Transaction t : list) {
                    System.out.println("ID : " + t.id() +
                            " | Date : " + t.date() +
                            " | Montant : " + t.montant() +
                            " | Lieu : " + t.lieu() +
                            " | Compte : " + t.idCompte());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du regroupement : " + e.getMessage());
        }
    }

    private void calculerTotalMoyenne() {
        System.out.println("1. Par compte");
        System.out.println("2. Par client");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choix) {
                case 1 -> { // Par compte
                    System.out.print("ID du compte : ");
                    String idCompte = scanner.nextLine();

                    System.out.println("1. Total");
                    System.out.println("2. Moyenne");
                    System.out.print("Votre choix : ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 1) {
                        double total = transactionService.totalTransactionsByCompte(idCompte);
                        System.out.println("Total des transactions pour le compte " + idCompte + " : " + total);
                    } else if (type == 2) {
                        double moyenne = transactionService.moyenneTransactionsByCompte(idCompte);
                        System.out.println("Moyenne des transactions pour le compte " + idCompte + " : " + moyenne);
                    } else {
                        System.out.println("Choix invalide !");
                    }
                }

                case 2 -> { // Par client
                    System.out.print("ID du client : ");
                    String idClient = scanner.nextLine();

                    System.out.println("1. Total");
                    System.out.println("2. Moyenne");
                    System.out.print("Votre choix : ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 1) {
                        double total = transactionService.totalTransactionsByClient(idClient);
                        System.out.println("Total des transactions pour le client " + idClient + " : " + total);
                    } else if (type == 2) {
                        double moyenne = transactionService.moyenneTransactionsByClient(idClient);
                        System.out.println("Moyenne des transactions pour le client " + idClient + " : " + moyenne);
                    } else {
                        System.out.println("Choix invalide !");
                    }
                }

                default -> System.out.println("Choix invalide !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du calcul : " + e.getMessage());
        }
    }

}
