-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: webshop
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `ProductID` int(255) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(45) NOT NULL DEFAULT '',
  `ProductPrice` int(11) NOT NULL DEFAULT '0',
  `ProductQuantity` int(11) NOT NULL DEFAULT '0',
  `ProductImage` varchar(150) NOT NULL DEFAULT '',
  `ProductDescription` varchar(300) NOT NULL DEFAULT '',
  `ProductCategory` int(10) DEFAULT NULL,
  `ProductSubcategory` int(10) DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `categories_idx` (`ProductCategory`),
  KEY `subcategories_idx` (`ProductSubcategory`),
  CONSTRAINT `categories` FOREIGN KEY (`ProductCategory`) REFERENCES `category` (`CategoryID`) ON UPDATE CASCADE,
  CONSTRAINT `subcategories` FOREIGN KEY (`ProductSubcategory`) REFERENCES `subcategory` (`SubCategoryID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'Acer Preditor XH2345',8000,3,'/images/hej.jpg','hej',1,2),(3,'MSI 980 ti',5000,23,' ','bla bla',3,1),(5,'Samsung ATIV Book 9',15000,1,'http://www.samsung.com/us/system/consumer/product/np/93/0x/np930x2kk01us/NewBook9_01.jpg','The Samsung ATIV Book 9 connects you to your work and entertainment on the go with a hyper-real display and studio-quality audio, all packed into an impressively thin, lightweight design.',2,NULL),(6,'Thinkpad T430s',7000,4,'images/t430s.jpg','',2,NULL),(8,'GTX Titan',9999,2,'','cool',3,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-01 18:32:11
