-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 23 Jun 2020 pada 05.28
-- Versi Server: 10.1.13-MariaDB
-- PHP Version: 5.5.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sarinah`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tblkaryawan`
--

CREATE TABLE `tblkaryawan` (
  `id` int(10) NOT NULL,
  `nip` varchar(10) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `nama_karyawan` varchar(100) DEFAULT NULL,
  `divisi` varchar(100) DEFAULT NULL,
  `unit` varchar(100) DEFAULT NULL,
  `lokasi` varchar(100) DEFAULT NULL,
  `jabatan` varchar(100) DEFAULT NULL,
  `hp` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tblkaryawan`
--

INSERT INTO `tblkaryawan` (`id`, `nip`, `pass`, `nama_karyawan`, `divisi`, `unit`, `lokasi`, `jabatan`, `hp`, `email`) VALUES
(1, '123456788', 'faisal23', 'Muhammad Faisal', 'IT', 'Pengembangan Software', 'Jakarta', 'Karyawan', '085691127761', 'faisalmuh2304@gmail.com'),
(2, '234567891', 'alfisahri', 'Alfiansyah', 'IT', 'Troubleshooting', 'Jakarta', 'Karyawan', '083742948204', 'Alfi@gmail.com'),
(3, '31039141', 'galih123', 'Galih', 'Perdagangan', 'Export', 'Jakarta', 'Karyawan', '082391401', 'galih@gmail.com'),
(4, '103104910', 'budi23', 'Budi Setyono', 'Akutansi', 'Pajak', 'Jakarta ', 'Karyawan ', '01108401420', 'budi@gmail.com'),
(5, '201391410', 'yanto123', 'Yanto123', 'SDM', 'Pegembangan Karakter ', 'Jakarta ', 'Karyawan', '08312837124', 'Yanto@gmail.com'),
(6, '1023921492', 'Juju', 'JUniansyah', 'Sekretaris', 'Agama', 'Jakarta', 'Karyawan', '08312931240', 'Juju@gmail.com'),
(7, 'fnskfnsq', 'kfnanwfkn', 'nfeanfan', 'nqfnf', 'fneafna', 'nfeafne', 'nfeafn', 'fnenaefl', 'fkenafnaf'),
(8, '1309214104', 'kari12345', 'kari', 'Akutansi', 'Pajak', 'Jakarta ', 'Karyawan', '0831841041', 'Kari@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblkaryawan`
--
ALTER TABLE `tblkaryawan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblkaryawan`
--
ALTER TABLE `tblkaryawan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
