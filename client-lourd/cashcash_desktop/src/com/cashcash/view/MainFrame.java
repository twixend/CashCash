package com.cashcash.view;

import com.cashcash.controller.MainController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Fenêtre principale de l'application CashCash Desktop.
 * Contient trois onglets correspondant aux trois fonctionnalités du Jalon 3 :
 * 1. Génération XML d'un client
 * 2. Couverture de matériels par un contrat de maintenance
 * 3. Génération du PDF de relance (contrats expirant sous 60 jours)
 */
public class MainFrame extends JFrame {

    private final MainController controller;

    // ── Onglets ───────────────────────────────────────────────────────────────
    private PanelXml       panelXml;
    private PanelContrat   panelContrat;
    private PanelRelance   panelRelance;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit la fenêtre principale et initialise les trois panneaux.
     *
     * @param controller Contrôleur principal de l'application.
     */
    public MainFrame(MainController controller) {
        this.controller = controller;

        configurerFenetre();
        construireInterface();
        controller.setVue(this);
    }

    // ── Configuration générale ────────────────────────────────────────────────

    /** Configure les propriétés de base de la JFrame. */
    private void configurerFenetre() {
        setTitle("CashCash – Gestion des contrats et matériels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 680);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 580));
    }

    /** Construit l'interface : bandeau + onglets. */
    private void construireInterface() {
        setLayout(new BorderLayout());

        // ── Bandeau supérieur ─────────────────────────────────────────────────
        add(construireBandeau(), BorderLayout.NORTH);

        // ── Panneau à onglets ─────────────────────────────────────────────────
        JTabbedPane onglets = new JTabbedPane();
        onglets.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        onglets.setBorder(new EmptyBorder(5, 5, 5, 5));

        panelXml     = new PanelXml(controller);
        panelContrat = new PanelContrat(controller);
        panelRelance = new PanelRelance(controller);

        onglets.addTab("📄  Génération XML",     panelXml);
        onglets.addTab("📋  Contrat de maintenance", panelContrat);
        onglets.addTab("📬  Relance PDF",         panelRelance);

        add(onglets, BorderLayout.CENTER);

        // ── Barre de statut ───────────────────────────────────────────────────
        add(construireBarreStatut(), BorderLayout.SOUTH);
    }

    /**
     * Construit le bandeau supérieur avec le logo textuel et le titre.
     *
     * @return Panneau bandeau.
     */
    private JPanel construireBandeau() {
        JPanel bandeau = new JPanel(new BorderLayout());
        bandeau.setBackground(new Color(30, 41, 59));   // bleu-gris foncé
        bandeau.setBorder(new EmptyBorder(12, 18, 12, 18));

        JLabel lblTitre = new JLabel("CashCash  |  Client lourd – Gestion matériels & contrats");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitre.setForeground(Color.WHITE);

        JLabel lblVersion = new JLabel("v1.0.0");
        lblVersion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblVersion.setForeground(new Color(148, 163, 184));

        bandeau.add(lblTitre,   BorderLayout.WEST);
        bandeau.add(lblVersion, BorderLayout.EAST);
        return bandeau;
    }

    /**
     * Construit la barre de statut inférieure.
     *
     * @return Panneau barre de statut.
     */
    private JPanel construireBarreStatut() {
        JPanel barre = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        barre.setBackground(new Color(241, 245, 249));
        barre.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(203, 213, 225)));

        JLabel lblStatut = new JLabel("Connecté à la base de données MySQL – cashcash_bdd");
        lblStatut.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblStatut.setForeground(new Color(100, 116, 139));

        // Indicateur de connexion (cercle vert)
        JLabel indicateur = new JLabel("●");
        indicateur.setForeground(new Color(22, 163, 74));
        indicateur.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        barre.add(indicateur);
        barre.add(lblStatut);
        return barre;
    }
}
