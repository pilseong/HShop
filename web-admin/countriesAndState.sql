-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: shopmedb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'Andorra','AD'),(2,'United Arab Emirates','AE'),(3,'Afghanistan','AF'),(4,'Antigua and Barbuda','AG'),(5,'Anguilla','AI'),(6,'Albania','AL'),(7,'Armenia','AM'),(8,'Netherlands Antilles','AN'),(9,'Angola','AO'),(10,'Antarctica','AQ'),(11,'Argentina','AR'),(12,'American Samoa','AS'),(13,'Austria','AT'),(14,'Australia','AU'),(15,'Aruba','AW'),(16,'Åland Islands','AX'),(17,'Azerbaijan','AZ'),(18,'Bosnia and Herzegovina','BA'),(19,'Barbados','BB'),(20,'Bangladesh','BD'),(21,'Belgium','BE'),(22,'Burkina Faso','BF'),(23,'Bulgaria','BG'),(24,'Bahrain','BH'),(25,'Burundi','BI'),(26,'Benin','BJ'),(27,'Saint Barthélemy','BL'),(28,'Bermuda','BM'),(29,'Brunei','BN'),(30,'Bolivia','BO'),(31,'Bonaire, Sint Eustatius and Saba','BQ'),(32,'Brazil','BR'),(33,'Bahamas','BS'),(34,'Bhutan','BT'),(35,'Bouvet Island','BV'),(36,'Botswana','BW'),(37,'Belarus','BY'),(38,'Belize','BZ'),(39,'Canada','CA'),(40,'Cocos Islands','CC'),(41,'The Democratic Republic Of Congo','CD'),(42,'Central African Republic','CF'),(43,'Congo','CG'),(44,'Switzerland','CH'),(45,'Côte d\'Ivoire','CI'),(46,'Cook Islands','CK'),(47,'Chile','CL'),(48,'Cameroon','CM'),(49,'China','CN'),(50,'Colombia','CO'),(51,'Costa Rica','CR'),(52,'Cuba','CU'),(53,'Cape Verde','CV'),(54,'Curaçao','CW'),(55,'Christmas Island','CX'),(56,'Cyprus','CY'),(57,'Czech Republic','CZ'),(58,'Germany','DE'),(59,'Djibouti','DJ'),(60,'Denmark','DK'),(61,'Dominica','DM'),(62,'Dominican Republic','DO'),(63,'Algeria','DZ'),(64,'Ecuador','EC'),(65,'Estonia','EE'),(66,'Egypt','EG'),(67,'Western Sahara','EH'),(68,'Eritrea','ER'),(69,'Spain','ES'),(70,'Ethiopia','ET'),(71,'Finland','FI'),(72,'Fiji','FJ'),(73,'Falkland Islands','FK'),(74,'Micronesia','FM'),(75,'Faroe Islands','FO'),(76,'France','FR'),(77,'Gabon','GA'),(78,'United Kingdom','GB'),(79,'Grenada','GD'),(80,'Georgia','GE'),(81,'French Guiana','GF'),(82,'Guernsey','GG'),(83,'Ghana','GH'),(84,'Gibraltar','GI'),(85,'Greenland','GL'),(86,'Gambia','GM'),(87,'Guinea','GN'),(88,'Guadeloupe','GP'),(89,'Equatorial Guinea','GQ'),(90,'Greece','GR'),(91,'South Georgia And The South Sandwich Islands','GS'),(92,'Guatemala','GT'),(93,'Guam','GU'),(94,'Guinea-Bissau','GW'),(95,'Guyana','GY'),(96,'Hong Kong','HK'),(97,'Heard Island And McDonald Islands','HM'),(98,'Honduras','HN'),(99,'Croatia','HR'),(100,'Haiti','HT'),(101,'Hungary','HU'),(102,'Indonesia','ID'),(103,'Ireland','IE'),(104,'Israel','IL'),(105,'Isle Of Man','IM'),(106,'India','IN'),(107,'British Indian Ocean Territory','IO'),(108,'Iraq','IQ'),(109,'Iran','IR'),(110,'Iceland','IS'),(111,'Italy','IT'),(112,'Jersey','JE'),(113,'Jamaica','JM'),(114,'Jordan','JO'),(115,'Japan','JP'),(116,'Kenya','KE'),(117,'Kyrgyzstan','KG'),(118,'Cambodia','KH'),(119,'Kiribati','KI'),(120,'Comoros','KM'),(121,'Saint Kitts And Nevis','KN'),(122,'North Korea','KP'),(123,'South Korea','KR'),(124,'Kuwait','KW'),(125,'Cayman Islands','KY'),(126,'Kazakhstan','KZ'),(127,'Laos','LA'),(128,'Lebanon','LB'),(129,'Saint Lucia','LC'),(130,'Liechtenstein','LI'),(131,'Sri Lanka','LK'),(132,'Liberia','LR'),(133,'Lesotho','LS'),(134,'Lithuania','LT'),(135,'Luxembourg','LU'),(136,'Latvia','LV'),(137,'Libya','LY'),(138,'Morocco','MA'),(139,'Monaco','MC'),(140,'Moldova','MD'),(141,'Montenegro','ME'),(142,'Saint Martin','MF'),(143,'Madagascar','MG'),(144,'Marshall Islands','MH'),(145,'Macedonia','MK'),(146,'Mali','ML'),(147,'Myanmar','MM'),(148,'Mongolia','MN'),(149,'Macao','MO'),(150,'Northern Mariana Islands','MP'),(151,'Martinique','MQ'),(152,'Mauritania','MR'),(153,'Montserrat','MS'),(154,'Malta','MT'),(155,'Mauritius','MU'),(156,'Maldives','MV'),(157,'Malawi','MW'),(158,'Mexico','MX'),(159,'Malaysia','MY'),(160,'Mozambique','MZ'),(161,'Namibia','NA'),(162,'New Caledonia','NC'),(163,'Niger','NE'),(164,'Norfolk Island','NF'),(165,'Nigeria','NG'),(166,'Nicaragua','NI'),(167,'Netherlands','NL'),(168,'Norway','NO'),(169,'Nepal','NP'),(170,'Nauru','NR'),(171,'Niue','NU'),(172,'New Zealand','NZ'),(173,'Oman','OM'),(174,'Panama','PA'),(175,'Peru','PE'),(176,'French Polynesia','PF'),(177,'Papua New Guinea','PG'),(178,'Philippines','PH'),(179,'Pakistan','PK'),(180,'Poland','PL'),(181,'Saint Pierre And Miquelon','PM'),(182,'Pitcairn','PN'),(183,'Puerto Rico','PR'),(184,'Palestine','PS'),(185,'Portugal','PT'),(186,'Palau','PW'),(187,'Paraguay','PY'),(188,'Qatar','QA'),(189,'Reunion','RE'),(190,'Romania','RO'),(191,'Serbia','RS'),(192,'Russia','RU'),(193,'Rwanda','RW'),(194,'Saudi Arabia','SA'),(195,'Solomon Islands','SB'),(196,'Seychelles','SC'),(197,'Sudan','SD'),(198,'Sweden','SE'),(199,'Singapore','SG'),(200,'Saint Helena','SH'),(201,'Slovenia','SI'),(202,'Svalbard And Jan Mayen','SJ'),(203,'Slovakia','SK'),(204,'Sierra Leone','SL'),(205,'San Marino','SM'),(206,'Senegal','SN'),(207,'Somalia','SO'),(208,'Suriname','SR'),(209,'South Sudan','SS'),(210,'Sao Tome And Principe','ST'),(211,'El Salvador','SV'),(212,'Sint Maarten (Dutch part)','SX'),(213,'Syria','SY'),(214,'Swaziland','SZ'),(215,'Turks And Caicos Islands','TC'),(216,'Chad','TD'),(217,'French Southern Territories','TF'),(218,'Togo','TG'),(219,'Thailand','TH'),(220,'Tajikistan','TJ'),(221,'Tokelau','TK'),(222,'Timor-Leste','TL'),(223,'Turkmenistan','TM'),(224,'Tunisia','TN'),(225,'Tonga','TO'),(226,'Turkey','TR'),(227,'Trinidad and Tobago','TT'),(228,'Tuvalu','TV'),(229,'Taiwan','TW'),(230,'Tanzania','TZ'),(231,'Ukraine','UA'),(232,'Uganda','UG'),(233,'United States Minor Outlying Islands','UM'),(234,'United States','US'),(235,'Uruguay','UY'),(236,'Uzbekistan','UZ'),(237,'Vatican','VA'),(238,'Saint Vincent And The Grenadines','VC'),(239,'Venezuela','VE'),(240,'British Virgin Islands','VG'),(241,'U.S. Virgin Islands','VI'),(242,'Vietnam','VN'),(243,'Vanuatu','VU'),(244,'Wallis And Futuna','WF'),(245,'Samoa','WS'),(246,'Yemen','YE'),(247,'Mayotte','YT'),(248,'South Africa','ZA'),(249,'Zambia','ZM'),(250,'Zimbabwe','ZW');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,'Ha Noi',242),(2,'Da Nang',242),(3,'New York',234),(4,'California',234),(5,'Ho Chi Minh City',242),(6,'Hai Phong',242),(7,'Bac Giang',242),(8,'Son La',242),(9,'Quang Ninh',242),(10,'Nghe An',242),(12,'Utah',234),(13,'Washington',234),(14,'Florida',234),(15,'Illinois',234),(16,'Texas',234),(18,'Colorado',234),(19,'Alaska',234),(20,'Can Tho',242),(22,'Binh Duong',242),(23,'Thanh Hoa',242),(24,'Andhra Pradesh',106),(25,'Arunachal Pradesh',106),(26,'Assam',106),(27,'Bihar',106),(28,'Haryana',106),(29,'Himacha Pradesh',106),(30,'Karnataka',106),(31,'Maharashtra',106),(32,'Meghalaya',106),(33,'Punjab',106),(34,'Tamil Nadu',106),(35,'Telangana',106),(36,'Uttar Pradesh',106),(37,'West Bengal',106),(38,'Bac Ninh',242),(40,'Ha Tinh',242),(41,'Hue',242),(42,'Binh Dinh',242),(43,'An Giang',242),(44,'Blackpool',78),(45,'London',78),(46,'Liverpool',78),(47,'Manchester',78),(48,'Newcastle',78),(49,'Bristol',78),(50,'Cambridge',78),(51,'Lang Son',242),(52,'Delhi',106),(53,'Hawaii',234),(54,'Georgia',234),(55,'Pennsylvania',234),(56,'Virginia',234),(57,'Arizona',234),(59,'Kerala',106),(60,'Rajasthan',106),(61,'Odisha',106),(62,'Mizoram',106),(63,'Sikkim',106),(64,'Manipur',106),(65,'Nagaland',106),(66,'Tripura',106),(67,'Jharkhand',106),(68,'Uttarakhand',106),(69,'Goa',106),(70,'Madhya Pradesh',106),(71,'Gujarat',106),(72,'Massachusetts',234),(73,'Indiana',234),(74,'Michigan',234),(75,'Ohio',234),(76,'New Jersey',234),(77,'Minnesota',234),(78,'North Carolina',234),(79,'Oregon',234),(80,'Maryland',234),(81,'Tennessee',234),(82,'Montana',234),(83,'Maine',234),(84,'Alabama',234),(85,'Wisconsin',234),(86,'Louisiana',234),(87,'Connecticut',234),(88,'Missouri',234),(89,'South Carolina',234),(90,'Mississippi',234),(91,'New Mexico',234),(92,'Nevada',234),(93,'Kentucky',234),(94,'Arkansas',234),(95,'Wyoming',234),(96,'Kansas',234),(97,'Delaware',234),(98,'Iowa',234),(99,'Idaho',234),(100,'Nebraska',234),(101,'Rhode Island',234),(102,'Vermont',234),(103,'South Dakota',234),(104,'New Hamsphire',234),(105,'West Virginia',234),(106,'North Dakota',234),(107,'Oklahoma',234),(109,'Nam Dinh',242),(110,'Khanh Hoa',242),(112,'Tay Ninh',242),(113,'Kien Giang',242),(114,'Lai Chau',242),(115,'Lao Cai',242),(116,'Ha Giang',242),(117,'Hoa Binh',242),(118,'Thai Binh',242),(119,'Dong Nai',242),(120,'Soc Trang',242),(121,'Bac Lieu',242),(123,'Ca Mau',242),(124,'Quang Binh',242),(125,'Quang Tri',242),(126,'Quang Nam',242),(127,'Quang Ngai',242),(128,'Gia Lai',242),(129,'Vinh Phuc',242),(130,'Lam Dong',242),(131,'Dak Lak',242),(132,'Phu Yen',242),(133,'Binh Phuoc',242),(134,'Dak Nong',242),(135,'Dong Thap',242),(136,'Hau Giang',242),(137,'Ba Ria Vung Tau',242),(138,'Binh Thuan',242),(139,'Hai Duong',242),(140,'Hung Yen',242),(141,'Thai Nguyen',242),(142,'Long An',242),(143,'Ben Tre',242),(144,'Bac Kan',242),(145,'Ninh Binh',242),(146,'Kon Tum',242),(147,'Tra Vinh',242),(148,'Yen Bai',242),(149,'Tuyen Quang',242),(150,'Phu Tho',242),(151,'Vinh Long',242),(152,'New South Wales',14),(153,'Queensland',14),(154,'Tasmania',14),(155,'Western Australia',14),(156,'Victoria',14),(157,'South Australia',14),(158,'Ontario',39),(159,'Quebec',39),(160,'Nova Scotia',39),(161,'New Brunswick',39),(162,'Manitoba',39),(163,'British Columbia',39),(164,'Prince Edward Island',39),(165,'Saskatchewan',39),(166,'Alberta',39),(167,'Newfoundland and Labrador',39),(168,'Grand Est',76),(169,'Hauts-de-France',76),(170,'Normandy',76),(171,'Nouvelle-Aquitaine',76),(172,'Occitanie',76),(173,'Brittany',76),(174,'Centre-Val de Loire',76),(175,'Corsica',76),(176,'French Guiana',76),(177,'Guadeloupe',76),(178,'Île-de-France',76),(179,'Martinique',76),(180,'Mayotte',76),(181,'Pays de la Loire',76),(182,'Provence-Alpes-Côte d\'Azur',76),(183,'Réunion',76),(184,'Aichi',115),(185,'Akita',115),(186,'Chiba',115),(187,'Ehime',115),(188,'Fukui',115),(189,'Fukuoka',115),(190,'Fukushima',115),(191,'Gunma',115),(192,'Kobe',115),(193,'Hiroshima',115),(194,'Hokkaido',115),(195,'Ishikawa',115),(196,'Kagawa',115),(197,'Kochi',115),(198,'Kyoto',115),(199,'Nagasaki',115),(200,'Okinawa',115),(201,'Osaka',115),(202,'Saga',115),(203,'Tokushima',115),(204,'Tokyo',115),(205,'Yamagata',115),(206,'Yamaguchi',115),(207,'Yamanashi',115),(208,'Greater Poland',180),(209,'Kuyavia-Pomerania',180),(210,'Lesser Poland',180),(211,'Łódź',180),(212,'Lower Silesia',180),(213,'Lublin',180),(214,'Lubusz',180),(215,'Masovia',180),(216,'Opole',180),(217,'Podlaskie',180),(218,'Pomerania',180),(219,'Silesia',180),(220,'Subcarpathia',180),(221,'Holy Cross Province',180),(222,'Warmia-Masuria',180),(223,'West Pomerania',180),(224,'Baden-Württemberg',58),(225,'Bavaria',58),(226,'Berlin',58),(227,'Brandenburg',58),(228,'Bremen',58),(229,'Hamburg',58),(230,'Hesse',58),(231,'Lower Saxony',58),(232,'Mecklenburg-Vorpommern',58),(233,'North Rhine- Westphalia',58),(234,'Rhineland-Palatinate',58),(235,'Saarland',58),(236,'Saxony',58),(237,'Saxony-Anhalt',58),(238,'Schleswig-Holstein',58),(239,'Thuringia',58),(240,'Acre',32),(241,'Alagoas',32),(242,'Amapá',32),(243,'Amazonas',32),(244,'Bahia',32),(245,'Ceará',32),(246,'Distrito Federal',32),(247,'Espírito Santo',32),(248,'Goiás',32),(249,'Maranhão',32),(250,'Mato Grosso',32),(251,'Minas Gerais',32),(252,'Pará',32),(253,'Paraíba',32),(254,'Paraná',32),(255,'Pernambuco',32),(256,'Piauí',32),(257,'Rio de Janeiro',32),(258,'Rio Grande do Norte',32),(259,'Rio Grande do Sul',32),(260,'Rondônia',32),(261,'Roraima',32),(262,'Santa Catarina',32),(263,'São Paulo',32),(264,'Sergipe',32),(265,'Tocantins',32),(266,'Adana',226),(267,'Adıyaman',226),(268,'Ankara',226),(269,'Balıkesir',226),(270,'Bilecik',226),(271,'Bingöl',226),(272,'Bursa',226),(273,'Çanakkale',226),(274,'Çankırı',226),(275,'Çorum',226),(276,'Denizli',226),(277,'Diyarbakır',226),(278,'Edirne',226),(279,'Erzurum',226),(280,'Gaziantep',226),(281,'Giresun',226),(282,'Hakkâri',226),(283,'Hatay',226),(284,'Isparta',226),(285,'Istanbul',226),(286,'Konya',226),(287,'Malatya',226),(288,'Manisa',226),(289,'Iğdır',226),(290,'Karaman',226),(291,'Osmaniye',226),(292,'Tokat',226),(293,'Sivas',226),(294,'Sinop',226),(295,'Siirt',226),(296,'Batman',226),(297,'Kilis',226),(298,'Rize',226),(299,'Ordu',226),(300,'Tunceli',226),(301,'Yozgat',226),(302,'Van',226),(303,'Bayburt',226),(304,'Yalova',226),(305,'Samsun',226),(306,'Sakarya',226);
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-29 12:30:41




