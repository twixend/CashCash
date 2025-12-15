<?php require 'views/header.php'; ?>

<div class="search-row">
    <div class="group"><label>Rechercher</label><input type="text" id="search" class="input" placeholder="Client..."></div>
</div>

<div class="grid">
    <?php foreach($interventions as $i): ?>
        <?php 
            $isRealise = ($i['statut'] === 'REALISE');
            $badgeClass = $isRealise ? 'badge-realise' : 'badge-prevu';
            $textClass = $isRealise ? 'txt-done' : 'txt-todo';
            $textStatus = $isRealise ? 'Réalisé' : 'À faire';
            
            // Gestion de l'affichage du technicien
            $nomTechnicien = !empty($i['tech_nom']) ? htmlspecialchars($i['tech_prenom'] . ' ' . $i['tech_nom']) : 'Non affecté';
        ?>

        <div class="card" data-client="<?= strtolower($i['client_nom']) ?>">
            
            <a href="index.php?action=details&id=<?= $i['id_intervention'] ?>" style="text-decoration:none; color:inherit;">
                <div class="card-badge <?= $badgeClass ?>">
                    <?= $i['statut'] ?>
                </div>
                
                <div class="card-content">
                    <p><strong>Client :</strong> <?= htmlspecialchars($i['client_nom']) ?></p>
                    <p><strong>Date :</strong> <?= date('d/m/Y', strtotime($i['date_intervention'])) ?></p>
                    
                    <p><strong>Technicien :</strong> <span style="color:#003366; font-weight:600;">👤 <?= $nomTechnicien ?></span></p>
                </div>
            </a>

            <div class="card-footer">
                <button class="btn btn-sm btn-secondary" onclick="alert('PDF Généré !')">PDF</button>
                
                <form method="POST" action="index.php?action=update_status" style="margin:0;">
                    <input type="hidden" name="id" value="<?= $i['id_intervention'] ?>">
                    
                    <label class="check-container" style="display:flex; align-items:center; cursor:pointer;">
                        <input type="checkbox" 
                               name="check_statut" 
                               onchange="this.form.submit()" 
                               <?= $isRealise ? 'checked' : '' ?>
                               style="cursor:pointer; width:16px; height:16px;">
                        
                        <span class="status-text <?= $textClass ?>">
                            <?= $textStatus ?>
                        </span>
                    </label>
                </form>
            </div>
        </div>
    <?php endforeach; ?>
</div>

<script>
    document.getElementById('search').addEventListener('keyup', function(e){
        let txt = e.target.value.toLowerCase();
        document.querySelectorAll('.card').forEach(c => {
            c.style.display = c.getAttribute('data-client').includes(txt) ? 'block' : 'none';
        });
    });
</script>
</body></html>