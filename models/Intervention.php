<?php
require_once 'config/db.php';

class Intervention {
    public static function getAll() {
        $pdo = DB::connect();
        $sql = "SELECT i.*, 
                       c.raison_sociale as client_nom, 
                       c.adresse as lieu,
                       t.nom as tech_nom, 
                       t.prenom as tech_prenom
                FROM intervention i
                JOIN client c ON i.id_client = c.id_client
                LEFT JOIN technicien t ON i.id_technicien = t.id_technicien
                ORDER BY i.date_intervention DESC";
        return $pdo->query($sql)->fetchAll(PDO::FETCH_ASSOC);
    }

    public static function getById($id) {
        $pdo = DB::connect();
        $sql = "SELECT i.*, 
                       c.raison_sociale as client_nom, 
                       c.adresse as lieu,
                       t.nom as tech_nom, 
                       t.prenom as tech_prenom
                FROM intervention i
                JOIN client c ON i.id_client = c.id_client
                LEFT JOIN technicien t ON i.id_technicien = t.id_technicien
                WHERE id_intervention = ?";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$id]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    }

    public static function getTechniciens() {
        $pdo = DB::connect();
        return $pdo->query("SELECT * FROM technicien ORDER BY nom ASC")->fetchAll(PDO::FETCH_ASSOC);
    }

    // NOUVELLE FONCTION : Récupère tous les clients
    public static function getClients() {
        $pdo = DB::connect();
        return $pdo->query("SELECT * FROM client ORDER BY raison_sociale ASC")->fetchAll(PDO::FETCH_ASSOC);
    }

    public static function save($data) {
        $pdo = DB::connect();
        
        $techId = !empty($data['id_technicien']) ? $data['id_technicien'] : NULL;
        // On récupère l'ID client du formulaire, sinon par défaut le 1 (sécurité)
        $clientId = !empty($data['id_client']) ? $data['id_client'] : 1;

        if (!empty($data['id_intervention'])) { 
            // UPDATE : On met à jour aussi le client (id_client = ?)
            $sql = "UPDATE intervention SET date_intervention = ?, statut = ?, distance_km = ?, commentaire_global = ?, id_technicien = ?, id_client = ?
                    WHERE id_intervention = ?";
            $stmt = $pdo->prepare($sql);
            return $stmt->execute([
                $data['date'], 
                $data['statut'], 
                $data['distance'], 
                $data['desc'], 
                $techId,
                $clientId, // Nouveau paramètre
                $data['id_intervention']
            ]);
        } else {
            // INSERT
            $sql = "INSERT INTO intervention (id_client, id_technicien, date_intervention, statut, distance_km, commentaire_global) 
                    VALUES (?, ?, ?, ?, ?, ?)";
            $stmt = $pdo->prepare($sql);
            return $stmt->execute([
                $clientId, // On utilise le client choisi
                $techId, 
                $data['date'], 
                $data['statut'], 
                $data['distance'], 
                $data['desc']
            ]);
        }
    }
    
    public static function delete($id) {
        $pdo = DB::connect();
        $stmt = $pdo->prepare("DELETE FROM intervention WHERE id_intervention = ?");
        return $stmt->execute([$id]);
    }

    public static function updateStatus($id, $newStatus) {
        $pdo = DB::connect();
        $sql = "UPDATE intervention SET statut = ? WHERE id_intervention = ?";
        $stmt = $pdo->prepare($sql);
        return $stmt->execute([$newStatus, $id]);
    }
}
?>