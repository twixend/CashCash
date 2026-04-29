# CashCash Desktop — Client lourd (Jalon 3)

Application Java Swing en architecture MVC permettant au gestionnaire CashCash de :
1. **Générer un fichier XML** des matériels d'un client (sous contrat / hors contrat)
2. **Créer un contrat de maintenance** pour les matériels non couverts
3. **Générer un PDF de relance** pour les clients dont le contrat expire sous 60 jours

---

## Structure du projet

```
cashcash_desktop/
├── src/
│   └── com/cashcash/
│       ├── Main.java                      ← Point d'entrée
│       ├── model/                         ← Couche Modèle (classes métier)
│       │   ├── Famille.java
│       │   ├── TypeMateriel.java
│       │   ├── Materiel.java              ← méthode xmlMateriel()
│       │   ├── ContratMaintenance.java    ← getJoursRestants(), estValide(), ajouteMateriel()
│       │   └── Client.java               ← getMaterielsSousContrat(), estAssure()
│       ├── dao/
│       │   └── PersistanceSQL.java        ← Accès MySQL (chargerDepuisBase, rangerDansBase)
│       ├── service/
│       │   └── GestionMateriels.java      ← getClient(), xmlClient(), xmlClientValide()
│       ├── controller/
│       │   └── MainController.java        ← Orchestration MVC
│       ├── view/                          ← Couche Vue (Swing)
│       │   ├── MainFrame.java             ← Fenêtre principale + onglets
│       │   ├── PanelXml.java              ← Onglet génération XML
│       │   ├── PanelContrat.java          ← Onglet création contrat
│       │   └── PanelRelance.java          ← Onglet relance PDF
│       └── util/
│           └── GenerateurPdf.java         ← Générateur PDF pur Java (sans lib externe)
├── lib/
│   └── mysql-connector-j.jar              ← À télécharger (voir ci-dessous)
├── compiler.bat / compiler.sh             ← Scripts de compilation
├── lancer.bat   / lancer.sh              ← Scripts de lancement
└── README.md
```

---

## Prérequis

| Élément | Version minimale |
|---|---|
| Java JDK | 17 ou supérieur |
| Connecteur MySQL | mysql-connector-j 8.3.0+ |

---

## Installation étape par étape

### Étape 1 — Télécharger le connecteur MySQL

1. Aller sur : https://dev.mysql.com/downloads/connector/j/
2. Choisir **Platform Independent**, télécharger le ZIP
3. Extraire et copier **mysql-connector-j-X.X.X.jar** dans le dossier `lib/`
4. Le renommer en `mysql-connector-j.jar`

### Étape 2 — Vérifier la connexion à la base

Les paramètres de connexion sont définis dans :
```
src/com/cashcash/dao/PersistanceSQL.java
```

```java
private static final String DB_USER     = "cashcash";
private static final String DB_PASSWORD = "Kovbuc57!";
// URL construite avec : "mysql-cashcash.alwaysdata.net", port 3306, base "cashcash_bdd"
```

Modifier ces valeurs si nécessaire avant de compiler.

### Étape 3 — Compiler

**Windows :**
```
compiler.bat
```

**Linux / macOS :**
```bash
chmod +x compiler.sh lancer.sh
bash compiler.sh
```

Le script produit un fichier `cashcash-desktop.jar` autonome (connecteur MySQL inclus).

### Étape 4 — Lancer l'application

**Windows :**
```
lancer.bat
```
ou double-cliquer sur `cashcash-desktop.jar`.

**Linux / macOS :**
```bash
bash lancer.sh
```
ou :
```bash
java -jar cashcash-desktop.jar
```

---

## Utilisation

### Onglet 1 — Génération XML

1. Saisir l'identifiant numérique du client dans le champ
2. Cliquer sur **Rechercher** → les informations et l'aperçu XML s'affichent
3. Cliquer sur **Sauvegarder le fichier XML…** pour choisir l'emplacement
4. Le fichier généré est nommé automatiquement `materielClientcliXXXX.xml`

Le XML sépare les matériels en deux sections conformément à l'annexe :
- `<sousContrat>` : matériels couverts par un contrat valide à la date du jour
- `<horsContrat>` : matériels non couverts ou dont le contrat est expiré

### Onglet 2 — Contrat de maintenance

1. Cliquer sur **Charger la liste** pour récupérer tous les clients
2. Sélectionner un client dans la liste déroulante
3. Cliquer sur **Sélectionner** → le tableau affiche ses matériels **hors contrat**
4. Cocher les matériels à couvrir (boutons Tout / Rien disponibles)
5. Ajuster le taux annuel (défaut : 10 %)
6. Cliquer sur **Créer le contrat de maintenance**

### Onglet 3 — Relance PDF

1. Cliquer sur **Actualiser** → la liste des clients dont le contrat expire sous 60 jours s'affiche
2. Les lignes sont colorées selon l'urgence : rouge (≤ 15 j), orange (≤ 30 j), vert (≤ 60 j)
3. Cliquer sur **Générer le PDF de relance…** → choisir l'emplacement du fichier
4. Le PDF contient une page par client avec ses coordonnées et un courrier de relance

---

## Architecture MVC

```
Utilisateur
    │  action (clic, saisie)
    ▼
 View (PanelXml / PanelContrat / PanelRelance)
    │  appelle
    ▼
 Controller (MainController)
    │  délègue
    ▼
 Service (GestionMateriels)
    │  utilise
    ▼
 DAO (PersistanceSQL)  ──►  Base MySQL
    │  retourne objets
    ▼
 Model (Client, ContratMaintenance, Materiel…)
    │  remonte vers
    ▼
 View (mise à jour affichage)
```

---

## Classes conformes à l'annexe du sujet

| Classe annexe | Fichier Java | Méthodes implémentées |
|---|---|---|
| `PersistanceSQL` | `dao/PersistanceSQL.java` | `rangerDansBase()`, `chargerDepuisBase()` |
| `GestionMateriels` | `service/GestionMateriels.java` | `getClient()`, `xmlClient()`, `xmlClientValide()` |
| `Client` | `model/Client.java` | `getMateriels()`, `getMaterielsSousContrat()`, `estAssure()` |
| `ContratMaintenance` | `model/ContratMaintenance.java` | `getJoursRestants()`, `estValide()`, `ajouteMateriel()` |
| `Materiel` | `model/Materiel.java` | `xmlMateriel()` |
| `TypeMateriel` | `model/TypeMateriel.java` | getters/setters |
| `Famille` | `model/Famille.java` | getters/setters |

---

## Dépannage

| Problème | Solution |
|---|---|
| `ClassNotFoundException: com.mysql.cj.jdbc.Driver` | mysql-connector-j.jar absent du dossier `lib/` |
| `Communications link failure` | Serveur MySQL inaccessible ou mauvais nom d'hôte |
| `Access denied for user` | Vérifier DB_USER / DB_PASSWORD dans PersistanceSQL.java |
| Fenêtre qui ne s'affiche pas | Vérifier que Java 17+ est installé : `java -version` |
| Erreur de compilation | Vérifier que `javac` est dans le PATH : `javac -version` |
