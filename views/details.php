<?php require 'views/header.php'; 
$isEdit = !empty($item);
?>

<div class="header-split">
    <h2><?= $isEdit ? 'Intervention #'.($item['id_intervention'] ?? '') : 'Nouvelle Intervention' ?></h2>
    <a href="javascript:history.back()" class="btn btn-secondary">← Retour</a>
</div>

<form method="POST" class="form-card">
    <input type="hidden" name="id_intervention" value="<?= $item['id_intervention'] ?? '' ?>">
    <input type="hidden" name="statut" value="<?= $item['statut'] ?? 'PREVU' ?>">

    <div class="row">
        <div class="col">
            <label class="label">Client</label>
            <select name="id_client" class="input" required>
                <option value="">-- Sélectionner un Client --</option>
                <?php foreach($clients as $c): ?>
                    <option value="<?= $c['id_client'] ?>" 
                        <?= (isset($item['id_client']) && $item['id_client'] == $c['id_client']) ? 'selected' : '' ?>>
                        <?= htmlspecialchars($c['raison_sociale']) ?>
                    </option>
                <?php endforeach; ?>
            </select>
        </div>
        <div class="col">
            <label class="label">Date et Heure</label>
            <input type="datetime-local" name="date" class="input" 
                   value="<?= isset($item['date_intervention']) ? date('Y-m-d\TH:i', strtotime($item['date_intervention'])) : '' ?>" required>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <label class="label">Technicien Affecté</label>
            <select name="id_technicien" class="input">
                <option value="">-- Choisir un technicien --</option>
                <?php foreach($techniciens as $tech): ?>
                    <option value="<?= $tech['id_technicien'] ?>" 
                        <?= (isset($item['id_technicien']) && $item['id_technicien'] == $tech['id_technicien']) ? 'selected' : '' ?>>
                        <?= htmlspecialchars($tech['prenom'] . ' ' . $tech['nom']) ?> (<?= $tech['qualification'] ?>)
                    </option>
                <?php endforeach; ?>
            </select>
        </div>
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