-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 01 avr. 2021 à 13:50
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `mstore`
--

-- --------------------------------------------------------

--
-- Structure de la table `actulites`
--

CREATE TABLE `actulites` (
  `id_actu` int(11) NOT NULL,
  `id_perso` int(11) NOT NULL DEFAULT 0,
  `body` text NOT NULL,
  `categorie` varchar(150) NOT NULL,
  `date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `actulites`
--

INSERT INTO `actulites` (`id_actu`, `id_perso`, `body`, `categorie`, `date`) VALUES
(14, 0, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">votre instrument est okay</font></p></body></html>', 'info', '2021-03-31'),
(15, 0, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">votre actu est okay</font></p></body></html>', 'actu', '2021-03-31'),
(16, 0, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">votre message est okay</font></p></body></html>', 'message', '2021-03-31');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_comment` int(11) NOT NULL,
  `id_actu` int(11) NOT NULL,
  `id_perso` int(11) NOT NULL,
  `body` text NOT NULL,
  `date` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_comment`, `id_actu`, `id_perso`, `body`, `date`) VALUES
(4, 14, 0, ',,sdf,v', '2021/03/31 15:23:03'),
(5, 16, 0, ', fvjsndfvns mevf', '2021/03/31 15:23:30'),
(6, 15, 0, 'kzopejfqpozed,', '2021/03/31 15:38:08'),
(7, 14, 0, ',rop,sdr,fv', '2021/04/01 11:10:34'),
(8, 14, 0, 'nnnnvjvjf', '2021/04/01 11:38:02'),
(9, 14, 0, 'fzefzefs', '2021/04/01 11:41:18');

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE `likes` (
  `id_like` int(11) NOT NULL,
  `id_actu` int(11) NOT NULL,
  `id_perso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `likes`
--

INSERT INTO `likes` (`id_like`, `id_actu`, `id_perso`) VALUES
(22, 15, 0),
(23, 15, 0),
(24, 15, 0),
(25, 15, 0),
(26, 15, 0),
(27, 15, 0),
(28, 14, 0),
(29, 14, 0),
(30, 14, 0),
(31, 15, 0),
(32, 15, 0),
(33, 15, 0),
(34, 16, 0),
(35, 16, 0),
(36, 16, 0),
(37, 16, 0),
(38, 16, 0),
(39, 16, 0),
(40, 16, 0),
(41, 15, 0),
(42, 15, 0),
(43, 15, 0),
(44, 14, 0),
(45, 14, 0),
(46, 15, 0),
(47, 15, 0),
(48, 16, 0),
(49, 16, 0),
(50, 16, 0),
(51, 16, 0),
(52, 15, 0),
(53, 15, 0),
(54, 14, 0),
(55, 14, 0),
(56, 14, 0),
(57, 14, 0),
(58, 14, 0);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `emailtoreply` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `emailtoreply`) VALUES
(3, 'yaya@yaya.com');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `email` varchar(250) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `num_telephone` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `role`, `email`, `username`, `password`, `photo`, `num_telephone`) VALUES
(1, 'yaya', 'yoyo', 'admin', 'yoyo@gmail.com', 'hayoo', 'hayoo', 'hayoo.png', '25200655'),
(20, 'hhhhhhhh', 'hhhhhhhh', 'moderateur', 'hhhh@hhh.tn', 'hahahaha', 'hahahaha', NULL, '22224445'),
(21, 'faress', 'faress', 'User', 'fares@gmail.com', 'faress', 'faress', '', '11112222'),
(22, 'wilfried', 'wilfried', 'journalist', 'wilfried@gmail.tn', 'wilfried', 'wilfried', NULL, '55554444');

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `Image` varchar(250) COLLATE utf8_bin NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  `Price` double NOT NULL,
  `Date` varchar(250) COLLATE utf8_bin NOT NULL DEFAULT current_timestamp(),
  `Description` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`ID`, `Image`, `Label`, `Price`, `Date`, `Description`) VALUES
(8, 'black-acoustic.jpg', 'Black Acoustic Guitar', 150, '2021-03-30', 'azeaze'),
(9, 'blue-drums-getreal.jpg', 'Blue Drums Get Real!', 4200, '2021-03-30', 'Blue Drums Get Real!'),
(10, 'saxophone.jpg', 'Golden Saxephone', 950, '2021-03-30', 'Golden Saxephone'),
(11, 'yamahagrandpiano.jpg', 'Grand Piano', 32000, '2021-03-30', 'Yamah Grand piano Japanese'),
(12, 'hailun.jpeg', 'Grand Piano', 19000, '2021-03-30', 'Hailun Grand Piano'),
(13, 'vstyle.png', 'Electric Guitar V Style', 1950, '2021-03-30', 'Electric Guitar V Style'),
(14, 'gotone.jpg', 'Black Guitar Electric', 2100, '2021-02-28', 'to be honest i own this one');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `sujet` text DEFAULT NULL,
  `id_moderateur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `id_user`, `id_produit`, `message`, `sujet`, `id_moderateur`) VALUES
(1, 0, 0, '0', '0', NULL),
(2, 0, 0, '0', '0', NULL),
(3, 0, 0, '0', '0', NULL),
(4, 0, 0, '0', '0', NULL),
(5, 0, 0, '0', '0', NULL),
(6, 0, 0, '0', '0', NULL),
(7, 0, 0, '0', '0', NULL),
(8, 0, 0, '0', '0', NULL),
(9, NULL, NULL, 'hi', 'hi', NULL),
(10, NULL, NULL, 'sdfsdifjosid', '', NULL),
(11, NULL, NULL, 'fzzojefpzjfo', '', NULL),
(12, NULL, NULL, 'ezfflerfeje', '', NULL),
(13, NULL, NULL, 'fdnsdfs', '', NULL),
(14, NULL, NULL, 'hi', '', NULL),
(15, NULL, NULL, 'izjfozjfoizrhgoizrjgoizrjgpzjùp', 'hi', NULL),
(16, NULL, NULL, 'hi', 'azoduhzeuofhezuofjzoivjszol', NULL),
(17, NULL, NULL, 'jnseljqn;', 'ak,kmclk', NULL),
(18, NULL, NULL, 'duiqehdouhzodhqzo', 'haha', NULL),
(19, NULL, NULL, '', '', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `actulites`
--
ALTER TABLE `actulites`
  ADD PRIMARY KEY (`id_actu`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_comment`);

--
-- Index pour la table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id_like`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `actulites`
--
ALTER TABLE `actulites`
  MODIFY `id_actu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_comment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `likes`
--
ALTER TABLE `likes`
  MODIFY `id_like` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
