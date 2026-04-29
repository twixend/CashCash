package com.cashcash;

import com.cashcash.controller.MainController;
import com.cashcash.view.MainFrame;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Point d'entrée de l'application CashCash Desktop (client lourd – Jalon 3).
 * Initialise le contrôleur, la connexion base de données, puis lance l'interface.
 */
public class Main {

    /**
     * Lance l'application CashCash Desktop.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {

        // Utiliser le Look & Feel natif du système d'exploitation
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // En cas d'échec, Swing utilise son thème par défaut
        }

        SwingUtilities.invokeLater(() -> {
            // ── Connexion à la base de données ────────────────────────────────
            MainController controller;
            try {
                controller = new MainController();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Impossible de se connecter à la base de données MySQL.\n\n"
                    + "Vérifiez :\n"
                    + "  • Que mysql-connector-j.jar est dans le dossier lib/\n"
                    + "  • Que le serveur MySQL est accessible\n"
                    + "  • Les identifiants de connexion dans PersistanceSQL.java\n\n"
                    + "Détail : " + e.getMessage(),
                    "Erreur de connexion",
                    JOptionPane.ERROR_MESSAGE
                );
                System.exit(1);
                return;
            }

            // ── Lancement de la fenêtre principale ────────────────────────────
            MainFrame frame = new MainFrame(controller);
            frame.setVisible(true);
        });
    }
}
