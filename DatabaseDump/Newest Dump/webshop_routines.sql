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
-- Temporary view structure for view `productview`
--

DROP TABLE IF EXISTS `productview`;
/*!50001 DROP VIEW IF EXISTS `productview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `productview` AS SELECT 
 1 AS `ProductID`,
 1 AS `ProductName`,
 1 AS `ProductPrice`,
 1 AS `ProductQuantity`,
 1 AS `ProductImage`,
 1 AS `ProductDescription`,
 1 AS `CategoryName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `editview`
--

DROP TABLE IF EXISTS `editview`;
/*!50001 DROP VIEW IF EXISTS `editview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `editview` AS SELECT 
 1 AS `ProductID`,
 1 AS `ProductName`,
 1 AS `ProductPrice`,
 1 AS `ProductQuantity`,
 1 AS `ProductImage`,
 1 AS `ProductDescription`,
 1 AS `CategoryName`,
 1 AS `ProductCategory`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `productview`
--

/*!50001 DROP VIEW IF EXISTS `productview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `productview` AS select `products`.`ProductID` AS `ProductID`,`products`.`ProductName` AS `ProductName`,`products`.`ProductPrice` AS `ProductPrice`,`products`.`ProductQuantity` AS `ProductQuantity`,`products`.`ProductImage` AS `ProductImage`,`products`.`ProductDescription` AS `ProductDescription`,`category`.`CategoryName` AS `CategoryName` from (`products` left join `category` on((`products`.`ProductCategory` = `category`.`CategoryID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `editview`
--

/*!50001 DROP VIEW IF EXISTS `editview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `editview` AS select `products`.`ProductID` AS `ProductID`,`products`.`ProductName` AS `ProductName`,`products`.`ProductPrice` AS `ProductPrice`,`products`.`ProductQuantity` AS `ProductQuantity`,`products`.`ProductImage` AS `ProductImage`,`products`.`ProductDescription` AS `ProductDescription`,`category`.`CategoryName` AS `CategoryName`,`products`.`ProductCategory` AS `ProductCategory` from (`products` left join `category` on((`products`.`ProductCategory` = `category`.`CategoryID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Dumping events for database 'webshop'
--

--
-- Dumping routines for database 'webshop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-19 19:47:29
