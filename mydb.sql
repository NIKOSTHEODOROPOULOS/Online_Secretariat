-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `CouID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `semester` int DEFAULT NULL,
  `PROFESSOR_ProID` int NOT NULL,
  PRIMARY KEY (`CouID`),
  KEY `fk_COURSES_PROFESSOR1_idx` (`PROFESSOR_ProID`),
  CONSTRAINT `fk_COURSES_PROFESSOR1` FOREIGN KEY (`PROFESSOR_ProID`) REFERENCES `professor` (`ProID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'mathimatika',2,2),(2,'maths',1,3),(3,'mathimatika',1,3),(4,'mathimatika2',4,3);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `Grade` int NOT NULL,
  `COURSES_CouID` int NOT NULL,
  `STUDENT_RegNum` int NOT NULL,
  PRIMARY KEY (`COURSES_CouID`),
  KEY `fk_GRADES_COURSES1_idx` (`COURSES_CouID`),
  KEY `fk_GRADES_STUDENT1_idx` (`STUDENT_RegNum`),
  CONSTRAINT `fk_GRADES_COURSES1` FOREIGN KEY (`COURSES_CouID`) REFERENCES `courses` (`CouID`),
  CONSTRAINT `fk_GRADES_STUDENT1` FOREIGN KEY (`STUDENT_RegNum`) REFERENCES `student` (`RegNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (10,1,4);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `ProID` int NOT NULL AUTO_INCREMENT,
  `Professor_Name` varchar(45) NOT NULL,
  `Professor_Surrname` varchar(45) NOT NULL,
  `USER_Username` varchar(45) NOT NULL,
  PRIMARY KEY (`ProID`),
  KEY `fk_PROFESSOR_USER1_idx` (`USER_Username`),
  CONSTRAINT `fk_PROFESSOR_USER1` FOREIGN KEY (`USER_Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (2,'Nikos','Theodoropoulos','professor1'),(3,'username4','surname4','user4');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretary`
--

DROP TABLE IF EXISTS `secretary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretary` (
  `Name` varchar(45) NOT NULL,
  `USER_Username` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_Username`),
  CONSTRAINT `fk_SECRETARY_USER1` FOREIGN KEY (`USER_Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretary`
--

LOCK TABLES `secretary` WRITE;
/*!40000 ALTER TABLE `secretary` DISABLE KEYS */;
INSERT INTO `secretary` VALUES ('username1','user3');
/*!40000 ALTER TABLE `secretary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `RegNum` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `USER_Username` varchar(45) NOT NULL,
  PRIMARY KEY (`RegNum`),
  KEY `fk_STUDENT_USER1_idx` (`USER_Username`),
  CONSTRAINT `fk_STUDENT_USER1` FOREIGN KEY (`USER_Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (4,'Nikolaos','Theodoropoulos','NIKOS'),(5,'nikolaos','theodorpoulos','user10');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `Username` varchar(45) NOT NULL,
  `Role` varchar(45) DEFAULT NULL,
  `hash` binary(64) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('NIKOS','student',_binary '69ba12f4eea340bfe9092ec96604b22042648697\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','bKVpCeEx8kDIQmXe',9,'test2'),('professor1','professor',_binary 'd26806dd4b936d7d6840063a25b5ed99a8e0f284\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','uX6YBmYFmSu7x1uP',10,'test1'),('user10','student',_binary 'bf1f87ac53d0518aef679cbdbe4d43cf42a921fd\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','jKXSCDaDdadvvPBx',13,NULL),('user3','secretary',_binary '48850ba4dd642451912268c6f21184fc2ea06977\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','FQ3sxI7dUs25gxYT',11,'pass'),('user4','professor',_binary '8cd0a961e11dce470fc80b8064155b5cd5fdd8bb\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','kCYbFpgpNHcc17o9',12,'test');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-08 11:39:36
