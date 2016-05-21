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
  `ProductDescription` varchar(450) NOT NULL DEFAULT '',
  `ProductCategory` int(10) NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `ProductID_UNIQUE` (`ProductID`),
  KEY `categories_idx` (`ProductCategory`),
  CONSTRAINT `categories` FOREIGN KEY (`ProductCategory`) REFERENCES `category` (`CategoryID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'Acer Preditor XH2345',8000,3,'Dell_4K_P2715.jpg','hej',1),(3,'MSI 980 ti',5000,5,'Dell_4K_P2715.jpg','bla bla',3),(5,'Samsung ATIV Book 9',15000,1,'Dell_4K_P2715.jpg','The Samsung ATIV Book 9 connects you to your work and entertainment on the go with a hyper-real display and studio-quality audio, all packed into an impressively thin, lightweight design.',2),(6,'Thinkpad T430s',7000,4,'Dell_4K_P2715.jpg','',2),(12,'Asus VG248QE',2300,4,'Dell_4K_P2715.jpg','Ultra smooth action with 144Hz rapid refresh rate and 1ms (GTG) response time',1),(13,'Acer R240HY',1290,11,'Dell_4K_P2715.jpg','The Acer R Series 23.8\" wide viewing IPS display shows every detail clearly and vivid without color difference from any viewing angle. Its zero frame design puts no boundary on your visual enjoyment while the brushed hairline finish stand matches any environment.',1),(14,'Dell Ultrasharp U2415',2200,6,'Dell_4K_P2715.jpg','Stunning color accuracy right out of the box. With 99% sRGB color coverage, and a factory color calibration report to certify that each monitor arrives at a deltaE of <3, you can be sure that colors are as accurate as they can be.',1),(15,'Dell Ultra HD 4k Monitor P2715Q',4500,2,'Dell_4K_P2715.jpg','The new Dell 27\" Ultra HD 4K Monitor will enable productivity with Dell Display Manager, has extensive digital connectivity, and advanced exchange service.',1),(16,'Dell UltraSharp U2715H',4800,3,'Dell_4K_P2715.jpg','ultra sharp 27 inch new monitor. Refresh existing model with improved productivity features such as better connectivity features and an ultra-thin bezel to support',1),(17,'Samsung S27E510CS',1800,5,'Dell_4K_P2715.jpg','With thoughtful design and engineering, the SE510C is much more than a monitor with stunning looks. Featuring a glossy black body that is framed perfectly by a narrow bezel and elevated by a T-shaped stand, it\'s carefully designed to keep the focus on your content viewing pleasure.',1),(18,'HP 15-ay011nr',4500,3,'Dell_4K_P2715.jpg','HP 15-ay011nr 15.6-inch Full-HD Laptop (Core i5, 8GB RAM, 1TB HDD) with Windows 10',2),(19,'Apple MacBook Pro MD101LL/A ',7900,5,'Dell_4K_P2715.jpg','The best design. For the best performance We designed every aspect of the all-new MacBook Pro with performance in mind. The entire internal structure was built to house the very best high-performance components: all-flash storage, the latest quad-core processors, powerful discrete graphics, massive amounts of memory. ',2),(20,'Apple MacBook Air MJVE2LL/A',8200,10,'Dell_4K_P2715.jpg','--1.6 GHz Intel Core i5 (Broadwell) 4GB of 1600 MHz LPDDR3 RAM. --128GB PCIe-Based Flash Storage Integrated Intel HD Graphics 6000. --13.3\" LED-Backlit Glossy Display 1440 x 900 Native Resolution. --802.11ac Wi-Fi, Bluetooth 4.0 USB 3.0, Thunderbolt 2. --720p FaceTime HD Camera, SDXC Card Slot Mac OS X 10.10 Yosemite.',2),(21,'Dell XPS 9350-1340SLV',7400,2,'Samsung_S27E510CS.jpg','Erasing borders, starting with the display. The smallest 13-inch laptop on the planet has the world\'s first virtually borderless Infinity Edge display - amazing both inside and out.',2),(22,'Lenovo Thinkpad Yoga',3000,5,'Dell_4K_P2715.jpg','Multimode Versatility - Easily change between four modes - Laptop, Stand, Tablet, and Tent',2),(23,'Lenovo ThinkPad E560',7700,9,'Dell_4K_P2715.jpg','Lenovo Notebook 20EV002JUS ThinkPad E560 15.6 Ci7-6500U 2.5GHz 8G 500G W10PD',2);
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

-- Dump completed on 2016-05-21 16:33:59
