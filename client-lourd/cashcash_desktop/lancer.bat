@echo off
:: ============================================================
::  CashCash Desktop – Lancement (Windows)
:: ============================================================

:: Se placer dans le dossier du script
cd /d "%~dp0"

:: Vérifier que le jar existe
if not exist cashcash-desktop.jar (
    echo Le fichier cashcash-desktop.jar est introuvable.
    echo Executez d'abord compiler.bat
    pause
    exit /b 1
)

:: Vérifier que Java est installé
java -version >nul 2>&1
if errorlevel 1 (
    echo Java n'est pas installe ou non accessible.
    pause
    exit /b 1
)

:: Lancer l'application
java -cp cashcash-desktop.jar com.cashcash.Main

:: Garder la fenêtre ouverte en cas d'erreur
if errorlevel 1 pause