<?php require 'views/header.php'; ?>
 
<div class="search-row">
<div class="group"><label>Rechercher</label><input type="text" id="search" class="input" placeholder="Client..."></div>
<a href="index.php?action=details" class="btn btn-orange">+ Nouvelle Intervention</a>
</div>
 
<div class="grid">
<?php foreach($interventions as $i): ?>
<a href="index.php?action=details&id=<?= $i['id_intervention'] ?>" class="card" data-client="<?= strtolower($i['client_nom']) ?>">
<div class="card-badge">Intervention #<?= $i['id_intervention'] ?></div>
<div class="card-content">
<p><strong>Client :</strong> <?= htmlspecialchars($i['client_nom']) ?></p>
<p><strong>Date :</strong> <?= date('d/m/Y', strtotime($i['date_intervention'])) ?></p>
<p><strong>Distance :</strong> <?= $i['distance_km'] ?> km</p>
</div>
<div class="card-footer">
<button class="btn btn-sm btn-orange" onclick="event.preventDefault(); alert('PDF Généré !')">Générer PDF</button>
<span style="color:green; font-weight:bold;">☑ Fait</span>
</div>
</a>
<?php endforeach; ?>
</div>
 
<script>

    // Filtre client JS

    document.getElementById('search').addEventListener('keyup', function(e){

        let txt = e.target.value.toLowerCase();

        document.querySelectorAll('.card').forEach(c => {

            c.style.display = c.getAttribute('data-client').includes(txt) ? 'block' : 'none';

        });

    });
</script>
</body></html>
 