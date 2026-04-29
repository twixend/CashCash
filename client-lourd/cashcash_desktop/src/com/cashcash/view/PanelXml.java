package com.cashcash.view;

import com.cashcash.controller.MainController;
import com.cashcash.model.Client;
import com.cashcash.model.ContratMaintenance;
import com.cashcash.model.Materiel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.List;

/**
 * Panneau de l'onglet "Génération XML".
 * Permet au gestionnaire de saisir un identifiant client, de visualiser
 * ses matériels (sous contrat / hors contrat) et de générer le fichier XML.
 */
public class PanelXml extends JPanel {

    private final MainController controller;

    // ── Composants ────────────────────────────────────────────────────────────
    private JTextField   champIdClient;
    private JButton      btnRechercher;
    private JLabel       lblInfoClient;

    private JTextArea    areaXmlPreview;
    private JButton      btnGenererXml;
    private JButton      btnSauvegarder;
    private JLabel       lblStatut;

    private Client clientCourant;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit le panneau XML.
     *
     * @param controller Contrôleur principal.
     */
    public PanelXml(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(248, 250, 252));

        add(construirePanneauRecherche(), BorderLayout.NORTH);
        add(construirePanneauPreview(),   BorderLayout.CENTER);
        add(construirePanneauActions(),   BorderLayout.SOUTH);
    }

    // ── Construction de l'interface ───────────────────────────────────────────

    /** Panneau supérieur : saisie de l'ID client et résumé. */
    private JPanel construirePanneauRecherche() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            new EmptyBorder(12, 12, 12, 12)
        ));

        // Ligne de saisie
        JPanel ligneSaisie = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        ligneSaisie.setBackground(Color.WHITE);

        JLabel lblId = new JLabel("Identifiant client :");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 13));

        champIdClient = new JTextField(10);
        champIdClient.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        champIdClient.addActionListener(e -> rechercherClient());

        btnRechercher = creerBouton("Rechercher", new Color(37, 99, 235));
        btnRechercher.addActionListener(e -> rechercherClient());

        ligneSaisie.add(lblId);
        ligneSaisie.add(champIdClient);
        ligneSaisie.add(btnRechercher);

        // Résumé client
        lblInfoClient = new JLabel("Aucun client chargé.");
        lblInfoClient.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfoClient.setForeground(new Color(100, 116, 139));
        lblInfoClient.setBorder(new EmptyBorder(6, 2, 0, 0));

        panel.add(ligneSaisie,   BorderLayout.NORTH);
        panel.add(lblInfoClient, BorderLayout.CENTER);
        return panel;
    }

    /** Panneau central : aperçu du XML généré. */
    private JPanel construirePanneauPreview() {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBackground(new Color(248, 250, 252));

        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            "Aperçu du fichier XML"
        );
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.setBorder(border);

        areaXmlPreview = new JTextArea();
        areaXmlPreview.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaXmlPreview.setEditable(false);
        areaXmlPreview.setBackground(new Color(30, 41, 59));
        areaXmlPreview.setForeground(new Color(186, 230, 253));
        areaXmlPreview.setCaretColor(Color.WHITE);
        areaXmlPreview.setText("// Le contenu XML s'affichera ici après la recherche...");

        JScrollPane scroll = new JScrollPane(areaXmlPreview);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /** Panneau inférieur : boutons d'action et statut. */
    private JPanel construirePanneauActions() {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBackground(new Color(248, 250, 252));

        JPanel boutons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        boutons.setBackground(new Color(248, 250, 252));

        btnGenererXml  = creerBouton("Générer l'aperçu XML", new Color(37, 99, 235));
        btnSauvegarder = creerBouton("Sauvegarder le fichier XML…", new Color(22, 163, 74));

        btnGenererXml.setEnabled(false);
        btnSauvegarder.setEnabled(false);

        btnGenererXml.addActionListener(e  -> genererApercu());
        btnSauvegarder.addActionListener(e -> sauvegarderXml());

        boutons.add(btnGenererXml);
        boutons.add(btnSauvegarder);

        lblStatut = new JLabel(" ");
        lblStatut.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblStatut.setForeground(new Color(22, 163, 74));

        panel.add(lblStatut, BorderLayout.WEST);
        panel.add(boutons,   BorderLayout.EAST);
        return panel;
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    /** Recherche et charge le client depuis la base de données. */
    private void rechercherClient() {
        clientCourant = controller.chargerClient(champIdClient.getText());
        if (clientCourant == null) {
            lblInfoClient.setText("Aucun client trouvé.");
            lblInfoClient.setForeground(new Color(220, 38, 38));
            btnGenererXml.setEnabled(false);
            btnSauvegarder.setEnabled(false);
            areaXmlPreview.setText("// Aucun résultat.");
            return;
        }

        // Résumé client
        ContratMaintenance contrat = clientCourant.getLeContrat();
        String infoContrat = contrat != null && contrat.estValide()
            ? "Contrat valide – " + contrat.getJoursRestants() + " jours restants"
            : "Aucun contrat valide";

        lblInfoClient.setText(
            "<html><b>" + clientCourant.getRaisonSociale() + "</b>"
            + "  –  " + clientCourant.getMateriels().size() + " matériel(s)"
            + "  –  " + clientCourant.getMaterielsSousContrat().size() + " sous contrat"
            + "  –  " + infoContrat + "</html>"
        );
        lblInfoClient.setForeground(new Color(30, 41, 59));

        btnGenererXml.setEnabled(true);
        btnSauvegarder.setEnabled(false);
        genererApercu();
    }

    /** Génère et affiche l'aperçu XML dans la zone de texte. */
    private void genererApercu() {
        if (clientCourant == null) return;
        String xml = controller.genererXml(clientCourant);
        areaXmlPreview.setText(xml);
        areaXmlPreview.setCaretPosition(0);
        btnSauvegarder.setEnabled(true);
        lblStatut.setText("Aperçu généré – " + xml.split("\n").length + " lignes");
    }

    /** Ouvre un sélecteur de fichier et sauvegarde le XML. */
    private void sauvegarderXml() {
        if (clientCourant == null) return;

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Enregistrer le fichier XML");
        chooser.setSelectedFile(new java.io.File(
            "materielClientcli" + clientCourant.getNumClient() + ".xml"
        ));
        chooser.setFileFilter(new FileNameExtensionFilter("Fichiers XML (*.xml)", "xml"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String chemin = chooser.getSelectedFile().getAbsolutePath();
            if (!chemin.endsWith(".xml")) chemin += ".xml";

            boolean ok = controller.genererEtSauvegarderXml(clientCourant, chemin);
            if (ok) {
                lblStatut.setText("✓ Fichier sauvegardé : " + chemin);
                lblStatut.setForeground(new Color(22, 163, 74));
                JOptionPane.showMessageDialog(this,
                    "Fichier XML généré avec succès :\n" + chemin,
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // ── Utilitaire ────────────────────────────────────────────────────────────

    /**
     * Crée un bouton stylé avec la couleur donnée.
     *
     * @param libelle Texte du bouton.
     * @param couleur Couleur de fond.
     * @return JButton configuré.
     */
    private JButton creerBouton(String libelle, Color couleur) {
        JButton btn = new JButton(libelle);
        btn.setBackground(couleur);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 20, 34));
        return btn;
    }
}
