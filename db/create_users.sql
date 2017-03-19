DROP TABLE IF EXISTS `rssfeed`.`users`;
CREATE TABLE `rssfeed`.`users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(450) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email_address` varchar(90) NOT NULL,
  `cell_phone` varchar(45) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `self_intro` varchar(90) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;