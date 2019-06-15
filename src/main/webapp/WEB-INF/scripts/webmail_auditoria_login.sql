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
-- Table structure for table `auditoria_login`
--

DROP TABLE IF EXISTS `auditoria_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditoria_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_sessao` varchar(100) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `data_login` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_auditoria_login_usuario` (`id_usuario`),
  CONSTRAINT `fk_auditoria_login_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria_login`
--

LOCK TABLES `auditoria_login` WRITE;
/*!40000 ALTER TABLE `auditoria_login` DISABLE KEYS */;
INSERT INTO `auditoria_login` VALUES (1,'cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU1NjYxMTY1NA==',27,'2019-06-14'),(2,'cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU1NzUyNzkzNA==',27,'2019-06-14'),(3,'dGVzdGUxMTU2MDU1ODE5OTIyNQ==',30,'2019-06-14'),(4,'dGVzdGUxMTU2MDU1ODc2NjU0Mw==',30,'2019-06-14'),(5,'cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU1ODg4NDM1NA==',27,'2019-06-14'),(6,'cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU2Mzc1NjQ1OQ==',27,'2019-06-14');
/*!40000 ALTER TABLE `auditoria_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-15 12:42:08
