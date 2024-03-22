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
-- Dumping data for table `brands`
--



INSERT INTO catalog.brands(id, name, logo, created_at) VALUES 
1('b36dce58-ca60-4ce3-9df7-38c42033a39b','Canon','Canon.png', now()),
2('7511103f-786b-4c84-b4ba-9e4b02973053','Fujifilm','Fujifilm.png', now()),
3('7a0dd3c7-0fc1-4b0e-9f93-c2629ea67d79','Sony','Sony.png', now()),
4('b5406a43-f0e7-425b-98d9-a183ee10edb0','HP','HP.png', now()),
5('95037960-20bd-4bd1-ab48-8c75fb26054a','SanDisk','SanDisk.png', now()),
6('06885dab-5693-46d0-a3b2-49047f37d648','Western Digital','Western Digital.png', now()),
7('51834d5e-bd49-4540-bfdf-45293fe4aeaa','Panasonic','Panasonic.png', now()),
8('a22b2965-cb5f-4f64-bd91-72a5da651c6b','Pelican','Pelican.png', now()),
9('9de64712-9c74-4ebc-8ec2-2d29785eb430','Apple','Apple.png', now()),
10('14fc2bac-4091-4322-9692-2e8a23acd0a5','Samsung','Samsung.png', now()),
11('c1c8de5f-54a9-4a3a-98d3-a93aafce99f0','Olympus','Olympus.png', now()),
12('2731edbd-5eae-411e-82f4-4db5a6de1123','CADeN','Caden.png', now()),
13('8976c325-b769-4ea9-8ee0-91b979b56cf3','AmazonBasics','amazonbasics.png', now()),
14('49a44004-9707-445b-a722-962dee18d51f','Nikon','Nikon.png', now()),
15('d6c1ca3b-3035-4af1-a85d-af9f2fdd5fe8','Neewer','Neewer.png', now()),
16('eb7b15df-dce9-4ba5-a1e3-358cfec4f6c1','Sigma','Sigma.png', now()),
17('f42f7f23-afaa-42c2-9df1-f7356982773e','Bosch','Bosch.png', now()),
18('12b6d6a2-6232-442f-bee4-35d64baa2bac','Joby','Joby.png', now()),
19('e5fae9cc-03d5-4c3a-9caa-d1a603f1fad9','GoPro','GoPro.png', now()),
20('55a19c9c-fe8d-40a3-b148-d567917e98f7','Manfrotto','Manfrotto.png', now()),
21('b2b7685b-ac88-4acc-bad7-19c1ad4e5b52','Google','Google.png', now()),
22('c50aa01e-e0ff-4a2e-b9b3-cbee77a88af4','LG','LG.png', now()),
23('cbdd7d3f-21e1-4e02-a3c0-261ba33933b5','Motorola','Motorola.png', now()),
24('a805c573-855b-465d-ad1f-5cddebf190f4','Pantech','Pantech.png', now()),
25('368dbebf-be0c-4280-933f-41a8495d0ede','Huawei','Huawei.png', now()),
26('30f51764-76ba-464b-9096-59c66eed614f','Xiaomi','Xiaomi.png', now()),
27('2ed6b719-ae99-4e33-9166-ce45782707e2','HOVAMP','Hovamp.png', now()),
28('aa77605d-1840-4c61-a54e-e16c4c4bbdd8','Aioneus','Aioneus.png', now()),
29('846cf14c-b9ea-4d1d-b4af-891cd8c22573','XIAE','XIAE.png', now()),
30('70a94d15-efdd-4e09-be42-0fec3e9e6cdd','Everyworth','Everyworth.png', now()),
31('44de8e18-d7f6-430e-9ac5-776c22f6fb13','Lexar','Lexar.png', now()),
32('d1f3c1bb-60a6-4929-83aa-cc1492614899','Nulaxy','Nulaxy.png', now()),
33('3b9a89e5-dec9-478e-9427-996b0d85bd21','Fitfort','Fitfort.png', now()),
34('ca8ac692-ac58-40b4-b907-82bad71abc31','PopSockets','PopSocket.png', now()),
35('dc933587-d6f0-4d8d-94dd-e1318e28061c','SHAWE','SHAWE.png', now()),
36('b3198223-bdd9-4507-b965-9ffa37545a80','Lenovo','Lenovo.png', now()),
37('23fa1020-7a30-4625-8e52-832486547552','Acer','Acer.png', now()),
38('28c6b099-4bf3-4596-8da1-d07f8256aa24','Dell','Dell.png', now()),
39('49813f9c-3a46-4b5b-8848-85ee02979d8c','Intel','Intel.png', now()),
40('06582935-23bb-40d8-85e3-b2d91a659f9c','ASUS','ASUS.png', now()),
41('951ac191-827d-426e-b5a4-2f5d74c9e908','Microsoft','Microsoft.png', now()),
42('701fb9a4-9f25-464b-9f29-2d694ba28de0','DragonTouch','DragonTouch.png', now()),
43('c393e80b-1f83-4651-858a-4c5ab8846e97','AMD','AMD.png', now()),
44('d91c3219-90fa-472d-9a3a-dd86319442df','XFX','XFX.png', now()),
45('4cfa3f0f-68d4-4d43-9605-2e1eefbf15b3','MSI','MSI.png', now()),
46('c2a79898-a36d-4d03-b2e8-a958613ff717','Seagate','Seagate.png', now()),
47('5bc91f77-dd62-4a7f-a288-1f2e7c945a18','Cosair','Corsair.png', now()),
48('7bf6e484-4e61-4195-83c2-eb1a559c1f4b','Thermaltake','Thermaltake.png', now()),
49('a6c52b80-9b06-4037-802c-23cb2850a271','Kingston','Kingston.png', now()),
50('1cf4d62e-1261-45a7-993f-5d7ba1b5af96','Creative','Creative.png', now()),
51('5faa5f46-a051-4718-a4cf-d9cf1397028a','Crucial','Crucial.png', now()),
52('71aa6979-d21c-4ea4-a067-7a4f721f0d2d','HyperX','HyperX.png', now()),
53('fddccc6b-d902-4137-ad0f-f4a34f8b481e','Gigabyte','Gigabyte.png', now()),
54('799ed6b9-4242-4cf9-a326-5b267c395199','TP-Link','TP-Link.png', now());
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `brands_categories`
--


