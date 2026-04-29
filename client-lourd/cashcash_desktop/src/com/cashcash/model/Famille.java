package com.cashcash.model;

/**
 * Représente une famille de matériels CashCash.
 * Notation Pascal respectée. Identificateurs sans accents ni caractères spéciaux.
 */
public class Famille {

    // ── Attributs privés ──────────────────────────────────────────────────────
    private String codeFamille;
    private String libelleFamille;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit une Famille avec son code et son libellé.
     *
     * @param codeFamille    Code unique identifiant la famille (ex : "CA").
     * @param libelleFamille Libellé descriptif de la famille (ex : "Caisses enregistreuses").
     */
    public Famille(String codeFamille, String libelleFamille) {
        this.codeFamille    = codeFamille;
        this.libelleFamille = libelleFamille;
    }

    // ── Accesseurs ────────────────────────────────────────────────────────────

    /**
     * Retourne le code de la famille.
     * @return Code famille (ex : "CA").
     */
    public String getCodeFamille()    { return codeFamille; }

    /**
     * Retourne le libellé de la famille.
     * @return Libellé famille (ex : "Caisses enregistreuses").
     */
    public String getLibelleFamille() { return libelleFamille; }

    // ── Mutateurs ─────────────────────────────────────────────────────────────

    /** @param codeFamille Nouveau code famille. */
    public void setCodeFamille(String codeFamille)       { this.codeFamille    = codeFamille; }

    /** @param libelleFamille Nouveau libellé famille. */
    public void setLibelleFamille(String libelleFamille) { this.libelleFamille = libelleFamille; }
}
