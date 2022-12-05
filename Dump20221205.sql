-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cse305
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `Id` int NOT NULL,
  `AccountNumber` bigint DEFAULT NULL,
  `AccountNum` int DEFAULT NULL,
  `DateOpened` varchar(255) DEFAULT NULL,
  `StockName` varchar(255) DEFAULT NULL,
  `NumShares` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `AccountNumber` (`AccountNumber`),
  KEY `StockName` (`StockName`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`AccountNumber`) REFERENCES `customer` (`AccountNumber`) ON UPDATE CASCADE,
  CONSTRAINT `account_ibfk_2` FOREIGN KEY (`StockName`) REFERENCES `stock` (`StockSymbol`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,444444444,1,'10-1-06','GM',250),(2,444444444,1,'10-1-06','F',100),(3,222222222,1,'10-15-06','IBM',50);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL,
  `State` varchar(255) NOT NULL,
  `ZipCode` int NOT NULL,
  `PassWord` varchar(255) DEFAULT NULL,
  `Telephone` bigint NOT NULL,
  `Email` varchar(100) NOT NULL,
  `AccountNumber` bigint NOT NULL,
  `AccountCreationDate` date DEFAULT NULL,
  `CreditCardNumber` bigint NOT NULL,
  `Rating` int NOT NULL,
  `StockPortfolio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AccountNumber`),
  CONSTRAINT `A` CHECK (((`ZipCode` >= 10000) and (`ZipCode` <= 99999))),
  CONSTRAINT `B` CHECK (((`Telephone` >= 1000000000) and (`Telephone` <= 9999999999)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('Du','Victor','456 Fortune Road','Stony Brook','NY',11790,'000000',5166324360,'vicdu@cs.sunysb.edu',222222222,NULL,5678123456781234,1,''),('Smith','John','789 Peace Blvd','Los Angeles','CA',93536,'000000',3154434321,'jsmith@ic.sunysb.edu',333333333,NULL,2345678923456789,1,''),('Philip','Lewis','135 Knowledge Lane',' Lane	Stony Brook','NY',11794,'000000',3154434321,'pml@cs.sunysb.edu',444444444,NULL,6789234567892345,1,'');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Email` varchar(100) NOT NULL,
  `SSN` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `City` varchar(255) NOT NULL,
  `State` varchar(255) NOT NULL,
  `ZipCode` int NOT NULL,
  `PassWord` varchar(255) NOT NULL,
  `EmployeeType` varchar(255) NOT NULL,
  `Telephone` bigint NOT NULL,
  `StartDate` date NOT NULL,
  `HourlyRate` float NOT NULL,
  PRIMARY KEY (`SSN`),
  CONSTRAINT `C` CHECK (((`ZipCode` >= 10000) and (`ZipCode` <= 99999))),
  CONSTRAINT `D` CHECK (((`Telephone` >= 1000000000) and (`Telephone` <= 9999999999)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('aaaa@gmail.com','123456789','Smith','David','123 College road','Stony Brook','NY',11790,'000000','manager',5162152345,'2011-01-05',60),('bbbb@gmail.com','34535','dfg','fggd','dfgdg','fdgdg','dfg',44444,'000000','customerRepresentative',3333333333,'2000-03-11',33);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderId` int DEFAULT NULL,
  `StockSymbol` varchar(255) NOT NULL,
  `OrderType` varchar(255) NOT NULL,
  `NOF_Shares` int NOT NULL,
  `Cus_Acc_Num` bigint DEFAULT NULL,
  `Date_time` date DEFAULT NULL,
  `Transaction_Fee` int DEFAULT NULL,
  `Price_type` varchar(255) NOT NULL,
  `Employee_ID` varchar(255) DEFAULT NULL,
  `pricePerShare` double DEFAULT NULL,
  `Percentage` double DEFAULT NULL,
  `Customer_name` varchar(255) NOT NULL,
  KEY `Cus_Acc_Num` (`Cus_Acc_Num`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Cus_Acc_Num`) REFERENCES `customer` (`AccountNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'GM','buy',75,444444444,'2022-06-08',0,'market',NULL,NULL,NULL,'YangShang'),(2,'IBM','sell',10,222222222,'2022-01-14',0,'trailing stop 10%',NULL,NULL,NULL,'SmithJohn'),(0,'AAPL','Buy',546,222222222,'2022-12-05',0,'Market','789123456',0,0,'DuVictor'),(0,'AAPL','Buy',434,222222222,'2022-12-05',0,'Market','789123456',0,0,'DuVictor'),(0,'AAPL','Buy',435,222222222,'2022-12-05',0,'Market','34535',0,0,'DuVictor');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `StockName` varchar(255) NOT NULL,
  `NumberofShares` int NOT NULL,
  `StockSymbol` varchar(255) NOT NULL,
  `StockType` varchar(255) NOT NULL,
  `SharePrice` double NOT NULL,
  `EmployeeId` int DEFAULT NULL,
  PRIMARY KEY (`StockSymbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('Apple',350,'AAPL','Tehchnology',33,NULL),('Ford',750,'F','automotive',9,NULL),('General Motors',1,'GM','automotive',4,NULL),('IBM',500,'IBM','computer',91.41,NULL);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-05 12:01:40
