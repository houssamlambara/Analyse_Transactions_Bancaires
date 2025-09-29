package model;

import enums.TypeTransaction;
import java.time.LocalDate;

public record Transaction(
        String id,
        LocalDate date,
        double montant,
        TypeTransaction type,
        String lieu,
        String idCompte
) {}
