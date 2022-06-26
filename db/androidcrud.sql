-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2022 at 04:26 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `androidcrud`
--

-- --------------------------------------------------------

--
-- Table structure for table `login_siswa`
--

CREATE TABLE `login_siswa` (
  `id` int(10) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(8) NOT NULL,
  `status` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_siswa`
--

INSERT INTO `login_siswa` (`id`, `username`, `password`, `status`, `email`) VALUES
(1, 'aryo', 'wisanggeni', 'Aktif', 'aws@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id` int(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `kelas` varchar(30) NOT NULL,
  `jurusan` varchar(40) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `alamat` varchar(150) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id`, `nama`, `kelas`, `jurusan`, `no_hp`, `alamat`) VALUES
(1, 'Aryo Wisanggeni', 'TI20A2', 'Teknik Informatika', '087821906555', 'Ngastino Puro');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login_siswa`
--
ALTER TABLE `login_siswa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login_siswa`
--
ALTER TABLE `login_siswa`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
