<?php

require_once 'config/db.php';
 
class Intervention {

    public static function getAll() {

        $pdo = DB::connect();

        // Jointure pour obtenir le nom du client et son adresse (utilisée comme 'lieu')

        $sql = "SELECT i.*, c.raison_sociale as client_nom, c.adresse as lieu 

                FROM intervention i

                JOIN client c ON i.id_client = c.id_client

                ORDER BY i.date_intervention DESC";

        return $pdo->query($sql)->fetchAll(PDO::FETCH_ASSOC);

    }
 
    public static function getById($id) {

        $pdo = DB::connect();

        $sql = "SELECT i.*, c.raison_sociale as client_nom, c.adresse as lieu 

                FROM intervention i

                JOIN client c ON i.id_client = c.id_client

                WHERE id_intervention = ?";

        $stmt = $pdo->prepare($sql);

        $stmt->execute([$id]);

        return $stmt->fetch(PDO::FETCH_ASSOC);

    }
 
    public static function save($data) {

        $pdo = DB::connect();

        if (!empty($data['id_intervention'])) { // UPDATE

            $sql = "UPDATE intervention SET 

                    date_intervention = ?, statut = ?, distance_km = ?, commentaire_global = ?

                    WHERE id_intervention = ?";

            $stmt = $pdo->prepare($sql);

            return $stmt->execute([

                $data['date'], 

                $data['statut'], 

                $data['distance'], 

                $data['desc'], 

                $data['id_intervention']

            ]);

        } else { // INSERT

            // ATTENTION: id_client est fixé à 1 pour le test. Dans un vrai cas, il faut le sélectionner.

            $sql = "INSERT INTO intervention (id_client, id_technicien, date_intervention, statut, distance_km, commentaire_global) 

                    VALUES (?, 1, ?, ?, ?, ?)";

            $stmt = $pdo->prepare($sql);

            return $stmt->execute([

                1, 

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

}

?>
 