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
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assunto` varchar(100) DEFAULT NULL,
  `corpo` varchar(2000) DEFAULT NULL,
  `id_remetente` int(11) DEFAULT NULL,
  `data_hora_criacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_filtro` int(11) DEFAULT NULL,
  `id_destinatario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_email_remetente` (`id_remetente`),
  KEY `FK_Email_Filtro` (`id_filtro`),
  KEY `FK_Email_Destinatario` (`id_destinatario`),
  CONSTRAINT `FK_Email_Destinatario` FOREIGN KEY (`id_destinatario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_Email_Filtro` FOREIGN KEY (`id_filtro`) REFERENCES `filtro` (`id`),
  CONSTRAINT `fk_email_remetente` FOREIGN KEY (`id_remetente`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,'','',27,'2015-10-23 00:35:46',NULL,NULL),(6,'','',27,'2015-10-23 00:58:30',NULL,NULL),(7,'teste','teste',27,'2015-10-25 18:39:49',NULL,NULL),(8,'teste','teste',27,'2015-10-25 18:41:22',NULL,NULL),(9,'teste','teste',27,'2015-10-25 19:04:08',NULL,NULL),(10,'teste','teste',27,'2015-10-25 19:07:19',NULL,NULL),(11,'teste','teste',27,'2015-10-25 19:08:18',NULL,NULL),(12,'teste','teste',27,'2015-10-25 19:10:44',NULL,NULL),(13,'teste','test',27,'2015-10-25 19:13:00',NULL,NULL),(14,'teste','teste',27,'2015-10-25 19:23:30',NULL,NULL),(15,'teste','teste',27,'2015-10-25 19:27:10',NULL,NULL),(23,'teste','teste',27,'2015-10-25 21:25:53',NULL,NULL),(24,'','',27,'2015-10-25 21:30:18',NULL,NULL),(25,'teste','teste',27,'2015-10-25 21:30:51',NULL,NULL),(26,'tet','ttddasfds',27,'2015-10-25 21:36:44',NULL,NULL),(27,'teste','asdfasfasf',27,'2015-10-25 21:37:39',NULL,NULL),(28,'teste','asdfasfasfklsdfa',27,'2015-10-25 21:41:04',NULL,NULL),(29,'asdfasf','asdfasfd',27,'2015-10-25 21:42:29',NULL,NULL),(30,'kaljsdlkfa','alksdjalksfd',27,'2015-10-25 21:46:55',NULL,NULL),(31,'tete','testa',27,'2015-10-25 21:52:11',NULL,NULL),(32,'teste','asjlkdfjaksfd',27,'2015-10-25 21:59:24',NULL,NULL),(33,'teste','asjlkdfjaksfd',27,'2015-10-25 22:01:37',NULL,NULL),(34,'teste','asdfafasdf',27,'2015-10-25 22:09:53',NULL,NULL),(35,'tetsasdf','aksdjfalksjkfasf',27,'2015-10-25 22:11:15',NULL,NULL),(36,'teste','lkasjdlkfasfd',27,'2015-10-25 22:12:33',NULL,NULL),(37,'teste','asdfasfasf',27,'2015-10-25 22:25:22',NULL,NULL),(38,'teste','asdfasfasf',27,'2015-10-25 22:26:17',NULL,NULL),(39,'teste','asdfasfd',27,'2015-10-25 22:29:24',NULL,NULL);
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-15 12:42:04