INSERT INTO setting.currencies(id, name, symbol, code, language, country) VALUES 
('ef01f342-ef96-45da-a573-b104ff408836', 'United States Dollar', '$', 'USD', 'en', 'US'),
('a5e87d86-07e1-49f8-ace3-927a286cae59', 'British Pound', '£', 'GBP', 'en', 'GB'),
('628fedc9-6327-4d01-8fe1-6b167c6450fb', 'Japanese Yen', '¥', 'JPY', 'ja', 'JP'),
('92351c80-4a3a-4fbb-bdf5-b4bddafdb999', 'Euro', '€', 'EUR', 'fr', 'FR'),
('7870b41c-3314-46cd-b376-bdb54dccea97', 'Russian Ruble', '₽', 'RUB', 'ru', 'RU'),
('fd0a113b-40f4-4a72-a4fa-9f7a844219ab', 'South Korean Won', '₩', 'KRW', 'ko', 'KR'),
('0c40b7b1-871d-4581-80fe-d38f7c007fa6', 'Chinese Yuan', '¥', 'CNY', 'zh-CN', 'CN'),
('c64bf6a3-f896-4a9f-98de-c72cb10d44fe', 'Brazilian Real', 'R$', 'BRL', 'pt-BR', 'BR'),
('97e87fb1-a501-47f3-86e6-a49ab773cdd6', 'Australian Dollar', '$', 'AUD', 'en-AU', 'AU'),
('dfb0af94-f91b-4c7f-bf36-70bae0c9aae9', 'Canadian Dollar', '$', 'CAD', 'en-CA', 'CA'),
('31474290-0522-4e92-9aed-ba20220c0965', 'Vietnamese đồng', 'đ', 'VND', 'vi', 'VN'),
('1cf3f54c-ae7f-4675-8330-0dc78baba85a', 'Indian Rupee', '₹', 'INR', 'hi', 'IN');


