<?php
class DB {
    private static $pdo = null;
 
    public static function connect() {
        if (self::$pdo == null) {
            try {
                self::$pdo = new PDO("mysql:host=localhost;dbname=cashcash;charset=utf8", 'root', '');
                self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch (PDOException $e) {
                die("Erreur de connexion à la base de données : " . $e->getMessage());
            }
        }
        return self::$pdo;
    }
}
?>