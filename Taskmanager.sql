-- phpMyAdmin SQL Dump
-- version 5.2.1deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 14, 2023 at 07:35 AM
-- Server version: 10.11.5-MariaDB-3
-- PHP Version: 8.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Taskmanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `Projects`
--

CREATE TABLE `Projects` (
  `project_ID` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `category` varchar(32) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `start_date` date NOT NULL,
  `deadline` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Projects`
--

INSERT INTO `Projects` (`project_ID`, `name`, `category`, `description`, `start_date`, `deadline`) VALUES
(1, 'Taskmanager', 'Desktop App', 'A Desktop Application to manage and organize tasks for your project.', '2023-10-24', '2023-11-12'),
(7, 'Project', 'project', 'This is a new Project', '2023-11-12', '2023-11-15'),
(8, 'Hammertime', 'Song', 'this is a song about hammers', '2023-11-12', '2023-12-06');

-- --------------------------------------------------------

--
-- Table structure for table `Project_Users`
--

CREATE TABLE `Project_Users` (
  `project_users_ID` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Project_Users`
--

INSERT INTO `Project_Users` (`project_users_ID`, `project_id`, `user_id`, `role_id`) VALUES
(34, 1, 1, 1),
(36, 1, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Roles`
--

CREATE TABLE `Roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Roles`
--

INSERT INTO `Roles` (`role_id`, `name`) VALUES
(1, 'Admin'),
(2, 'Worker');

-- --------------------------------------------------------

--
-- Table structure for table `States`
--

CREATE TABLE `States` (
  `state_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `States`
--

INSERT INTO `States` (`state_id`, `name`) VALUES
(1, 'new'),
(2, 'active'),
(3, 'tested'),
(4, 'closed');

-- --------------------------------------------------------

--
-- Table structure for table `Tasks`
--

CREATE TABLE `Tasks` (
  `task_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `created` date NOT NULL DEFAULT current_timestamp(),
  `deadline` date NOT NULL,
  `project_id` int(11) NOT NULL,
  `state_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Tasks`
--

INSERT INTO `Tasks` (`task_id`, `name`, `description`, `created`, `deadline`, `project_id`, `state_id`) VALUES
(1, 'create Database', 'Create This, Create Database, implement jdbc access', '2023-10-24', '2023-10-26', 1, 4),
(2, 'Create App', 'insert tasks into database and create Task class', '2023-10-24', '2023-10-24', 1, 2),
(6, 'hi', '', '2023-11-12', '2023-11-12', 8, 1),
(7, 'oh boy', '', '2023-11-12', '2023-11-12', 8, 2),
(8, 'hiadf', '', '2023-11-12', '2023-11-12', 8, 1),
(9, 'hello', '', '2023-11-12', '2023-11-12', 8, 1),
(10, 'kdslfölf', '', '2023-11-12', '2023-11-12', 8, 4),
(11, 'köljdfldal', '', '2023-11-12', '2023-11-12', 8, 1),
(12, 'this is me', '', '2023-11-12', '2023-11-12', 8, 2),
(13, 'yooooo', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren,', '2023-11-12', '2023-11-12', 8, 2),
(14, 'kjfdsaj', '', '2023-11-12', '2023-11-12', 8, 1),
(15, 'dsfafewr', '', '2023-11-12', '2023-11-12', 8, 3),
(16, 'blub', '', '2023-11-12', '2023-11-12', 8, 4),
(17, 'jfdsaöfdalk', '', '2023-11-12', '2023-11-12', 8, 2),
(18, 'öajdlföldaflsa', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren,', '2023-11-12', '2023-11-12', 8, 4),
(19, 'hi', '', '2023-11-13', '2023-11-13', 1, 1),
(20, 'testtttttt', '', '2023-11-13', '2023-11-13', 1, 3),
(21, 'hi', 'I bims ', '2023-11-14', '2023-11-14', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Task_users`
--

CREATE TABLE `Task_users` (
  `task_users_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `user_ID` int(11) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `birthdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`user_ID`, `first_name`, `last_name`, `birthdate`) VALUES
(1, 'Leon', 'Tieber', '1997-06-14'),
(2, 'jeff', 'harvey', '2004-11-24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Projects`
--
ALTER TABLE `Projects`
  ADD PRIMARY KEY (`project_ID`);

--
-- Indexes for table `Project_Users`
--
ALTER TABLE `Project_Users`
  ADD PRIMARY KEY (`project_users_ID`),
  ADD KEY `project_id` (`project_id`,`user_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `Roles`
--
ALTER TABLE `Roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `States`
--
ALTER TABLE `States`
  ADD PRIMARY KEY (`state_id`);

--
-- Indexes for table `Tasks`
--
ALTER TABLE `Tasks`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `project_id` (`project_id`,`state_id`),
  ADD KEY `state_id` (`state_id`);

--
-- Indexes for table `Task_users`
--
ALTER TABLE `Task_users`
  ADD PRIMARY KEY (`task_users_id`),
  ADD KEY `task_id` (`task_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Projects`
--
ALTER TABLE `Projects`
  MODIFY `project_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `Project_Users`
--
ALTER TABLE `Project_Users`
  MODIFY `project_users_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `Roles`
--
ALTER TABLE `Roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Tasks`
--
ALTER TABLE `Tasks`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `user_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Project_Users`
--
ALTER TABLE `Project_Users`
  ADD CONSTRAINT `Project_Users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `Roles` (`role_id`),
  ADD CONSTRAINT `Project_Users_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_ID`),
  ADD CONSTRAINT `Project_Users_ibfk_4` FOREIGN KEY (`project_id`) REFERENCES `Projects` (`project_ID`);

--
-- Constraints for table `Tasks`
--
ALTER TABLE `Tasks`
  ADD CONSTRAINT `Tasks_ibfk_1` FOREIGN KEY (`state_id`) REFERENCES `States` (`state_id`),
  ADD CONSTRAINT `Tasks_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `Projects` (`project_ID`);

--
-- Constraints for table `Task_users`
--
ALTER TABLE `Task_users`
  ADD CONSTRAINT `Task_users_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_ID`),
  ADD CONSTRAINT `Task_users_ibfk_2` FOREIGN KEY (`task_users_id`) REFERENCES `Tasks` (`task_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
