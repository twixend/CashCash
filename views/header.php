<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>CashCash Intranet</title>
<link rel="stylesheet" href="public/style.css?v=<?= time() ?>">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>
<header>
<div class="container">
<div class="header-flex">
<div class="logo"><span>₹</span> CashCash</div>
<?php if(isset($_SESSION['user'])): ?>
<div class="user-info">

                        👤 <?= htmlspecialchars($_SESSION['user']['username'] ?? 'Utilisateur') ?>
<a href="index.php?action=logout" class="btn btn-sm btn-danger">Déconnexion</a>
</div>
<?php else: ?>
<a href="index.php?action=login" class="btn btn-sm btn-secondary">Se connecter</a>
<?php endif; ?>
</div>
<?php if(isset($_SESSION['user'])): ?>
<div class="nav-tabs">
<a href="index.php?action=maintenance" class="nav-item <?= ($_GET['action']=='maintenance')?'active':'' ?>">Maintenance (PDF)</a>
<a href="index.php?action=affectation" class="nav-item <?= ($_GET['action']=='affectation')?'active':'' ?>">Installations (Affecter)</a>
</div>
<?php endif; ?>
</div>
</header>
<main class="container">
 