-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 23 jan. 2022 à 21:19
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java_projet_ism`
--

-- --------------------------------------------------------

--
-- Structure de la table `constantes`
--

DROP TABLE IF EXISTS `constantes`;
CREATE TABLE IF NOT EXISTS `constantes` (
  `id_constante` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL,
  `consultation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_constante`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `constantes`
--

INSERT INTO `constantes` (`id_constante`, `libelle`, `valeur`, `consultation_id`) VALUES
(1, 'TEMPERATURE', '12', 4),
(2, 'TENSION', '10', 2),
(3, 'TEMPERATURE', '50', 2),
(4, 'TENSION', '15', 2),
(5, 'TEMPERATURE', '12', 2);

-- --------------------------------------------------------

--
-- Structure de la table `dosmedi`
--

DROP TABLE IF EXISTS `dosmedi`;
CREATE TABLE IF NOT EXISTS `dosmedi` (
  `id_dosMedi` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `motif` varchar(25) NOT NULL,
  `rendezVous_id` int(11) NOT NULL,
  PRIMARY KEY (`id_dosMedi`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `dosmedi`
--

INSERT INTO `dosmedi` (`id_dosMedi`, `patient_id`, `motif`, `rendezVous_id`) VALUES
(1, 2, 'PRESTATION', 1),
(2, 2, 'PRESTATION', 4),
(3, 3, 'CONSULTATION', 2),
(4, 3, 'CONSULTATION', 2),
(5, 3, 'CONSULTATION', 2),
(6, 3, 'CONSULTATION', 2);

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `id_medicament` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `ordonnance_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_medicament`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`id_medicament`, `code`, `nom`, `ordonnance_id`) VALUES
(1, '100', 'PARACETAMOLE', NULL),
(2, '101', 'EPHERALGANT', NULL),
(3, '102', 'PARAPHISE', NULL),
(4, '103', 'ANTIBACTERIE', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

DROP TABLE IF EXISTS `ordonnance`;
CREATE TABLE IF NOT EXISTS `ordonnance` (
  `id_ordonnance` int(11) NOT NULL AUTO_INCREMENT,
  `posologie` varchar(255) NOT NULL,
  `medicament_id` int(11) NOT NULL,
  `consultation_id` int(11) NOT NULL,
  PRIMARY KEY (`id_ordonnance`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ordonnance`
--

INSERT INTO `ordonnance` (`id_ordonnance`, `posologie`, `medicament_id`, `consultation_id`) VALUES
(1, 'posologie1', 1, 3),
(2, 'posologie3', 3, 3),
(3, 'posologie4', 4, 3),
(4, 'posologie0', 2, 3),
(5, 'posologie0', 1, 3),
(6, 'AZERTYUIOP', 1, 4),
(7, '1matin', 1, 2),
(8, '1matin', 1, 2),
(9, '1matin', 1, 2),
(10, '1matin', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
CREATE TABLE IF NOT EXISTS `rendezvous` (
  `id_rv` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  `statut` varchar(25) NOT NULL,
  `motif` varchar(25) NOT NULL,
  `medecin_id` int(11) DEFAULT NULL,
  `typeMedecin` varchar(25) NOT NULL,
  `ordonnance_id` int(11) DEFAULT NULL,
  `constante_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_rv`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rendezvous`
--

INSERT INTO `rendezvous` (`id_rv`, `date`, `patient_id`, `statut`, `motif`, `medecin_id`, `typeMedecin`, `ordonnance_id`, `constante_id`) VALUES
(1, '2021-10-10', 1, 'EN_COURS', 'PRESTATION', 5, 'RADIOLOGUE', NULL, NULL),
(2, '2022-03-28', 3, 'EN_COURS', 'CONSULTATION', 5, 'RADIOLOGUE', 9, 4),
(3, '2021-12-08', 1, 'VALIDER', 'CONSULTATION', 5, 'RADIOLOGUE', NULL, NULL),
(4, '2021-12-08', 2, 'VALIDER', 'PRESTATION', 5, 'RADIOLOGUE', NULL, NULL),
(7, NULL, 3, 'EN_COURS', 'PRESTATION', 6, '', NULL, NULL),
(9, NULL, 1, 'VALIDE', 'CONSULTATION', 6, 'RESPONSSABLE ', NULL, NULL),
(10, NULL, 1, 'EN_COURS', 'CONSULTATION', 8, 'RESPONSSALBE', NULL, NULL),
(11, NULL, 1, 'EN_COURS', 'CONSULTATION', NULL, 'RESPONSSABLE', NULL, NULL),
(12, NULL, 1, 'EN_COURS', 'CONSULTATION', NULL, 'RESPONSSABLE', NULL, NULL),
(13, NULL, 3, 'EN_COURS', 'CONSULTATION', NULL, 'RESPONSSABLE', NULL, NULL),
(14, NULL, 1, 'VALIDER', 'CONSULTATION', 4, 'GENERALISTE', NULL, NULL),
(15, NULL, 1, 'EN_COURS', 'PRESTATION', 5, 'RADIOLOGUE', NULL, NULL),
(16, NULL, 1, 'EN_COURS', 'CONSULTATION', NULL, 'RHUMATOLOGUE', NULL, NULL),
(17, NULL, 1, 'EN_COURS', 'PRESTATION', 6, 'DENTISTE', NULL, NULL),
(18, NULL, 1, 'EN_COURS', 'CONSULTATION', NULL, 'NEUROLOGUE', NULL, NULL),
(19, NULL, 1, 'EN_COURS', 'PRESTATION', NULL, 'OPHTALMOLOGUE', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_complet` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `fonction` varchar(255) DEFAULT NULL,
  `antecedent` varchar(255) DEFAULT NULL,
  `rv_id` int(11) DEFAULT NULL,
  `dosMedi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom_complet`, `login`, `password`, `role`, `code`, `fonction`, `antecedent`, `rv_id`, `dosMedi_id`) VALUES
(1, 'nom1', 'mail1@gmail.com', 'passe1', 'ROLE_PATIENT', 'f7e664cf-b72b-415d-83b2-04e4e84b4876', NULL, NULL, NULL, NULL),
(2, 'nom2', 'mail2@gmail.com', 'passe2', 'ROLE_PATIENT', 'f7e664cf-b72b-415d-83b2-04e4e84b4875', NULL, 'Diabete', NULL, NULL),
(3, 'nom3', 'login3@gmail.com', 'passe3', 'ROLE_PATIENT', 'f7e664cf-b72b-415d-83b2-04e4e84b4874', NULL, 'DIABETE', NULL, NULL),
(4, 'Medecin4', 'login4@gmail.com', 'passe4', 'ROLE_MEDECIN', NULL, 'GENERALISTE', NULL, NULL, NULL),
(5, 'medecin5', 'login5@gmail.com', 'passe5', 'ROLE_MEDECIN', NULL, 'RADIOLOGUE', NULL, NULL, NULL),
(6, 'Medecin6', 'login6@gmail.com', 'password6', 'ROLE_MEDECIN', '', 'OPHTALMOLOGUE', NULL, NULL, NULL),
(7, 'secretaire', 'secretaire@gmail.com', 'passe7', 'ROLE_SECRETAIRE', '', 'SECRETAIRE', NULL, NULL, NULL),
(8, 'responsable', 'responsable@gmail.com', 'passe8', 'ROLE_RESPONSABLE_PRESTATION', '', 'RESPONSSABLE', NULL, NULL, NULL),
(9, 'test', 'mailtest@gmail.com', 'passe', 'ROLE_PATIENT', 'f7e664cf-b72b-415d-83b2-04e4e84b4870', NULL, NULL, NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
