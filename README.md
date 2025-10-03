# README - Analyse des Transactions Bancaires et Détection des Anomalies

## 1. Contexte du projet

* Gestion des transactions bancaires pour la Banque Al Baraka.
* Objectif : centraliser données clients, comptes, transactions.
* Détecter anomalies, comptes inactifs, générer des rapports.

## 2. Objectifs de l'application

* Centraliser les informations clients et transactions.
* Détecter automatiquement les transactions suspectes.
* Identifier comptes inactifs et comportements inhabituels.
* Produire des rapports fiables pour la prise de décision.

## 3. Structure de l'application

1. **UI/Menu** : interface textuelle interactive.
2. **Services** : logique métier, calculs, détection d'anomalies, rapports.
3. **Entity/Modèle** : Client, Compte (Courant/Epargne), Transaction.
4. **DAO** : accès à la base de données, CRUD et recherches.
5. **Utilitaires** : validation, formatage, gestion des dates.

## 4. Contenu des classes

### 4.1 Entity

* **Client (record)** : id, nom, email
* **Compte (sealed)** : id, numéro, solde, idClient

  * CompteCourant : découvert autorisé
  * CompteEpargne : taux d'intérêt
* **Transaction (record)** : id, date, montant, type (VERSEMENT, RETRAIT, VIREMENT), lieu, idCompte

### 4.2 DAO

* ClientDAO : CRUD clients
* CompteDAO : CRUD comptes, recherche par client
* TransactionDAO : CRUD transactions, recherche par compte

### 4.3 Services

* ClientService : gérer clients, recherches, statistiques simples
* CompteService : créer/modifier comptes, recherche, solde max/min
* TransactionService : liste, filtrage, regroupement, totaux/moyennes, détection anomalies
* RapportService : top clients, rapports mensuels, transactions suspectes, comptes inactifs

### 4.4 UI

* Menu interactif : création client et comptes, transactions, consultation historique, analyses et alertes.

## 5. Base de données

### Tables principales

* **Client** : id, nom, email
* **Compte** : id, numéro, solde, idClient, typeCompte, découvert/taux
* **Transaction** : id, date, montant, type, lieu, idCompte

### Contraintes

* Un client peut avoir plusieurs comptes
* Un compte peut avoir plusieurs transactions
* Clés primaires et étrangères définies pour intégrité

## 6. Exigences techniques

* Java 17 (record, sealed, switch expressions, var)
* Programmation fonctionnelle : Stream API, Collectors, Optional, Lambda
* Persistance JDBC (MySQL ou PostgreSQL)
* Architecture en couches : Entity, DAO, Service, UI
* Gestion des exceptions (try/catch avec messages clairs)
* Git pour versionning
* Livrable : fichier JAR exécutable

## 7. Fonctionnalités principales

1. Gestion des clients : ajouter, modifier, supprimer, rechercher
2. Gestion des comptes : création, modification, recherche, solde max/min
3. Gestion des transactions : versement, retrait, virement, filtrage, regroupement
4. Analyse financière : total/moyenne par client ou compte
5. Détection d'anomalies : transactions suspectes, comptes inactifs
6. Rapports : top clients, statistiques mensuelles des transactions
