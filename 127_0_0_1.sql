-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 15 déc. 2025 à 13:59
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `annuaire`
--
CREATE DATABASE IF NOT EXISTS `annuaire` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `annuaire`;

-- --------------------------------------------------------

--
-- Structure de la table `armees`
--
-- Erreur de lecture de structure pour la table annuaire.armees : #1932 - Table 'annuaire.armees' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.armees : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`armees`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `chercheurs`
--
-- Erreur de lecture de structure pour la table annuaire.chercheurs : #1932 - Table 'annuaire.chercheurs' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.chercheurs : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`chercheurs`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `disciplines`
--
-- Erreur de lecture de structure pour la table annuaire.disciplines : #1932 - Table 'annuaire.disciplines' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.disciplines : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`disciplines`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `grades`
--
-- Erreur de lecture de structure pour la table annuaire.grades : #1932 - Table 'annuaire.grades' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.grades : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`grades`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `laboratoires`
--
-- Erreur de lecture de structure pour la table annuaire.laboratoires : #1932 - Table 'annuaire.laboratoires' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.laboratoires : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`laboratoires`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `ministeres`
--
-- Erreur de lecture de structure pour la table annuaire.ministeres : #1932 - Table 'annuaire.ministeres' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.ministeres : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`ministeres`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `organismes`
--
-- Erreur de lecture de structure pour la table annuaire.organismes : #1932 - Table 'annuaire.organismes' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.organismes : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`organismes`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `organismes_academ`
--
-- Erreur de lecture de structure pour la table annuaire.organismes_academ : #1932 - Table 'annuaire.organismes_academ' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.organismes_academ : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`organismes_academ`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `universites`
--
-- Erreur de lecture de structure pour la table annuaire.universites : #1932 - Table 'annuaire.universites' doesn't exist in engine
-- Erreur de lecture des données pour la table annuaire.universites : #1064 - Erreur de syntaxe près de 'FROM `annuaire`.`universites`' à la ligne 1
--
-- Base de données : `capliez_bd1`
--
CREATE DATABASE IF NOT EXISTS `capliez_bd1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `capliez_bd1`;

-- --------------------------------------------------------

--
-- Structure de la table `aimer`
--

CREATE TABLE `aimer` (
  `idR` bigint(20) NOT NULL,
  `mailU` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `aimer`
--

INSERT INTO `aimer` (`idR`, `mailU`) VALUES
(1, 'mathieu.capliez@gmail.com'),
(1, 'michel.garay@gmail.com'),
(1, 'test@bts.sio'),
(1, 'yann@lechambon.fr'),
(2, 'mathieu.capliez@gmail.com'),
(3, 'mathieu.capliez@gmail.com'),
(4, 'mathieu.capliez@gmail.com'),
(4, 'test@bts.sio'),
(5, 'test@bts.sio'),
(6, 'test@bts.sio'),
(7, 'mathieu.capliez@gmail.com'),
(7, 'test@bts.sio'),
(8, 'mathieu.capliez@gmail.com'),
(8, 'test@bts.sio'),
(8, 'yann@lechambon.fr'),
(10, 'alex.garat@gmail.com'),
(11, 'nicolas.harispe@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `critiquer`
--

CREATE TABLE `critiquer` (
  `idR` bigint(20) NOT NULL,
  `mailU` varchar(150) NOT NULL,
  `note` int(11) DEFAULT NULL,
  `commentaire` varchar(4096) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `critiquer`
--

INSERT INTO `critiquer` (`idR`, `mailU`, `note`, `commentaire`) VALUES
(1, 'jj.soueix@gmail.com', 3, 'moyen'),
(1, 'mathieu.capliez@gmail.com', 3, 'Très bonne entrecote, les frites sont maisons et delicieuses.'),
(1, 'nicolas.harispe@gmail.com', 4, 'Très bon accueil.'),
(1, 'test@bts.sio', 4, '5/5 parce que j\'aime les entrecotes'),
(1, 'yann@lechambon.fr', 5, NULL),
(2, 'jj.soueix@gmail.com', 2, 'bof.'),
(2, 'mathieu.capliez@gmail.com', 1, 'À éviter...'),
(2, 'nicolas.harispe@gmail.com', 1, 'Cuisine tres moyenne.'),
(2, 'test@bts.sio', 5, NULL),
(4, 'mathieu.capliez@gmail.com', 5, NULL),
(4, 'nicolas.harispe@gmail.com', 5, 'Rapide.'),
(5, 'nicolas.harispe@gmail.com', 3, 'Cuisine correcte.'),
(6, 'nicolas.harispe@gmail.com', 4, 'Cuisine de qualité.'),
(7, 'alex.garat@gmail.com', 4, 'Bon accueil.'),
(7, 'mathieu.capliez@gmail.com', NULL, NULL),
(7, 'nicolas.harispe@gmail.com', 5, 'Excellent.'),
(8, 'test@bts.sio', 1, NULL),
(8, 'yann@lechambon.fr', 4, NULL),
(9, 'mathieu.capliez@gmail.com', 4, 'Très bon accueil :)');

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

CREATE TABLE `photo` (
  `idP` bigint(20) NOT NULL,
  `cheminP` varchar(255) DEFAULT NULL,
  `idR` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `photo`
--

INSERT INTO `photo` (`idP`, `cheminP`, `idR`) VALUES
(0, 'entrepote.jpg', 1),
(2, 'sapporo.jpg', 3),
(3, 'entrepote.jpg', 1),
(4, 'barDuCharcutier.jpg', 2),
(6, 'cidrerieDuFronton.jpg', 4),
(7, 'agadir.jpg', 5),
(8, 'leBistrotSainteCluque.jpg', 6),
(9, 'auberge.jpg', 7),
(10, 'laTableDePottoka.jpg', 8),
(11, 'rotisserieDuRoyLeon.jpg', 9),
(12, 'barDuMarche.jpg', 10),
(13, 'trinquetModerne.jpg', 11),
(14, 'cidrerieDuFronton2.jpg', 4),
(15, 'cidrerieDuFronton3.jpg', 4);

-- --------------------------------------------------------

--
-- Structure de la table `preferer`
--

CREATE TABLE `preferer` (
  `mailU` varchar(150) NOT NULL,
  `idTC` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `preferer`
--

INSERT INTO `preferer` (`mailU`, `idTC`) VALUES
('mathieu.capliez@gmail.com', 1),
('mathieu.capliez@gmail.com', 5),
('mathieu.capliez@gmail.com', 9),
('mathieu.capliez@gmail.com', 10),
('mathieu.capliez@gmail.com', 11),
('michel.garay@gmail.com', 1),
('michel.garay@gmail.com', 2),
('michel.garay@gmail.com', 3),
('michel.garay@gmail.com', 10),
('nicolas.harispe@gmail.com', 1),
('nicolas.harispe@gmail.com', 2),
('nicolas.harispe@gmail.com', 11),
('test@bts.sio', 1),
('test@bts.sio', 2),
('test@bts.sio', 3),
('test@bts.sio', 10),
('test@bts.sio', 11),
('yann@lechambon.fr', 1),
('yann@lechambon.fr', 7),
('yann@lechambon.fr', 9),
('yann@lechambon.fr', 10),
('yann@lechambon.fr', 11);

-- --------------------------------------------------------

--
-- Structure de la table `proposer`
--

CREATE TABLE `proposer` (
  `idR` bigint(20) NOT NULL,
  `idTC` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `proposer`
--

INSERT INTO `proposer` (`idR`, `idTC`) VALUES
(1, 1),
(2, 1),
(3, 3),
(4, 1),
(4, 8),
(4, 11),
(5, 3),
(6, 10),
(7, 6),
(8, 11),
(9, 10),
(10, 1),
(11, 1),
(11, 10);

-- --------------------------------------------------------

--
-- Structure de la table `resto`
--

CREATE TABLE `resto` (
  `idR` bigint(20) NOT NULL,
  `nomR` varchar(255) DEFAULT NULL,
  `numAdrR` varchar(20) DEFAULT NULL,
  `voieAdrR` varchar(255) DEFAULT NULL,
  `cpR` char(5) DEFAULT NULL,
  `villeR` varchar(255) DEFAULT NULL,
  `latitudeDegR` float DEFAULT NULL,
  `longitudeDegR` float DEFAULT NULL,
  `descR` text DEFAULT NULL,
  `horairesR` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `resto`
--

INSERT INTO `resto` (`idR`, `nomR`, `numAdrR`, `voieAdrR`, `cpR`, `villeR`, `latitudeDegR`, `longitudeDegR`, `descR`, `horairesR`) VALUES
(1, 'l\'entrepote', '2', 'rue Maurice Ravel', '33000', 'Bordeaux', 44.7948, -0.58754, 'description', '<table>\n    <thead>\n        <tr>\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\n        </tr>\n    </thead>\n    <tbody>\n        <tr>\n            <td class=\"label\">Midi</td>\n            <td class=\"cell\">de 11h45 à 14h30</td>\n            <td class=\"cell\">de 11h45 à 15h00</td>\n        </tr>\n        <tr>\n            <td class=\"label\">Soir</td>\n            <td class=\"cell\">de 18h45 à 22h30</td>\n            <td class=\"cell\">de 18h45 à 1h</td>	\n        </tr>\n        <tr>\n            <td class=\"label\">À emporter</td>\n            <td class=\"cell\">de 11h30 à 23h</td>\n            <td class=\"cell\">de 11h30 à 2h</td>\n        </tr>\n    </tbody>\n</table>'),
(2, 'le bar du charcutier', '30', 'rue Parlement Sainte-Catherine', '33000', 'Bordeaux', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(3, 'Sapporo', '33', 'rue Saint Rémi', '33000', 'Bordeaux', NULL, NULL, 'Le Sapporo propose à ses clients de délicieux plats typiques japonais.', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(4, 'Cidrerie du fronton', NULL, 'Place du Fronton', '64210', 'Arbonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(5, 'Agadir', '3', 'Rue Sainte-Catherine', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(6, 'Le Bistrot Sainte Cluque', '9', 'Rue Hugues', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(7, 'la petite auberge', '15', 'rue des cordeliers', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(8, 'La table de POTTOKA', '21', 'Quai Amiral Dubourdieu', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(9, 'La Rotisserie du Roy Léon', '8', 'rue de coursic', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(10, 'Bar du Marché', '39', 'Rue des Basques', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>'),
(11, 'Trinquet Moderne', '60', 'Avenue Dubrocq', '64100', 'Bayonne', NULL, NULL, 'description', '<table>\r\n    <thead>\r\n        <tr>\r\n            <th>Ouverture</th><th>Semaine</th>	<th>Week-end</th>\r\n        </tr>\r\n    </thead>\r\n    <tbody>\r\n        <tr>\r\n            <td class=\"label\">Midi</td>\r\n            <td class=\"cell\">de 11h45 à 14h30</td>\r\n            <td class=\"cell\">de 11h45 à 15h00</td>\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">Soir</td>\r\n            <td class=\"cell\">de 18h45 à 22h30</td>\r\n            <td class=\"cell\">de 18h45 à 1h</td>	\r\n        </tr>\r\n        <tr>\r\n            <td class=\"label\">À emporter</td>\r\n            <td class=\"cell\">de 11h30 à 23h</td>\r\n            <td class=\"cell\">de 11h30 à 2h</td>\r\n        </tr>\r\n    </tbody>\r\n</table>');

-- --------------------------------------------------------

--
-- Structure de la table `typecuisine`
--

CREATE TABLE `typecuisine` (
  `idTC` bigint(20) NOT NULL,
  `libelleTC` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `typecuisine`
--

INSERT INTO `typecuisine` (`idTC`, `libelleTC`) VALUES
(1, 'sud ouest'),
(2, 'japonaise'),
(3, 'orientale'),
(4, 'fastfood'),
(5, 'vegetarienne'),
(6, 'vegan'),
(7, 'crepe'),
(8, 'sandwich'),
(9, 'tartes'),
(10, 'viande'),
(11, 'grillade');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `mailU` varchar(150) NOT NULL,
  `mdpU` varchar(50) DEFAULT NULL,
  `pseudoU` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`mailU`, `mdpU`, `pseudoU`) VALUES
('alex.garat@gmail.com', '$1$zvN5hYSQSQDFUIQSdufUQSDFznHF5osT.', '@lex'),
('cacaprout2@gmail.com', 'seeoVZ8vYaeRQ', 'caca'),
('cacaprout@gmail.com', 'seeoVZ8vYaeRQ', 'caca'),
('corentincollomb@gmail.com', 'seqfsCBnJT4b.', 'corentin'),
('jj.soueix@gmail.com', '$1$zvN5hYMI$SDFGSDFGJqJSDJF.', 'drskott'),
('mathieu.capliez@gmail.com', 'seSzpoUAQgIl.', 'pich'),
('michel.garay@gmail.com', '$1$zvN5hYMI$VSatLQ6SDFGdsfgznHF5osT.', 'Mitch'),
('nicolas.harispe@gmail.com', '$1$zvNDSFQSdfqsDfQsdfsT.', 'Nico40'),
('test@bts.sio', 'seSzpoUAQgIl.', 'testeur SIO'),
('yann@lechambon.fr', 'sej6dETYl/ea.', 'yann');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `aimer`
--
ALTER TABLE `aimer`
  ADD PRIMARY KEY (`idR`,`mailU`),
  ADD KEY `mailU` (`mailU`);

--
-- Index pour la table `critiquer`
--
ALTER TABLE `critiquer`
  ADD PRIMARY KEY (`idR`,`mailU`),
  ADD KEY `mailU` (`mailU`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`idP`),
  ADD KEY `idR` (`idR`);

--
-- Index pour la table `preferer`
--
ALTER TABLE `preferer`
  ADD PRIMARY KEY (`mailU`,`idTC`),
  ADD KEY `idTC` (`idTC`);

--
-- Index pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD PRIMARY KEY (`idR`,`idTC`),
  ADD KEY `idTC` (`idTC`);

--
-- Index pour la table `resto`
--
ALTER TABLE `resto`
  ADD PRIMARY KEY (`idR`);

--
-- Index pour la table `typecuisine`
--
ALTER TABLE `typecuisine`
  ADD PRIMARY KEY (`idTC`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`mailU`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `aimer`
--
ALTER TABLE `aimer`
  ADD CONSTRAINT `aimer_ibfk_1` FOREIGN KEY (`idR`) REFERENCES `resto` (`idR`),
  ADD CONSTRAINT `aimer_ibfk_2` FOREIGN KEY (`mailU`) REFERENCES `utilisateur` (`mailU`);

--
-- Contraintes pour la table `critiquer`
--
ALTER TABLE `critiquer`
  ADD CONSTRAINT `critiquer_ibfk_1` FOREIGN KEY (`idR`) REFERENCES `resto` (`idR`),
  ADD CONSTRAINT `critiquer_ibfk_2` FOREIGN KEY (`mailU`) REFERENCES `utilisateur` (`mailU`);

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`idR`) REFERENCES `resto` (`idR`);

--
-- Contraintes pour la table `preferer`
--
ALTER TABLE `preferer`
  ADD CONSTRAINT `preferer_ibfk_1` FOREIGN KEY (`mailU`) REFERENCES `utilisateur` (`mailU`),
  ADD CONSTRAINT `preferer_ibfk_2` FOREIGN KEY (`idTC`) REFERENCES `typecuisine` (`idTC`);

--
-- Contraintes pour la table `proposer`
--
ALTER TABLE `proposer`
  ADD CONSTRAINT `proposer_ibfk_1` FOREIGN KEY (`idR`) REFERENCES `resto` (`idR`),
  ADD CONSTRAINT `proposer_ibfk_2` FOREIGN KEY (`idTC`) REFERENCES `typecuisine` (`idTC`);
--
-- Base de données : `cashcash`
--
CREATE DATABASE IF NOT EXISTS `cashcash` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cashcash`;

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE `agence` (
  `id_agence` int(11) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `adresse` text DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id_agence`, `nom`, `adresse`, `telephone`, `email`) VALUES
(1, 'Agence Lille', '10 rue de la République, Lille', '0320000000', 'lille@cashcash.local'),
(2, 'Agence Paris', '5 avenue de la Gare, Paris', '0100000000', 'paris@cashcash.local'),
(3, 'Agence Lyon', '23 boulevard de la Croix-Rousse, Lyon', '0400000000', 'lyon@cashcash.local'),
(4, 'Agence Bordeaux', '7 quai de Brazza, Bordeaux', '0500000000', 'bordeaux@cashcash.local'),
(5, 'Agence Marseille', '15 rue Saint-Ferréol, Marseille', '0401000000', 'marseille@cashcash.local'),
(6, 'Agence Toulouse', '8 place du Capitole, Toulouse', '0502000000', 'toulouse@cashcash.local'),
(7, 'Agence Nantes', '12 rue Crucy, Nantes', '0200000000', 'nantes@cashcash.local'),
(8, 'Agence Strasbourg', '5 rue des Hallebardes, Strasbourg', '0300000000', 'strasbourg@cashcash.local'),
(9, 'Agence Nice', '24 avenue Jean Médecin, Nice', '0403000000', 'nice@cashcash.local'),
(10, 'Agence Rennes', '1 place de la Mairie, Rennes', '0201000000', 'rennes@cashcash.local');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `raison_sociale` varchar(255) NOT NULL,
  `siren` varchar(14) DEFAULT NULL,
  `code_ape` varchar(10) DEFAULT NULL,
  `adresse` text DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `id_agence` int(11) NOT NULL,
  `distance_km` int(11) DEFAULT 0,
  `duree_deplacement_min` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `raison_sociale`, `siren`, `code_ape`, `adresse`, `telephone`, `email`, `id_agence`, `distance_km`, `duree_deplacement_min`) VALUES
(1, 'SuperMarché Dupont', '12345678901234', '4711', '1 rue du Test, Lille', '0320111111', 'contact@dupont.fr', 1, 12, 30),
(2, 'Boulangerie Patisserie', '45678912304567', '4781', '3 rue de la Gare, Lille', '0320333333', 'contact@boulangerie.fr', 1, 8, 20),
(3, 'Boutique Martin', '98765432109876', '5610', '2 rue du Commerce, Paris', '0144222222', 'martin@boutique.fr', 2, 5, 15),
(4, 'Restaurant Lyonnais', '11121314151617', '5610', '10 rue de la République, Lyon', '0478111111', 'contact@restaurantlyonnais.fr', 3, 7, 20),
(5, 'Cave à Vin Bordeaux', '22232425262728', '4725', '5 quai des Chartrons, Bordeaux', '0556222222', 'contact@caveavin.fr', 4, 10, 25),
(6, 'Boutique de Plage', '33343536373839', '4778', '12 corniche Kennedy, Marseille', '0491333333', 'contact@boutiquedeplage.fr', 5, 15, 30),
(7, 'Café des Arts', '44454647484950', '5630', '15 rue des Arts, Toulouse', '0502111111', 'contact@cafedesarts.fr', 6, 10, 25),
(8, 'Épicerie du Coin', '55565758596061', '4711', '7 rue du Marché, Toulouse', '0502444444', 'contact@epicerieducoin.fr', 6, 5, 10),
(9, 'Librairie Horizon', '66676869707172', '4761', '3 avenue des Lumières, Nantes', '0200222222', 'contact@librairiehorizon.fr', 7, 7, 20),
(10, 'Boulangerie Tradition', '77787980818283', '4781', '15 rue des Artisans, Nantes', '0201444444', 'contact@boulangerietradition.fr', 7, 12, 30),
(11, 'Garage AutoPlus', '88899091929394', '4520', '22 boulevard de la République, Strasbourg', '0300333333', 'contact@garageautoplus.fr', 8, 15, 35),
(12, 'Restaurant Le Bistrot', '99909192939495', '5610', '12 rue de la Liberté, Nice', '0403111111', 'contact@lebistrot.fr', 9, 8, 20),
(13, 'Pharmacie Santé', '10111213141516', '4773', '8 rue de la Pharmacie, Nice', '0403333333', 'contact@pharmaciesante.fr', 9, 5, 15),
(14, 'Café Littéraire', '20212223242526', '5630', '3 place des Livres, Nice', '0403555555', 'contact@cafelitteraire.fr', 9, 7, 18),
(15, 'Magasin Sportif', '30313233343536', '4764', '5 avenue du Stade, Rennes', '0201222222', 'contact@magasinsportif.fr', 10, 10, 25);

-- --------------------------------------------------------

--
-- Structure de la table `contrat_maintenance`
--

CREATE TABLE `contrat_maintenance` (
  `num_contrat` varchar(50) NOT NULL,
  `id_client` int(11) NOT NULL,
  `date_signature_initiale` date NOT NULL,
  `date_signature` date NOT NULL,
  `date_echeance` date NOT NULL,
  `taux_annuel` decimal(5,2) DEFAULT 10.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `contrat_maintenance`
--

INSERT INTO `contrat_maintenance` (`num_contrat`, `id_client`, `date_signature_initiale`, `date_signature`, `date_echeance`, `taux_annuel`) VALUES
('C-0001', 1, '2021-05-01', '2021-05-01', '2022-05-01', 10.00),
('C-0002', 2, '2022-02-01', '2022-02-01', '2023-02-01', 10.00),
('C-0003', 3, '2021-07-01', '2021-07-01', '2022-07-01', 12.00),
('C-0004', 4, '2022-04-01', '2022-04-01', '2023-04-01', 10.00),
('C-0005', 5, '2022-07-01', '2022-07-01', '2023-07-01', 12.00),
('C-0006', 6, '2022-08-01', '2022-08-01', '2023-08-01', 10.00),
('C-0007', 7, '2022-10-01', '2022-10-01', '2023-10-01', 10.00),
('C-0008', 8, '2023-02-01', '2023-02-01', '2024-02-01', 10.00),
('C-0009', 9, '2023-04-01', '2023-04-01', '2024-04-01', 12.00),
('C-0010', 10, '2023-05-01', '2023-05-01', '2024-05-01', 10.00),
('C-0011', 11, '2023-07-01', '2023-07-01', '2024-07-01', 12.00),
('C-0012', 12, '2023-08-01', '2023-08-01', '2024-08-01', 10.00),
('C-0013', 13, '2023-09-01', '2023-09-01', '2024-09-01', 10.00),
('C-0014', 14, '2023-10-01', '2023-10-01', '2024-10-01', 12.00),
('C-0015', 15, '2023-11-01', '2023-11-01', '2024-11-01', 10.00);

-- --------------------------------------------------------

--
-- Structure de la table `contrat_materiel`
--

CREATE TABLE `contrat_materiel` (
  `id` int(11) NOT NULL,
  `num_contrat` varchar(50) NOT NULL,
  `num_serie` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `contrat_materiel`
--

INSERT INTO `contrat_materiel` (`id`, `num_contrat`, `num_serie`) VALUES
(1, 'C-0001', '00201'),
(2, 'C-0001', '00202'),
(3, 'C-0002', '00203'),
(4, 'C-0003', '00204'),
(5, 'C-0003', '00205'),
(6, 'C-0004', '00206'),
(7, 'C-0004', '00207'),
(8, 'C-0005', '00208'),
(9, 'C-0005', '00209'),
(10, 'C-0006', '00210'),
(11, 'C-0006', '00211'),
(12, 'C-0007', '00212'),
(13, 'C-0007', '00213'),
(14, 'C-0008', '00214'),
(15, 'C-0008', '00215'),
(16, 'C-0009', '00216'),
(17, 'C-0010', '00217'),
(18, 'C-0011', '00218'),
(19, 'C-0011', '00219'),
(20, 'C-0012', '00220'),
(21, 'C-0013', '00221'),
(22, 'C-0014', '00222'),
(23, 'C-0015', '00223'),
(24, 'C-0015', '00224');

-- --------------------------------------------------------

--
-- Structure de la table `famille`
--

CREATE TABLE `famille` (
  `code_famille` varchar(10) NOT NULL,
  `libelle_famille` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `famille`
--

INSERT INTO `famille` (`code_famille`, `libelle_famille`) VALUES
('CA', 'Caisses enregistreuses'),
('EC', 'Écrans tactiles'),
('IM', 'Imprimantes'),
('PC', 'Petite caisse'),
('SC', 'Scanners'),
('SE', 'Serveur'),
('SO', 'Solutions logicielles');

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

CREATE TABLE `intervention` (
  `id_intervention` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_technicien` int(11) NOT NULL,
  `date_intervention` datetime NOT NULL,
  `statut` enum('PREVU','REALISE','ANNULE') DEFAULT 'PREVU',
  `distance_km` int(11) DEFAULT 0,
  `temps_total` int(11) DEFAULT 0,
  `commentaire_global` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `intervention`
--

INSERT INTO `intervention` (`id_intervention`, `id_client`, `id_technicien`, `date_intervention`, `statut`, `distance_km`, `temps_total`, `commentaire_global`) VALUES
(1, 1, 1, '2024-01-15 09:30:00', 'REALISE', 12, 0, NULL),
(2, 1, 1, '2024-02-20 14:00:00', 'REALISE', 12, 0, NULL),
(3, 2, 2, '2024-03-10 10:15:00', 'REALISE', 8, 0, NULL),
(4, 3, 3, '2024-01-20 11:00:00', 'REALISE', 5, 0, NULL),
(5, 3, 3, '2024-03-15 16:30:00', 'REALISE', 5, 0, NULL),
(6, 4, 4, '2024-02-10 09:00:00', 'REALISE', 7, 0, NULL),
(7, 4, 4, '2024-04-05 14:00:00', 'REALISE', 7, 0, NULL),
(8, 5, 5, '2024-02-20 10:30:00', 'REALISE', 10, 0, NULL),
(9, 5, 5, '2024-05-10 15:00:00', 'REALISE', 10, 0, NULL),
(10, 6, 6, '2024-03-05 09:30:00', 'REALISE', 15, 0, NULL),
(11, 6, 6, '2024-05-20 14:00:00', 'REALISE', 15, 0, NULL),
(12, 7, 7, '2024-03-15 10:00:00', 'REALISE', 10, 0, NULL),
(13, 7, 7, '2024-05-25 16:00:00', 'REALISE', 10, 0, NULL),
(14, 8, 7, '2024-04-10 11:30:00', 'REALISE', 5, 0, NULL),
(15, 8, 7, '2024-06-15 13:45:00', 'REALISE', 5, 0, NULL),
(16, 9, 8, '2024-04-05 09:00:00', 'REALISE', 7, 0, NULL),
(17, 10, 8, '2024-06-20 14:00:00', 'REALISE', 12, 0, NULL),
(18, 11, 9, '2024-04-15 10:30:00', 'REALISE', 15, 0, NULL),
(19, 11, 9, '2024-06-30 16:00:00', 'REALISE', 15, 0, NULL),
(20, 12, 10, '2024-05-10 09:30:00', 'REALISE', 8, 0, NULL),
(21, 13, 10, '2024-07-05 14:00:00', 'REALISE', 5, 0, NULL),
(22, 14, 11, '2024-07-20 10:30:00', 'REALISE', 7, 0, NULL),
(23, 15, 12, '2024-05-25 09:00:00', 'REALISE', 10, 0, NULL),
(24, 15, 12, '2024-07-30 16:00:00', 'REALISE', 10, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `intervention_materiel`
--

CREATE TABLE `intervention_materiel` (
  `id_intervention_materiel` int(11) NOT NULL,
  `id_intervention` int(11) NOT NULL,
  `num_serie` varchar(50) NOT NULL,
  `temps_passe_minutes` int(11) DEFAULT 0,
  `commentaire` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

CREATE TABLE `materiel` (
  `num_serie` varchar(50) NOT NULL,
  `ref_interne` varchar(20) NOT NULL,
  `id_client` int(11) NOT NULL,
  `date_vente` date DEFAULT NULL,
  `date_installation` date DEFAULT NULL,
  `prix_vente` decimal(10,2) DEFAULT NULL,
  `emplacement` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`num_serie`, `ref_interne`, `id_client`, `date_vente`, `date_installation`, `prix_vente`, `emplacement`) VALUES
('00201', 'A506', 1, '2021-03-10', '2021-05-12', 32.50, 'Boulangerie'),
('00202', 'B106', 1, '2021-04-10', '2021-05-17', 18.50, 'Boulangerie'),
('00203', 'D200', 2, '2022-01-15', '2022-02-20', 450.00, 'Caisse'),
('00204', 'C701', 3, '2021-06-15', '2021-07-20', 120.00, 'Caisse'),
('00205', 'E300', 3, '2022-02-10', '2022-03-15', 120.00, 'Comptoir'),
('00206', 'F400', 4, '2022-03-05', '2022-04-10', 350.00, 'Salle'),
('00207', 'G500', 4, '2022-04-20', '2022-05-25', 550.00, 'Cuisine'),
('00208', 'H600', 5, '2022-05-10', '2022-06-15', 220.00, 'Stock'),
('00209', 'I700', 5, '2022-06-20', '2022-07-25', 650.00, 'Caisse'),
('00210', 'J800', 6, '2022-07-05', '2022-08-10', 250.00, 'Boutique'),
('00211', 'K900', 6, '2022-08-15', '2022-09-20', 500.00, 'Bureau'),
('00212', 'L100', 7, '2022-09-10', '2022-10-15', 350.00, 'Café'),
('00213', 'M200', 7, '2022-10-20', '2022-11-25', 600.00, 'Stock'),
('00214', 'A506', 8, '2023-01-05', '2023-02-10', 32.50, 'Épicerie'),
('00215', 'B106', 8, '2023-02-15', '2023-03-20', 18.50, 'Épicerie'),
('00216', 'N300', 9, '2023-03-10', '2023-04-15', 200.00, 'Librairie'),
('00217', 'D200', 10, '2023-04-20', '2023-05-25', 450.00, 'Boulangerie'),
('00218', 'E300', 11, '2023-05-10', '2023-06-15', 120.00, 'Garage'),
('00219', 'F400', 11, '2023-06-20', '2023-07-25', 350.00, 'Atelier'),
('00220', 'G500', 12, '2023-07-10', '2023-08-15', 550.00, 'Restaurant'),
('00221', 'H600', 13, '2023-08-05', '2023-09-10', 220.00, 'Pharmacie'),
('00222', 'I700', 14, '2023-09-15', '2023-10-20', 650.00, 'Café'),
('00223', 'J800', 15, '2023-10-10', '2023-11-15', 250.00, 'Magasin'),
('00224', 'K900', 15, '2023-11-20', '2023-12-25', 500.00, 'Bureau');

-- --------------------------------------------------------

--
-- Structure de la table `technicien`
--

CREATE TABLE `technicien` (
  `id_technicien` int(11) NOT NULL,
  `matricule` varchar(50) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `qualification` varchar(150) DEFAULT NULL,
  `date_obtention_qualification` date DEFAULT NULL,
  `mail` varchar(150) DEFAULT NULL,
  `tel_mobile` varchar(30) DEFAULT NULL,
  `id_agence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `technicien`
--

INSERT INTO `technicien` (`id_technicien`, `matricule`, `nom`, `prenom`, `qualification`, `date_obtention_qualification`, `mail`, `tel_mobile`, `id_agence`) VALUES
(1, 'T-100', 'Dupuis', 'Jean', 'Niveau 2', '2019-06-12', 'j.dupuis@cashcash.local', '0600000001', 1),
(2, 'T-101', 'Martin', 'Pierre', 'Niveau 1', '2020-01-15', 'p.martin@cashcash.local', '0600000002', 1),
(3, 'T-102', 'Bernard', 'Luc', 'Niveau 3', '2018-11-20', 'l.bernard@cashcash.local', '0600000003', 2),
(4, 'T-103', 'Durand', 'Marie', 'Niveau 2', '2020-05-10', 'm.durand@cashcash.local', '0600000004', 3),
(5, 'T-104', 'Lefèvre', 'Paul', 'Niveau 1', '2021-02-18', 'p.lefevre@cashcash.local', '0600000005', 4),
(6, 'T-105', 'Moreau', 'Sophie', 'Niveau 3', '2019-11-05', 's.moreau@cashcash.local', '0600000006', 5),
(7, 'T-106', 'Fournier', 'Luc', 'Niveau 2', '2020-09-12', 'l.fournier@cashcash.local', '0600000007', 6),
(8, 'T-107', 'Girard', 'Élodie', 'Niveau 1', '2021-07-22', 'e.girard@cashcash.local', '0600000008', 7),
(9, 'T-108', 'Rousseau', 'Thomas', 'Niveau 3', '2018-12-30', 't.rousseau@cashcash.local', '0600000009', 8),
(10, 'T-109', 'Leroy', 'Camille', 'Niveau 2', '2020-08-15', 'c.leroy@cashcash.local', '0600000010', 9),
(11, 'T-110', 'Dubois', 'Hugo', 'Niveau 1', '2021-03-20', 'h.dubois@cashcash.local', '0600000011', 9),
(12, 'T-111', 'Petit', 'Laura', 'Niveau 3', '2019-10-10', 'l.petit@cashcash.local', '0600000012', 10),
(13, 'T-112', 'Roux', 'Nicolas', 'Niveau 2', '2020-11-05', 'n.roux@cashcash.local', '0600000013', 10);

-- --------------------------------------------------------

--
-- Structure de la table `type_materiel`
--

CREATE TABLE `type_materiel` (
  `ref_interne` varchar(20) NOT NULL,
  `libelle_type` varchar(200) NOT NULL,
  `code_famille` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `type_materiel`
--

INSERT INTO `type_materiel` (`ref_interne`, `libelle_type`, `code_famille`) VALUES
('A506', 'Souris sans fil Logitech S', 'SE'),
('B106', 'Clavier sans fil Logitech S', 'PC'),
('C701', 'Imprimante thermique Epson', 'IM'),
('D200', 'Caisse enregistreuse électronique', 'CA'),
('E300', 'Scanner code-barres 2D', 'SC'),
('F400', 'Écran tactile 15 pouces', 'EC'),
('G500', 'Caisse enregistreuse portable', 'CA'),
('H600', 'Scanner industriel', 'SC'),
('I700', 'Écran tactile 22 pouces', 'EC'),
('J800', 'Imprimante thermique portable', 'IM'),
('K900', 'Logiciel de gestion de caisse', 'SO'),
('L100', 'Imprimante laser', 'IM'),
('M200', 'Logiciel de gestion de stock', 'SO'),
('N300', 'Imprimante à tickets', 'IM');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('GESTIONNAIRE','TECHNICIEN','ADMIN') NOT NULL,
  `id_agence` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `username`, `email`, `password_hash`, `role`, `id_agence`) VALUES
(1, 'jdupuis', 'j.dupuis@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'TECHNICIEN', 1),
(2, 'admin', 'admin@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN', NULL),
(3, 'm.durand', 'm.durand@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'TECHNICIEN', 3),
(4, 'p.lefevre', 'p.lefevre@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'TECHNICIEN', 4),
(5, 's.moreau', 's.moreau@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'GESTIONNAIRE', 5),
(6, 'c.leroy', 'c.leroy@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'TECHNICIEN', 9),
(7, 'h.dubois', 'h.dubois@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'TECHNICIEN', 9),
(8, 'l.petit', 'l.petit@cashcash.local', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'GESTIONNAIRE', 10);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id_agence`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD KEY `id_agence` (`id_agence`);

--
-- Index pour la table `contrat_maintenance`
--
ALTER TABLE `contrat_maintenance`
  ADD PRIMARY KEY (`num_contrat`),
  ADD KEY `id_client` (`id_client`);

--
-- Index pour la table `contrat_materiel`
--
ALTER TABLE `contrat_materiel`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `num_contrat` (`num_contrat`,`num_serie`),
  ADD KEY `num_serie` (`num_serie`);

--
-- Index pour la table `famille`
--
ALTER TABLE `famille`
  ADD PRIMARY KEY (`code_famille`);

--
-- Index pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD PRIMARY KEY (`id_intervention`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_technicien` (`id_technicien`);

--
-- Index pour la table `intervention_materiel`
--
ALTER TABLE `intervention_materiel`
  ADD PRIMARY KEY (`id_intervention_materiel`),
  ADD UNIQUE KEY `id_intervention` (`id_intervention`,`num_serie`),
  ADD KEY `num_serie` (`num_serie`);

--
-- Index pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD PRIMARY KEY (`num_serie`),
  ADD KEY `ref_interne` (`ref_interne`),
  ADD KEY `id_client` (`id_client`);

--
-- Index pour la table `technicien`
--
ALTER TABLE `technicien`
  ADD PRIMARY KEY (`id_technicien`),
  ADD UNIQUE KEY `matricule` (`matricule`),
  ADD KEY `id_agence` (`id_agence`);

--
-- Index pour la table `type_materiel`
--
ALTER TABLE `type_materiel`
  ADD PRIMARY KEY (`ref_interne`),
  ADD KEY `code_famille` (`code_famille`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_agence` (`id_agence`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `agence`
--
ALTER TABLE `agence`
  MODIFY `id_agence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `contrat_materiel`
--
ALTER TABLE `contrat_materiel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `intervention`
--
ALTER TABLE `intervention`
  MODIFY `id_intervention` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `intervention_materiel`
--
ALTER TABLE `intervention_materiel`
  MODIFY `id_intervention_materiel` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `technicien`
--
ALTER TABLE `technicien`
  MODIFY `id_technicien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);

--
-- Contraintes pour la table `contrat_maintenance`
--
ALTER TABLE `contrat_maintenance`
  ADD CONSTRAINT `contrat_maintenance_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `contrat_materiel`
--
ALTER TABLE `contrat_materiel`
  ADD CONSTRAINT `contrat_materiel_ibfk_1` FOREIGN KEY (`num_contrat`) REFERENCES `contrat_maintenance` (`num_contrat`),
  ADD CONSTRAINT `contrat_materiel_ibfk_2` FOREIGN KEY (`num_serie`) REFERENCES `materiel` (`num_serie`);

--
-- Contraintes pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD CONSTRAINT `intervention_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `intervention_ibfk_2` FOREIGN KEY (`id_technicien`) REFERENCES `technicien` (`id_technicien`);

--
-- Contraintes pour la table `intervention_materiel`
--
ALTER TABLE `intervention_materiel`
  ADD CONSTRAINT `fk_intervention_materiel_intervention` FOREIGN KEY (`id_intervention`) REFERENCES `intervention` (`id_intervention`) ON DELETE CASCADE,
  ADD CONSTRAINT `intervention_materiel_ibfk_2` FOREIGN KEY (`num_serie`) REFERENCES `materiel` (`num_serie`) ON DELETE CASCADE;

--
-- Contraintes pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `materiel_ibfk_1` FOREIGN KEY (`ref_interne`) REFERENCES `type_materiel` (`ref_interne`),
  ADD CONSTRAINT `materiel_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `technicien`
--
ALTER TABLE `technicien`
  ADD CONSTRAINT `technicien_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);

--
-- Contraintes pour la table `type_materiel`
--
ALTER TABLE `type_materiel`
  ADD CONSTRAINT `type_materiel_ibfk_1` FOREIGN KEY (`code_famille`) REFERENCES `famille` (`code_famille`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id_agence`);
--
-- Base de données : `csv_db 8`
--
CREATE DATABASE IF NOT EXISTS `csv_db 8` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `csv_db 8`;

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--
-- Erreur de lecture de structure pour la table csv_db 8.personnel : #1932 - Table 'csv_db 8.personnel' doesn't exist in engine
-- Erreur de lecture des données pour la table csv_db 8.personnel : #1064 - Erreur de syntaxe près de 'FROM `csv_db 8`.`personnel`' à la ligne 1

-- --------------------------------------------------------

--
-- Structure de la table `section`
--
-- Erreur de lecture de structure pour la table csv_db 8.section : #1932 - Table 'csv_db 8.section' doesn't exist in engine
-- Erreur de lecture des données pour la table csv_db 8.section : #1064 - Erreur de syntaxe près de 'FROM `csv_db 8`.`section`' à la ligne 1
--
-- Base de données : `exercice_curseurs`
--
CREATE DATABASE IF NOT EXISTS `exercice_curseurs` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `exercice_curseurs`;

-- --------------------------------------------------------

--
-- Structure de la table `achats`
--

CREATE TABLE `achats` (
  `id` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL,
  `montant` decimal(10,2) DEFAULT NULL,
  `dateAchat` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `achats`
--

INSERT INTO `achats` (`id`, `idClient`, `montant`, `dateAchat`) VALUES
(1, 1, 50.00, '2025-01-05'),
(2, 1, 95.90, '2025-01-22'),
(3, 1, 80.00, '2025-02-10'),
(4, 2, 200.00, '2025-01-15'),
(5, 3, 40.00, '2025-03-05'),
(6, 3, 60.00, '2025-03-18');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `nom`) VALUES
(1, 'Alice'),
(2, 'Bob'),
(3, 'Charlie');

-- --------------------------------------------------------

--
-- Structure de la table `resultat`
--

CREATE TABLE `resultat` (
  `id` int(11) NOT NULL,
  `message` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `resultat`
--

INSERT INTO `resultat` (`id`, `message`) VALUES
(1, 'Client 1 : Alice'),
(2, 'Client 2 : Bob'),
(3, 'Client 3 : Charlie'),
(4, 'Client 1 : Alice'),
(5, 'Client 2 : Bob'),
(6, 'Client 3 : Charlie');

-- --------------------------------------------------------

--
-- Structure de la table `resultat_achats`
--

CREATE TABLE `resultat_achats` (
  `id` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL,
  `mois` varchar(7) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `resultat_achats`
--

INSERT INTO `resultat_achats` (`id`, `idClient`, `mois`, `total`) VALUES
(1, 1, '2025-01', 145.90),
(2, 1, '2025-02', 80.00),
(3, 2, '2025-01', 200.00),
(4, 3, '2025-03', 100.00);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `achats`
--
ALTER TABLE `achats`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `resultat_achats`
--
ALTER TABLE `resultat_achats`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `achats`
--
ALTER TABLE `achats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `resultat`
--
ALTER TABLE `resultat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `resultat_achats`
--
ALTER TABLE `resultat_achats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `achats`
--
ALTER TABLE `achats`
  ADD CONSTRAINT `achats_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `clients` (`id`);
--
-- Base de données : `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Structure de la table `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) NOT NULL DEFAULT '',
  `user` varchar(255) NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `query` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Structure de la table `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) NOT NULL,
  `col_name` varchar(64) NOT NULL,
  `col_type` varchar(64) NOT NULL,
  `col_length` text DEFAULT NULL,
  `col_collation` varchar(64) NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) DEFAULT '',
  `col_default` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Structure de la table `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `column_name` varchar(64) NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transformation` varchar(255) NOT NULL DEFAULT '',
  `transformation_options` varchar(255) NOT NULL DEFAULT '',
  `input_transformation` varchar(255) NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) NOT NULL,
  `settings_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Déchargement des données de la table `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"snap_to_grid\":\"off\",\"angular_direct\":\"direct\",\"relation_lines\":\"true\"}');

-- --------------------------------------------------------

--
-- Structure de la table `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `export_type` varchar(10) NOT NULL,
  `template_name` varchar(64) NOT NULL,
  `template_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Structure de la table `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Structure de la table `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db` varchar(64) NOT NULL DEFAULT '',
  `table` varchar(64) NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_type` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Structure de la table `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Déchargement des données de la table `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"cashcash\",\"table\":\"intervention_materiel\"},{\"db\":\"cashcash\",\"table\":\"materiel\"},{\"db\":\"capliez_bd1\",\"table\":\"utilisateur\"},{\"db\":\"cashcash\",\"table\":\"intervention\"},{\"db\":\"INFORMATION_SCHEMA\",\"table\":\"KEY_COLUMN_USAGE\"},{\"db\":\"cashcash\",\"table\":\"intervention_old\"},{\"db\":\"cashcash\",\"table\":\"technicien\"},{\"db\":\"cashcash\",\"table\":\"client\"},{\"db\":\"cashcash\",\"table\":\"utilisateur\"},{\"db\":\"cashcash\",\"table\":\"type_materiel\"}]');

-- --------------------------------------------------------

--
-- Structure de la table `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) NOT NULL DEFAULT '',
  `master_table` varchar(64) NOT NULL DEFAULT '',
  `master_field` varchar(64) NOT NULL DEFAULT '',
  `foreign_db` varchar(64) NOT NULL DEFAULT '',
  `foreign_table` varchar(64) NOT NULL DEFAULT '',
  `foreign_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Structure de la table `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `search_name` varchar(64) NOT NULL DEFAULT '',
  `search_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `display_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prefs` text NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

--
-- Déchargement des données de la table `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'cashcash', 'intervention', '[]', '2025-12-15 09:48:20');

-- --------------------------------------------------------

--
-- Structure de la table `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text NOT NULL,
  `schema_sql` text DEFAULT NULL,
  `data_sql` longtext DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Déchargement des données de la table `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2025-12-15 12:59:04', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"fr\"}');

-- --------------------------------------------------------

--
-- Structure de la table `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) NOT NULL,
  `tab` varchar(64) NOT NULL,
  `allowed` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Structure de la table `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) NOT NULL,
  `usergroup` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Index pour la table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Index pour la table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Index pour la table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Index pour la table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Index pour la table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Index pour la table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Index pour la table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Index pour la table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Index pour la table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Index pour la table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Index pour la table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Index pour la table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Index pour la table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Base de données : `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
--
-- Base de données : `testmdp`
--
CREATE DATABASE IF NOT EXISTS `testmdp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `testmdp`;
--
-- Base de données : `tpmdp`
--
CREATE DATABASE IF NOT EXISTS `tpmdp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tpmdp`;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--
-- Erreur de lecture de structure pour la table tpmdp.users : #1932 - Table 'tpmdp.users' doesn't exist in engine
-- Erreur de lecture des données pour la table tpmdp.users : #1064 - Erreur de syntaxe près de 'FROM `tpmdp`.`users`' à la ligne 1
--
-- Base de données : `xelfi`
--
CREATE DATABASE IF NOT EXISTS `xelfi` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `xelfi`;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `code` varchar(6) NOT NULL,
  `code_categorie` varchar(3) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` double NOT NULL,
  `date` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `code` varchar(3) NOT NULL,
  `designation` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `code` varchar(6) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `carte_fidelite` tinyint(1) NOT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`code`, `nom`, `prenom`, `carte_fidelite`, `date`) VALUES
('1', 'collomb', 'corentin', 1, '2025-11-24');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `code` varchar(15) NOT NULL,
  `code_client` varchar(6) NOT NULL,
  `total_ttc` double NOT NULL,
  `code_mode_reglement` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `lignes_factures`
--

CREATE TABLE `lignes_factures` (
  `code_facture` varchar(15) NOT NULL,
  `code_article` varchar(6) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prix_unitaire` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `mode_reglement`
--

CREATE TABLE `mode_reglement` (
  `code` int(11) NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`code`),
  ADD KEY `code_categorie` (`code_categorie`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`code`),
  ADD KEY `code_client` (`code_client`),
  ADD KEY `code_mode_reglement` (`code_mode_reglement`);

--
-- Index pour la table `lignes_factures`
--
ALTER TABLE `lignes_factures`
  ADD PRIMARY KEY (`code_facture`,`code_article`);

--
-- Index pour la table `mode_reglement`
--
ALTER TABLE `mode_reglement`
  ADD PRIMARY KEY (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