INSERT INTO setting.settings(id, name, symbol, code) VALUES 




INSERT INTO setting.settings(
	id, key, value, category)
	VALUES 
('f16e9560-cebc-44e0-bfe7-b565c81d7532', 'COPYRIGHT', 'Copyright (C) 2024 by Pilseong Heo', 'GENERAL'),
('54e2a46c-bf0d-4d3b-bb6c-8adcf9a833ad', 'CURRENCY_ID', 'ef01f342-ef96-45da-a573-b104ff408836', 'CURRENCY'),
('c670de1a-7268-4220-b02a-6a80a3209321', 'CURRENCY_SYMBOL', '$', 'CURRENCY'),
('1859b17b-8da4-4296-a2a6-f35e0a374994', 'CURRENCY_SYMBOL_POSITION', 'before', 'CURRENCY'),
('e162e281-8296-4195-a18e-5839fa252339', 'DECIMAL_DIGITS', '2', 'CURRENCY'),
('0b018f74-15b9-4eca-8de7-80168ce1518d', 'DECIMAL_POINT_TYPE', 'POINT', 'CURRENCY'),
('641122f5-226b-45fb-a853-5c6089f94bb1', 'SITE_LOGO', 'hshop.jpg', 'GENERAL'),
('0b950efa-fec7-44e1-b757-9440ee6894bf', 'SITE_NAME', 'HShop', 'GENERAL'),
('7c4cbad7-ae5a-479f-9d75-c3d68e5ccdb8', 'THOUSANDS_POINT_TYPE', 'COMMA', 'CURRENCY');




INSERT INTO setting.settings(
	id, key, value, category)
	VALUES 
('f16e9560-cebc-44e0-bfe7-b565c81d7532', 'COPYRIGHT', 'Copyright (C) 2024 by Pilseong Heo', 'GENERAL'),
('54e2a46c-bf0d-4d3b-bb6c-8adcf9a833ad', 'CURRENCY', 'ko,KR', 'GENERAL'),
('641122f5-226b-45fb-a853-5c6089f94bb1', 'SITE_LOGO', 'hshop.jpg', 'GENERAL'),
('0b950efa-fec7-44e1-b757-9440ee6894bf', 'SITE_NAME', 'HShop', 'GENERAL');







