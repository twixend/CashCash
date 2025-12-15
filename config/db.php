<?php
class DB {
    private static $pdo = null;
 
    public static function connect() {
        if (self::$pdo == null) {
            try {
                // ATTENTION: Vérifiez et modifiez 'root' et '' si votre base de données a un mot de passe
                self::$pdo = new PDO("mysql:host=localhost;dbname=cashcash;charset=utf8", 'root', '');
                self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch (PDOException $e) {
                // Arrête l'application si la connexion échoue
                die("Erreur de connexion à la base de données : " . $e->getMessage());
            }
        }
        return self::$pdo;
    }
}
?>