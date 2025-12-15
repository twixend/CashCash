<?php
require_once 'models/Intervention.php';
require_once 'models/User.php';

class HomeController {

    private function checkAuth() {
        if (!isset($_SESSION['user'])) {
            header('Location: index.php?action=login');
            exit;
        }
    }

    private function getRole() {
        return $_SESSION['user']['role'] ?? '';
    }

    public function login() {
        $error = '';
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $user = User::login($_POST['user'], $_POST['pass']);
            if ($user) {
                $_SESSION['user'] = $user;
                if ($user['role'] === 'GESTIONNAIRE') {
                    header('Location: index.php?action=affectation');
                } else {
                    header('Location: index.php?action=maintenance');
                }
                exit;
            } else {
                $error = "Identifiants incorrects";
            }
        }
        require 'views/login.php';
    }

    public function logout() {
        session_destroy();
        header('Location: index.php?action=login');
    }

    public function maintenance() {
        $this->checkAuth();
        if ($this->getRole() === 'GESTIONNAIRE') {
            header('Location: index.php?action=affectation');
            exit;
        }

        $all = Intervention::getAll();
        $interventions = array_filter($all, function($i) {
            return $i['statut'] === 'REALISE' || $i['statut'] === 'PREVU';
        });
        
        require 'views/maintenance.php';
    }

    public function affectation() {
        $this->checkAuth();
        if ($this->getRole() === 'TECHNICIEN') {
            header('Location: index.php?action=maintenance');
            exit;
        }

        $all = Intervention::getAll();
        $interventions = array_filter($all, function($i) {
             return $i['statut'] === 'ANNULE' || $i['statut'] === 'PREVU'; 
        });
        
        require 'views/affectation.php';
    }

    public function details() {
        $this->checkAuth();
        $item = [];
        $techniciens = Intervention::getTechniciens();
        
        // NOUVEAU : On charge la liste des clients
        $clients = Intervention::getClients();

        if (isset($_GET['id'])) $item = Intervention::getById($_GET['id']);
        
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            if (isset($_POST['delete'])) {
                if ($this->getRole() === 'ADMIN') {
                    Intervention::delete($_POST['id_intervention']);
                }
            } else {
                $data = [
                    'id_intervention' => $_POST['id_intervention'] ?? null,
                    'date' => $_POST['date'],
                    'distance' => $_POST['distance'],
                    'desc' => $_POST['desc'],
                    'id_technicien' => $_POST['id_technicien'],
                    'id_client' => $_POST['id_client'], // NOUVEAU : On récupère le client choisi
                    'statut' => $_POST['statut'] ?? 'PREVU'
                ];
                Intervention::save($data);
            }
            
            if ($this->getRole() === 'GESTIONNAIRE') {
                header("Location: index.php?action=affectation");
            } else {
                header("Location: index.php?action=maintenance");
            }
            exit;
        }
        require 'views/details.php';
    }

    public function updateStatus() {
        $this->checkAuth();
        if ($this->getRole() === 'GESTIONNAIRE') {
            header('Location: index.php?action=affectation');
            exit;
        }

        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $id = $_POST['id'];
            $statut = isset($_POST['check_statut']) ? 'REALISE' : 'PREVU';
            Intervention::updateStatus($id, $statut);
        }

        header('Location: index.php?action=maintenance');
        exit;
    }
}
?>