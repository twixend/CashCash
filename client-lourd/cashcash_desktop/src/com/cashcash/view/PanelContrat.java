package com.cashcash.view;

import com.cashcash.controller.MainController;
import com.cashcash.model.Client;
import com.cashcash.model.Materiel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Panneau de l'onglet "Contrat de maintenance".
 * Permet au gestionnaire de sélectionner un client, de voir ses matériels
 * hors contrat et de créer un nouveau contrat pour les matériels choisis.
 */
public class PanelContrat extends JPanel {

    private final MainController controller;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // ── Composants ────────────────────────────────────────────────────────────
    private JComboBox<String>   comboClients;
    private JButton             btnChargerClients;
    private JButton             btnValiderClient;
    private JLabel              lblInfoClient;
    private JLabel              lblInfoContrat;

    private DefaultTableModel   modeleTable;
    private JTable              tableMateriels;

    private JSpinner            spinnerTaux;
    private JButton             btnCreerContrat;
    private JLabel              lblResultat;

    private List<Client>        tousLesClients = new ArrayList<>();
    private Client              clientSelectionne;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit le panneau de gestion des contrats.
     *
     * @param controller Contrôleur principal.
     */
    public PanelContrat(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(248, 250, 252));

        add(construirePanneauSelection(), BorderLayout.NORTH);
        add(construirePanneauMateriels(), BorderLayout.CENTER);
        add(construirePanneauCreation(),  BorderLayout.SOUTH);
    }

    // ── Construction de l'interface ───────────────────────────────────────────

    /** Panneau supérieur : sélection du client. */
    private JPanel construirePanneauSelection() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            new EmptyBorder(12, 12, 12, 12)
        ));

        // Ligne de sélection
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        ligne.setBackground(Color.WHITE);

        JLabel lbl = new JLabel("Client :");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));

        comboClients = new JComboBox<>();
        comboClients.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboClients.setPreferredSize(new Dimension(300, 30));

        btnChargerClients = creerBouton("Charger la liste", new Color(100, 116, 139));
        btnValiderClient  = creerBouton("Sélectionner", new Color(37, 99, 235));

        btnChargerClients.addActionListener(e -> chargerListeClients());
        btnValiderClient.addActionListener(e  -> selectionnerClient());

        ligne.add(lbl);
        ligne.add(comboClients);
        ligne.add(btnChargerClients);
        ligne.add(btnValiderClient);

        // Infos client
        JPanel infos = new JPanel(new GridLayout(2, 1, 2, 2));
        infos.setBackground(Color.WHITE);
        infos.setBorder(new EmptyBorder(6, 2, 0, 0));

        lblInfoClient = new JLabel("Aucun client sélectionné.");
        lblInfoClient.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfoClient.setForeground(new Color(100, 116, 139));

        lblInfoContrat = new JLabel(" ");
        lblInfoContrat.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        infos.add(lblInfoClient);
        infos.add(lblInfoContrat);

        panel.add(ligne, BorderLayout.NORTH);
        panel.add(infos, BorderLayout.CENTER);
        return panel;
    }

    /** Panneau central : tableau des matériels hors contrat avec cases à cocher. */
    private JPanel construirePanneauMateriels() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 250, 252));

        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            "Matériels hors contrat – cochez ceux à couvrir"
        );
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.setBorder(border);

        // Colonnes : [sélection, numéro de série, type, prix de vente, emplacement, date installation]
        modeleTable = new DefaultTableModel(
            new Object[]{"Sélectionner", "N° Série", "Type", "Prix (€)", "Emplacement", "Date installation"},
            0
        ) {
            @Override public Class<?> getColumnClass(int col) {
                return col == 0 ? Boolean.class : String.class;
            }
            @Override public boolean isCellEditable(int row, int col) {
                return col == 0;
            }
        };

        tableMateriels = new JTable(modeleTable);
        tableMateriels.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableMateriels.setRowHeight(26);
        tableMateriels.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableMateriels.setSelectionBackground(new Color(219, 234, 254));
        tableMateriels.setGridColor(new Color(226, 232, 240));

        // Largeurs des colonnes
        tableMateriels.getColumnModel().getColumn(0).setPreferredWidth(80);
        tableMateriels.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableMateriels.getColumnModel().getColumn(2).setPreferredWidth(220);
        tableMateriels.getColumnModel().getColumn(3).setPreferredWidth(80);
        tableMateriels.getColumnModel().getColumn(4).setPreferredWidth(150);
        tableMateriels.getColumnModel().getColumn(5).setPreferredWidth(130);

        JScrollPane scroll = new JScrollPane(tableMateriels);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);

        // Boutons Tout / Rien
        JPanel boutonsTout = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        boutonsTout.setBackground(new Color(248, 250, 252));
        JButton btnTout = creerBouton("Tout sélectionner", new Color(100, 116, 139));
        JButton btnRien = creerBouton("Tout désélectionner", new Color(100, 116, 139));
        btnTout.addActionListener(e -> changerSelection(true));
        btnRien.addActionListener(e -> changerSelection(false));
        boutonsTout.add(btnTout);
        boutonsTout.add(btnRien);
        panel.add(boutonsTout, BorderLayout.SOUTH);

        return panel;
    }

    /** Panneau inférieur : taux et bouton de création du contrat. */
    private JPanel construirePanneauCreation() {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBackground(new Color(248, 250, 252));
        panel.setBorder(new EmptyBorder(8, 0, 0, 0));

        JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        options.setBackground(new Color(248, 250, 252));

        JLabel lblTaux = new JLabel("Taux annuel (%) :");
        lblTaux.setFont(new Font("Segoe UI", Font.BOLD, 13));

        spinnerTaux = new JSpinner(new SpinnerNumberModel(10.0, 1.0, 100.0, 0.5));
        spinnerTaux.setPreferredSize(new Dimension(80, 30));
        spinnerTaux.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        options.add(lblTaux);
        options.add(spinnerTaux);

        btnCreerContrat = creerBouton("Créer le contrat de maintenance", new Color(22, 163, 74));
        btnCreerContrat.setEnabled(false);
        btnCreerContrat.addActionListener(e -> creerContrat());

        lblResultat = new JLabel(" ");
        lblResultat.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JPanel droite = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        droite.setBackground(new Color(248, 250, 252));
        droite.add(lblResultat);
        droite.add(btnCreerContrat);

        panel.add(options, BorderLayout.WEST);
        panel.add(droite,  BorderLayout.EAST);
        return panel;
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    /** Charge la liste de tous les clients dans la combo. */
    private void chargerListeClients() {
        tousLesClients = controller.chargerTousLesClients();
        comboClients.removeAllItems();
        for (Client c : tousLesClients) {
            comboClients.addItem(c.getNumClient() + " – " + c.getRaisonSociale());
        }
        if (tousLesClients.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Aucun client trouvé dans la base.", "Information",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /** Sélectionne le client choisi dans la combo et affiche ses matériels hors contrat. */
    private void selectionnerClient() {
        int index = comboClients.getSelectedIndex();
        if (index < 0 || index >= tousLesClients.size()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez d'abord charger la liste puis sélectionner un client.",
                "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        clientSelectionne = tousLesClients.get(index);

        // Infos client
        lblInfoClient.setText(
            "<html><b>" + clientSelectionne.getRaisonSociale() + "</b>"
            + " – SIREN : " + clientSelectionne.getSiren()
            + " – " + clientSelectionne.getAdresse() + "</html>"
        );
        lblInfoClient.setForeground(new Color(30, 41, 59));

        // Info contrat existant
        if (clientSelectionne.estAssure()) {
            long jours = clientSelectionne.getLeContrat().getJoursRestants();
            lblInfoContrat.setText("⚠ Ce client possède déjà un contrat valide (" + jours + " jours restants).");
            lblInfoContrat.setForeground(new Color(217, 119, 6));
        } else {
            lblInfoContrat.setText("✓ Aucun contrat valide – vous pouvez créer un nouveau contrat.");
            lblInfoContrat.setForeground(new Color(22, 163, 74));
        }

        // Remplir le tableau avec les matériels hors contrat
        modeleTable.setRowCount(0);
        List<Materiel> horsContrat = clientSelectionne.getMaterielsHorsContrat();
        for (Materiel m : horsContrat) {
            modeleTable.addRow(new Object[]{
                false,
                m.getNumSerie(),
                m.getLeType().getLibelleTypeMateriel(),
                String.format("%.2f", m.getPrixVente()),
                m.getEmplacement(),
                m.getDateInstallation() != null ? m.getDateInstallation().format(FMT) : "—"
            });
        }

        btnCreerContrat.setEnabled(!horsContrat.isEmpty());
        lblResultat.setText(" ");

        if (horsContrat.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Tous les matériels de ce client sont déjà sous contrat.",
                "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /** Coche ou décoche toutes les lignes du tableau. */
    private void changerSelection(boolean valeur) {
        for (int i = 0; i < modeleTable.getRowCount(); i++) {
            modeleTable.setValueAt(valeur, i, 0);
        }
    }

    /** Crée le contrat pour les matériels cochés dans la table. */
    private void creerContrat() {
        if (clientSelectionne == null) return;

        // Récupérer les numéros de série cochés
        List<String> numSeriesCoches = new ArrayList<>();
        for (int i = 0; i < modeleTable.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modeleTable.getValueAt(i, 0))) {
                numSeriesCoches.add((String) modeleTable.getValueAt(i, 1));
            }
        }

        if (numSeriesCoches.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Cochez au moins un matériel à couvrir.",
                "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmation
        double taux = (Double) spinnerTaux.getValue();
        int confirm = JOptionPane.showConfirmDialog(this,
            "Créer un contrat à " + taux + "% couvrant " + numSeriesCoches.size()
            + " matériel(s) pour " + clientSelectionne.getRaisonSociale() + " ?",
            "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        String numContrat = controller.creerContrat(
            clientSelectionne.getNumClient(), numSeriesCoches, taux
        );

        if (numContrat != null) {
            lblResultat.setText("✓ Contrat " + numContrat + " créé !");
            lblResultat.setForeground(new Color(22, 163, 74));
            JOptionPane.showMessageDialog(this,
                "Contrat " + numContrat + " créé avec succès !\n"
                + numSeriesCoches.size() + " matériel(s) couverts.",
                "Succès", JOptionPane.INFORMATION_MESSAGE);
            // Recharger le client
            selectionnerClient();
        }
    }

    // ── Utilitaire ────────────────────────────────────────────────────────────

    /**
     * Crée un bouton stylé.
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
        return btn;
    }
}
