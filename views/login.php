<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link rel="stylesheet" href="public/style.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body style="display:flex; align-items:center; justify-content:center; min-height:100vh;">
<form method="POST" class="form-card" style="width:100%; max-width:400px; text-align:center;">
<div class="logo" style="justify-content:center; margin-bottom:30px;"><span>₹</span> CashCash</div>
<h2>Connexion Intranet</h2>
<?php if(!empty($error)): ?>
<div style="background:#ffebee; color:#c62828; padding:10px; margin-bottom:15px; border-radius:4px;"><?= $error ?></div>
<?php endif; ?>
<div style="text-align:left; margin-bottom:15px;">
<label class="label">Utilisateur</label>
<input type="text" name="user" class="input" required>
</div>
<div style="text-align:left; margin-bottom:30px;">
<label class="label">Mot de passe</label>
<input type="password" name="pass" class="input" required>
</div>
<button type="submit" class="btn btn-orange" style="width:100%">Se connecter</button>
</form>
</body></html>