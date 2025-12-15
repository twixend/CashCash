<?php
require_once 'config/db.php';
 
class User {
    public static function login($username, $password) {
        $pdo = DB::connect();
        // Utilisation du champ 'username' et 'password_hash' de votre base de données
        $stmt = $pdo->prepare("SELECT * FROM utilisateur WHERE username = :u");
        $stmt->execute(['u' => $username]);
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
 
        // password_verify est utilisé pour comparer le mot de passe entré
        // avec le hash stocké dans la colonne 'password_hash'
        if ($user && password_verify($password, $user['password_hash'])) {
            return $user;
        }
        return false;
    }
}
?>