package com.cashcash.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un contrat de maintenance souscrit par un client pour
 * tout ou partie de ses matériels.
 * Un contrat a une durée d'un an et peut être renouvelé.
 */
public class ContratMaintenance {

    // ── Attributs privés ──────────────────────────────────────────────────────
    private String     numContrat;
    private LocalDate  dateSignature;      // date du dernier renouvellement (ou signature initiale)
    private LocalDate  dateSignatureInitiale; // conservée sans modification
    private LocalDate  dateEcheance;
    private double     tauxAnnuel;

    /** Collection de tous les matériels couverts par ce contrat. */
    private List<Materiel> lesMaterielsAssures;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit un ContratMaintenance.
     *
     * @param numContrat              Numéro unique du contrat (ex : "C-0001").
     * @param dateSignature           Date de la dernière signature / renouvellement.
     * @param dateSignatureInitiale   Date de la toute première signature (immuable).
     * @param dateEcheance            Date d'échéance du contrat.
     * @param tauxAnnuel              Taux annuel appliqué au prix des matériels (%).
     */
    public ContratMaintenance(String numContrat, LocalDate dateSignature,
                              LocalDate dateSignatureInitiale, LocalDate dateEcheance,
                              double tauxAnnuel) {
        this.numContrat             = numContrat;
        this.dateSignature          = dateSignature;
        this.dateSignatureInitiale  = dateSignatureInitiale;
        this.dateEcheance           = dateEcheance;
        this.tauxAnnuel             = tauxAnnuel;
        this.lesMaterielsAssures    = new ArrayList<>();
    }

    // ── Méthodes métier (spécifiées dans l'annexe) ───────────────────────────

    /**
     * Renvoie le nombre de jours restants avant que le contrat arrive à échéance.
     * Retourne 0 si le contrat est déjà expiré.
     *
     * @return Nombre de jours avant l'échéance (>= 0).
     */
    public long getJoursRestants() {
        long jours = ChronoUnit.DAYS.between(LocalDate.now(), dateEcheance);
        return Math.max(0, jours);
    }

    /**
     * Indique si le contrat est valide : la date du jour est comprise entre
     * la date de signature et la date d'échéance.
     *
     * @return true si le contrat est en cours de validité, false sinon.
     */
    public boolean estValide() {
        LocalDate aujourd_hui = LocalDate.now();
        return !aujourd_hui.isBefore(dateSignature) && !aujourd_hui.isAfter(dateEcheance);
    }

    /**
     * Ajoute un matériel à la liste des matériels assurés, à condition que
     * la date de signature du contrat soit antérieure à la date d'installation
     * du matériel.
     *
     * @param unMateriel Matériel à ajouter au contrat.
     */
    public void ajouteMateriel(Materiel unMateriel) {
        if (unMateriel.getDateInstallation() != null
                && !dateSignature.isAfter(unMateriel.getDateInstallation())) {
            lesMaterielsAssures.add(unMateriel);
        }
    }

    /**
     * Calcule le montant annuel du contrat en appliquant le taux
     * au prix de vente total des matériels couverts.
     *
     * @return Montant annuel en euros.
     */
    public double calculerMontantAnnuel() {
        double totalPrix = lesMaterielsAssures.stream()
                .mapToDouble(Materiel::getPrixVente)
                .sum();
        return totalPrix * tauxAnnuel / 100.0;
    }

    // ── Accesseurs ────────────────────────────────────────────────────────────

    /** @return Numéro du contrat. */
    public String    getNumContrat()            { return numContrat; }

    /** @return Date de signature (ou dernier renouvellement). */
    public LocalDate getDateSignature()         { return dateSignature; }

    /** @return Date de première signature (immuable). */
    public LocalDate getDateSignatureInitiale() { return dateSignatureInitiale; }

    /** @return Date d'échéance du contrat. */
    public LocalDate getDateEcheance()          { return dateEcheance; }

    /** @return Taux annuel en pourcentage. */
    public double    getTauxAnnuel()            { return tauxAnnuel; }

    /** @return Liste des matériels assurés par ce contrat. */
    public List<Materiel> getLesMaterielsAssures() { return new ArrayList<>(lesMaterielsAssures); }

    // ── Mutateurs ─────────────────────────────────────────────────────────────

    /** @param numContrat Nouveau numéro de contrat. */
    public void setNumContrat(String numContrat)               { this.numContrat    = numContrat; }

    /** @param dateSignature Nouvelle date de signature. */
    public void setDateSignature(LocalDate dateSignature)      { this.dateSignature = dateSignature; }

    /** @param dateEcheance Nouvelle date d'échéance. */
    public void setDateEcheance(LocalDate dateEcheance)        { this.dateEcheance  = dateEcheance; }

    /** @param tauxAnnuel Nouveau taux annuel. */
    public void setTauxAnnuel(double tauxAnnuel)               { this.tauxAnnuel    = tauxAnnuel; }
}
