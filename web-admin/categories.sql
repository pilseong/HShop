-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: shopmedb
-- ------------------------------------------------------
-- Server version	8.0.15


 SET NAMES utf8 ;


--
-- Dumping data for table `categories`
--

INSERT INTO catalog.categories (id, name, alias, image, status, parent_id, created_at) VALUES 
1('72171605-c122-4300-b76a-1282b5974c78','Electronics','electronics','electronics.png','ACTIVE',NULL, now()),
2('739690af-e808-4dd8-a07d-b110703f3789','Camera & Photo','camera','camera.jpg','ACTIVE','72171605-c122-4300-b76a-1282b5974c78', now()),
3('a850dfdc-2d40-45bf-9813-82419f4f6e3e','Computers','computers','computers.png','ACTIVE',NULL, now()),
4('f5c8aaa2-8e30-491f-b707-4aedba6186c7','Cell Phones & Accessories','cellphones','cellphones.png','ACTIVE','72171605-c122-4300-b76a-1282b5974c78', now()),
5('dc1c3d19-54bf-42e4-a29b-0d8ef8401391','Desktops','desktop_computers','desktop computers.png','ACTIVE','a850dfdc-2d40-45bf-9813-82419f4f6e3e', now()),
6('294a861b-8be4-4445-8a7f-013082552762','Laptops','laptop_computers','laptop computers.png','ACTIVE','a850dfdc-2d40-45bf-9813-82419f4f6e3e', now()),
7('683052ec-6395-490c-be9a-55b2ac6a8965','Tablets','tablet_computers','tablets.png','ACTIVE','a850dfdc-2d40-45bf-9813-82419f4f6e3e', now()),
8('670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2','Computer Components','computer_components','computer components.png','ACTIVE','a850dfdc-2d40-45bf-9813-82419f4f6e3e', now()),
9('00ce881a-e213-4c23-b445-fb66a90fb155','Bags & Cases','camera_bags_cases','bags_cases.png','ACTIVE','739690af-e808-4dd8-a07d-b110703f3789', now()),
10('f2e8f432-4628-4627-9ac1-ac6c6b5ceed5','Digital Cameras','digital_cameras','digital cameras.png','ACTIVE','739690af-e808-4dd8-a07d-b110703f3789', now()),
11('1baa41b7-67d2-4157-9846-bffa92e4c6d9','Flashes','camera_flashes','flashes.png','ACTIVE','739690af-e808-4dd8-a07d-b110703f3789', now()),
12('1f86d572-90f4-4c6a-923e-19cf32ddfa51','Lenses','camera_lenses','lenses.png','ACTIVE','739690af-e808-4dd8-a07d-b110703f3789', now()),
13('84d3faa9-b78b-471b-a0cc-f857aa0f858c','Tripods & Monopods','camera_tripods_monopods','tripods_monopods.png','ACTIVE','739690af-e808-4dd8-a07d-b110703f3789', now()),
14('1c169bc7-3baf-47af-ad41-1a925d74b0fa','Carrier Cell Phones','carrier_cellphones','carrier cellphones.png','ACTIVE','f5c8aaa2-8e30-491f-b707-4aedba6186c7', now()),
15('fdb36023-6407-4cd6-a458-6ca5672edc2c','Unlocked Cell Phones','unlocked_cellphones','unlocked cellphones.png','ACTIVE','f5c8aaa2-8e30-491f-b707-4aedba6186c7', now()),
16('e4cc021a-ae58-4dc8-bf64-62c7c98cd954','Accessories','cellphone_accessories','cellphone accessories.png','ACTIVE','f5c8aaa2-8e30-491f-b707-4aedba6186c7', now()),
17('61c62506-361c-493c-adf9-743c2b119800','Cables & Adapters','cellphone_cables_adapters','cables and adapters.png','ACTIVE','294a861b-8be4-4445-8a7f-013082552762', now()),
18('07719f1c-b732-4121-b515-74362e06895b','MicroSD Cards','microsd_cards','microsd cards.png','ACTIVE','294a861b-8be4-4445-8a7f-013082552762', now()),
19('af08f20a-9359-4a47-ad38-b0f7a1625776','Stands','cellphone_stands','cellphone_stands.png','ACTIVE','294a861b-8be4-4445-8a7f-013082552762', now()),
20('bf87cc09-541c-4640-9907-9d9c460c3fdd','Cases','cellphone_cases','cellphone cases.png','ACTIVE','294a861b-8be4-4445-8a7f-013082552762', now()),
21('98fff531-6318-41ba-b8e6-33f0d8535085','Headphones','headphones','headphones.png','ACTIVE','294a861b-8be4-4445-8a7f-013082552762', now()),
22('b09ab402-1538-498a-b7bc-b33975852f07','CPU Processors Unit','computer_processors','computer processors.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
23('ff067de6-5875-406b-a355-a7a46fd01516','Graphic Cards','computer_graphic_cards','graphic cards.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
24('f81f8da4-3dd0-4c6b-9ab0-ea9d2c683652','Internal Hard Drives','hard_drive','internal hard drive.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
25('d0707957-b51d-48f9-918b-b1602991f0dd','Internal Optical Drives','computer_optical_drives','internal optical drives.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
26('b0c67b40-28ae-49fe-81f3-1d14a32db16e','Power Supplies','computer_power_supplies','power supplies.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
27('648aa3ef-e303-4a65-8bfa-89707c85a674','Solid State Drives','solid_state_drives','solid state drives.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
28('c0e80d29-a884-481a-8659-f14fd3a81bb5','Sound Cards','computer_sound_cards','sound cards.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
29('2ff3ddd3-121a-4110-ac1a-cbf198c478ff','Memory','computer_memory','computer memory.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
30('c4d02780-bd8b-4630-9088-c9c4ac1060a6','Motherboard','computer_motherboard','motherboards.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now()),
31('b2eb5981-e067-482a-8596-76f55d80b304','Network Cards','computer_network_cards','network cards.png','ACTIVE','670f06c0-f0d3-4c25-b3cf-d4d5a04fc1f2', now());

UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-20  8:23:08
