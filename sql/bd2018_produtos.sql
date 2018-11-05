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
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `PRO_CODIGO` int(5) NOT NULL,
  `PRO_FOR_CODIGO` int(5) NOT NULL,
  `PRO_CAT_CODIGO` int(5) NOT NULL,
  `PRO_DESCRICAO` varchar(100) NOT NULL,
  `PRO_QNT_DISPONIVEL` int(5) DEFAULT '1',
  `PRO_ULTIMA_AQUISICAO` date DEFAULT '1963-12-31',
  `PRO_VALOR_UNIT` float DEFAULT '0.1',
  `PRO_FABRICANTE` varchar(50) DEFAULT 'Nao informado',
  `PRO_DETALHES` text,
  PRIMARY KEY (`PRO_CODIGO`),
  KEY `fk_pro_for_cod` (`PRO_FOR_CODIGO`),
  KEY `fk_cat_prod_cod` (`PRO_CAT_CODIGO`),
  CONSTRAINT `fk_cat_prod_cod` FOREIGN KEY (`PRO_CAT_CODIGO`) REFERENCES `categorias_produtos` (`CAT_CODIGO`),
  CONSTRAINT `fk_pro_for_cod` FOREIGN KEY (`PRO_FOR_CODIGO`) REFERENCES `fornecedores` (`FOR_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,4,105,'Cheiro Verde',95,'2018-07-24',1,'Nao informado           ','null'),(2,1,102,'Geladeira',15,'1963-12-31',800,'Nao informado  ','null'),(3,2,103,'Peito de Frango',22,'1963-12-31',10,'Nao informado',NULL),(4,4,105,'Dipirona',99,'1963-12-31',5,'Nao informado',NULL),(5,3,106,'Chave de Fenda',23,'1963-12-31',20,'Nao informado',NULL),(6,3,101,'Mesa Redonda',10,'1963-12-31',870,'Nao informado',NULL),(9,3,103,'Costela',12,'1963-12-31',13,'Nao informado',NULL),(11,5,106,'Martelo',0,'1963-12-31',15,'Nao informado',NULL),(12,6,101,'Cadeira de Plastico',3,'1963-12-31',60,'Nao informado',NULL),(13,10,107,'AllStar Preto',5,'1963-12-31',120,'Nao informado',NULL),(14,11,108,'Rodas 18x20',7,'1963-12-31',450,'Nao informado',NULL),(15,12,109,'Moto G 4 Plus',8,'1963-12-31',800,'Nao informado',NULL),(16,4,105,'Bepantol',1,'1963-12-31',15,'Nao informado',NULL),(17,3,106,'Pregos',1,'1963-12-31',4.5,'Nao informado',NULL),(18,3,101,'Armario',1,'1963-12-31',578,'Nao informado',NULL),(19,12,109,'Moto G 5',1,'1963-12-31',750,'Nao informado',NULL),(20,13,109,'Galaxy S7',1,'1963-12-31',1350,'Nao informado',NULL),(21,14,109,'Smartv 32pol',1,'1963-12-31',1200,'Nao informado',NULL),(22,15,109,'Projetor',1,'1963-12-31',3200,'Nao informado',NULL),(23,15,108,'Ferrari 125 S',1,'1963-12-31',6.5,'Nao informado',NULL),(25,11,103,'AAAAAAAAA',1,'1963-12-31',0.1,'Nao informado','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),(29,1,100,'AAAAAAAA',1,'2018-09-09',1,'','AAAAAAAAAAAA');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-15 22:32:12
