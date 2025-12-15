<?php require 'views/header.php'; 

$isEdit = !empty($item);

?>
 
<div class="header-split">
<h2><?= $isEdit ? 'Intervention #'.($item['id_intervention'] ?? '') : 'Nouvelle Intervention' ?></h2>
<a href="javascript:history.back()" class="btn btn-secondary">← Retour</a>
</div>
 
<form method="POST" class="form-card">
<input type="hidden" name="id_intervention" value="<?= $item['id_intervention'] ?? '' ?>">
 
    <div class="row">
<div class="col">
<label class="label">Client</label>
<input type="text" class="input" value="<?= $item['client_nom'] ?? 'Client par défaut (ID 1)' ?>" disabled>
</div>
<div class="col">
<label class="label">Date et Heure</label>
<input type="datetime-local" name="date" class="input" 

                   value="<?= isset($item['date_intervention']) ? date('Y-m-d\TH:i', strtotime($item['date_intervention'])) : '' ?>" required>
</div>
</div>
 
    <div class="row">
<div class="col">
<label class="label">Distance (km)</label>
<input type="number" name="distance" class="input" value="<?= $item['distance_km'] ?? '' ?>" required>
</div>
</div>
 
    <div class="row">
<div class="col">
<label class="label">Commentaire</label>
<textarea name="desc" class="input" rows="3"><?= $item['commentaire_global'] ?? '' ?></textarea>
</div>
</div>
 
    <div class="check-box">
<input type="checkbox" name="statut_fait" value="1" <?= (isset($item['statut']) && $item['statut']=='REALISE') ? 'checked' : '' ?> style="width:20px; height:20px;">
<span>Intervention Réalisée (Cochez pour passer en Maintenance/PDF)</span>
</div>
 
    <div class="actions">
<?php if($isEdit): ?>
<button type="submit" name="delete" class="btn btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer cette intervention ?')">Supprimer</button>
<?php else: ?>
<div></div>
<?php endif; ?>
<button type="submit" class="btn btn-orange">Enregistrer</button>
</div>
</form>
</body></html>
 