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
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores` (
  `FOR_CODIGO` int(5) NOT NULL,
  `FOR_MUN_CODIGO` int(4) NOT NULL,
  `FOR_RAZAO_SOCIAL` varchar(100) NOT NULL,
  `FOR_NOME_FANTASIA` varchar(50) NOT NULL,
  `FOR_NOME_CONTATO` varchar(50) NOT NULL,
  `FOR_CNPJ` varchar(14) NOT NULL,
  `FOR_ENDERECO` varchar(100) DEFAULT 'Nao informado',
  `FOR_DATA_CADASTRO` date DEFAULT '1963-12-31',
  `FOR_FONE` varchar(20) DEFAULT 'Nao possui',
  `FOR_EMAIL` varchar(50) DEFAULT 'Nao possui',
  `FOR_WEBSITE` varchar(100) DEFAULT 'Nao possui',
  PRIMARY KEY (`FOR_CODIGO`),
  KEY `fk_mun_codigo_forne` (`FOR_MUN_CODIGO`),
  CONSTRAINT `fk_mun_codigo_forne` FOREIGN KEY (`FOR_MUN_CODIGO`) REFERENCES `municipios` (`MUN_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` VALUES (1,1,'Buriti Hortifruit Ltda','Buriti Verde','Hortifruits Buriti','65929876000162',NULL,'1963-12-31','Nao possui','Nao possui','Nao possui'),(2,2,'Ricardo Eletrodomesticos Ltda','Ricardo Eletro','Eletrodomesticos Ricardo','93346258000125','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(3,5,'JBS Carnes e Frios S/A','Friboi','Carnes Friboi','13047359000166','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(4,4,'Popular Farmaceuticas Ltda','Drogaria Popular','Drogaria Popular','05943208000168','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(5,5,'Robert Bosch GmbH','Bosch','Ferramentas Bosch','90831316000118','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(6,6,'Grupo Luiza','Magazine Luiza','Magazine Luiza Varejo ','29655886000193','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(7,7,'Uniao Educacional Do Norte do Brasil','UNINORTE','IESACRE','16798383000125','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(8,1,'Universidade Federal do Acre','IFAC','Universidade do Acre','33683795000139','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(9,9,'Marlene Lanches ltda','Cafe com letra','Marlene','43636646000172','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(10,10,'Converse INC','Converse AllStar','Converse','88681714000153','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(11,11,'Firestone Tire and Rubber Company','Bridgestone','Bridgestone Pneus','06419177000103','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(12,12,'Motorola, Inc.','Motorola','Motorola Brasil','33138304000179','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(13,13,'Samsung Electronics','Samsumg','Samsumg Brasil','56663323000110','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(14,14,'LG Corporation','LG','Lifes Good','04462546000115','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui'),(15,15,'Sony Mobile Communications','Sony',' Sony Mobile','91503841000177','Nao informado','1963-12-31','Nao possui','Nao possui','Nao possui');
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-15 22:32:11
