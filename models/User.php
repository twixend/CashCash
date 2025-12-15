<?php
require_once 'config/db.php';
 
class User {
    public static function login($username, $password) {
        $pdo = DB::connect();
        $stmt = $pdo->prepare("SELECT * FROM utilisateur WHERE username = :u");
        $stmt->execute(['u' => $username]);
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
 
        if ($user && password_verify($password, $user['password_hash'])) {
            return $user;
        }
        return false;
    }
}
?>