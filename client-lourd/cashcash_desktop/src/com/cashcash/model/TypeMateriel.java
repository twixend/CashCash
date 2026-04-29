package com.cashcash.model;

/**
 * Représente un type de matériel caractérisé par une référence interne
 * et une famille.
 */
public class TypeMateriel {

    // ── Attributs privés ──────────────────────────────────────────────────────
    private String    referenceInterne;
    private String    libelleTypeMateriel;
    private Famille   laFamille;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit un TypeMateriel.
     *
     * @param referenceInterne     Référence interne unique (ex : "A506").
     * @param libelleTypeMateriel  Libellé du type (ex : "Souris sans fil Logitech S").
     * @param laFamille            Famille à laquelle appartient ce type.
     */
    public TypeMateriel(String referenceInterne, String libelleTypeMateriel, Famille laFamille) {
        this.referenceInterne    = referenceInterne;
        this.libelleTypeMateriel = libelleTypeMateriel;
        this.laFamille           = laFamille;
    }

    // ── Accesseurs ────────────────────────────────────────────────────────────

    /** @return Référence interne du type (ex : "A506"). */
    public String  getReferenceInterne()    { return referenceInterne; }

    /** @return Libellé du type de matériel. */
    public String  getLibelleTypeMateriel() { return libelleTypeMateriel; }

    /** @return Famille associée à ce type de matériel. */
    public Famille getLaFamille()           { return laFamille; }

    // ── Mutateurs ─────────────────────────────────────────────────────────────

    /** @param referenceInterne Nouvelle référence interne. */
    public void setReferenceInterne(String referenceInterne)       { this.referenceInterne    = referenceInterne; }

    /** @param libelleTypeMateriel Nouveau libellé. */
    public void setLibelleTypeMateriel(String libelleTypeMateriel) { this.libelleTypeMateriel = libelleTypeMateriel; }

    /** @param laFamille Nouvelle famille. */
    public void setLaFamille(Famille laFamille)                    { this.laFamille           = laFamille; }
}
