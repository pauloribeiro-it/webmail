-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: webmail
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `email_destinatario`
--

DROP TABLE IF EXISTS `email_destinatario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_destinatario` (
  `id_email` int(11) NOT NULL DEFAULT '0',
  `id_destinatario` int(11) NOT NULL DEFAULT '0',
  `is_cc` tinyint(1) DEFAULT NULL,
  `is_cco` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_email`,`id_destinatario`),
  KEY `fk_email_destinatario_usuario` (`id_destinatario`),
  CONSTRAINT `fk_email_destinatario_email` FOREIGN KEY (`id_email`) REFERENCES `email` (`id`),
  CONSTRAINT `fk_email_destinatario_usuario` FOREIGN KEY (`id_destinatario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_destinatario`
--

LOCK TABLES `email_destinatario` WRITE;
/*!40000 ALTER TABLE `email_destinatario` DISABLE KEYS */;
INSERT INTO `email_destinatario` VALUES (25,28,0,0),(26,28,0,0),(27,28,0,0),(28,28,0,0),(29,28,0,0),(30,28,0,0),(31,28,0,0),(32,28,0,0),(33,28,0,0),(34,28,0,0),(35,28,0,0),(36,28,0,0),(37,28,0,0),(38,28,0,0),(39,28,0,0);
/*!40000 ALTER TABLE `email_destinatario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-15 12:42:02
