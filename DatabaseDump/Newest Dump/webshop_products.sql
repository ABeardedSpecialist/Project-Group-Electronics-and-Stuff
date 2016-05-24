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
  `ProductImage` varchar(150) NOT NULL DEFAULT 'imageNotFound.jpg',
  `ProductDescription` varchar(650) NOT NULL DEFAULT '',
  `ProductCategory` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ProductID`),
  UNIQUE KEY `ProductID_UNIQUE` (`ProductID`),
  KEY `categories_idx` (`ProductCategory`),
  CONSTRAINT `categories` FOREIGN KEY (`ProductCategory`) REFERENCES `category` (`CategoryID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (3,'MSI 980 ti',5000,5,'msi_gtx_980ti.jpg','MSI NVIDIA GeForce GTX 980TI GAMING 6G GDDR5 DVI/HDMI/3DisplayPort PCI-Express Video Card ',3),(5,'Samsung ATIV Book 9',15000,5,'ativ-book-9-spin-1.jpg','The Samsung ATIV Book 9 connects you to your work and entertainment on the go with a hyper-real display and studio-quality audio, all packed into an impressively thin, lightweight design.',2),(6,'Thinkpad T430s',7000,2,'thinkpad-t430s-01.jpg','Lenovo ThinkPad T430s 23539WU 14\" LED Notebook - Intel - Core i7 i7-3520M 2.9GHz - Black 23539WU Laptops & Notebooks',2),(12,'Asus VG248QE',2300,3,'227a.jpg','Ultra smooth action with 144Hz rapid refresh rate and 1ms (GTG) response time',8),(13,'Acer R240HY',1290,7,'519rzl-wIQL._SX300_.jpg','The Acer R Series 23.8\" wide viewing IPS display shows every detail clearly and vivid without color difference from any viewing angle. Its zero frame design puts no boundary on your visual enjoyment while the brushed hairline finish stand matches any environment.',8),(14,'Dell Ultrasharp U2415',2200,6,'71gAv7K-jsL._SY355_.jpg','Stunning color accuracy right out of the box. With 99% sRGB color coverage, and a factory color calibration report to certify that each monitor arrives at a deltaE of <3, you can be sure that colors are as accurate as they can be.',8),(15,'Dell Ultra HD 4k Monitor P2715Q',4500,2,'dell-ultrasharp-u2515h-790x672.jpg','The new Dell 27\" Ultra HD 4K Monitor will enable productivity with Dell Display Manager, has extensive digital connectivity, and advanced exchange service.',8),(16,'Dell UltraSharp U2715H',4800,3,'dell-ultrasharp-u2515h-790x672.jpg','ultra sharp 27 inch new monitor. Refresh existing model with improved productivity features such as better connectivity features and an ultra-thin bezel to support',8),(17,'Samsung S27E510CS',1800,5,'uk_LS27E510CS-EN_005_Dynamic_black.jpg','With thoughtful design and engineering, the SE510C is much more than a monitor with stunning looks. Featuring a glossy black body that is framed perfectly by a narrow bezel and elevated by a T-shaped stand, it\'s carefully designed to keep the focus on your content viewing pleasure.',8),(18,'HP 15-ay011nr',4500,0,'81CuEd1xIOL._SX355_.jpg','HP 15-ay011nr 15.6-inch Full-HD Laptop (Core i5, 8GB RAM, 1TB HDD) with Windows 10',2),(19,'Apple MacBook Pro MD101LL/A ',7900,2,'macbook pro.jpg','The best design. For the best performance We designed every aspect of the all-new MacBook Pro with performance in mind. The entire internal structure was built to house the very best high-performance components: all-flash storage, the latest quad-core processors, powerful discrete graphics, massive amounts of memory. ',2),(20,'Apple MacBook Air MJVE2LL/A',8200,10,'macbook air.jpg','1.6 GHz Intel Core i5 (Broadwell) 4GB of 1600 MHz LPDDR3 RAM. --128GB PCIe-Based Flash Storage Integrated Intel HD Graphics 6000. --13.3\" LED-Backlit Glossy Display 1440 x 900 Native Resolution. --802.11ac Wi-Fi, Bluetooth 4.0 USB 3.0, Thunderbolt 2. --720p FaceTime HD Camera, SDXC Card Slot Mac OS X 10.10 Yosemite.',2),(21,'Dell XPS 9350-1340SLV',7400,2,'dell_XPS.jpg','Erasing borders, starting with the display. The smallest 13-inch laptop on the planet has the world\'s first virtually borderless Infinity Edge display - amazing both inside and out.',2),(22,'Lenovo Thinkpad Yoga',3000,5,'lenovo-laptop-thinkpad-yoga-12-main.png','Multimode Versatility - Easily change between four modes - Laptop, Stand, Tablet, and Tent',2),(23,'Lenovo ThinkPad E560',7700,9,'lenovo-laptop-thinkpad-e560-front.png','Lenovo Notebook 20EV002JUS ThinkPad E560 15.6 Ci7-6500U 2.5GHz 8G 500G W10PD',2),(24,'Samsung 850 evo',899,4,'samsung SSD.jpg','Upgrading your PC with a Samsung SSD is the most economical way to breathe new life into an aging PC. The 850 EVO reads, writes and multi-tasks at incredible speeds, enhancing boot-up speed, application loading and multi-tasking performance. It\'s more than an upgrade, it\'s a complete transformation of your PC.',7),(25,'Western Digital 1TB SATA 7200 Rpm',550,16,'WD_1TB.jpg','WD Blue hard drives have a multitude of features including third generation SATA interface with 6 GB/s transfer rate, plus rock solid performance and ultra-cool and quiet operation.',7),(26,'WD Red 4TB NAS Hard Disk Drive - 5400 RPM',1580,5,'WD_4TB.jpg','a long-time leader in hard drive technology designs and manufactures the #1 selling internal and external hard drives and award-winning media players and network drives',7),(27,'EVGA GeForce GTX 970 4GB',2920,9,'970.jpg','Base Clock: 1165 MHZBoost Clock: 1317 MHzMemory Clock: 7010 MHz Effectiv',3),(28,'Intel Boxed Core I7-6700K 4.00 GHz',4200,6,'i7.jpg','With faster, intelligent, multi-core technology that applies processing power where it\'s needed most, Intel Core i7 processors deliver an incredible breakthrough in PC performance. You\'ll multitask applications faster and unleash incredible digital media creation. And you\'ll experience maximum performance for everything you do.',5),(29,'ntel Boxed Core I5-6600K 3.50 GHz',2569,12,'i5.jpg','With intelligent performance that accelerates in response to demanding tasks, such as playing games and editing photos, the Intel Core i5 processor moves faster when you do. The Intel Core i5 processor automatically allocates processing power where it\'s needed most. Whether you\'re creating HD video, composing digital music, editing photos, or playing the coolest PC games - with the Intel Core i5 processor you can multitask with ease and be more productive than ever.',5),(30,'EVGA GeForce GTX TITAN Black 6GB GDDR5',26999,2,'titan_black.jpg','GeForce GTX TITAN Black is a masterpiece in design and engineering. Evolved from the award-winning GTX TITAN, the Black edition lets you take on your most graphics-intensive games with 10% faster performance, while still retaining whisper-quiet acoustics and cool thermals. This is the elite gaming GPU for gamers who demand the ultimate pure gaming experience',3),(31,'ASUS Z170-A ATX ',1455,3,'Asus_z170.jpg','Z170 ATX with One-Click Total System Optimization, packed with M.2, high-clarity Crystal Sound 3 and enduring 5X Protection II for enhanced performance and potent entertainment',4),(32,'Gigabyte GA-Z170X-Gaming 3',1199,5,'Gigabyte LGA1151.jpg','Audio Noise Guard with LED path lighting. Realtek ALC1150 with gain boost cover audio noise guard. 2-way SLI and 3-way Cross Fire support. SATA Express support for 16 Gb/s data transfer. M.2 for SSDs drives with up to 32 Gb/s data transfer. Long lifespan Durable Solid caps.',4),(33,'ASUS ROG MAXIMUS VIII HERO ',2500,1,'ASUS_rog_hero.jpg','The ROG MAXIMUS VIII HERO ALPHA improves upon the award-winning design of the original ROG MAXIMUS VIII HERO by offering improved USB 3.1 performance, 2T2R 802.11ac Wi-Fi with MU-MIMO, dual NVMe U.2 slots, and all-new Aura RGB Strip headers for custom system lighting.',4),(34,'Dell Inspiron 3847 Desktop ',7200,2,'Dell_desktop.jpg','Big on expansion. Big on power.   Easy expandability:You\'ll never be short on room with large hard drives and the option to add more later. ',6),(35,'SkyTech Archangel ST-FX6300',5999,3,'SkyTech.jpg','Tired of purchasing gaming computers that looks great but can\'t really play games? We offer a blazing fast ultra-gamer computer that will blow your mind away. Equipped with the best durable case along with amazing speed, this is a must buy!',6),(36,'ASUS G20AJ-B11 Desktop',11000,1,'Asus_desktop.jpg','Microsoft Windows 8.1 operating system preinstalled Upgrade to Windows 10 for free 4th Gen Intel® CoreTM i7-4790 processor Features up to 4.0GHz processor speed. Intel® CoreTM i7 processor 16GB DDR3 memory For multitasking power. Multiformat DVD±RW/CD-RW drive Create custom DVDs and CDs.',6),(37,'Alienware Alpha Desktop Console ',17099,2,'alienware_alpha.jpg','The Alienware Alpha combines the power of a PC with the ease of a gaming console - use it as a POWERFUL compact desktop or a SUPERFAST game console! ',6);
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

-- Dump completed on 2016-05-24 15:52:24
