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
-- Dumping data for table `roles`
--

INSERT INTO users.roles(id, name, description, created_at) VALUES 
('4c3b1ef6-9a19-43a4-bcb1-75bceecc0997','Admin','manage everything', now()),
('b6c86aa4-8b73-4fe5-af24-d55833993c7a','Salesperson','manage product price, customers, shipping, orders and sales report', now()),
('b12ec1c6-64e7-44af-8844-8afe04aa5e8b','Editor','manage categories, brands, products, articles and menus', now()),
('536ba063-3361-43bd-ae3b-9bf635f815e0','Shipper','view products, view orders and update order status', now()),
('cbf5569f-b51a-4eae-9223-d189827a1b9c','Assistant','manage questions and reviews', now());

--
-- Dumping data for table `users`
--


INSERT INTO users(id, email, status, first_name, last_name, password, photo, created_at) VALUES 
('9b93b0f0-7a18-4e8a-9b2e-cb501e7394a3','nam@codejava.net','ACTIVE','Nam','Ha Minh','$2a$10$bDqskP9Z/y6BIZnFLgJ8HuwMYaZXD9w2jVk2pYHXgn1k6N4nckleu','namhm.png', now()),
('d4ca2ccf-97dc-4f91-90cf-da8035c894e4','kanna.allada@gmail.com','ACTIVE','Allada','Pavan','$2a$10$zRa/rmQ8JarpYG2bNKftyelKnsUhsHwGB.xmCKTWJClsB7O9wzTnG','Allada Pavan.png', now()),
('d1575f99-eaf5-468f-ac6e-34ab9cc910f5','aecllc.bnk@gmail.com','INACTIVE','Bruce','Kitchell','$2a$10$GINThwCjVZAbGnmOe9BIeuDuvDlyfuwZrg/Rsmrjs1Lsq2pnXtO/S','Bruce Kitchell.png', now()),
('151274b5-b815-48a6-a0f7-07e3483fef7c','muhammad.evran13@gmail.com','ACTIVE','Muhammad','Evran','$2a$10$UcHWHC72azPVZJb5Ky.Yy.X695WGf1ZkkGMS3WL3B9WqWf2dQD04G','Muhammad Evran.png', now()),
('d5fe7375-4adc-408b-90b8-224c9ac7409f','kent.hervey8@gmail.com','ACTIVE','Kent','Hervey','$2a$10$YHXRsZ07/Btv.qCgGht.7u2PW.GLWzpVB7eabfgH1mhTVVXffDT6K','KentHervey.png', now()),
('257c0fe9-6d33-4d60-b362-458069b8c045','alfredephraim26@gmail.com','INACTIVE','Alfred','Ephraim','$2a$10$1jl3q3r/Fh9ZBv6ziM4XhuxCi2GMFWcfHUrxsesXAEwnsiV/NJKbq','Alfred.png', now()),
('5cd80c75-33d0-41b5-a816-f26a5093f1f5','nathihsa@gmail.com','ACTIVE','Nathi','Sangweni','$2a$10$WyHmQiXCSYuHcGeg8eFWvOScqzSgg88MmqpajPdzSkLsvZjT3tKC.','Nathi_Sangweni.png', now()),
('6d4af4b5-def3-44a4-9f8e-a0e1b3ccb4f4','ammanollashirisha2020@gmail.com','ACTIVE','Ammanolla','Shirisha','$2a$10$N1eE87eXFB2XQ5nmWKaTXOofnrPn8koeNvZhEpleJzO49i55e/Vk.','Ammanolla.png', now()),
('508a143b-8a0c-4d2d-a1b2-00bd9608de71','bfeeny238@hotmail.com','ACTIVE','Bill','Feeny','$2a$10$3sH0v..zpjwA8ux5/q.OAeu0HgmSdMj8VzMWzhwwBDkE8wOISsUyi','Bill Feeny.png', now()),
('69bb70e6-822c-4b27-a8a9-1d7b5df8ac40','redsantosph@gmail.com','ACTIVE','Frederick','delos Santos','$2a$10$KXHmKpE6YB/0sjiy3fkMv.muKyxqvOXE6jVeaPu8KEaExx62ZmmNe','Frederick Santos.png', now()),
('95f57290-3488-43b9-856c-2f533e7fee9e','ro_anamaria@mail.ru','ACTIVE','Ana','Maria','$2a$10$sz0CHOHAY1Xjt2ajIZgnG.L2KBQ4SsQkOGsPYue.C5gr6j.KMDdqS','Anna Maria.jpg', now()),
('ce08e189-a594-4323-928c-2f6b045641fe','maytassatya@hotmail.com','INACTIVE','Satya','Narayana','$2a$10$R7EJcaYijjJo/IVk6c1CieBML2uP3RAKMVlCxylPAePlCfJsX7OOy','Satya Narayana.png', now()),
('c403a210-e156-44be-86ec-c5a06c853b92','matthewefoli@gmail.com','ACTIVE','Matthew','Efoli','$2a$10$ECNnxXSVArnwS9KCet3yguQ1qHKyBIhh2G8c4F9CYgvp/Hadl8OS6','Matthew.png', now()),
('3cbc4036-f9c5-48d8-be3d-173859b161cf','davekumara2@gmail.com','ACTIVE','Dave','Kumar','$2a$10$5ebeZu18V5RheieYqpl/LORCN41E3H7yvxKqEwtq5Zq2JVw.E9dva','Dave Kumar.png', now()),
('0df97e3e-38e9-4207-96b2-511ec96948f7','jackkbruce@yahoo.com','ACTIVE','Jack','Bruce','$2a$10$a6iyIHRj8DNpu15obVHTSOGcLe4IfpBcD4iEEJesWaFpBQvRoF2da','Jack Bruce.png', now()),
('3c931bcd-a53f-4d4b-bc95-30ce66530e26','zirri.mohamed@gmail.com','ACTIVE','Mohamed','Zirri','$2a$10$TmvyH1AoyDqRmQ4uC8NAZOOV29CPEDGuxVsHLP1cJoHQGr78L4kjW','Mohamed Zirri.jpg', now()),
('d5d3ca01-e65a-4db9-a8b5-8dd2248b4d5b','mk.majumdar009@hotmail.com','INACTIVE','Mithun','Kumar Majumdar','$2a$10$Y6SEy2INN0Rk/vhLHHJUYO6IMqNW3Ar.jVe9o0W1lpBRX8xr2Itui','Mithun Kumar Majumdar.png', now()),
('03127a51-d19c-47b0-bccb-bfacf7e0258f','aliraza.arain.28@gmail.com','ACTIVE','Ali','Raza','$2a$10$PISZ2KitSbhE4/Z3dtIGk.WUi2ILiDl4PzRUDEQSp5BJIxcdcPq4G','Ali Raza.png', now()),
('fdf1d220-c442-4666-848f-b34ced44d2ca','isaachenry2877@gmail.com','ACTIVE','Isaac','Henry','$2a$10$CtmhrOz/AhDoCpKbeYl8O.0ngCFMukcznNZq7.YcHrkRyKpBG8Zca','Isaac Henry.jpg', now()),
('0c491faf-8c25-4fc5-8fe0-86ced7511135','s.stasovska82@hotmail.com','ACTIVE','Svetlana','Stasovska','$2a$10$fcN2cNa7vB.78QnmzfNZEeUBkrwUaM.bVK3iDB.KFQlR15DwL7QZy','Svetlana Stasovska.png', now()),
('10c9c9c9-043f-425c-bc7c-2b8ff311d5d0','mikegates2012@gmail.com','ACTIVE','Mike','Gates','$2a$10$zIO1tygsw6cB2ymiR.WX0ulr9NKdTlZHqu7w/W/LLwk8HhK7nVnH.','Mike Gates.png', now()),
('409a8af9-691f-405a-bdf1-243b3f28228a','pedroquintero67@gmail.com','INACTIVE','Pedro','Quintero','$2a$10$UPX5EwZw0MyBvbe.7mxg2u8GOl/4KgaUU40iSjr1PLFYvhu35fMmu','Pedro Quintero.png', now()),
('1831086f-599f-4a8d-b2bc-301383eceb90','amina.elshal2@yahoo.com','ACTIVE','Amina','Elshal','$2a$10$J1yoyqG5vWNe5N663PkgY.h53gfJtTR7Bb8E8u3sXdNbOZxhXgHu.','Amina Elshal.png', now());


