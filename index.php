<?php

session_start();

require_once 'controllers/HomeController.php';
 
$controller = new HomeController();
 
// Routeur simple

$action = $_GET['action'] ?? 'login';
 
switch ($action) {

    case 'maintenance':

        $controller->maintenance();

        break;

    case 'affectation':

        $controller->affectation();

        break;

    case 'details':

        $controller->details();

        break;

    case 'login':

        $controller->login();

        break;

    case 'logout':

        $controller->logout();

        break;

    default:

        // Redirection par défaut après login

        if(isset($_SESSION['user'])) {

            header('Location: index.php?action=maintenance');

        } else {

            header('Location: index.php?action=login');

        }

        break;

}

?>
 