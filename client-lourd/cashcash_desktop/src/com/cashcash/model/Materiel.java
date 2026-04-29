package com.cashcash.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Représente un matériel vendu et installé chez un client CashCash.
 * Contient la méthode xmlMateriel() qui génère le fragment XML
 * conforme au format défini dans l'annexe du sujet.
 */
public class Materiel {

    // ── Attributs privés ──────────────────────────────────────────────────────
    private String      numSerie;
    private LocalDate   dateVente;
    private LocalDate   dateInstallation;
    private double      prixVente;
    private String      emplacement;
    private TypeMateriel leType;

    /** Nombre de jours avant échéance du contrat — null si hors contrat. */
    private Long nbJoursAvantEcheance;

    private static final DateTimeFormatter FORMAT_XML = DateTimeFormatter.ofPattern("dd-MM-yy");

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit un Materiel avec toutes ses caractéristiques.
     *
     * @param numSerie         Numéro de série unique identifiant le matériel.
     * @param dateVente        Date de vente du matériel au client.
     * @param dateInstallation Date d'installation chez le client.
     * @param prixVente        Prix de vente en euros.
     * @param emplacement      Emplacement dans le magasin du client.
     * @param leType           Type du matériel (référence et famille).
     */
    public Materiel(String numSerie, LocalDate dateVente, LocalDate dateInstallation,
                    double prixVente, String emplacement, TypeMateriel leType) {
        this.numSerie         = numSerie;
        this.dateVente        = dateVente;
        this.dateInstallation = dateInstallation;
        this.prixVente        = prixVente;
        this.emplacement      = emplacement;
        this.leType           = leType;
        this.nbJoursAvantEcheance = null;
    }

    // ── Méthode métier principale ─────────────────────────────────────────────

    /**
     * Retourne la chaîne correspondant au fragment XML représentant ce matériel,
     * conforme au format de l'annexe du sujet (materielClientcliXXXXX.xml).
     * Si nbJoursAvantEcheance est renseigné, la balise est incluse (matériel sous contrat).
     *
     * @return Fragment XML du matériel sous forme de String indenté.
     */
    public String xmlMateriel() {
        StringBuilder sb = new StringBuilder();
        sb.append("    <materiel numSerie=\"").append(numSerie).append("\">\n");

        // Type
        sb.append("        <type refInterne=\"")
          .append(leType.getReferenceInterne())
          .append("\" libelle=\"")
          .append(leType.getLibelleTypeMateriel())
          .append("\"/>\n");

        // Famille
        Famille f = leType.getLaFamille();
        sb.append("        <famille codeFamille=\"")
          .append(f.getCodeFamille())
          .append("\" libelle=\"")
          .append(f.getLibelleFamille())
          .append("\"/>\n");

        // Dates et prix
        sb.append("        <date_vente>")
          .append(dateVente != null ? dateVente.format(FORMAT_XML) : "")
          .append("</date_vente>\n");

        sb.append("        <date_installation>")
          .append(dateInstallation != null ? dateInstallation.format(FORMAT_XML) : "")
          .append("</date_installation>\n");

        sb.append("        <prix_vente>")
          .append(String.format("%.2f", prixVente))
          .append("</prix_vente>\n");

        sb.append("        <emplacement>")
          .append(emplacement)
          .append("</emplacement>\n");

        // Balise optionnelle : présente uniquement si le matériel est sous contrat valide
        if (nbJoursAvantEcheance != null) {
            sb.append("        <nbJourAvantEcheance>")
              .append(nbJoursAvantEcheance)
              .append("</nbJourAvantEcheance>\n");
        }

        sb.append("    </materiel>\n");
        return sb.toString();
    }

    // ── Accesseurs ────────────────────────────────────────────────────────────

    /** @return Numéro de série du matériel. */
    public String      getNumSerie()              { return numSerie; }

    /** @return Date de vente. */
    public LocalDate   getDateVente()             { return dateVente; }

    /** @return Date d'installation. */
    public LocalDate   getDateInstallation()      { return dateInstallation; }

    /** @return Prix de vente en euros. */
    public double      getPrixVente()             { return prixVente; }

    /** @return Emplacement dans le magasin. */
    public String      getEmplacement()           { return emplacement; }

    /** @return Type du matériel. */
    public TypeMateriel getLeType()               { return leType; }

    /** @return Nombre de jours avant échéance du contrat, ou null si hors contrat. */
    public Long        getNbJoursAvantEcheance()  { return nbJoursAvantEcheance; }

    // ── Mutateurs ─────────────────────────────────────────────────────────────

    /** @param numSerie Nouveau numéro de série. */
    public void setNumSerie(String numSerie)                      { this.numSerie              = numSerie; }

    /** @param dateVente Nouvelle date de vente. */
    public void setDateVente(LocalDate dateVente)                 { this.dateVente             = dateVente; }

    /** @param dateInstallation Nouvelle date d'installation. */
    public void setDateInstallation(LocalDate dateInstallation)   { this.dateInstallation      = dateInstallation; }

    /** @param prixVente Nouveau prix de vente. */
    public void setPrixVente(double prixVente)                    { this.prixVente             = prixVente; }

    /** @param emplacement Nouvel emplacement. */
    public void setEmplacement(String emplacement)                { this.emplacement           = emplacement; }

    /** @param leType Nouveau type de matériel. */
    public void setLeType(TypeMateriel leType)                    { this.leType                = leType; }

    /**
     * Renseigne le nombre de jours avant échéance (utilisé pour les matériels sous contrat).
     * @param nbJoursAvantEcheance Nombre de jours restants, ou null si hors contrat.
     */
    public void setNbJoursAvantEcheance(Long nbJoursAvantEcheance) { this.nbJoursAvantEcheance = nbJoursAvantEcheance; }
}