--
-- Dumping data for table `users_roles`
--

INSERT INTO users_roles VALUES 
('9b93b0f0-7a18-4e8a-9b2e-cb501e7394a3','4c3b1ef6-9a19-43a4-bcb1-75bceecc0997'),
('d4ca2ccf-97dc-4f91-90cf-da8035c894e4','4c3b1ef6-9a19-43a4-bcb1-75bceecc0997'),
('d1575f99-eaf5-468f-ac6e-34ab9cc910f5','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('151274b5-b815-48a6-a0f7-07e3483fef7c','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('d5fe7375-4adc-408b-90b8-224c9ac7409f','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('257c0fe9-6d33-4d60-b362-458069b8c045','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('5cd80c75-33d0-41b5-a816-f26a5093f1f5','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('6d4af4b5-def3-44a4-9f8e-a0e1b3ccb4f4','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('508a143b-8a0c-4d2d-a1b2-00bd9608de71','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('69bb70e6-822c-4b27-a8a9-1d7b5df8ac40','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('95f57290-3488-43b9-856c-2f533e7fee9e','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('ce08e189-a594-4323-928c-2f6b045641fe','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('c403a210-e156-44be-86ec-c5a06c853b92','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('3cbc4036-f9c5-48d8-be3d-173859b161cf','536ba063-3361-43bd-ae3b-9bf635f815e0'),
('0df97e3e-38e9-4207-96b2-511ec96948f7','536ba063-3361-43bd-ae3b-9bf635f815e0'),
('3c931bcd-a53f-4d4b-bc95-30ce66530e26','536ba063-3361-43bd-ae3b-9bf635f815e0'),
('d5d3ca01-e65a-4db9-a8b5-8dd2248b4d5b','536ba063-3361-43bd-ae3b-9bf635f815e0'),
('03127a51-d19c-47b0-bccb-bfacf7e0258f','536ba063-3361-43bd-ae3b-9bf635f815e0'),
('fdf1d220-c442-4666-848f-b34ced44d2ca','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('0c491faf-8c25-4fc5-8fe0-86ced7511135','b6c86aa4-8b73-4fe5-af24-d55833993c7a'),
('10c9c9c9-043f-425c-bc7c-2b8ff311d5d0','cbf5569f-b51a-4eae-9223-d189827a1b9c'),
('409a8af9-691f-405a-bdf1-243b3f28228a','cbf5569f-b51a-4eae-9223-d189827a1b9c'),
('1831086f-599f-4a8d-b2bc-301383eceb90','cbf5569f-b51a-4eae-9223-d189827a1b9c'),
('d5fe7375-4adc-408b-90b8-224c9ac7409f','cbf5569f-b51a-4eae-9223-d189827a1b9c'),
('95f57290-3488-43b9-856c-2f533e7fee9e','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('3cbc4036-f9c5-48d8-be3d-173859b161cf','cbf5569f-b51a-4eae-9223-d189827a1b9c'),
('0df97e3e-38e9-4207-96b2-511ec96948f7','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('03127a51-d19c-47b0-bccb-bfacf7e0258f','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('fdf1d220-c442-4666-848f-b34ced44d2ca','cbf5569f-b51a-4eae-9223-d189827a1b9c'),
('0c491faf-8c25-4fc5-8fe0-86ced7511135','b12ec1c6-64e7-44af-8844-8afe04aa5e8b'),
('0c491faf-8c25-4fc5-8fe0-86ced7511135','cbf5569f-b51a-4eae-9223-d189827a1b9c');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-23  6:33:57
