package com.cashcash.service;

import com.cashcash.dao.PersistanceSQL;
import com.cashcash.model.Client;
import com.cashcash.model.ContratMaintenance;
import com.cashcash.model.Materiel;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;

/**
 * Service principal du client lourd CashCash.
 * Orchestre les opérations métier : génération XML, gestion des contrats
 * et génération du PDF de relance. Utilise PersistanceSQL comme couche de données.
 *
 * Conforme à la classe GestionMateriels décrite dans l'annexe du sujet (Jalon 3).
 */
public class GestionMateriels {

    // ── Attribut privé (conforme à l'annexe) ─────────────────────────────────
    /** Attribut qui permet de rendre les objets métiers accessibles. */
    private PersistanceSQL donnees;

    // ── Constructeur (conforme à l'annexe) ────────────────────────────────────

    /**
     * Construit un objet GestionMateriels avec le modèle de persistance associé.
     *
     * @param lesDonnees Instance de PersistanceSQL ouverte et prête à l'emploi.
     */
    public GestionMateriels(PersistanceSQL lesDonnees) {
        this.donnees = lesDonnees;
    }

    // ── Méthodes principales (conformes à l'annexe) ───────────────────────────

    /**
     * Retourne l'objet Client qui possède l'identifiant idClient passé en paramètre,
     * avec l'ensemble de ses matériels et son contrat chargés.
     * Retourne null si aucun client ne possède cet identifiant.
     *
     * @param idClient Identifiant numérique du client.
     * @return Instance de Client chargée depuis la base, ou null.
     * @throws SQLException En cas d'erreur d'accès à la base.
     */
    public Client getClient(int idClient) throws SQLException {
        return (Client) donnees.chargerDepuisBase(String.valueOf(idClient), "Client");
    }

    /**
     * Retourne une chaîne de caractères qui représente le document XML complet
     * de la liste des matériels du client passé en paramètre.
     * Le format respecte exactement le modèle de l'annexe (materielClientcliXXXXX.xml) :
     * les matériels sous contrat valide sont dans &lt;sousContrat&gt;,
     * les autres dans &lt;horsContrat&gt;.
     *
     * @param unClient Client dont on génère le XML.
     * @return Document XML sous forme de String.
     */
    public String xmlClient(Client unClient) {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<listeMateriel>\n");
        sb.append("  <materiels idClient=\"cli").append(unClient.getNumClient()).append("\">\n");

        // ── Matériels sous contrat valide ────────────────────────────────────
        List<Materiel> sousContrat = unClient.getMaterielsSousContrat();
        sb.append("    <sousContrat>\n");
        if (sousContrat.isEmpty()) {
            sb.append("      <!-- Aucun matériel sous contrat valide -->\n");
        } else {
            for (Materiel m : sousContrat) {
                sb.append(m.xmlMateriel());
            }
        }
        sb.append("    </sousContrat>\n");

        // ── Matériels hors contrat ───────────────────────────────────────────
        List<Materiel> horsContrat = unClient.getMaterielsHorsContrat();
        sb.append("    <horsContrat>\n");
        if (horsContrat.isEmpty()) {
            sb.append("      <!-- Aucun matériel hors contrat -->\n");
        } else {
            for (Materiel m : horsContrat) {
                sb.append(m.xmlMateriel());
            }
        }
        sb.append("    </horsContrat>\n");

        sb.append("  </materiels>\n");
        sb.append("</listeMateriel>\n");

        return sb.toString();
    }

    /**
     * Retourne true si la chaîne XML passée en paramètre est un XML bien formé.
     * (Validation de la bonne formation syntaxique sans DTD externe pour simplicité.)
     *
     * @param xml Chaîne XML à valider.
     * @return true si le XML est valide syntaxiquement, false sinon.
     */
    public static boolean xmlClientValide(String xml) {
        if (xml == null || xml.isBlank()) return false;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new StringReader(xml)), new org.xml.sax.helpers.DefaultHandler());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ── Gestion des contrats de maintenance ───────────────────────────────────

    /**
     * Crée un nouveau contrat de maintenance pour un client et lui associe
     * les matériels sélectionnés.
     *
     * @param idClient     Identifiant du client.
     * @param numSeries    Liste des numéros de série des matériels à couvrir.
     * @param tauxAnnuel   Taux annuel du contrat (%).
     * @return Numéro du contrat créé.
     * @throws SQLException En cas d'erreur SQL.
     */
    public String creerContratAvecMateriels(String idClient, List<String> numSeries,
                                             double tauxAnnuel) throws SQLException {
        String numContrat = donnees.getProchainNumeroContrat();
        donnees.creerContrat(idClient, numContrat, tauxAnnuel);
        for (String numSerie : numSeries) {
            donnees.ajouterMaterielAuContrat(numContrat, numSerie);
        }
        return numContrat;
    }

    /**
     * Retourne la liste de tous les clients dont le contrat expire dans moins de
     * 60 jours, pour générer les courriers de relance.
     *
     * @return Liste des clients avec contrat expirant sous 60 jours.
     * @throws SQLException En cas d'erreur SQL.
     */
    public List<Client> getClientsARelancer() throws SQLException {
        List<Client> tousLesClients = donnees.chargerTousLesClients();
        List<Client> aRelancer = new java.util.ArrayList<>();

        for (Client c : tousLesClients) {
            ContratMaintenance contrat = c.getLeContrat();
            if (contrat != null && contrat.estValide() && contrat.getJoursRestants() <= 60) {
                aRelancer.add(c);
            }
        }
        return aRelancer;
    }

    /**
     * Retourne la liste de tous les clients chargés depuis la base.
     *
     * @return Liste complète des clients.
     * @throws SQLException En cas d'erreur SQL.
     */
    public List<Client> getTousLesClients() throws SQLException {
        return donnees.chargerTousLesClients();
    }

    /**
     * Retourne l'instance de PersistanceSQL utilisée par ce service.
     *
     * @return Objet PersistanceSQL associé.
     */
    public PersistanceSQL getDonnees() {
        return donnees;
    }
}
