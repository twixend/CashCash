#!/bin/bash
# ============================================================
#  CashCash Desktop – Lancement (Linux / macOS)
# ============================================================

if [ ! -f cashcash-desktop.jar ]; then
    echo "Le fichier cashcash-desktop.jar est introuvable."
    echo "Exécutez d'abord : bash compiler.sh"
    exit 1
fi

java -jar cashcash-desktop.jar
