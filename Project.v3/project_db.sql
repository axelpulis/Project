-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2018 at 08:31 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_id`, `username`, `password`, `admin`, `email`) VALUES
(29, 'axelpulis', 'test123', 0, 'axelpulis@hotmail.com'),
(30, 'Admin', 'Admin', 1, 'admin@admin.com'),
(31, 'admin1', 'admin', 1, 'admi@admi@hotmail.com'),
(55, 'xylonpulis', 'test123', 0, 'xylonpulis@hotmail.com'),
(56, 'saaa', 'aaaaa', 0, 'axel.pulis.a100872@mcast.edu.mt');

-- --------------------------------------------------------

--
-- Table structure for table `barber`
--

CREATE TABLE `barber` (
  `barber_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `town_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barber`
--

INSERT INTO `barber` (`barber_id`, `name`, `surname`, `mobile`, `town_id`, `account_id`) VALUES
(2, 'Christian', 'Barber', '99477859', 214, 30),
(8, 'Kurt', 'saliba', '444444', 214, 31);

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `client_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `start_date`, `end_date`, `client_id`, `service_id`, `barber_id`) VALUES
(1, '2018-05-01 10:00:00', '2018-05-01 10:35:00', 17, 4, 8),
(3, '2018-05-10 15:30:00', '2018-05-10 15:50:00', 17, 1, 8),
(4, '2018-05-03 00:00:00', '2018-05-24 00:00:00', 17, 4, 8);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `town_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `name`, `surname`, `mobile`, `town_id`, `account_id`) VALUES
(17, 'Axel', 'Pulis', '99477859', 214, 29),
(19, 'shawn', 'abela', '22222222', 215, 56);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `service_id` int(11) NOT NULL,
  `type` text NOT NULL,
  `price` double NOT NULL,
  `Duration` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`service_id`, `type`, `price`, `Duration`) VALUES
(1, 'Cut', 6, '00:20:00'),
(2, 'Blow Dry', 4, '00:10:00'),
(3, 'Shave', 2, '00:05:00'),
(4, 'Cut / Blow Dry / Shave', 12, '00:35:00');

-- --------------------------------------------------------

--
-- Table structure for table `town`
--

CREATE TABLE `town` (
  `town_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `town`
--

INSERT INTO `town` (`town_id`, `name`) VALUES
(214, 'Attard'),
(215, 'Balzan'),
(216, 'Birgu'),
(217, 'Birkirkara'),
(218, 'Birzebbuga'),
(219, 'Bormla'),
(220, 'Dingli'),
(221, 'Fgura'),
(222, 'Furjana'),
(223, 'Gudja'),
(224, 'Gharghur'),
(225, 'Ghaxaq'),
(226, 'Gzira'),
(227, 'Iklin'),
(228, 'Imdina'),
(229, 'Imqabba'),
(230, 'Imsida'),
(231, 'Imtarfa'),
(232, 'Imgarr'),
(233, 'Isla'),
(234, 'Zejtun'),
(235, 'Kalkara'),
(236, 'Kirkop'),
(237, 'Lija'),
(238, 'Luqa'),
(239, 'Marsa'),
(240, 'Marsaskala'),
(241, 'Marsaxlokk'),
(242, 'Mellieha'),
(243, 'Mosta'),
(244, 'Naxxar'),
(245, 'Paola'),
(246, 'Pembroke'),
(247, 'Pieta'),
(248, 'Qormi'),
(249, 'Qrendi'),
(250, 'Rabat'),
(251, 'Safi'),
(252, 'San Pawl il-Bahar'),
(253, 'San Giljan'),
(254, 'San Gwann'),
(255, 'Santa Lucija'),
(256, 'Santa Venera'),
(257, 'Siggiewi'),
(258, 'Sliema'),
(259, 'Swieqi'),
(260, 'Tarxien'),
(261, 'Valletta'),
(262, 'Xbiex'),
(263, 'Xghajra'),
(264, 'Hamrun'),
(265, 'Zabbar'),
(266, 'Zebbug'),
(267, 'Zurrieq');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `barber`
--
ALTER TABLE `barber`
  ADD PRIMARY KEY (`barber_id`),
  ADD UNIQUE KEY `account_id` (`account_id`),
  ADD KEY `town_id` (`town_id`) USING BTREE;

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `client_id` (`client_id`) USING BTREE,
  ADD KEY `service_id` (`service_id`) USING BTREE,
  ADD KEY `barber_id` (`barber_id`) USING BTREE;

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`),
  ADD UNIQUE KEY `town_id` (`town_id`),
  ADD UNIQUE KEY `account_id` (`account_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `town`
--
ALTER TABLE `town`
  ADD PRIMARY KEY (`town_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `barber`
--
ALTER TABLE `barber`
  MODIFY `barber_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `town`
--
ALTER TABLE `town`
  MODIFY `town_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=268;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barber`
--
ALTER TABLE `barber`
  ADD CONSTRAINT `barber_ibfk_1` FOREIGN KEY (`town_id`) REFERENCES `town` (`town_id`),
  ADD CONSTRAINT `barber_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`),
  ADD CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`barber_id`) REFERENCES `barber` (`barber_id`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`town_id`) REFERENCES `town` (`town_id`),
  ADD CONSTRAINT `client_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
