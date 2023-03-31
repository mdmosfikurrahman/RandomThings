CREATE DATABASE  IF NOT EXISTS `randomthings`;
USE `randomthings`;

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `task_completed` bit(1) NOT NULL,
  `task_details` varchar(255) NOT NULL,
  `task_priority` int NOT NULL,
  `task_title` varchar(255) NOT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `app_users`;
CREATE TABLE `app_users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_contact_number` varchar(255) DEFAULT NULL,
  `user_date_of_birth` datetime DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_gender` int DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_role` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
  `assignment_id` bigint NOT NULL AUTO_INCREMENT,
  `task_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `FKa2wotauggxl7e6p271pud2v0r` (`task_id`),
  KEY `FK5sn4qfxnra3j8956kopxuij6r` (`user_id`),
  CONSTRAINT `FK5sn4qfxnra3j8956kopxuij6r` FOREIGN KEY (`user_id`) REFERENCES `app_users` (`user_id`),
  CONSTRAINT `FKa2wotauggxl7e6p271pud2v0r` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
