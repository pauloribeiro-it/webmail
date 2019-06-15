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
-- Table structure for table `auditoria_operacao`
--

DROP TABLE IF EXISTS `auditoria_operacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditoria_operacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_auditoria_login` int(11) DEFAULT NULL,
  `desc_operacao` varchar(8000) DEFAULT NULL,
  `data_operacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `descricao_erro` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_auditoria_operacao_usuario` (`id_auditoria_login`),
  CONSTRAINT `fk_auditoria_operacao_usuario` FOREIGN KEY (`id_auditoria_login`) REFERENCES `auditoria_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria_operacao`
--

LOCK TABLES `auditoria_operacao` WRITE;
/*!40000 ALTER TABLE `auditoria_operacao` DISABLE KEYS */;
INSERT INTO `auditoria_operacao` VALUES (1,2,'{\"nomeMetodo\":\"public java.util.List br.com.webmail.domain.email.EmailServiceImpl.obtemEmailsPorUsuarioEFiltro(br.com.webmail.domain.usuario.Usuario,java.lang.Long)\",\"parametros\":{\"arg0\":\"Usuario [id=27, nome=Paulo, email=pauloroberto@webmail.com.br, dataCriacao=2015-10-08 22:53:39.0, idSessao=cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU1NzUyNzkzNA==]\",\"arg1\":\"1\"}}','2019-06-15 00:17:50',''),(2,3,'{\"nomeMetodo\":\"public java.util.List br.com.webmail.domain.email.EmailServiceImpl.obtemEmailsPorUsuarioEFiltro(br.com.webmail.domain.usuario.Usuario,java.lang.Long)\",\"parametros\":{\"arg0\":\"Usuario [id=30, nome=teste1, email=teste1, dataCriacao=2019-06-14 21:21:15.0, idSessao=dGVzdGUxMTU2MDU1ODE5OTIyNQ==]\",\"arg1\":\"1\"}}','2019-06-15 00:23:22',''),(3,4,'{\"nomeMetodo\":\"public java.util.List br.com.webmail.domain.email.EmailServiceImpl.obtemEmailsPorUsuarioEFiltro(br.com.webmail.domain.usuario.Usuario,java.lang.Long)\",\"parametros\":{\"arg0\":\"Usuario [id=30, nome=teste1, email=teste1, dataCriacao=2019-06-14 21:21:15.0, idSessao=dGVzdGUxMTU2MDU1ODc2NjU0Mw==]\",\"arg1\":\"1\"}}','2019-06-15 00:32:46',''),(4,5,'{\"nomeMetodo\":\"public java.util.List br.com.webmail.domain.email.EmailServiceImpl.obtemEmailsPorUsuarioEFiltro(br.com.webmail.domain.usuario.Usuario,java.lang.Long)\",\"parametros\":{\"arg0\":\"Usuario [id=27, nome=Paulo, email=pauloroberto@webmail.com.br, dataCriacao=2015-10-08 22:53:39.0, idSessao=cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU1ODg4NDM1NA==]\",\"arg1\":\"1\"}}','2019-06-15 00:34:44',''),(5,6,'{\"nomeMetodo\":\"public java.util.List br.com.webmail.domain.email.EmailServiceImpl.obtemEmailsPorUsuarioEFiltro(br.com.webmail.domain.usuario.Usuario,java.lang.Long)\",\"parametros\":{\"arg0\":\"Usuario [id=27, nome=Paulo, email=pauloroberto@webmail.com.br, dataCriacao=2015-10-08 22:53:39.0, idSessao=cGF1bG9yb2JlcnRvQHdlYm1haWwuY29tLmJyMTU2MDU2Mzc1NjQ1OQ==]\",\"arg1\":\"1\"}}','2019-06-15 01:55:56','');
/*!40000 ALTER TABLE `auditoria_operacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-15 12:42:03
