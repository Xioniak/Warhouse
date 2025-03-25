-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2025 at 11:39 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warhouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(20) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `amount` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `brand`, `name`, `price`, `amount`) VALUES
(1, 'Tangodown', 'vertical fore grip(RIS)', 75, 2500),
(2, 'EOTech', 'HWS HOLO SIGHT EXPS 3-2', 1300, 500),
(3, 'Olight', 'Flashlight ProTac Railmount 1 Long Gun', 260, 5000),
(4, 'Magpul', 'PMAG gen 3 (30rnds | Black | ar-15)', 25, 30000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `pass_hash` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `login`, `pass_hash`) VALUES
(2, 'root', '36a9e7f1c95b82ffb99743e0c5c4ce95d83c9a430aac59f84ef3cbfab6145068'),
(3, 'test', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

-- --------------------------------------------------------

--
-- Table structure for table `user_transactions`
--

CREATE TABLE `user_transactions` (
  `id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `payment_method` varchar(40) NOT NULL,
  `price` float NOT NULL,
  `order_date` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_transactions`
--

INSERT INTO `user_transactions` (`id`, `user_id`, `payment_method`, `price`, `order_date`) VALUES
(2, 2, 'blik', 549.99, '2025-03-13');

-- --------------------------------------------------------

--
-- Table structure for table `weapons`
--

CREATE TABLE `weapons` (
  `id` int(20) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(80) NOT NULL,
  `caliber` varchar(15) NOT NULL,
  `type` varchar(30) NOT NULL,
  `price` float NOT NULL,
  `amount` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `weapons`
--

INSERT INTO `weapons` (`id`, `brand`, `model`, `caliber`, `type`, `price`, `amount`) VALUES
(1, 'Glock', '17', '9x19mm', 'pistol', 800, 500),
(2, 'Colt’s Manufacturing Company', 'M4', '5.56x45mm NATO', 'Carbine assault rifle', 1000, 150),
(3, 'Fabrique Nationale de Herstal', 'Five-seveN', '5,7x28mm', 'pistol', 2500, 50),
(4, 'Knights Armament Co.', 'MK110', '7,62x51mm NATO', 'designated marksman rifle', 12000, 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_transactions`
--
ALTER TABLE `user_transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `weapons`
--
ALTER TABLE `weapons`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_transactions`
--
ALTER TABLE `user_transactions`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `weapons`
--
ALTER TABLE `weapons`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_transactions`
--
ALTER TABLE `user_transactions`
  ADD CONSTRAINT `user_transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
