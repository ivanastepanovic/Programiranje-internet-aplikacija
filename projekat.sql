-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 02, 2018 at 04:47 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `autobus`
--

CREATE TABLE `autobus` (
  `idAutobus` int(11) UNSIGNED NOT NULL,
  `marka` varchar(30) CHARACTER SET latin1 NOT NULL,
  `model` varchar(30) CHARACTER SET latin1 NOT NULL,
  `brSedista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `autobus`
--

INSERT INTO `autobus` (`idAutobus`, `marka`, `model`, `brSedista`) VALUES
(1, 'Iveco', '50C13', 21),
(2, 'Neoplan', 'N122', 72);

-- --------------------------------------------------------

--
-- Table structure for table `gradskalin`
--

CREATE TABLE `gradskalin` (
  `idLinija` int(11) NOT NULL,
  `brojLinije` int(11) NOT NULL,
  `redVoznje` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `gradskalin`
--

INSERT INTO `gradskalin` (`idLinija`, `brojLinije`, `redVoznje`) VALUES
(3, 23, 'redoviVoznje/23.pdf'),
(4, 25, 'redoviVoznje/25.pdf'),
(7, 16, 'redoviVoznje/16.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `idKorisnik` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 NOT NULL,
  `ime` varchar(30) CHARACTER SET latin1 NOT NULL,
  `prezime` varchar(50) CHARACTER SET latin1 NOT NULL,
  `grad` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `opstina` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `adresa` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `datumRodjenja` date DEFAULT NULL,
  `telefon` varchar(15) CHARACTER SET latin1 NOT NULL,
  `zaposlenje` varchar(30) CHARACTER SET latin1 NOT NULL,
  `mail` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `prihvacen` smallint(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`idKorisnik`, `username`, `password`, `ime`, `prezime`, `grad`, `opstina`, `adresa`, `datumRodjenja`, `telefon`, `zaposlenje`, `mail`, `prihvacen`) VALUES
(1, 'admin', 'Admin0)', 'Ivana', 'Stepanovic', 'Beograd', '', '', '1996-03-04', '0652222244', 'student', NULL, 1),
(2, 'ivana', 'Ivana9(', 'ivana', 'st', NULL, NULL, NULL, '1996-08-13', '0652222244', 'student', NULL, 1),
(3, 'ivanka', 'Ivanka9(', 'Ivana', 'Stepanovic', 'Beograd', 'Palilula', 'Bulevar kralja Aleksandra 74', '1996-03-04', '648464698', 'student', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `linija`
--

CREATE TABLE `linija` (
  `idLinija` int(11) NOT NULL,
  `polazakStanica` int(11) NOT NULL,
  `dolazakStanica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `linija`
--

INSERT INTO `linija` (`idLinija`, `polazakStanica`, `dolazakStanica`) VALUES
(0, 1, 2),
(1, 4, 9),
(2, 3, 4),
(3, 10, 11),
(4, 11, 13),
(5, 11, 13),
(6, 11, 13),
(7, 11, 20),
(8, 4, 8);

-- --------------------------------------------------------

--
-- Table structure for table `medjugradskalin`
--

CREATE TABLE `medjugradskalin` (
  `idLinija` int(11) NOT NULL,
  `datumPolaska` date NOT NULL,
  `vremePolaska` time NOT NULL,
  `datumDolaska` date NOT NULL,
  `vremeDolaska` time NOT NULL,
  `idAutobus` int(11) NOT NULL,
  `idPrevoznik` int(11) NOT NULL,
  `brSlobodnihMesta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `medjugradskalin`
--

INSERT INTO `medjugradskalin` (`idLinija`, `datumPolaska`, `vremePolaska`, `datumDolaska`, `vremeDolaska`, `idAutobus`, `idPrevoznik`, `brSlobodnihMesta`) VALUES
(0, '2018-08-31', '12:00:00', '2018-08-31', '19:00:00', 1, 1, 21),
(1, '2018-09-28', '07:00:00', '2018-09-28', '12:00:00', 1, 1, 20),
(2, '2018-09-12', '08:00:00', '2018-09-12', '10:30:00', 0, 2, 0),
(8, '2018-09-12', '06:00:00', '2018-09-12', '13:00:00', 1, 2, 21);

-- --------------------------------------------------------

--
-- Table structure for table `medjustanice`
--

CREATE TABLE `medjustanice` (
  `idStanica` int(30) NOT NULL,
  `idLinija` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `medjustanice`
--

INSERT INTO `medjustanice` (`idStanica`, `idLinija`) VALUES
(1, 0),
(2, 0),
(3, 0),
(3, 2),
(4, 0),
(4, 1),
(4, 2),
(5, 0),
(5, 1),
(6, 0),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 3),
(11, 3),
(11, 4),
(13, 4),
(15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `mesecnagodisnja`
--

CREATE TABLE `mesecnagodisnja` (
  `idMesGod` int(11) NOT NULL,
  `zaposlenje` varchar(30) NOT NULL,
  `cenaMesecne` int(11) NOT NULL,
  `cenaGodisnje` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `mesecnagodisnja`
--

INSERT INTO `mesecnagodisnja` (`idMesGod`, `zaposlenje`, `cenaMesecne`, `cenaGodisnje`) VALUES
(7, 'nezaposlen', 1200, 9000),
(8, 'zaposlen', 3500, 20000),
(9, 'student', 1200, 10000),
(10, 'lice sa invaliditetom', 1000, 7000),
(11, 'penzioner', 500, 2000);

-- --------------------------------------------------------

--
-- Table structure for table `mesecnakarta`
--

CREATE TABLE `mesecnakarta` (
  `idMesecna` int(11) NOT NULL,
  `idKorisnik` int(11) NOT NULL,
  `tip` varchar(30) CHARACTER SET latin2 DEFAULT NULL,
  `cena` int(11) NOT NULL,
  `datumKupovine` date NOT NULL,
  `odobreno` tinyint(2) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `mesecnakarta`
--

INSERT INTO `mesecnakarta` (`idMesecna`, `idKorisnik`, `tip`, `cena`, `datumKupovine`, `odobreno`) VALUES
(6, 2, 'mese?na', 1200, '2018-09-01', 2),
(7, 2, 'godišnja', 10000, '2018-09-01', 0);

-- --------------------------------------------------------

--
-- Table structure for table `otkazane`
--

CREATE TABLE `otkazane` (
  `idOtkaz` int(11) NOT NULL,
  `idLinija` int(11) NOT NULL,
  `datumOd` date NOT NULL,
  `datumDo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `otkazane`
--

INSERT INTO `otkazane` (`idOtkaz`, `idLinija`, `datumOd`, `datumDo`) VALUES
(1, 4, '2018-08-30', '2018-08-31');

-- --------------------------------------------------------

--
-- Table structure for table `prevoznik`
--

CREATE TABLE `prevoznik` (
  `idPrevoznik` int(11) NOT NULL,
  `naziv` varchar(50) CHARACTER SET latin1 NOT NULL,
  `adresa` varchar(50) CHARACTER SET latin1 NOT NULL,
  `telefon` varchar(50) CHARACTER SET latin1 NOT NULL,
  `logo` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `prevoznik`
--

INSERT INTO `prevoznik` (`idPrevoznik`, `naziv`, `adresa`, `telefon`, `logo`) VALUES
(1, 'Banbus', 'Ulica cetvrta 21, Obrenovac', '+(381)(11)8720899', NULL),
(2, 'AS Tours', 'Sofije Paskovic 46', '+(381)(21)499749', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rezervacijakarata`
--

CREATE TABLE `rezervacijakarata` (
  `idRezervacijaKarata` int(11) NOT NULL,
  `idKorisnik` int(11) NOT NULL,
  `idLinija` int(11) NOT NULL,
  `odobreno` smallint(2) NOT NULL DEFAULT '0' COMMENT '0 na cekanju, 1 prihvacen, 2 odbijen',
  `brojKarata` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `rezervacijakarata`
--

INSERT INTO `rezervacijakarata` (`idRezervacijaKarata`, `idKorisnik`, `idLinija`, `odobreno`, `brojKarata`) VALUES
(5, 2, 1, 1, 1),
(6, 2, 1, 0, 3),
(7, 2, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `slike`
--

CREATE TABLE `slike` (
  `idSlika` int(11) NOT NULL,
  `idAutobus` int(11) NOT NULL,
  `nazivSlike` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `slike`
--

INSERT INTO `slike` (`idSlika`, `idAutobus`, `nazivSlike`) VALUES
(1, 1, 'busImages/1.jpg'),
(2, 1, 'busImages/2.jpg'),
(12, 2, 'busImages/4.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `stanica`
--

CREATE TABLE `stanica` (
  `idStanica` int(11) NOT NULL,
  `nazivStan` varchar(30) CHARACTER SET latin2 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin7;

--
-- Dumping data for table `stanica`
--

INSERT INTO `stanica` (`idStanica`, `nazivStan`) VALUES
(1, 'Subotica'),
(2, 'Dimitrovgrad'),
(3, 'Novi Sad'),
(4, 'Beograd'),
(5, 'Jagodina'),
(6, 'Niš'),
(7, 'Aleksinac'),
(8, 'Leskovac'),
(9, 'Vranje'),
(10, 'Vidikovac'),
(11, 'Karaburma'),
(13, 'Kumodraž'),
(14, 'Vukov Spomenik'),
(15, 'Glavna pošta'),
(17, 'Dalmatinska'),
(18, 'Pančevački most'),
(19, 'Vuka Vrčevića'),
(20, 'Novi Beograd');

-- --------------------------------------------------------

--
-- Table structure for table `vozac`
--

CREATE TABLE `vozac` (
  `idVozac` int(11) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `datumRodjenja` date DEFAULT NULL,
  `godinaPocetka` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `vozac`
--

INSERT INTO `vozac` (`idVozac`, `ime`, `prezime`, `datumRodjenja`, `godinaPocetka`) VALUES
(1, 'Ivana', 'Ivanović', '1990-08-15', 2010),
(2, 'Marko', 'Marković', '1980-07-01', 2000),
(4, 'Žika', 'Žiki?', '1965-07-23', 2000),
(5, 'Stefan', 'Stefanovi?', '1980-03-03', 1999),
(6, 'Nikola', 'Nikolic', '1990-03-03', 2009),
(7, 'Filip', 'Filipovic', '1991-03-12', 2009),
(10, 'Filip', 'Milinkovic', '1989-03-12', 2009),
(11, 'Nevena', 'Milinkovic', '1988-03-10', 2013),
(13, 'Jana', 'Jankovic', '1998-02-02', 2015),
(15, 'Jelena', 'Stepanovic', '1991-04-22', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `vozi`
--

CREATE TABLE `vozi` (
  `idLinija` int(11) NOT NULL,
  `idVozac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

--
-- Dumping data for table `vozi`
--

INSERT INTO `vozi` (`idLinija`, `idVozac`) VALUES
(3, 1),
(3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autobus`
--
ALTER TABLE `autobus`
  ADD PRIMARY KEY (`idAutobus`);

--
-- Indexes for table `gradskalin`
--
ALTER TABLE `gradskalin`
  ADD PRIMARY KEY (`idLinija`),
  ADD KEY `idLinija` (`idLinija`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`idKorisnik`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `username_2` (`username`);

--
-- Indexes for table `linija`
--
ALTER TABLE `linija`
  ADD PRIMARY KEY (`idLinija`),
  ADD KEY `dolazakStanica` (`dolazakStanica`),
  ADD KEY `polazakStanica` (`polazakStanica`);

--
-- Indexes for table `medjugradskalin`
--
ALTER TABLE `medjugradskalin`
  ADD PRIMARY KEY (`idLinija`);

--
-- Indexes for table `medjustanice`
--
ALTER TABLE `medjustanice`
  ADD PRIMARY KEY (`idStanica`,`idLinija`),
  ADD KEY `idLinija` (`idLinija`);

--
-- Indexes for table `mesecnagodisnja`
--
ALTER TABLE `mesecnagodisnja`
  ADD PRIMARY KEY (`idMesGod`);

--
-- Indexes for table `mesecnakarta`
--
ALTER TABLE `mesecnakarta`
  ADD PRIMARY KEY (`idMesecna`),
  ADD KEY `idKorisnik` (`idKorisnik`);

--
-- Indexes for table `otkazane`
--
ALTER TABLE `otkazane`
  ADD PRIMARY KEY (`idOtkaz`),
  ADD KEY `idLinija` (`idLinija`);

--
-- Indexes for table `prevoznik`
--
ALTER TABLE `prevoznik`
  ADD PRIMARY KEY (`idPrevoznik`);

--
-- Indexes for table `rezervacijakarata`
--
ALTER TABLE `rezervacijakarata`
  ADD PRIMARY KEY (`idRezervacijaKarata`),
  ADD KEY `idKorisnik` (`idKorisnik`),
  ADD KEY `idLinija` (`idLinija`);

--
-- Indexes for table `slike`
--
ALTER TABLE `slike`
  ADD PRIMARY KEY (`idSlika`);

--
-- Indexes for table `stanica`
--
ALTER TABLE `stanica`
  ADD PRIMARY KEY (`idStanica`);

--
-- Indexes for table `vozac`
--
ALTER TABLE `vozac`
  ADD PRIMARY KEY (`idVozac`);

--
-- Indexes for table `vozi`
--
ALTER TABLE `vozi`
  ADD PRIMARY KEY (`idLinija`,`idVozac`),
  ADD KEY `idVozac` (`idVozac`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autobus`
--
ALTER TABLE `autobus`
  MODIFY `idAutobus` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `idKorisnik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `mesecnagodisnja`
--
ALTER TABLE `mesecnagodisnja`
  MODIFY `idMesGod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `mesecnakarta`
--
ALTER TABLE `mesecnakarta`
  MODIFY `idMesecna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `otkazane`
--
ALTER TABLE `otkazane`
  MODIFY `idOtkaz` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `prevoznik`
--
ALTER TABLE `prevoznik`
  MODIFY `idPrevoznik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rezervacijakarata`
--
ALTER TABLE `rezervacijakarata`
  MODIFY `idRezervacijaKarata` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `slike`
--
ALTER TABLE `slike`
  MODIFY `idSlika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `stanica`
--
ALTER TABLE `stanica`
  MODIFY `idStanica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `vozac`
--
ALTER TABLE `vozac`
  MODIFY `idVozac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `gradskalin`
--
ALTER TABLE `gradskalin`
  ADD CONSTRAINT `gradskalin_ibfk_1` FOREIGN KEY (`idLinija`) REFERENCES `linija` (`idLinija`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `linija`
--
ALTER TABLE `linija`
  ADD CONSTRAINT `linija_ibfk_1` FOREIGN KEY (`dolazakStanica`) REFERENCES `stanica` (`idStanica`),
  ADD CONSTRAINT `linija_ibfk_2` FOREIGN KEY (`polazakStanica`) REFERENCES `stanica` (`idStanica`);

--
-- Constraints for table `medjugradskalin`
--
ALTER TABLE `medjugradskalin`
  ADD CONSTRAINT `medjugradskalin_ibfk_1` FOREIGN KEY (`idLinija`) REFERENCES `linija` (`idLinija`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `medjustanice`
--
ALTER TABLE `medjustanice`
  ADD CONSTRAINT `medjustanice_ibfk_1` FOREIGN KEY (`idStanica`) REFERENCES `stanica` (`idStanica`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `medjustanice_ibfk_2` FOREIGN KEY (`idLinija`) REFERENCES `linija` (`idLinija`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mesecnakarta`
--
ALTER TABLE `mesecnakarta`
  ADD CONSTRAINT `mesecnakarta_ibfk_1` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `otkazane`
--
ALTER TABLE `otkazane`
  ADD CONSTRAINT `otkazane_ibfk_1` FOREIGN KEY (`idLinija`) REFERENCES `linija` (`idLinija`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rezervacijakarata`
--
ALTER TABLE `rezervacijakarata`
  ADD CONSTRAINT `rezervacijakarata_ibfk_1` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rezervacijakarata_ibfk_2` FOREIGN KEY (`idLinija`) REFERENCES `linija` (`idLinija`);

--
-- Constraints for table `vozi`
--
ALTER TABLE `vozi`
  ADD CONSTRAINT `vozi_ibfk_1` FOREIGN KEY (`idLinija`) REFERENCES `linija` (`idLinija`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vozi_ibfk_2` FOREIGN KEY (`idVozac`) REFERENCES `vozac` (`idVozac`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