INSERT INTO catalog.brands_categories (brand_id, category_id) VALUES 
('06885dab-5693-46d0-a3b2-49047f37d648','72171605-c122-4300-b76a-1282b5974c78'),
('49a44004-9707-445b-a722-962dee18d51f','f2e8f432-4628-4627-9ac1-ac6c6b5ceed5'),
('b36dce58-ca60-4ce3-9df7-38c42033a39b','739690af-e808-4dd8-a07d-b110703f3789'),
('7511103f-786b-4c84-b4ba-9e4b02973053','739690af-e808-4dd8-a07d-b110703f3789'),
('7a0dd3c7-0fc1-4b0e-9f93-c2629ea67d79','739690af-e808-4dd8-a07d-b110703f3789'),
('b5406a43-f0e7-425b-98d9-a183ee10edb0','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('b5406a43-f0e7-425b-98d9-a183ee10edb0','294a861b-8be4-4445-8a7f-013082552762'),
('95037960-20bd-4bd1-ab48-8c75fb26054a','f81f8da4-3dd0-4c6b-9ab0-ea9d2c683652'),
('95037960-20bd-4bd1-ab48-8c75fb26054a','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('95037960-20bd-4bd1-ab48-8c75fb26054a','648aa3ef-e303-4a65-8bfa-89707c85a674'),
('06885dab-5693-46d0-a3b2-49047f37d648','f81f8da4-3dd0-4c6b-9ab0-ea9d2c683652'),
('06885dab-5693-46d0-a3b2-49047f37d648','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('06885dab-5693-46d0-a3b2-49047f37d648','648aa3ef-e303-4a65-8bfa-89707c85a674'),
('b36dce58-ca60-4ce3-9df7-38c42033a39b','f2e8f432-4628-4627-9ac1-ac6c6b5ceed5'),
('7a0dd3c7-0fc1-4b0e-9f93-c2629ea67d79','f2e8f432-4628-4627-9ac1-ac6c6b5ceed5'),
('51834d5e-bd49-4540-bfdf-45293fe4aeaa','739690af-e808-4dd8-a07d-b110703f3789'),
('51834d5e-bd49-4540-bfdf-45293fe4aeaa','f2e8f432-4628-4627-9ac1-ac6c6b5ceed5'),
('a22b2965-cb5f-4f64-bd91-72a5da651c6b','739690af-e808-4dd8-a07d-b110703f3789'),
('a22b2965-cb5f-4f64-bd91-72a5da651c6b','00ce881a-e213-4c23-b445-fb66a90fb155'),
('9de64712-9c74-4ebc-8ec2-2d29785eb430','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('9de64712-9c74-4ebc-8ec2-2d29785eb430','294a861b-8be4-4445-8a7f-013082552762'),
('9de64712-9c74-4ebc-8ec2-2d29785eb430','683052ec-6395-490c-be9a-55b2ac6a8965'),
('9de64712-9c74-4ebc-8ec2-2d29785eb430','f5c8aaa2-8e30-491f-b707-4aedba6186c7'),
('9de64712-9c74-4ebc-8ec2-2d29785eb430','1c169bc7-3baf-47af-ad41-1a925d74b0fa'),
('9de64712-9c74-4ebc-8ec2-2d29785eb430','fdb36023-6407-4cd6-a458-6ca5672edc2c'),
('95037960-20bd-4bd1-ab48-8c75fb26054a','b2eb5981-e067-482a-8596-76f55d80b304'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','f81f8da4-3dd0-4c6b-9ab0-ea9d2c683652'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','d0707957-b51d-48f9-918b-b1602991f0dd'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','648aa3ef-e303-4a65-8bfa-89707c85a674'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','294a861b-8be4-4445-8a7f-013082552762'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','683052ec-6395-490c-be9a-55b2ac6a8965'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','1c169bc7-3baf-47af-ad41-1a925d74b0fa'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','fdb36023-6407-4cd6-a458-6ca5672edc2c'),
('c1c8de5f-54a9-4a3a-98d3-a93aafce99f0','f2e8f432-4628-4627-9ac1-ac6c6b5ceed5'),
('2731edbd-5eae-411e-82f4-4db5a6de1123','00ce881a-e213-4c23-b445-fb66a90fb155'),
('8976c325-b769-4ea9-8ee0-91b979b56cf3','00ce881a-e213-4c23-b445-fb66a90fb155'),
('49a44004-9707-445b-a722-962dee18d51f','1baa41b7-67d2-4157-9846-bffa92e4c6d9'),
('b36dce58-ca60-4ce3-9df7-38c42033a39b','1baa41b7-67d2-4157-9846-bffa92e4c6d9'),
('7a0dd3c7-0fc1-4b0e-9f93-c2629ea67d79','1baa41b7-67d2-4157-9846-bffa92e4c6d9'),
('d6c1ca3b-3035-4af1-a85d-af9f2fdd5fe8','1baa41b7-67d2-4157-9846-bffa92e4c6d9'),
('b36dce58-ca60-4ce3-9df7-38c42033a39b','1f86d572-90f4-4c6a-923e-19cf32ddfa51'),
('49a44004-9707-445b-a722-962dee18d51f','1f86d572-90f4-4c6a-923e-19cf32ddfa51'),
('eb7b15df-dce9-4ba5-a1e3-358cfec4f6c1','1f86d572-90f4-4c6a-923e-19cf32ddfa51'),
('f42f7f23-afaa-42c2-9df1-f7356982773e','84d3faa9-b78b-471b-a0cc-f857aa0f858c'),
('12b6d6a2-6232-442f-bee4-35d64baa2bac','84d3faa9-b78b-471b-a0cc-f857aa0f858c'),
('e5fae9cc-03d5-4c3a-9caa-d1a603f1fad9','84d3faa9-b78b-471b-a0cc-f857aa0f858c'),
('55a19c9c-fe8d-40a3-b148-d567917e98f7','84d3faa9-b78b-471b-a0cc-f857aa0f858c'),
('b2b7685b-ac88-4acc-bad7-19c1ad4e5b52','1c169bc7-3baf-47af-ad41-1a925d74b0fa'),
('c50aa01e-e0ff-4a2e-b9b3-cbee77a88af4','1c169bc7-3baf-47af-ad41-1a925d74b0fa'),
('cbdd7d3f-21e1-4e02-a3c0-261ba33933b5','1c169bc7-3baf-47af-ad41-1a925d74b0fa'),
('a805c573-855b-465d-ad1f-5cddebf190f4','1c169bc7-3baf-47af-ad41-1a925d74b0fa'),
('b2b7685b-ac88-4acc-bad7-19c1ad4e5b52','fdb36023-6407-4cd6-a458-6ca5672edc2c'),
('cbdd7d3f-21e1-4e02-a3c0-261ba33933b5','fdb36023-6407-4cd6-a458-6ca5672edc2c'),
('368dbebf-be0c-4280-933f-41a8495d0ede','fdb36023-6407-4cd6-a458-6ca5672edc2c'),
('30f51764-76ba-464b-9096-59c66eed614f','fdb36023-6407-4cd6-a458-6ca5672edc2c'),
('2ed6b719-ae99-4e33-9166-ce45782707e2','61c62506-361c-493c-adf9-743c2b119800'),
('aa77605d-1840-4c61-a54e-e16c4c4bbdd8','61c62506-361c-493c-adf9-743c2b119800'),
('846cf14c-b9ea-4d1d-b4af-891cd8c22573','61c62506-361c-493c-adf9-743c2b119800'),
('70a94d15-efdd-4e09-be42-0fec3e9e6cdd','61c62506-361c-493c-adf9-743c2b119800'),
('14fc2bac-4091-4322-9692-2e8a23acd0a5','07719f1c-b732-4121-b515-74362e06895b'),
('95037960-20bd-4bd1-ab48-8c75fb26054a','07719f1c-b732-4121-b515-74362e06895b'),
('44de8e18-d7f6-430e-9ac5-776c22f6fb13','07719f1c-b732-4121-b515-74362e06895b'),
('55a19c9c-fe8d-40a3-b148-d567917e98f7','af08f20a-9359-4a47-ad38-b0f7a1625776'),
('d1f3c1bb-60a6-4929-83aa-cc1492614899','af08f20a-9359-4a47-ad38-b0f7a1625776'),
('3b9a89e5-dec9-478e-9427-996b0d85bd21','af08f20a-9359-4a47-ad38-b0f7a1625776'),
('ca8ac692-ac58-40b4-b907-82bad71abc31','af08f20a-9359-4a47-ad38-b0f7a1625776'),
('dc933587-d6f0-4d8d-94dd-e1318e28061c','af08f20a-9359-4a47-ad38-b0f7a1625776'),
('b3198223-bdd9-4507-b965-9ffa37545a80','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('b3198223-bdd9-4507-b965-9ffa37545a80','294a861b-8be4-4445-8a7f-013082552762'),
('b3198223-bdd9-4507-b965-9ffa37545a80','683052ec-6395-490c-be9a-55b2ac6a8965'),
('23fa1020-7a30-4625-8e52-832486547552','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('23fa1020-7a30-4625-8e52-832486547552','294a861b-8be4-4445-8a7f-013082552762'),
('23fa1020-7a30-4625-8e52-832486547552','683052ec-6395-490c-be9a-55b2ac6a8965'),
('28c6b099-4bf3-4596-8da1-d07f8256aa24','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('28c6b099-4bf3-4596-8da1-d07f8256aa24','294a861b-8be4-4445-8a7f-013082552762'),
('28c6b099-4bf3-4596-8da1-d07f8256aa24','683052ec-6395-490c-be9a-55b2ac6a8965'),
('49813f9c-3a46-4b5b-8848-85ee02979d8c','b09ab402-1538-498a-b7bc-b33975852f07'),
('49813f9c-3a46-4b5b-8848-85ee02979d8c','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','c4d02780-bd8b-4630-9088-c9c4ac1060a6'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','dc1c3d19-54bf-42e4-a29b-0d8ef8401391'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','294a861b-8be4-4445-8a7f-013082552762'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','683052ec-6395-490c-be9a-55b2ac6a8965'),
('951ac191-827d-426e-b5a4-2f5d74c9e908','683052ec-6395-490c-be9a-55b2ac6a8965'),
('701fb9a4-9f25-464b-9f29-2d694ba28de0','683052ec-6395-490c-be9a-55b2ac6a8965'),
('c393e80b-1f83-4651-858a-4c5ab8846e97','b09ab402-1538-498a-b7bc-b33975852f07'),
('c393e80b-1f83-4651-858a-4c5ab8846e97','ff067de6-5875-406b-a355-a7a46fd01516'),
('d91c3219-90fa-472d-9a3a-dd86319442df','ff067de6-5875-406b-a355-a7a46fd01516'),
('4cfa3f0f-68d4-4d43-9605-2e1eefbf15b3','ff067de6-5875-406b-a355-a7a46fd01516'),
('4cfa3f0f-68d4-4d43-9605-2e1eefbf15b3','c4d02780-bd8b-4630-9088-c9c4ac1060a6'),
('c2a79898-a36d-4d03-b2e8-a958613ff717','f81f8da4-3dd0-4c6b-9ab0-ea9d2c683652'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','d0707957-b51d-48f9-918b-b1602991f0dd'),
('c50aa01e-e0ff-4a2e-b9b3-cbee77a88af4','d0707957-b51d-48f9-918b-b1602991f0dd'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','b0c67b40-28ae-49fe-81f3-1d14a32db16e'),
('5bc91f77-dd62-4a7f-a288-1f2e7c945a18','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('5bc91f77-dd62-4a7f-a288-1f2e7c945a18','b0c67b40-28ae-49fe-81f3-1d14a32db16e'),
('7bf6e484-4e61-4195-83c2-eb1a559c1f4b','b0c67b40-28ae-49fe-81f3-1d14a32db16e'),
('a6c52b80-9b06-4037-802c-23cb2850a271','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('a6c52b80-9b06-4037-802c-23cb2850a271','648aa3ef-e303-4a65-8bfa-89707c85a674'),
('06582935-23bb-40d8-85e3-b2d91a659f9c','c0e80d29-a884-481a-8659-f14fd3a81bb5'),
('1cf4d62e-1261-45a7-993f-5d7ba1b5af96','c0e80d29-a884-481a-8659-f14fd3a81bb5'),
('5faa5f46-a051-4718-a4cf-d9cf1397028a','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('71aa6979-d21c-4ea4-a067-7a4f721f0d2d','2ff3ddd3-121a-4110-ac1a-cbf198c478ff'),
('fddccc6b-d902-4137-ad0f-f4a34f8b481e','c4d02780-bd8b-4630-9088-c9c4ac1060a6'),
('799ed6b9-4242-4cf9-a326-5b267c395199','b2eb5981-e067-482a-8596-76f55d80b304');
/*!40000 ALTER TABLE `brands_categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-24 16:35:25
