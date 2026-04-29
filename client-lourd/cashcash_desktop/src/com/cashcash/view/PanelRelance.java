package com.cashcash.view;

import com.cashcash.controller.MainController;
import com.cashcash.model.Client;
import com.cashcash.model.ContratMaintenance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Panneau de l'onglet "Relance PDF".
 * Affiche les clients dont le contrat expire dans moins de 60 jours
 * et permet de générer un fichier PDF de courriers de relance.
 */
public class PanelRelance extends JPanel {

    private final MainController controller;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // ── Composants ────────────────────────────────────────────────────────────
    private JButton           btnActualiser;
    private JLabel            lblNbClients;

    private DefaultTableModel modeleTable;
    private JTable            tableClients;

    private JButton           btnGenererPdf;
    private JLabel            lblStatut;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit le panneau de relance PDF.
     *
     * @param controller Contrôleur principal.
     */
    public PanelRelance(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(new Color(248, 250, 252));

        add(construirePanneauEntete(), BorderLayout.NORTH);
        add(construirePanneauTable(),  BorderLayout.CENTER);
        add(construirePanneauBas(),    BorderLayout.SOUTH);
    }

    // ── Construction de l'interface ───────────────────────────────────────────

    /** Panneau supérieur : titre et bouton actualisation. */
    private JPanel construirePanneauEntete() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            new EmptyBorder(12, 12, 12, 12)
        ));

        // Bloc texte explicatif
        JPanel blocTexte = new JPanel(new GridLayout(2, 1, 2, 2));
        blocTexte.setBackground(Color.WHITE);

        JLabel lblTitre = new JLabel("Clients dont le contrat expire dans moins de 60 jours");
        lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitre.setForeground(new Color(30, 41, 59));

        lblNbClients = new JLabel("Cliquez sur « Actualiser » pour charger la liste.");
        lblNbClients.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblNbClients.setForeground(new Color(100, 116, 139));

        blocTexte.add(lblTitre);
        blocTexte.add(lblNbClients);

        // Bouton actualiser
        btnActualiser = creerBouton("↻  Actualiser", new Color(37, 99, 235));
        btnActualiser.addActionListener(e -> actualiserListe());

        JPanel droite = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        droite.setBackground(Color.WHITE);
        droite.add(btnActualiser);

        panel.add(blocTexte, BorderLayout.CENTER);
        panel.add(droite,    BorderLayout.EAST);
        return panel;
    }

    /** Panneau central : tableau des clients à relancer. */
    private JPanel construirePanneauTable() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 250, 252));

        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            "Liste des clients à relancer"
        );
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.setBorder(border);

        modeleTable = new DefaultTableModel(
            new Object[]{"N° Client", "Raison sociale", "Téléphone", "Email",
                         "N° Contrat", "Date échéance", "Jours restants"},
            0
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        tableClients = new JTable(modeleTable);
        tableClients.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableClients.setRowHeight(26);
        tableClients.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableClients.setSelectionBackground(new Color(219, 234, 254));
        tableClients.setGridColor(new Color(226, 232, 240));
        tableClients.setShowVerticalLines(false);

        // Colorier la colonne "Jours restants" selon l'urgence
        tableClients.getColumnModel().getColumn(6).setCellRenderer(
            new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int col) {
                    Component comp = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                    if (value != null && !isSelected) {
                        try {
                            long jours = Long.parseLong(value.toString().replaceAll("[^0-9]", ""));
                            if (jours <= 15)      comp.setForeground(new Color(220, 38, 38));   // rouge
                            else if (jours <= 30) comp.setForeground(new Color(217, 119, 6));   // orange
                            else                  comp.setForeground(new Color(22, 163, 74));    // vert
                            comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                        } catch (NumberFormatException ignored) {}
                    }
                    setHorizontalAlignment(SwingConstants.CENTER);
                    return comp;
                }
            }
        );

        // Largeurs des colonnes
        tableClients.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableClients.getColumnModel().getColumn(1).setPreferredWidth(220);
        tableClients.getColumnModel().getColumn(2).setPreferredWidth(110);
        tableClients.getColumnModel().getColumn(3).setPreferredWidth(170);
        tableClients.getColumnModel().getColumn(4).setPreferredWidth(90);
        tableClients.getColumnModel().getColumn(5).setPreferredWidth(110);
        tableClients.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane scroll = new JScrollPane(tableClients);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /** Panneau inférieur : bouton de génération PDF et statut. */
    private JPanel construirePanneauBas() {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBackground(new Color(248, 250, 252));
        panel.setBorder(new EmptyBorder(8, 0, 0, 0));

        // Légende couleurs
        JPanel legende = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        legende.setBackground(new Color(248, 250, 252));
        legende.add(pastille(new Color(220, 38, 38),  "≤ 15 jours"));
        legende.add(pastille(new Color(217, 119, 6),  "≤ 30 jours"));
        legende.add(pastille(new Color(22, 163, 74),  "≤ 60 jours"));

        // Bouton PDF + statut
        JPanel droite = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        droite.setBackground(new Color(248, 250, 252));

        lblStatut = new JLabel(" ");
        lblStatut.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblStatut.setForeground(new Color(22, 163, 74));

        btnGenererPdf = creerBouton("Générer le PDF de relance…", new Color(220, 38, 38));
        btnGenererPdf.setEnabled(false);
        btnGenererPdf.addActionListener(e -> genererPdf());

        droite.add(lblStatut);
        droite.add(btnGenererPdf);

        panel.add(legende, BorderLayout.WEST);
        panel.add(droite,  BorderLayout.EAST);
        return panel;
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    /** Actualise la liste des clients à relancer depuis la base. */
    private void actualiserListe() {
        modeleTable.setRowCount(0);
        btnGenererPdf.setEnabled(false);
        lblStatut.setText("Chargement en cours…");
        lblStatut.setForeground(new Color(100, 116, 139));

        // Chargement dans un thread séparé pour ne pas bloquer l'UI
        SwingWorker<List<Client>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Client> doInBackground() {
                return controller.chargerTousLesClients().stream()
                    .filter(c -> {
                        ContratMaintenance ct = c.getLeContrat();
                        return ct != null && ct.estValide() && ct.getJoursRestants() <= 60;
                    })
                    .sorted((a, b) -> Long.compare(
                        a.getLeContrat().getJoursRestants(),
                        b.getLeContrat().getJoursRestants()
                    ))
                    .collect(java.util.stream.Collectors.toList());
            }

            @Override
            protected void done() {
                try {
                    List<Client> clients = get();
                    for (Client c : clients) {
                        ContratMaintenance ct = c.getLeContrat();
                        modeleTable.addRow(new Object[]{
                            c.getNumClient(),
                            c.getRaisonSociale(),
                            c.getTelClient(),
                            c.getEmail(),
                            ct.getNumContrat(),
                            ct.getDateEcheance().format(FMT),
                            ct.getJoursRestants() + " j"
                        });
                    }

                    int nb = clients.size();
                    if (nb == 0) {
                        lblNbClients.setText("Aucun client à relancer dans les 60 prochains jours.");
                        lblNbClients.setForeground(new Color(22, 163, 74));
                        lblStatut.setText("Liste vide.");
                    } else {
                        lblNbClients.setText(nb + " client(s) à relancer — triés par urgence.");
                        lblNbClients.setForeground(new Color(220, 38, 38));
                        lblStatut.setText("Liste chargée.");
                        lblStatut.setForeground(new Color(22, 163, 74));
                        btnGenererPdf.setEnabled(true);
                    }
                } catch (Exception ex) {
                    lblStatut.setText("Erreur : " + ex.getMessage());
                    lblStatut.setForeground(new Color(220, 38, 38));
                }
            }
        };
        worker.execute();
    }

    /** Ouvre un sélecteur de fichier et génère le PDF de relance. */
    private void genererPdf() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Enregistrer le PDF de relance");
        chooser.setSelectedFile(new java.io.File("relance_contrats_cashcash.pdf"));
        chooser.setFileFilter(new FileNameExtensionFilter("Fichiers PDF (*.pdf)", "pdf"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String chemin = chooser.getSelectedFile().getAbsolutePath();
            if (!chemin.endsWith(".pdf")) chemin += ".pdf";

            final String cheminFinal = chemin;
            lblStatut.setText("Génération en cours…");
            lblStatut.setForeground(new Color(100, 116, 139));
            btnGenererPdf.setEnabled(false);

            SwingWorker<Integer, Void> worker = new SwingWorker<>() {
                @Override protected Integer doInBackground() {
                    return controller.genererPdfRelance(cheminFinal);
                }
                @Override protected void done() {
                    try {
                        int nb = get();
                        if (nb > 0) {
                            lblStatut.setText("✓ PDF généré : " + nb + " client(s) — " + cheminFinal);
                            lblStatut.setForeground(new Color(22, 163, 74));
                            JOptionPane.showMessageDialog(PanelRelance.this,
                                "PDF de relance généré avec succès !\n"
                                + nb + " client(s) inclus.\n\n" + cheminFinal,
                                "Succès", JOptionPane.INFORMATION_MESSAGE);
                        } else if (nb == 0) {
                            lblStatut.setText("Aucun client à relancer.");
                        }
                    } catch (Exception ex) {
                        lblStatut.setText("Erreur : " + ex.getMessage());
                        lblStatut.setForeground(new Color(220, 38, 38));
                    }
                    btnGenererPdf.setEnabled(true);
                }
            };
            worker.execute();
        }
    }

    // ── Utilitaires ───────────────────────────────────────────────────────────

    /**
     * Crée un label "pastille colorée + texte" pour la légende.
     *
     * @param couleur Couleur de la pastille.
     * @param texte   Texte de la légende.
     * @return JLabel configuré.
     */
    private JLabel pastille(Color couleur, String texte) {
        JLabel lbl = new JLabel("● " + texte);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(couleur);
        return lbl;
    }

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
