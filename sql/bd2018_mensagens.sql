-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bd2018
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `mensagens`
--

DROP TABLE IF EXISTS `mensagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensagens` (
  `MEN_CODIGO` int(5) NOT NULL,
  `MEN_ATE_CODIGO` int(5) NOT NULL,
  `MEN_TMS_CODIGO` int(3) NOT NULL,
  `MEN_TEXTO` text NOT NULL,
  `MEN_DATA_ENVIO` date DEFAULT NULL,
  PRIMARY KEY (`MEN_CODIGO`),
  KEY `fk_men_ate_cod` (`MEN_ATE_CODIGO`),
  KEY `fk_men_tms_cod` (`MEN_TMS_CODIGO`),
  CONSTRAINT `fk_men_ate_cod` FOREIGN KEY (`MEN_ATE_CODIGO`) REFERENCES `atendentes` (`ATE_CODIGO`),
  CONSTRAINT `fk_men_tms_cod` FOREIGN KEY (`MEN_TMS_CODIGO`) REFERENCES `tipo_mensagens` (`TMS_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagens`
--

LOCK TABLES `mensagens` WRITE;
/*!40000 ALTER TABLE `mensagens` DISABLE KEYS */;
INSERT INTO `mensagens` VALUES (1,1,1,'Solicito moedas pois meu troco esta acabando.',NULL),(2,2,1,'Preciso de uma nova maquina de leitura de codigo de barras',NULL),(3,3,2,'Expediente extendido devido aos feriado prolongado',NULL),(4,2,1,'Necessito de um novo mouse para a minha unidade',NULL),(5,1,1,'Solicito troco para 100 reais, uma de 50, duas de 20 e uma de 10.',NULL);
/*!40000 ALTER TABLE `mensagens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-15 22:32:10
