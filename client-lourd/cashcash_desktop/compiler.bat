@echo off
:: ============================================================
::  CashCash Desktop – Compilation (Windows)
::  Prérequis : JDK 17+ installé, mysql-connector-j.jar dans lib/
:: ============================================================

echo [1/4] Nettoyage...
if exist out rmdir /s /q out
if exist cashcash-desktop.jar del cashcash-desktop.jar
mkdir out

echo [2/4] Compilation des sources Java...
javac -encoding UTF-8 ^
      -cp "lib\mysql-connector-j.jar" ^
      -d out ^
      src\com\cashcash\model\Famille.java ^
      src\com\cashcash\model\TypeMateriel.java ^
      src\com\cashcash\model\Materiel.java ^
      src\com\cashcash\model\ContratMaintenance.java ^
      src\com\cashcash\model\Client.java ^
      src\com\cashcash\dao\PersistanceSQL.java ^
      src\com\cashcash\service\GestionMateriels.java ^
      src\com\cashcash\util\GenerateurPdf.java ^
      src\com\cashcash\controller\MainController.java ^
      src\com\cashcash\view\PanelXml.java ^
      src\com\cashcash\view\PanelContrat.java ^
      src\com\cashcash\view\PanelRelance.java ^
      src\com\cashcash\view\MainFrame.java ^
      src\com\cashcash\Main.java

if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] Compilation echouee.
    pause
    exit /b 1
)

echo [3/4] Creation du manifest...
mkdir out\META-INF
(
    echo Main-Class: com.cashcash.Main
    echo.
) > out\META-INF\MANIFEST.MF

echo [4/4] Creation du JAR...
:: Extraire le connecteur MySQL dans out/ pour l'inclure
cd out
jar xf ..\lib\mysql-connector-j.jar
:: Supprimer les signatures du connecteur qui causent des conflits
if exist META-INF\*.SF del META-INF\*.SF
if exist META-INF\*.DSA del META-INF\*.DSA
if exist META-INF\*.RSA del META-INF\*.RSA
cd ..

:: Creer le JAR avec notre MANIFEST
jar cfm cashcash-desktop.jar out\META-INF\MANIFEST.MF -C out .

if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] Creation du JAR echouee.
    pause
    exit /b 1
)

echo.
echo ============================================================
echo  Succes !
echo  Pour lancer : java -jar cashcash-desktop.jar
echo ============================================================
pause
