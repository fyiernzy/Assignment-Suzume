-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2023 at 11:28 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `suzume_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `gamer`
--

CREATE TABLE `gamer` (
  `id` int(3) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `win` int(2) NOT NULL,
  `lose` int(2) NOT NULL,
  `draw` int(2) NOT NULL,
  `score` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gamer`
--

INSERT INTO `gamer` (`id`, `username`, `password`, `win`, `lose`, `draw`, `score`) VALUES
(1, 'ys', 'password', 0, 0, 0, 0),
(2, 'ys2', 'password', 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gamer`
--
ALTER TABLE `gamer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gamer`
--
ALTER TABLE `gamer`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
