package com.cashcash.dao;

import com.cashcash.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de persistance SQL conforme à l'annexe du sujet Jalon 3.
 * Permet de charger les objets métier depuis la base MySQL et de les sauvegarder.
 *
 * Utilisation :
 * <pre>
 *   PersistanceSQL persist = new PersistanceSQL("mysql-cashcash.alwaysdata.net", 3306, "cashcash_bdd");
 *   Client leClient = (Client) persist.chargerDepuisBase("2", "Client");
 * </pre>
 */
public class PersistanceSQL {

    // ── Attributs de connexion ────────────────────────────────────────────────
    private static final String DB_USER     = "cashcash";
    private static final String DB_PASSWORD = "Kovbuc57!";

    private final String ipBase;
    private final int    port;
    private final String nomBaseDonnee;
    private Connection   connexion;

    // ── Constructeur (conforme à l'annexe) ────────────────────────────────────

    /**
     * Construit un objet PersistanceSQL et ouvre la connexion à la base MySQL.
     * Cet objet permettra de charger les données depuis la base ou de les sauvegarder.
     *
     * @param ipBase        Adresse IP ou nom d'hôte du serveur MySQL.
     * @param port          Port MySQL (généralement 3306).
     * @param nomBaseDonnee Nom de la base de données.
     * @throws SQLException En cas d'échec de connexion à la base.
     */
    public PersistanceSQL(String ipBase, int port, String nomBaseDonnee) throws SQLException {
        this.ipBase        = ipBase;
        this.port          = port;
        this.nomBaseDonnee = nomBaseDonnee;
        ouvrirConnexion();
    }

    // ── Gestion de la connexion ───────────────────────────────────────────────

