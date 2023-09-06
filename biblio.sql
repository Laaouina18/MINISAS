-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 06 sep. 2023 à 18:06
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `biblio`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `num`, `adresse`, `cin`) VALUES
(1, 'NOUHA', 'NOUHA', 56467, 'BFNFJKJK', 'YU6578');

-- --------------------------------------------------------

--
-- Structure de la table `emprunte`
--

CREATE TABLE `emprunte` (
  `idLivre` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déclencheurs `emprunte`
--
DELIMITER $$
CREATE TRIGGER `emprunte_i_trigger` BEFORE INSERT ON `emprunte` FOR EACH ROW BEGIN

    UPDATE livre
    SET status = 'emprunté'  
    WHERE id = NEW.idLivre;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `emprunte_trigger` BEFORE DELETE ON `emprunte` FOR EACH ROW BEGIN
 
    UPDATE livre
    SET status = 'disponible' 
    WHERE id = OLD.idLivre;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `NomAuth` varchar(255) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'disponible'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `nom`, `NomAuth`, `ISBN`, `status`) VALUES
(1, 'Titre du livre', 'Auteur du livre', '123456', 'Archivé'),
(2, 'Nouveau Titre', 'Nouvel Auteur', '987654', 'Archivé'),
(3, 'Titre du livre', 'Auteur du livre', '123456', 'Archivé'),
(4, 'FGG', 'FGYT', '45', 'disponible');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `emprunte`
--
ALTER TABLE `emprunte`
  ADD PRIMARY KEY (`idLivre`,`idClient`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `emprunte`
--
ALTER TABLE `emprunte`
  ADD CONSTRAINT `emprunte_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `emprunte_ibfk_2` FOREIGN KEY (`idLivre`) REFERENCES `livre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
