#!/bin/bash
# ============================================================
#  CashCash Desktop – Compilation (Linux / macOS)
#  Prérequis : JDK 17+ installé, mysql-connector-j.jar dans lib/
# ============================================================

echo "[1/3] Nettoyage du dossier de sortie..."
rm -rf out && mkdir out

echo "[2/3] Compilation des sources Java..."
javac -encoding UTF-8 \
      -cp "lib/mysql-connector-j.jar" \
      -d out \
      src/com/cashcash/model/Famille.java \
      src/com/cashcash/model/TypeMateriel.java \
      src/com/cashcash/model/Materiel.java \
      src/com/cashcash/model/ContratMaintenance.java \
      src/com/cashcash/model/Client.java \
      src/com/cashcash/dao/PersistanceSQL.java \
      src/com/cashcash/service/GestionMateriels.java \
      src/com/cashcash/util/GenerateurPdf.java \
      src/com/cashcash/controller/MainController.java \
      src/com/cashcash/view/PanelXml.java \
      src/com/cashcash/view/PanelContrat.java \
      src/com/cashcash/view/PanelRelance.java \
      src/com/cashcash/view/MainFrame.java \
      src/com/cashcash/Main.java

if [ $? -ne 0 ]; then
    echo ""
    echo "[ERREUR] La compilation a échoué. Vérifiez les messages ci-dessus."
    exit 1
fi

echo "[3/3] Création du JAR exécutable..."
mkdir -p out/META-INF
echo "Main-Class: com.cashcash.Main" > out/META-INF/MANIFEST.MF

# Extraire le connecteur MySQL pour l'inclure dans le JAR
cd out
jar xf ../lib/mysql-connector-j.jar
cd ..

jar cfm cashcash-desktop.jar out/META-INF/MANIFEST.MF -C out .

if [ $? -ne 0 ]; then
    echo "[ERREUR] Création du JAR échouée."
    exit 1
fi

echo ""
echo "============================================================"
echo " Compilation réussie !"
echo " Pour lancer : bash lancer.sh   ou   java -jar cashcash-desktop.jar"
echo "============================================================"
