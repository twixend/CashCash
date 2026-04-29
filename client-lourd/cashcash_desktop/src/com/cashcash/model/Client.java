package com.cashcash.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représente un client de la société CashCash.
 * Un client peut posséder plusieurs matériels et souscrire un contrat de maintenance.
 */
public class Client {

    // ── Attributs privés (conformes à l'annexe) ───────────────────────────────
    private String             numClient;
    private String             raisonSociale;
    private String             siren;
    private String             codeApe;
    private String             adresse;
    private String             telClient;
    private String             email;
    private int                dureeDeplacement; // en minutes
    private int                distanceKm;

    /** Tous les matériels du client (avec et sans contrat). */
    private List<Materiel>     lesMateriels;

    /** Contrat de maintenance — peut être null si le client n'en possède pas. */
    private ContratMaintenance leContrat;

    // ── Constructeur ──────────────────────────────────────────────────────────

    /**
     * Construit un Client avec ses informations administratives.
     *
     * @param numClient        Identifiant unique du client.
     * @param raisonSociale    Raison sociale de l'entreprise cliente.
     * @param siren            Numéro SIREN (14 chiffres).
     * @param codeApe          Code APE de l'activité.
     * @param adresse          Adresse postale complète.
     * @param telClient        Numéro de téléphone.
     * @param email            Adresse email.
     * @param dureeDeplacement Durée moyenne d'un déplacement en minutes.
     * @param distanceKm       Distance kilométrique depuis l'agence.
     */
    public Client(String numClient, String raisonSociale, String siren, String codeApe,
                  String adresse, String telClient, String email,
                  int dureeDeplacement, int distanceKm) {
        this.numClient        = numClient;
        this.raisonSociale    = raisonSociale;
        this.siren            = siren;
        this.codeApe          = codeApe;
        this.adresse          = adresse;
        this.telClient        = telClient;
        this.email            = email;
        this.dureeDeplacement = dureeDeplacement;
        this.distanceKm       = distanceKm;
        this.lesMateriels     = new ArrayList<>();
        this.leContrat        = null;
    }

    // ── Méthodes métier (spécifiées dans l'annexe) ───────────────────────────

    /**
     * Retourne l'ensemble des matériels du client (avec et sans contrat).
     *
     * @return Liste de tous les matériels du client.
     */
    public List<Materiel> getMateriels() {
        return new ArrayList<>(lesMateriels);
    }

    /**
     * Retourne l'ensemble des matériels pour lesquels le client a souscrit
     * un contrat de maintenance encore valide à la date du jour.
     *
     * @return Liste des matériels sous contrat valide, ou liste vide si aucun contrat.
     */
    public List<Materiel> getMaterielsSousContrat() {
        if (leContrat == null || !leContrat.estValide()) {
            return new ArrayList<>();
        }
        return leContrat.getLesMaterielsAssures();
    }

    /**
     * Retourne les matériels du client qui ne sont couverts par aucun contrat valide.
     *
     * @return Liste des matériels hors contrat.
     */
    public List<Materiel> getMaterielsHorsContrat() {
        List<Materiel> sousContrat = getMaterielsSousContrat();
        List<String>   numSeriesSousContrat = sousContrat.stream()
                .map(Materiel::getNumSerie)
                .collect(Collectors.toList());
        return lesMateriels.stream()
                .filter(m -> !numSeriesSousContrat.contains(m.getNumSerie()))
                .collect(Collectors.toList());
    }

    /**
     * Indique si le client dispose d'un contrat de maintenance actuellement valide.
     *
     * @return true si le client est assuré, false sinon.
     */
    public boolean estAssure() {
        return leContrat != null && leContrat.estValide();
    }

    /**
     * Ajoute un matériel à la liste des matériels du client.
     *
     * @param unMateriel Matériel à ajouter.
     */
    public void ajouterMateriel(Materiel unMateriel) {
        if (unMateriel != null) {
            lesMateriels.add(unMateriel);
        }
    }

    // ── Accesseurs ────────────────────────────────────────────────────────────

    /** @return Identifiant unique du client. */
    public String             getNumClient()        { return numClient; }

    /** @return Raison sociale du client. */
    public String             getRaisonSociale()    { return raisonSociale; }

    /** @return Numéro SIREN. */
    public String             getSiren()            { return siren; }

    /** @return Code APE. */
    public String             getCodeApe()          { return codeApe; }

    /** @return Adresse postale. */
    public String             getAdresse()          { return adresse; }

    /** @return Numéro de téléphone. */
    public String             getTelClient()        { return telClient; }

    /** @return Adresse email. */
    public String             getEmail()            { return email; }

    /** @return Durée de déplacement en minutes. */
    public int                getDureeDeplacement() { return dureeDeplacement; }

    /** @return Distance kilométrique depuis l'agence. */
    public int                getDistanceKm()       { return distanceKm; }

    /** @return Contrat de maintenance du client, ou null s'il n'en a pas. */
    public ContratMaintenance getLeContrat()        { return leContrat; }

    // ── Mutateurs ─────────────────────────────────────────────────────────────

    /** @param raisonSociale Nouvelle raison sociale. */
    public void setRaisonSociale(String raisonSociale)       { this.raisonSociale    = raisonSociale; }

    /** @param siren Nouveau SIREN. */
    public void setSiren(String siren)                       { this.siren            = siren; }

    /** @param codeApe Nouveau code APE. */
    public void setCodeApe(String codeApe)                   { this.codeApe          = codeApe; }

    /** @param adresse Nouvelle adresse. */
    public void setAdresse(String adresse)                   { this.adresse          = adresse; }

    /** @param telClient Nouveau téléphone. */
    public void setTelClient(String telClient)               { this.telClient        = telClient; }

    /** @param email Nouveau mail. */
    public void setEmail(String email)                       { this.email            = email; }

    /** @param dureeDeplacement Nouvelle durée de déplacement (minutes). */
    public void setDureeDeplacement(int dureeDeplacement)    { this.dureeDeplacement = dureeDeplacement; }

    /** @param distanceKm Nouvelle distance kilométrique. */
    public void setDistanceKm(int distanceKm)               { this.distanceKm       = distanceKm; }

    /**
     * Associe un contrat de maintenance au client.
     * @param leContrat Contrat de maintenance (peut être null).
     */
    public void setLeContrat(ContratMaintenance leContrat)   { this.leContrat        = leContrat; }
}
