package service;

import model.Client;
import model.Compte;
import model.Transaction;
import enums.TypeTransaction;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class RapportService {

    private final ClientService clientService = new ClientService();
    private final CompteService compteService = new CompteService();
    private final TransactionService transactionService = new TransactionService();


    public List<Map.Entry<Client, Double>> top5ClientsParSolde() throws SQLException {
        List<Compte> comptes = compteService.getAll();

        Map<String, Double> soldeParClient = comptes.stream()
                .collect(Collectors.groupingBy(
                        Compte::getIdClient,
                        Collectors.summingDouble(Compte::getSolde)
                ));

        List<Map.Entry<Client, Double>> result = new ArrayList<>();
        for (var entry : soldeParClient.entrySet()) {
            String idClient = entry.getKey();
            Client client = clientService.findById(idClient);
            result.add(Map.entry(client, entry.getValue()));
        }

        return result.stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(5)
                .toList();
    }

    public Map<TypeTransaction, Map<String, Double>> rapportMensuel(int mois, int annee) throws SQLException {
        List<Transaction> transactions = transactionService.findAll();

        List<Transaction> transactionsMois = transactions.stream()
                .filter(t -> t.date().getMonthValue() == mois && t.date().getYear() == annee)
                .toList();

        return transactionsMois.stream()
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    double total = list.stream().mapToDouble(Transaction::montant).sum();
                                    double count = list.size();
                                    Map<String, Double> map = new HashMap<>();
                                    map.put("nombre", count);
                                    map.put("total", total);
                                    return map;
                                }
                        )
                ));
    }
}
