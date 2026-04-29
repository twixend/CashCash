package com.cashcash.controller;

import com.cashcash.dao.PersistanceSQL;
import com.cashcash.model.Client;
import com.cashcash.model.Materiel;
import com.cashcash.service.GestionMateriels;
import com.cashcash.util.GenerateurPdf;
import com.cashcash.view.MainFrame;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Contrôleur principal de l'application CashCash Desktop.
 * Reçoit les actions des vues, délègue au service GestionMateriels,
 * puis met à jour les vues avec les résultats (architecture MVC).
 */
public class MainController {

    // ── Références MVC ────────────────────────────────────────────────────────
    private final GestionMateriels service;
    private MainFrame              vue;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit le contrôleur en initialisant la couche de persistance et le service.
     *
     * @throws SQLException En cas d'échec de connexion à la base de données.
     */
    public MainController() throws SQLException {
        PersistanceSQL persistance = new PersistanceSQL(
            "mysql-cashcash.alwaysdata.net", 3306, "cashcash_bdd"
        );
        this.service = new GestionMateriels(persistance);
    }

    /**
     * Associe la vue principale au contrôleur.
     *
     * @param vue Instance de MainFrame à piloter.
     */
    public void setVue(MainFrame vue) {
        this.vue = vue;
    }

    // ── Actions Fonctionnalité 1 : Génération XML ─────────────────────────────

    /**
     * Charge et retourne un client depuis la base de données.
     * Affiche une erreur dans la vue en cas de problème.
     *
     * @param idClient Identifiant du client saisi par l'utilisateur.
     * @return Instance de Client chargée, ou null en cas d'erreur ou d'absence.
     */
    public Client chargerClient(String idClient) {
        if (idClient == null || idClient.isBlank()) {
            afficherErreur("Veuillez saisir un identifiant client.");
            return null;
        }
        try {
            int id = Integer.parseInt(idClient.trim());
            Client client = service.getClient(id);
            if (client == null) {
                afficherErreur("Aucun client trouvé avec l'identifiant : " + idClient);
            }
            return client;
        } catch (NumberFormatException e) {
            afficherErreur("L'identifiant client doit être un nombre entier.");
            return null;
        } catch (SQLException e) {
            afficherErreur("Erreur base de données : " + e.getMessage());
            return null;
        }
    }

    /**
     * Génère le XML d'un client et l'enregistre dans un fichier.
     * Valide le XML produit avant la sauvegarde.
     *
     * @param client       Client dont on génère le fichier XML.
     * @param cheminFichier Chemin complet du fichier de destination.
     * @return true si la génération et la sauvegarde ont réussi, false sinon.
     */
    public boolean genererEtSauvegarderXml(Client client, String cheminFichier) {
        if (client == null) {
            afficherErreur("Aucun client sélectionné.");
            return false;
        }
        String contenuXml = service.xmlClient(client);

        // Validation du XML avant sauvegarde
        if (!GestionMateriels.xmlClientValide(contenuXml)) {
            afficherErreur("Le XML généré est invalide — opération annulée.");
            return false;
        }

        try (FileWriter fw = new FileWriter(cheminFichier)) {
            fw.write(contenuXml);
            return true;
        } catch (IOException e) {
            afficherErreur("Impossible d'écrire le fichier : " + e.getMessage());
            return false;
        }
    }

    /**
     * Retourne la représentation XML d'un client sans la sauvegarder.
     *
     * @param client Client source.
     * @return Chaîne XML ou chaîne vide en cas d'erreur.
     */
    public String genererXml(Client client) {
        if (client == null) return "";
        return service.xmlClient(client);
    }

    // ── Actions Fonctionnalité 2 : Création de contrat ────────────────────────

    /**
     * Crée un contrat de maintenance pour un client et les matériels sélectionnés.
     *
     * @param idClient   Identifiant du client.
     * @param numSeries  Liste des numéros de série des matériels à couvrir.
     * @param tauxAnnuel Taux annuel du contrat (%).
     * @return Numéro du contrat créé, ou null en cas d'erreur.
     */
    public String creerContrat(String idClient, List<String> numSeries, double tauxAnnuel) {
        if (numSeries == null || numSeries.isEmpty()) {
            afficherErreur("Sélectionnez au moins un matériel à couvrir.");
            return null;
        }
        try {
            String numContrat = service.creerContratAvecMateriels(idClient, numSeries, tauxAnnuel);
            return numContrat;
        } catch (SQLException e) {
            afficherErreur("Erreur lors de la création du contrat : " + e.getMessage());
            return null;
        }
    }

    /**
     * Charge la liste de tous les clients de la base.
     *
     * @return Liste de clients, ou liste vide en cas d'erreur.
     */
    public List<Client> chargerTousLesClients() {
        try {
            return service.getTousLesClients();
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des clients : " + e.getMessage());
            return List.of();
        }
    }

    // ── Actions Fonctionnalité 3 : Génération PDF de relance ──────────────────

    /**
     * Génère le PDF des clients dont le contrat expire dans moins de 60 jours
     * et l'enregistre dans le fichier indiqué.
     *
     * @param cheminFichier Chemin du fichier PDF de destination.
     * @return Nombre de clients inclus dans le PDF, ou -1 en cas d'erreur.
     */
    public int genererPdfRelance(String cheminFichier) {
        try {
            List<Client> aRelancer = service.getClientsARelancer();
            if (aRelancer.isEmpty()) {
                afficherInfo("Aucun client à relancer dans les 60 prochains jours.");
                return 0;
            }
            GenerateurPdf.genererPdfRelance(aRelancer, cheminFichier);
            return aRelancer.size();
        } catch (SQLException e) {
            afficherErreur("Erreur base de données : " + e.getMessage());
            return -1;
        } catch (IOException e) {
            afficherErreur("Erreur lors de la génération du PDF : " + e.getMessage());
            return -1;
        }
    }

    // ── Utilitaires d'affichage ───────────────────────────────────────────────

    /**
     * Affiche un message d'erreur dans une boîte de dialogue.
     *
     * @param message Message à afficher.
     */
    private void afficherErreur(String message) {
        JOptionPane.showMessageDialog(vue, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Affiche un message d'information dans une boîte de dialogue.
     *
     * @param message Message à afficher.
     */
    private void afficherInfo(String message) {
        JOptionPane.showMessageDialog(vue, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
