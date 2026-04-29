package com.cashcash.util;

import com.cashcash.model.Client;
import com.cashcash.model.ContratMaintenance;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Générateur de fichiers PDF en Java pur, sans bibliothèque externe.
 * Produit un PDF 1.4 avec du texte simple, conforme aux spécifications
 * du Jalon 3 : courriers de relance pour les contrats expirant sous 60 jours.
 */
public class GenerateurPdf {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Génère un fichier PDF listant les clients dont le contrat expire sous 60 jours.
     * Chaque client occupe une section avec ses coordonnées et les détails de son contrat.
     *
     * @param clients       Liste des clients à relancer.
     * @param cheminFichier Chemin complet du fichier PDF à créer.
     * @throws IOException En cas d'erreur d'écriture.
     */
    public static void genererPdfRelance(List<Client> clients, String cheminFichier)
            throws IOException {

        // Construire chaque page en contenu PDF (stream de texte)
        List<byte[]> pagesContenu  = new ArrayList<>();
        List<Integer> pagesLongueur = new ArrayList<>();

        for (Client client : clients) {
            byte[] contenu = construirePageClient(client);
            pagesContenu.add(contenu);
            pagesLongueur.add(contenu.length);
        }

        // Assemblage du document PDF
        try (FileOutputStream fos = new FileOutputStream(cheminFichier)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            // ── En-tête PDF ───────────────────────────────────────────────────
            ecrire(buffer, "%PDF-1.4\n");

            // Offsets des objets (pour la table xref)
            List<Integer> offsets = new ArrayList<>();

            // ── Objet 1 : Catalogue ───────────────────────────────────────────
            offsets.add(buffer.size());
            ecrire(buffer, "1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");

            // ── Objet 2 : Pages (rempli après) ───────────────────────────────
            // Réservé — sera construit avec la liste des pages
            int objetPages = buffer.size();
            offsets.add(objetPages);

            int nbPages = clients.size();
            StringBuilder kidsBuilder = new StringBuilder("[");
            for (int i = 0; i < nbPages; i++) {
                kidsBuilder.append(3 + i * 2).append(" 0 R ");
            }
            kidsBuilder.append("]");
            ecrire(buffer, "2 0 obj\n<< /Type /Pages /Kids " + kidsBuilder + " /Count " + nbPages + " >>\nendobj\n");

            // ── Objets 3..N : une Page + un ContentStream par client ──────────
            for (int i = 0; i < nbPages; i++) {
                int objetPage    = 3 + i * 2;
                int objetContenu = 4 + i * 2;

                // Objet Page
                offsets.add(buffer.size());
                ecrire(buffer,
                    objetPage + " 0 obj\n"
                    + "<< /Type /Page /Parent 2 0 R\n"
                    + "   /MediaBox [0 0 595 842]\n"                         // A4
                    + "   /Contents " + objetContenu + " 0 R\n"
                    + "   /Resources << /Font << /F1 << /Type /Font "
                    + "/Subtype /Type1 /BaseFont /Helvetica >> "
                    + "/F2 << /Type /Font /Subtype /Type1 /BaseFont /Helvetica-Bold >> >> >> >>\n"
                    + "endobj\n"
                );

                // Objet ContentStream
                byte[] contenu = pagesContenu.get(i);
                offsets.add(buffer.size());
                ecrire(buffer,
                    objetContenu + " 0 obj\n"
                    + "<< /Length " + contenu.length + " >>\n"
                    + "stream\n"
                );
                buffer.write(contenu);
                ecrire(buffer, "\nendstream\nendobj\n");
            }

            // ── Table xref ────────────────────────────────────────────────────
            int xrefOffset = buffer.size();
            int totalObjets = 2 + nbPages * 2; // catalogue + pages + (page+stream)*n
            ecrire(buffer, "xref\n0 " + (totalObjets + 1) + "\n");
            ecrire(buffer, "0000000000 65535 f \n");
            for (int offset : offsets) {
                ecrire(buffer, String.format("%010d 00000 n \n", offset));
            }

            // ── Trailer ───────────────────────────────────────────────────────
            ecrire(buffer,
                "trailer\n<< /Size " + (totalObjets + 1) + " /Root 1 0 R >>\n"
                + "startxref\n" + xrefOffset + "\n%%EOF\n"
            );

            fos.write(buffer.toByteArray());
        }
    }

