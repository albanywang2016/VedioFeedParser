CREATE DATABASE  IF NOT EXISTS `source` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `source`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: source
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `image_id` varchar(90) NOT NULL,
  `image_type` varchar(45) DEFAULT NULL,
  `image_name` varchar(90) DEFAULT NULL,
  `image_file_name` varchar(90) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES ('image_1488491245254_0','jpg','image_1488491245254_0.jpg','image/2017-03-02/image_1488491245254_0.jpg',300,208),('image_1488491247584_0','jpg','image_1488491247584_0.jpg','image/2017-03-02/image_1488491247584_0.jpg',300,285),('image_1488491248895_0','jpg','image_1488491248895_0.jpg','image/2017-03-02/image_1488491248895_0.jpg',227,300),('image_1488491248895_1','jpg','image_1488491248895_1.jpg','image/2017-03-02/image_1488491248895_1.jpg',300,200),('image_1488491250324_0','jpg','image_1488491250324_0.jpg','image/2017-03-02/image_1488491250324_0.jpg',227,300),('image_1488491250324_1','jpg','image_1488491250324_1.jpg','image/2017-03-02/image_1488491250324_1.jpg',225,300),('image_1488491251707_0','jpg','image_1488491251707_0.jpg','image/2017-03-02/image_1488491251707_0.jpg',263,300),('image_1488491251707_1','jpg','image_1488491251707_1.jpg','image/2017-03-02/image_1488491251707_1.jpg',300,221),('image_1488491255086_0','jpg','image_1488491255086_0.jpg','image/2017-03-02/image_1488491255086_0.jpg',300,265),('image_1488491257113_0','jpg','image_1488491257113_0.jpg','image/2017-03-02/image_1488491257113_0.jpg',300,170),('image_1488491261934_0','jpg','image_1488491261934_0.jpg','image/2017-03-02/image_1488491261934_0.jpg',300,212),('image_1488491264398_0','jpg','image_1488491264398_0.jpg','image/2017-03-02/image_1488491264398_0.jpg',220,300),('image_1488491264398_1','jpg','image_1488491264398_1.jpg','image/2017-03-02/image_1488491264398_1.jpg',181,300),('image_1488491264398_2','jpg','image_1488491264398_2.jpg','image/2017-03-02/image_1488491264398_2.jpg',300,212),('image_1488491264398_3','jpg','image_1488491264398_3.jpg','image/2017-03-02/image_1488491264398_3.jpg',300,175),('image_1488491266909_0','jpg','image_1488491266909_0.jpg','image/2017-03-02/image_1488491266909_0.jpg',300,279),('image_1488491266909_1','jpg','image_1488491266909_1.jpg','image/2017-03-02/image_1488491266909_1.jpg',300,205),('image_1488491271812_0','jpg','image_1488491271812_0.jpg','image/2017-03-02/image_1488491271812_0.jpg',278,300),('image_1488491271812_1','jpg','image_1488491271812_1.jpg','image/2017-03-02/image_1488491271812_1.jpg',300,200),('image_1488491272764_0','jpg','image_1488491272764_0.jpg','image/2017-03-02/image_1488491272764_0.jpg',300,200),('image_1488491273739_0','jpg','image_1488491273739_0.jpg','image/2017-03-02/image_1488491273739_0.jpg',300,207),('image_1488491273739_1','jpg','image_1488491273739_1.jpg','image/2017-03-02/image_1488491273739_1.jpg',165,300),('image_1488491274814_0','jpg','image_1488491274814_0.jpg','image/2017-03-02/image_1488491274814_0.jpg',199,300),('image_1488491274814_1','jpg','image_1488491274814_1.jpg','image/2017-03-02/image_1488491274814_1.jpg',199,300),('image_1488491274814_2','jpg','image_1488491274814_2.jpg','image/2017-03-02/image_1488491274814_2.jpg',199,300),('image_1488491277848_0','jpg','image_1488491277848_0.jpg','image/2017-03-02/image_1488491277848_0.jpg',181,300),('image_1488491277848_1','jpg','image_1488491277848_1.jpg','image/2017-03-02/image_1488491277848_1.jpg',300,199),('image_1488491280577_0','jpg','image_1488491280577_0.jpg','image/2017-03-02/image_1488491280577_0.jpg',179,300),('image_1488491280577_1','jpg','image_1488491280577_1.jpg','image/2017-03-02/image_1488491280577_1.jpg',179,300),('image_1488491280577_2','jpg','image_1488491280577_2.jpg','image/2017-03-02/image_1488491280577_2.jpg',179,300),('image_1488491280577_3','jpg','image_1488491280577_3.jpg','image/2017-03-02/image_1488491280577_3.jpg',300,200),('image_1488491280577_4','jpg','image_1488491280577_4.jpg','image/2017-03-02/image_1488491280577_4.jpg',300,223),('image_1488491284381_0','jpg','image_1488491284381_0.jpg','image/2017-03-02/image_1488491284381_0.jpg',300,200),('image_1488491284381_1','jpg','image_1488491284381_1.jpg','image/2017-03-02/image_1488491284381_1.jpg',300,200),('image_1488491284381_2','jpg','image_1488491284381_2.jpg','image/2017-03-02/image_1488491284381_2.jpg',200,300),('image_1488491284381_3','jpg','image_1488491284381_3.jpg','image/2017-03-02/image_1488491284381_3.jpg',200,300),('image_1488491284381_4','jpg','image_1488491284381_4.jpg','image/2017-03-02/image_1488491284381_4.jpg',300,200),('image_1488491284381_5','jpg','image_1488491284381_5.jpg','image/2017-03-02/image_1488491284381_5.jpg',300,200),('image_1488491284381_6','jpg','image_1488491284381_6.jpg','image/2017-03-02/image_1488491284381_6.jpg',300,200),('image_1488491287580_0','jpg','image_1488491287580_0.jpg','image/2017-03-02/image_1488491287580_0.jpg',300,206),('image_1488491287580_1','jpg','image_1488491287580_1.jpg','image/2017-03-02/image_1488491287580_1.jpg',300,200);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-03 13:05:12
