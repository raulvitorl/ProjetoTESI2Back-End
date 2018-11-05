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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `CLI_CODIGO` int(5) NOT NULL,
  `CLI_MUN_CODIGO` int(4) NOT NULL,
  `CLI_NOME` varchar(50) NOT NULL,
  `CLI_DATA_NASCIMENTO` date NOT NULL,
  `CLI_SEXO` char(1) DEFAULT 'I',
  `CLI_CPF` varchar(14) DEFAULT '',
  `CLI_RG` varchar(20) DEFAULT '',
  `CLI_CNPJ` varchar(14) DEFAULT '',
  `CLI_ENDERECO` varchar(100) DEFAULT 'Nao informado',
  `CLI_EMAIL` varchar(50) DEFAULT 'Nao possui',
  `CLI_DATA_CADASTRO` date DEFAULT '2000-01-01',
  `CLI_TIPO` char(1) NOT NULL,
  `CLI_STATUS` char(1) NOT NULL,
  `CLI_FONE` varchar(20) DEFAULT 'Nao possui',
  `CLI_NOME_CONTATO` varchar(50) DEFAULT 'Nao escolhido',
  PRIMARY KEY (`CLI_CODIGO`),
  KEY `fk_codigo_mun` (`CLI_MUN_CODIGO`),
  CONSTRAINT `fk_codigo_mun` FOREIGN KEY (`CLI_MUN_CODIGO`) REFERENCES `municipios` (`MUN_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'Elisangela Diniz','2004-09-20','F','02772506258','11144629','','RUA','raulawliet@gmail.com','2017-11-30','A','A','',''),(2,2,'Raul','1998-03-08','M','0277506258','11144629','','RUA','raulawliet@gmail.com','2018-07-26','A','A','(68)992454310','NAOTEM'),(3,3,'Regiane Santos Paiva','2001-03-08','M','00000000000','1111111','','Rua Avelã','regianesantosp7@gmail.com ','2018-07-26','A','I','',''),(4,14,'AAAAAAAAA','2018-09-09','M','2345678','3456789','','DFGHJKLÇ','SDFGHJKLÇ','2018-09-09','A','I','',''),(5,2,'Raul','1998-03-08','M','0277506258','11144629','1234567','RUA','raulawliet@gmail.com','2018-07-26','A','A','68992454310','NAOTEM'),(6,1,'Adrian Marcilio','1997-12-17','F','03746537207','12724483','','Rua Doutora Maria Tapajós','adrian.marcilio@gmail.com','2018-07-28','A','A','689237-6753','gay'),(7,1,'Mariana Bezerra da Silva','1998-12-20','F','02772506258','11144629','','Rua Alvorada n 467','raulawliet@gmail.com','2017-07-27','A','A','68992454310','Mari'),(8,3,'Regiane Santos Paiva','2001-03-08','M','00000000000','1111111','','Rua Avelã','regianesantosp7@gmail.com ','2018-07-26','A','I','68992454310','regis'),(9,1,'Elisangela Diniz','2004-09-20','F','02772506258','11144629','1234567','RUA','raulawliet@gmail.com','2017-11-30','A','A','68992454310','NAOTEM');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
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