    // ── Construction du contenu d'une page ────────────────────────────────────

    /**
     * Construit le flux de contenu PDF (opérateurs de texte) pour un client.
     *
     * @param client Client dont on génère la page de relance.
     * @return Tableau d'octets représentant le stream PDF.
     */
    private static byte[] construirePageClient(Client client) {
        ContratMaintenance contrat = client.getLeContrat();
        StringBuilder sb = new StringBuilder();

        float y = 780; // Position verticale de départ (bas = 0, haut = 842)

        // ── En-tête CashCash ──────────────────────────────────────────────────
        sb.append("BT\n");
        sb.append("/F2 18 Tf\n");
        sb.append("50 ").append(y).append(" Td\n");
        sb.append("(CashCash - Relance Contrat de Maintenance) Tj\n");
        sb.append("ET\n");
        y -= 10;
        sb.append(ligneHorizontale(50, y, 545));
        y -= 25;

        // Date du jour
        sb.append(texte("/F1", 10, 400, y,
            "Date : " + LocalDate.now().format(FORMAT_DATE)));
        y -= 30;

        // ── Coordonnées client ────────────────────────────────────────────────
        sb.append(texteGras("/F2", 12, 50, y, "Coordonnees client"));
        y -= 18;
        sb.append(texte("/F1", 11, 50, y, nettoyer(client.getRaisonSociale())));
        y -= 15;
        sb.append(texte("/F1", 11, 50, y, nettoyer(client.getAdresse())));
        y -= 15;
        sb.append(texte("/F1", 11, 50, y, "Tel : " + nettoyer(client.getTelClient())));
        y -= 15;
        sb.append(texte("/F1", 11, 50, y, "Email : " + nettoyer(client.getEmail())));
        y -= 30;

        // ── Détails du contrat ────────────────────────────────────────────────
        sb.append(texteGras("/F2", 12, 50, y, "Details du contrat"));
        y -= 18;

        if (contrat != null) {
            sb.append(texte("/F1", 11, 50, y,
                "Numero de contrat : " + nettoyer(contrat.getNumContrat())));
            y -= 15;
            sb.append(texte("/F1", 11, 50, y,
                "Date de signature initiale : "
                + contrat.getDateSignatureInitiale().format(FORMAT_DATE)));
            y -= 15;
            sb.append(texte("/F1", 11, 50, y,
                "Date d'echeance : " + contrat.getDateEcheance().format(FORMAT_DATE)));
            y -= 15;
            sb.append(texte("/F1", 11, 50, y,
                "Jours restants : " + contrat.getJoursRestants() + " jour(s)"));
            y -= 15;
            sb.append(texte("/F1", 11, 50, y,
                "Taux annuel : " + contrat.getTauxAnnuel() + " %"));
            y -= 15;
            sb.append(texte("/F1", 11, 50, y,
                "Montant annuel : " + String.format("%.2f", contrat.calculerMontantAnnuel()) + " EUR"));
            y -= 30;

            // ── Matériels couverts ────────────────────────────────────────────
            sb.append(texteGras("/F2", 12, 50, y, "Materiels couverts par le contrat"));
            y -= 5;
            sb.append(ligneHorizontale(50, y, 545));
            y -= 18;

            sb.append(texteGras("/F2", 10, 55,  y, "N. Serie"));
            sb.append(texteGras("/F2", 10, 150, y, "Type"));
            sb.append(texteGras("/F2", 10, 350, y, "Prix vente"));
            sb.append(texteGras("/F2", 10, 460, y, "Emplacement"));
            y -= 14;

            for (com.cashcash.model.Materiel m : contrat.getLesMaterielsAssures()) {
                sb.append(texte("/F1", 10, 55,  y, nettoyer(m.getNumSerie())));
                sb.append(texte("/F1", 10, 150, y,
                    nettoyer(m.getLeType().getLibelleTypeMateriel())));
                sb.append(texte("/F1", 10, 350, y,
                    String.format("%.2f EUR", m.getPrixVente())));
                sb.append(texte("/F1", 10, 460, y, nettoyer(m.getEmplacement())));
                y -= 14;
                if (y < 60) break; // sécurité débordement page
            }
            y -= 20;
        } else {
            sb.append(texte("/F1", 11, 50, y, "Aucun contrat en cours."));
            y -= 30;
        }

        // ── Corps du courrier de relance ──────────────────────────────────────
        y -= 10;
        sb.append(ligneHorizontale(50, y, 545));
        y -= 25;
        sb.append(texteGras("/F2", 12, 50, y, "Objet : Renouvellement de votre contrat de maintenance"));
        y -= 22;
        sb.append(texte("/F1", 11, 50, y,
            "Madame, Monsieur,"));
        y -= 18;
        sb.append(texte("/F1", 11, 50, y,
            "Votre contrat de maintenance arrive a echeance dans moins de 60 jours."));
        y -= 15;
        sb.append(texte("/F1", 11, 50, y,
            "Nous vous invitons a prendre contact avec votre agence CashCash afin"));
        y -= 15;
        sb.append(texte("/F1", 11, 50, y,
            "de proceder au renouvellement dans les meilleurs delais."));
        y -= 25;
        sb.append(texte("/F1", 11, 50, y, "Cordialement,"));
        y -= 15;
        sb.append(texteGras("/F2", 11, 50, y, "Le service commercial CashCash"));

        return sb.toString().getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);
    }

    // ── Helpers PDF ───────────────────────────────────────────────────────────

    /**
     * Retourne l'opérateur PDF pour afficher du texte à une position donnée.
     *
     * @param police  Nom de la police PDF (/F1 ou /F2).
     * @param taille  Taille en points.
     * @param x       Position X (en points, origine bas-gauche).
     * @param y       Position Y (en points).
     * @param texte   Texte à afficher.
     * @return Chaîne d'opérateurs PDF.
     */
    private static String texte(String police, int taille, float x, float y, String texte) {
        return "BT\n" + police + " " + taille + " Tf\n"
             + x + " " + y + " Td\n"
             + "(" + echapper(texte) + ") Tj\n"
             + "ET\n";
    }

    /** Variante texte en gras (utilise /F2 = Helvetica-Bold). */
    private static String texteGras(String police, int taille, float x, float y, String texte) {
        return texte(police, taille, x, y, texte);
    }

    /**
     * Retourne l'opérateur PDF pour tracer une ligne horizontale.
     *
     * @param x1 Abscisse de départ.
     * @param y  Ordonnée.
     * @param x2 Abscisse de fin.
     * @return Chaîne d'opérateurs PDF.
     */
    private static String ligneHorizontale(float x1, float y, float x2) {
        return x1 + " " + y + " m\n" + x2 + " " + y + " l\nS\n";
    }

    /**
     * Échappe les caractères spéciaux pour les chaînes littérales PDF.
     *
     * @param s Chaîne originale.
     * @return Chaîne échappée.
     */
    private static String echapper(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("(", "\\(")
                .replace(")", "\\)");
    }

    /**
     * Supprime les caractères non-ISO-8859-1 pour éviter les problèmes d'encodage PDF.
     *
     * @param s Chaîne originale.
     * @return Chaîne nettoyée.
     */
    private static String nettoyer(String s) {
        if (s == null) return "";
        return s.replaceAll("[^\\x20-\\xFF]", "?");
    }

    /**
     * Écrit une chaîne dans un ByteArrayOutputStream en ISO-8859-1.
     *
     * @param out    Flux de sortie.
     * @param texte  Texte à écrire.
     * @throws IOException En cas d'erreur d'écriture.
     */
    private static void ecrire(ByteArrayOutputStream out, String texte) throws IOException {
        out.write(texte.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1));
    }
}
