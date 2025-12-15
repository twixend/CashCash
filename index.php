<?php
session_start();
require_once 'controllers/HomeController.php';

$controller = new HomeController();

$action = $_GET['action'] ?? 'default';

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
    case 'update_status':
        $controller->updateStatus();
        break;
    case 'login':
        $controller->login();
        break;
    case 'logout':
        $controller->logout();
        break;
    default:
        // Redirection intelligente par défaut
        if(isset($_SESSION['user'])) {
            if ($_SESSION['user']['role'] === 'GESTIONNAIRE') {
                header('Location: index.php?action=affectation');
            } else {
                header('Location: index.php?action=maintenance');
            }
        } else {
            header('Location: index.php?action=login');
        }
        break;
}
?>