    /**
     * Ouvre (ou rouvre) la connexion JDBC à la base de données.
     *
     * @throws SQLException En cas d'erreur de connexion.
     */
    private void ouvrirConnexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "Driver MySQL introuvable. Ajoutez mysql-connector-j.jar dans le dossier lib/", e);
        }
        String url = "jdbc:mysql://" + ipBase + ":" + port + "/" + nomBaseDonnee
                   + "?useSSL=false&serverTimezone=Europe/Paris&characterEncoding=UTF-8";
        this.connexion = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
    }

    /**
     * Vérifie que la connexion est ouverte et la rouvre si nécessaire.
     *
     * @throws SQLException En cas d'erreur.
     */
    private void verifierConnexion() throws SQLException {
        if (connexion == null || connexion.isClosed()) {
            ouvrirConnexion();
        }
    }

    /**
     * Ferme proprement la connexion à la base de données.
     */
    public void fermer() {
        if (connexion != null) {
            try {
                if (!connexion.isClosed()) connexion.close();
            } catch (SQLException ignored) {}
        }
    }

    // ── Méthodes principales (conformes à l'annexe) ───────────────────────────

    /**
     * Stocke les données d'un objet Client dans la base de données.
     * Met à jour le contrat de maintenance si nécessaire.
     *
     * @param unObjet Objet à persister (doit être une instance de Client ou ContratMaintenance).
     * @throws SQLException           En cas d'erreur SQL.
     * @throws IllegalArgumentException Si le type d'objet n'est pas supporté.
     */
    public void rangerDansBase(Object unObjet) throws SQLException {
        verifierConnexion();
        if (unObjet instanceof ContratMaintenance) {
            rangerContrat((ContratMaintenance) unObjet);
        } else if (unObjet instanceof Client) {
            rangerClient((Client) unObjet);
        } else {
            throw new IllegalArgumentException(
                "Type d'objet non supporté : " + unObjet.getClass().getSimpleName());
        }
    }

    /**
     * Retourne l'objet de la classe nomClasse dont l'identifiant est id,
     * chargé depuis la base de données avec l'ensemble de ses objets liés.
     * Retourne null si aucun objet ne correspond.
     *
     * @param id        Identifiant de l'objet recherché.
     * @param nomClasse Nom de la classe à instancier ("Client", "ContratMaintenance").
     * @return L'objet chargé depuis la base, ou null s'il n'existe pas.
     * @throws SQLException En cas d'erreur SQL.
     */
    public Object chargerDepuisBase(String id, String nomClasse) throws SQLException {
        verifierConnexion();
        switch (nomClasse) {
            case "Client":             return chargerClient(id);
            case "ContratMaintenance": return chargerContrat(id);
            default:
                throw new IllegalArgumentException("Classe non supportée : " + nomClasse);
        }
    }

    // ── Chargement Client ─────────────────────────────────────────────────────

    /**
     * Charge un Client depuis la base avec tous ses matériels et son contrat.
     *
     * @param idClient Identifiant du client.
     * @return Instance de Client complètement chargée, ou null.
     * @throws SQLException En cas d'erreur SQL.
     */
    private Client chargerClient(String idClient) throws SQLException {
        String sql = "SELECT id_client, raison_sociale, siren, code_ape, adresse, "
                   + "telephone, email, duree_deplacement_min, distance_km "
                   + "FROM client WHERE id_client = ?";

        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, idClient);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            Client client = new Client(
                String.valueOf(rs.getInt("id_client")),
                rs.getString("raison_sociale"),
                rs.getString("siren"),
                rs.getString("code_ape"),
                rs.getString("adresse"),
                rs.getString("telephone"),
                rs.getString("email"),
                rs.getInt("duree_deplacement_min"),
                rs.getInt("distance_km")
            );

            // Charger tous les matériels du client
            List<Materiel> tousLesMateriels = chargerMaterielsDuClient(idClient);
            for (Materiel m : tousLesMateriels) {
                client.ajouterMateriel(m);
            }

            // Charger le contrat de maintenance s'il existe
            ContratMaintenance contrat = chargerContratDuClient(idClient, tousLesMateriels);
            client.setLeContrat(contrat);

            return client;
        }
    }

    /**
     * Charge tous les matériels appartenant à un client donné.
     *
     * @param idClient Identifiant du client.
     * @return Liste des matériels du client.
     * @throws SQLException En cas d'erreur SQL.
     */
    private List<Materiel> chargerMaterielsDuClient(String idClient) throws SQLException {
        String sql = "SELECT m.num_serie, m.date_vente, m.date_installation, "
                   + "m.prix_vente, m.emplacement, "
                   + "t.ref_interne, t.libelle_type, "
                   + "f.code_famille, f.libelle_famille "
                   + "FROM materiel m "
                   + "JOIN type_materiel t ON m.ref_interne = t.ref_interne "
                   + "JOIN famille f ON t.code_famille = f.code_famille "
                   + "WHERE m.id_client = ?";

        List<Materiel> liste = new ArrayList<>();
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, idClient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Famille      famille = new Famille(
                    rs.getString("code_famille"),
                    rs.getString("libelle_famille")
                );
                TypeMateriel type = new TypeMateriel(
                    rs.getString("ref_interne"),
                    rs.getString("libelle_type"),
                    famille
                );
                Date dateVenteSQL       = rs.getDate("date_vente");
                Date dateInstallSQL     = rs.getDate("date_installation");
                LocalDate dateVente     = dateVenteSQL   != null ? dateVenteSQL.toLocalDate()   : null;
                LocalDate dateInstall   = dateInstallSQL != null ? dateInstallSQL.toLocalDate() : null;

                Materiel materiel = new Materiel(
                    rs.getString("num_serie"),
                    dateVente,
                    dateInstall,
                    rs.getDouble("prix_vente"),
                    rs.getString("emplacement"),
                    type
                );
                liste.add(materiel);
            }
        }
        return liste;
    }

    /**
     * Charge le contrat de maintenance d'un client et y associe ses matériels couverts.
     *
     * @param idClient         Identifiant du client.
     * @param tousLesMateriels Liste de tous les matériels du client (déjà chargés).
     * @return ContratMaintenance chargé, ou null si le client n'a pas de contrat.
     * @throws SQLException En cas d'erreur SQL.
     */
    private ContratMaintenance chargerContratDuClient(String idClient,
                                                      List<Materiel> tousLesMateriels)
            throws SQLException {

        String sql = "SELECT num_contrat, date_signature_initiale, date_signature, "
                   + "date_echeance, taux_annuel "
                   + "FROM contrat_maintenance WHERE id_client = ? "
                   + "ORDER BY date_echeance DESC LIMIT 1";

        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, idClient);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            ContratMaintenance contrat = new ContratMaintenance(
                rs.getString("num_contrat"),
                rs.getDate("date_signature").toLocalDate(),
                rs.getDate("date_signature_initiale").toLocalDate(),
                rs.getDate("date_echeance").toLocalDate(),
                rs.getDouble("taux_annuel")
            );

            // Associer les matériels couverts par ce contrat
            List<String> numSeriesContrat = chargerNumSeriesContrat(rs.getString("num_contrat"));
            for (Materiel m : tousLesMateriels) {
                if (numSeriesContrat.contains(m.getNumSerie())) {
                    // Renseigner le nb de jours avant échéance pour le XML
                    m.setNbJoursAvantEcheance(contrat.getJoursRestants());
                    contrat.ajouteMateriel(m);
                }
            }
            return contrat;
        }
    }

    /**
     * Récupère la liste des numéros de série couverts par un contrat donné.
     *
     * @param numContrat Numéro du contrat.
     * @return Liste des numéros de série.
     * @throws SQLException En cas d'erreur SQL.
     */
    private List<String> chargerNumSeriesContrat(String numContrat) throws SQLException {
        List<String> liste = new ArrayList<>();
        String sql = "SELECT num_serie FROM contrat_materiel WHERE num_contrat = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, numContrat);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) liste.add(rs.getString("num_serie"));
        }
        return liste;
    }

    // ── Chargement ContratMaintenance ─────────────────────────────────────────

    /**
     * Charge un ContratMaintenance depuis la base via son numéro de contrat.
     *
     * @param numContrat Numéro du contrat.
     * @return ContratMaintenance chargé, ou null s'il n'existe pas.
     * @throws SQLException En cas d'erreur SQL.
     */
    private ContratMaintenance chargerContrat(String numContrat) throws SQLException {
        String sql = "SELECT num_contrat, date_signature_initiale, date_signature, "
                   + "date_echeance, taux_annuel "
                   + "FROM contrat_maintenance WHERE num_contrat = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, numContrat);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;
            return new ContratMaintenance(
                rs.getString("num_contrat"),
                rs.getDate("date_signature").toLocalDate(),
                rs.getDate("date_signature_initiale").toLocalDate(),
                rs.getDate("date_echeance").toLocalDate(),
                rs.getDouble("taux_annuel")
            );
        }
    }

    // ── Persistance Contrat ───────────────────────────────────────────────────

    /**
     * Insère ou met à jour un ContratMaintenance dans la base.
     * Si un contrat avec le même numéro existe déjà, met à jour la date d'échéance
     * (renouvellement). Sinon, insère un nouveau contrat.
     *
     * @param contrat ContratMaintenance à persister.
     * @throws SQLException En cas d'erreur SQL.
     */
    private void rangerContrat(ContratMaintenance contrat) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM contrat_maintenance WHERE num_contrat = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sqlCheck)) {
            ps.setString(1, contrat.getNumContrat());
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean existe = rs.getInt(1) > 0;

            if (existe) {
                // Renouvellement : mise à jour date_signature et date_echeance
                String sqlUpdate = "UPDATE contrat_maintenance "
                                 + "SET date_signature = ?, date_echeance = ? "
                                 + "WHERE num_contrat = ?";
                try (PreparedStatement psUp = connexion.prepareStatement(sqlUpdate)) {
                    psUp.setDate(1, Date.valueOf(contrat.getDateSignature()));
                    psUp.setDate(2, Date.valueOf(contrat.getDateEcheance()));
                    psUp.setString(3, contrat.getNumContrat());
                    psUp.executeUpdate();
                }
            } else {
                // Nouveau contrat
                String sqlInsert = "INSERT INTO contrat_maintenance "
                                 + "(num_contrat, id_client, date_signature_initiale, "
                                 + "date_signature, date_echeance, taux_annuel) "
                                 + "VALUES (?, ?, ?, ?, ?, ?)";
                // Note : id_client est extrait du numContrat ou passé séparément selon contexte
                throw new UnsupportedOperationException(
                    "Utilisez creerContrat(idClient, numContrat, taux) pour un nouveau contrat.");
            }
        }
    }

    /**
     * Crée un nouveau contrat de maintenance pour un client donné.
     *
     * @param idClient    Identifiant du client.
     * @param numContrat  Numéro du nouveau contrat.
     * @param tauxAnnuel  Taux annuel (%).
     * @throws SQLException En cas d'erreur SQL.
     */
    public void creerContrat(String idClient, String numContrat, double tauxAnnuel)
            throws SQLException {
        verifierConnexion();
        LocalDate aujourd_hui = LocalDate.now();
        LocalDate echeance    = aujourd_hui.plusYears(1);

        String sql = "INSERT INTO contrat_maintenance "
                   + "(num_contrat, id_client, date_signature_initiale, "
                   + "date_signature, date_echeance, taux_annuel) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, numContrat);
            ps.setInt(2, Integer.parseInt(idClient));
            ps.setDate(3, Date.valueOf(aujourd_hui));
            ps.setDate(4, Date.valueOf(aujourd_hui));
            ps.setDate(5, Date.valueOf(echeance));
            ps.setDouble(6, tauxAnnuel);
            ps.executeUpdate();
        }
    }

    /**
     * Associe un matériel à un contrat de maintenance existant.
     *
     * @param numContrat Numéro du contrat.
     * @param numSerie   Numéro de série du matériel à couvrir.
     * @throws SQLException En cas d'erreur SQL.
     */
    public void ajouterMaterielAuContrat(String numContrat, String numSerie)
            throws SQLException {
        verifierConnexion();
        String sqlCheck = "SELECT COUNT(*) FROM contrat_materiel "
                        + "WHERE num_contrat = ? AND num_serie = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sqlCheck)) {
            ps.setString(1, numContrat);
            ps.setString(2, numSerie);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) return; // déjà associé
        }
        String sql = "INSERT INTO contrat_materiel (num_contrat, num_serie) VALUES (?, ?)";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, numContrat);
            ps.setString(2, numSerie);
            ps.executeUpdate();
        }
    }

    // ── Persistance Client ────────────────────────────────────────────────────

    /**
     * Met à jour les informations d'un client dans la base.
     *
     * @param client Client dont les données sont à mettre à jour.
     * @throws SQLException En cas d'erreur SQL.
     */
    private void rangerClient(Client client) throws SQLException {
        String sql = "UPDATE client SET raison_sociale=?, siren=?, code_ape=?, "
                   + "adresse=?, telephone=?, email=? WHERE id_client=?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, client.getRaisonSociale());
            ps.setString(2, client.getSiren());
            ps.setString(3, client.getCodeApe());
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getTelClient());
            ps.setString(6, client.getEmail());
            ps.setString(7, client.getNumClient());
            ps.executeUpdate();
        }
    }

    // ── Requêtes utilitaires ──────────────────────────────────────────────────

    /**
     * Retourne la liste de tous les clients de la base avec leur contrat.
     *
     * @return Liste de tous les clients chargés.
     * @throws SQLException En cas d'erreur SQL.
     */
    public List<Client> chargerTousLesClients() throws SQLException {
        verifierConnexion();
        List<Client> liste = new ArrayList<>();
        String sql = "SELECT id_client FROM client ORDER BY raison_sociale";
        try (Statement st = connexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Client c = chargerClient(String.valueOf(rs.getInt("id_client")));
                if (c != null) liste.add(c);
            }
        }
        return liste;
    }

    /**
     * Retourne le prochain numéro de contrat disponible (format C-XXXX).
     *
     * @return Prochain numéro de contrat.
     * @throws SQLException En cas d'erreur SQL.
     */
    public String getProchainNumeroContrat() throws SQLException {
        verifierConnexion();
        String sql = "SELECT MAX(CAST(SUBSTRING(num_contrat, 3) AS UNSIGNED)) AS max_num "
                   + "FROM contrat_maintenance";
        try (Statement st = connexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                int max = rs.getInt("max_num");
                return String.format("C-%04d", max + 1);
            }
        }
        return "C-0001";
    }
}
