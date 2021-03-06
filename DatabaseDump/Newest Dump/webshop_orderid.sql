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
-- Table structure for table `orderid`
--

DROP TABLE IF EXISTS `orderid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderid` (
  `OrderID` int(6) NOT NULL AUTO_INCREMENT,
  `OrderName` varchar(45) DEFAULT NULL,
  `OrderAddress` varchar(45) DEFAULT NULL,
  `OrderPhone` int(10) DEFAULT NULL,
  `OrderEmail` varchar(45) DEFAULT NULL,
  `OrderTotalPrice` int(5) DEFAULT NULL,
  `OrderNumberOfProducts` int(4) DEFAULT NULL,
  `OrderStatus` varchar(45) DEFAULT NULL,
  `OrderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`OrderID`),
  UNIQUE KEY `OrderID_UNIQUE` (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=10021 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderid`
--

LOCK TABLES `orderid` WRITE;
/*!40000 ALTER TABLE `orderid` DISABLE KEYS */;
INSERT INTO `orderid` VALUES (10016,'dawdawd','',0,'',43000,5,'SHIPPED','2016-05-22 10:22:30'),(10017,'dawdawda','',0,'',12600,3,'NEW','2016-05-22 10:22:46'),(10018,'kalle','awdawd',0,'wdawd',13500,3,'NEW','2016-05-22 11:27:59'),(10019,'kalle','awdawd',12313,'adawwd',3247,4,'NEW','2016-05-23 14:30:56'),(10020,'Michael','Kallevägen 92',1231244,'kalle@kalle.com',23700,3,'NEW','2016-05-23 16:13:18');
/*!40000 ALTER TABLE `orderid` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-24 15:52:24
