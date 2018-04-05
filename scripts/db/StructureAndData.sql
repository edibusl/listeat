-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: listeat
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carts` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `glist_id` int(11) DEFAULT NULL,
  `purchase_date` datetime DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FK_carts_glists_glist_id_idx` (`glist_id`),
  CONSTRAINT `FK_carts_glists_glist_id` FOREIGN KEY (`glist_id`) REFERENCES `glists` (`glist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,21,'2018-03-28 00:00:00'),(2,21,'2018-03-28 00:00:00'),(3,21,'2018-03-28 00:00:00'),(5,21,'2018-03-28 00:00:00'),(6,21,'2018-03-28 00:00:00'),(7,21,'2018-03-28 00:00:00'),(8,21,'2018-03-28 00:00:00'),(9,21,'2018-03-28 00:00:00'),(10,21,'2018-03-28 00:00:00'),(11,21,'2018-03-28 00:00:00'),(12,21,'2018-03-28 00:00:00'),(13,21,'2018-03-28 00:00:00'),(14,21,'2018-03-28 00:00:00'),(15,21,'2018-03-29 00:00:00'),(16,21,'2018-03-29 00:00:00'),(17,21,'2018-03-31 00:00:00'),(18,21,'2018-03-31 00:00:00'),(19,60,'2018-04-01 00:00:00'),(20,60,'2018-04-01 00:00:00'),(21,60,'2018-04-01 00:00:00'),(22,60,'2018-04-01 00:00:00'),(23,60,'2018-04-01 00:00:00'),(24,60,'2018-04-01 00:00:00'),(25,60,'2018-04-01 00:00:00'),(26,60,'2018-04-01 00:00:00'),(27,60,'2018-04-01 00:00:00'),(28,21,'2018-04-01 00:00:00'),(29,61,'2018-04-03 00:00:00'),(30,64,'2018-04-03 00:00:00'),(31,64,'2018-04-03 00:00:00'),(32,64,'2018-04-03 00:00:00'),(33,66,'2018-04-03 00:00:00'),(34,67,'2018-04-05 00:00:00'),(35,67,'2018-04-05 00:00:00'),(36,68,'2018-04-05 00:00:00'),(37,69,'2018-04-05 00:00:00'),(38,70,'2018-04-05 00:00:00'),(39,71,'2018-04-05 00:00:00');
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'מוצרי חלב','כל סוגי מוצרי החלב'),(2,'נקניקים',NULL),(3,'מעדנייה - בשרים',NULL),(4,'מעדניה - גבינות וסלטים',NULL),(5,'פירות יבשים ופיצוחים',NULL),(6,'ירקות ופירות',NULL),(7,'משקאות',NULL),(8,'בשר עוף ודגים',NULL),(9,'שימורים',NULL),(10,'מוצרי יסוד',NULL),(11,'רטבים ותבלינים',NULL),(12,'אטריות ופסטה',NULL),(13,'מרקים ותבשילים',NULL),(14,'תכשירי כביסה',NULL),(15,'חומרי ניקוי',NULL),(16,'מוצרי נייר ושונים',NULL),(17,'ממרחים מתוקים',NULL),(18,'מלוחים',NULL),(19,'ממתקים ושוקולד',NULL),(20,'לחם ומאפים',NULL),(21,'סלטים ארוזים',NULL),(22,'מוצרי בצק קפוא',NULL),(23,'פירות וירקות קפואים',NULL),(24,'משקאות קפואים וגלידות',NULL),(25,'ארוחות מוכנות קפואות',NULL),(26,'מוצרי תינוק',NULL),(27,'יופי וטיפוח',NULL),(28,'דגנים וקורנפלקס',NULL),(29,'בריאות וארוגני',NULL),(30,'מזון בעלי חיים',NULL),(31,'ציוד משרדי',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gitems`
--

DROP TABLE IF EXISTS `gitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gitems` (
  `gitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `glist_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `cart_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `comments` text,
  `is_checked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`gitem_id`),
  KEY `FK_gitems_glists_idx` (`glist_id`),
  KEY `FK_gitems_users_idx` (`user_id`),
  KEY `FK_gitems_products_idx` (`product_id`),
  KEY `FK_gitems_carts_idx` (`cart_id`),
  CONSTRAINT `FK_gitems_carts` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_gitems_glists` FOREIGN KEY (`glist_id`) REFERENCES `glists` (`glist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_gitems_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_gitems_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gitems`
--

LOCK TABLES `gitems` WRITE;
/*!40000 ALTER TABLE `gitems` DISABLE KEYS */;
INSERT INTO `gitems` VALUES (38,2,NULL,2,28,2,NULL,'2018-04-01 00:00:00','שרית אוהבת שטראוס',1),(39,2,NULL,4,8,NULL,500,'2018-03-28 00:00:00','',1),(45,2,NULL,10,13,NULL,500,'2018-03-28 00:00:00','',1),(46,2,NULL,24,28,5,NULL,'2018-04-01 00:00:00','',1),(47,2,NULL,25,28,3,NULL,'2018-04-01 00:00:00','בטעם קוקוס',1),(48,2,22,2,NULL,30,NULL,'2018-03-25 00:00:00','',0),(49,2,40,4,NULL,NULL,100,'2018-03-26 00:00:00','',0),(51,2,51,4,NULL,1,NULL,'2018-03-27 00:00:00','',0),(52,2,NULL,2,8,3,NULL,'2018-03-28 00:00:00','',1),(53,2,NULL,5,9,1,NULL,'2018-03-28 00:00:00','',1),(54,2,NULL,10,10,12,NULL,'2018-03-28 00:00:00','',1),(55,2,NULL,4,14,1,NULL,'2018-03-28 00:00:00','',1),(56,2,NULL,29,16,1,NULL,'2018-03-29 00:00:00','',1),(57,2,NULL,2,17,2,NULL,'2018-03-31 00:00:00','',1),(58,2,NULL,1,18,12,NULL,'2018-03-31 00:00:00','',1),(59,4,NULL,30,20,2,NULL,'2018-04-01 00:00:00','',1),(60,2,NULL,31,19,NULL,500,'2018-04-01 00:00:00','uu',1),(61,2,NULL,30,23,2,NULL,'2018-04-01 00:00:00','',1),(62,4,NULL,30,24,NULL,500,'2018-04-01 00:00:00','',1),(63,4,NULL,31,27,1,NULL,'2018-04-01 00:00:00','',1),(64,4,NULL,30,26,4,NULL,'2018-04-01 00:00:00','',1),(65,2,21,32,NULL,3,NULL,'2018-04-01 00:00:00','',0),(66,2,21,33,NULL,NULL,100,'2018-04-01 00:00:00','',0),(67,2,21,35,NULL,2,NULL,'2018-04-01 00:00:00','',0),(68,2,21,36,NULL,1,NULL,'2018-04-01 00:00:00','',0),(69,2,21,37,NULL,NULL,400,'2018-04-01 00:00:00','',0),(70,2,21,42,NULL,1,NULL,'2018-04-01 00:00:00','',0),(71,2,21,38,NULL,8,NULL,'2018-04-01 00:00:00','רק של סטארקיסט!',0),(72,2,21,45,NULL,3,NULL,'2018-04-05 00:00:00','',0),(73,2,21,47,NULL,NULL,250,'2018-04-01 00:00:00','של תנובה',0),(74,2,21,80,NULL,4,NULL,'2018-04-04 00:00:00','',0),(75,2,21,48,NULL,1,NULL,'2018-04-01 00:00:00','חבילה סגורה',0),(77,2,21,44,NULL,2,NULL,'2018-04-04 00:00:00','במבצע',1),(78,2,21,81,NULL,NULL,300,'2018-04-01 00:00:00','',0),(79,2,61,32,NULL,4,NULL,'2018-04-01 00:00:00','חבילות של שקיות',0),(80,2,61,45,NULL,2,NULL,'2018-04-01 00:00:00','',0),(81,2,61,83,NULL,4,NULL,'2018-04-01 00:00:00','חבילות קטנות מעוצבות',0),(82,2,21,7,NULL,8,NULL,'2018-04-03 00:00:00','',0),(83,2,NULL,43,29,2,NULL,'2018-04-03 00:00:00','',1),(86,2,NULL,85,31,1,NULL,'2018-04-03 00:00:00','',1),(87,2,NULL,43,32,NULL,600,'2018-04-03 00:00:00','הערות',1),(88,2,64,60,NULL,2,NULL,'2018-04-03 00:00:00','',0),(90,2,21,87,NULL,2,NULL,'2018-04-04 00:00:00','עדיף עם פקק',1),(91,2,NULL,43,35,NULL,600,'2018-04-05 00:00:00','הערות 2',1),(93,2,NULL,89,36,NULL,500,'2018-04-05 00:00:00','',1),(94,2,NULL,33,37,2,NULL,'2018-04-05 00:00:00','',1),(95,2,NULL,90,38,1,NULL,'2018-04-05 00:00:00','',1),(96,2,NULL,91,39,30,NULL,'2018-04-05 00:00:00','',1);
/*!40000 ALTER TABLE `gitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glists`
--

DROP TABLE IF EXISTS `glists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glists` (
  `glist_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(45) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`glist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glists`
--

LOCK TABLES `glists` WRITE;
/*!40000 ALTER TABLE `glists` DISABLE KEYS */;
INSERT INTO `glists` VALUES (21,'רשימת מכולת','זאת הרשימה המשותפת שלנו'),(22,'רשימת בדיקה','זאת רשימה בשביל בדיקות'),(23,'My first list','This is the 8th item'),(24,'My first list','This is the 8th item'),(25,'My first list','This is the 8th item'),(26,'My first list','This is the 8th item'),(27,'My first list','This is the 8th item'),(28,'My first list','This is the 8th item'),(29,'My first list','This is the 8th item'),(30,'My first list','This is the 8th item'),(31,'My first list','This is the 8th item'),(32,'My first list','This is the 8th item'),(33,'My first list','This is the 8th item'),(34,'My first list','This is the 8th item'),(35,'My first list','This is the 8th item'),(39,'Updated subject','Updated description'),(40,'בדיקה2','תיאור'),(41,'שלום','היי'),(42,'חחיי','ייי'),(43,'יייי',''),(44,'ששששש',''),(45,'יחח',''),(46,'אא',''),(47,'בבב',''),(48,'גגג',''),(49,'דדד',''),(50,'ההה',''),(51,'רשימה לפסח',''),(52,'חדש',''),(53,'חדש',''),(54,'חדש 2',''),(55,'חדש 3',''),(56,'חדש 4',''),(57,'חדש 5',''),(58,'רשימה עם משתמש',''),(59,'רשימה חדשה עם משתמש',''),(60,'רשימה לפסח 2',''),(61,'ארוחת חג שני',''),(62,'רשימה חדשה',''),(63,'רשימה חדשה','תיאור'),(64,'רשינה חדשה 2',''),(65,'רשימת בדיקה',''),(66,'רשימה נוספת',''),(67,'רשימה חדשה 3','תיאור רשימה 3'),(68,'רשימה חדשה',''),(69,'רשימת בדיקה',''),(70,'רשימה',''),(71,'רשימה חדשה','');
/*!40000 ALTER TABLE `glists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glists_users`
--

DROP TABLE IF EXISTS `glists_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glists_users` (
  `user_id` int(11) NOT NULL,
  `glist_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`glist_id`),
  KEY `FK_glists_users_glist_id_idx` (`glist_id`),
  CONSTRAINT `FK_glists_users_glist_id` FOREIGN KEY (`glist_id`) REFERENCES `glists` (`glist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_glists_users_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glists_users`
--

LOCK TABLES `glists_users` WRITE;
/*!40000 ALTER TABLE `glists_users` DISABLE KEYS */;
INSERT INTO `glists_users` VALUES (2,21),(3,21),(4,21),(3,23),(3,24),(3,25),(3,26),(3,27),(3,28),(3,29),(3,30),(3,31),(3,32),(3,33),(3,34),(3,35),(4,60),(2,61),(4,61),(4,63),(4,64),(4,65),(4,67),(4,68),(4,69);
/*!40000 ALTER TABLE `glists_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `glist_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK_products_glists_glist_id_idx` (`glist_id`),
  KEY `FK_products_categories_category_id_idx` (`category_id`),
  CONSTRAINT `FK_products_categories_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_products_glists_glist_id` FOREIGN KEY (`glist_id`) REFERENCES `glists` (`glist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,NULL,'_',NULL,NULL),(2,1,NULL,'_','קוטג\' 3 או 5 אחוז',NULL),(3,1,NULL,'_',NULL,NULL),(4,2,NULL,'נקניק סלמי',NULL,NULL),(5,2,NULL,'נקניק חזה עוף',NULL,NULL),(6,3,NULL,'חזה עוף',NULL,NULL),(7,3,NULL,'בשר טחון',NULL,NULL),(10,4,NULL,'סלט ביצים','',NULL),(23,1,NULL,'','','f7d99cc6-02cc-45f6-83eb-ba0f6f552718.png'),(24,2,NULL,'_','','c6d3aecd-005b-4a12-91cf-e7f5615c5f23.png'),(25,1,NULL,'_','קוקוס קלוי','ce91e546-008b-4ac5-b257-787268de16c2.png'),(26,2,NULL,'_','תיאור','75a3f055-33b8-45c8-a8c1-83992d5a8769.png'),(27,1,NULL,'_','','1509ecc1-d90a-4bc0-a078-eab96b048b53.png'),(28,2,NULL,'_','','c4e77e2a-9008-4fa6-92bf-20655525307d.png'),(29,1,NULL,'_','','03b6441b-fadb-4cdb-a5e6-754a5a754d8b.png'),(30,1,NULL,'_','',NULL),(31,1,NULL,'_','','78b7d430-d7d1-4d9e-827d-23f7e598a47a.png'),(32,20,NULL,'מצות','','b51931b4-e59c-4582-b01a-df445ea423ac.png'),(33,6,NULL,'שום','','600ac52d-16a5-4641-9cc3-0ae18afd7429.png'),(34,6,NULL,'בצל','','db6dee2e-2df6-4b20-8145-71efe400ee8b.png'),(35,16,NULL,'נייר סופג','','fd6c2e8d-1d3a-4aa6-8b94-c9d8eea60e5c.png'),(36,10,NULL,'דבש','','a9157475-8c28-4ce2-85cc-bae3cca76856.png'),(37,10,NULL,'מלח','','fc3cf5e0-6cbe-478a-90fc-1ebfd4c9a3db.png'),(38,9,NULL,'טונה','','ce77203c-3546-4b6a-a557-7236372d2e36.png'),(39,17,NULL,'נוטלה','','f4377dcd-7645-42f8-b704-14dafd356bfb.png'),(40,11,NULL,'מיונז','','76dbb060-4d1e-4614-ab3b-a9921993031b.png'),(41,11,NULL,'טחינה','','cb2b286d-4da4-41ea-aebb-87bdc074a4ed.png'),(42,10,NULL,'ביצים','','53cdaa01-d2ff-4fde-a4ab-4bd30f1861ca.png'),(43,19,NULL,'שוקולד','','4f2a28c9-5870-428c-b11b-1a5fbe7e4a24.png'),(44,6,NULL,'עגבניות שרי','','f4cba7df-f12e-4e41-968a-0586650e1795.png'),(45,1,NULL,'חלב 3%','','d3e6d65a-4c29-43bc-b8a8-722c6e6d9ebc.png'),(46,7,NULL,'שישיית מים','','238b9425-aa42-4a61-8411-19ec4e4976fe.png'),(47,1,NULL,'חמאה','','e003aaf3-81aa-462b-a1f5-a93129c334bb.png'),(48,6,NULL,'תמרים','','4240355c-6f07-4179-a2fb-46f5cd457f36.png'),(49,10,NULL,'סוכר','','0bcc9b3b-e40e-4fee-9ce6-3d38fff2b43e.png'),(50,20,NULL,'חלה','',NULL),(51,20,NULL,'לחם שחור','',NULL),(52,5,NULL,'שקדים','','e6927047-684e-4de0-86b6-480dfd5253a4.png'),(53,12,NULL,'פסטה פנה','',NULL),(54,12,NULL,'אורז פרסי','',NULL),(55,12,NULL,'אורז יסמין','',NULL),(56,10,NULL,'שמן חמניות','','093ba301-ca54-465c-9f9a-e9690dc9268d.png'),(57,4,NULL,'גבינת עמק פרוסה','',NULL),(58,8,NULL,'סלמון','',NULL),(59,8,NULL,'אמנון','',NULL),(60,11,NULL,'קטשופ','',NULL),(61,13,NULL,'מרק עוף','',NULL),(62,14,NULL,'מרכך כביסה','','9153ba83-8bb2-4438-b476-7522e924d288.png'),(63,14,NULL,'אבקת כביסה','',NULL),(64,14,NULL,'ג\'ל כביסה','','53b1fed6-da4d-4f62-b5e9-3f47787e2fc3.png'),(65,15,NULL,'נוזל ניקוי כללי','','9b4f5456-03cf-4eb6-8082-03bf93f625f3.png'),(66,15,NULL,'שקיות אשפה','',NULL),(67,24,NULL,'גלידה שטראוס','',NULL),(68,26,NULL,'מטרנה','',NULL),(69,26,NULL,'חיתולים','',NULL),(70,27,NULL,'שמפו','',NULL),(71,27,NULL,'מרכך שיער','',NULL),(72,31,NULL,'מספריים','',NULL),(73,30,NULL,'אוכל לכלב','',NULL),(74,29,NULL,'טופו','',NULL),(75,28,NULL,'כריות','',NULL),(76,1,NULL,'מילקי','',NULL),(77,25,NULL,'שניצל קפוא','',NULL),(78,22,NULL,'ג\'חנון','',NULL),(79,21,NULL,'סלט טונה','',NULL),(80,1,21,'יוגורט יופלה ששרית אוהבת','בצבע אדום, טעם תותים',NULL),(81,6,NULL,'מלפפון','',NULL),(82,6,NULL,'עגבניות','',NULL),(83,16,NULL,'מפיות','',NULL),(84,31,61,'מתנה לילדים','','1d3f6608-48eb-446b-bf02-aff0fa8834b2.png'),(85,1,64,'אצבעות שוקולד וניל','','623bbbf6-a1be-4e88-aadd-450578509ba5.png'),(86,4,66,'סלט כלשהו','לאורחים','5c6b8c60-5176-4281-a039-fc980c82c8f8.png'),(87,7,21,'בקבוק מים קטן','של נביעות','ee4c5b57-58ba-4fa7-b358-831aeeba3a48.png'),(88,1,67,'מילקי אדום שהילדים אוהבים','','aaa59b0c-06aa-4df6-bc68-384092212a3c.png'),(89,31,68,'מהדק','','4e9f5e3f-28d1-40d1-bf46-a9c5cb62f764.png'),(90,31,70,'דפים','',NULL),(91,31,71,'מהדק','',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `profile_image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'edibusl@gmail.com','2018-04-05','Edi Bv','https://lookaside.facebook.com/platform/profilepic/?asid=10156301257506834&height=50&width=50&ext=1523210989&hash=AeQaQcVJCh6o9Qym'),(3,'edibusl','2018-03-27',NULL,NULL),(4,'sarit.zafri@gmail.com','2018-04-01','Sarit Zafri Buslovich','https://lookaside.facebook.com/platform/profilepic/?asid=10211179668343318&height=50&width=50&ext=1522869325&hash=AeSr3q5Hy25pF3f2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-05 21:30:25
