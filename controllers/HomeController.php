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
 
    public function login() {

        $error = '';

        if ($_SERVER['REQUEST_METHOD'] === 'POST') {

            $user = User::login($_POST['user'], $_POST['pass']);

            if ($user) {

                $_SESSION['user'] = $user;

                header('Location: index.php?action=maintenance');

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

        $all = Intervention::getAll();

        // Filtre : uniquement les REALISE pour la page Maintenance/PDF

        $interventions = array_filter($all, function($i) {

            return $i['statut'] === 'REALISE';

        });

        require 'views/maintenance.php';

    }
 
    public function affectation() {

        $this->checkAuth();

        $all = Intervention::getAll();

        // Filtre : uniquement les PREVU (ou annulé) pour la page Affectation

        $interventions = array_filter($all, function($i) {

            return $i['statut'] === 'PREVU' || $i['statut'] === 'ANNULE';

        });

        require 'views/affectation.php';

    }
 
    public function details() {

        $this->checkAuth();

        $item = [];

        if (isset($_GET['id'])) {

            $item = Intervention::getById($_GET['id']);

        }

        if ($_SERVER['REQUEST_METHOD'] === 'POST') {

            if (isset($_POST['delete'])) {

                Intervention::delete($_POST['id_intervention']);

                $redirect = 'maintenance'; // Redirection après suppression

            } else {

                $data = [

                    'id_intervention' => $_POST['id_intervention'] ?? null,

                    'date' => $_POST['date'],

                    'distance' => $_POST['distance'],

                    'desc' => $_POST['desc'],

                    // Si on coche, statut REALISE (Maintenance), sinon PREVU (Affectation)

                    'statut' => isset($_POST['statut_fait']) ? 'REALISE' : 'PREVU'

                ];

                Intervention::save($data);

                $redirect = $data['statut'] == 'REALISE' ? 'maintenance' : 'affectation';

            }

            header("Location: index.php?action=$redirect");

            exit;

        }
 
        require 'views/details.php';

    }

}

?>
 