DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` bigint(8) NOT NULL,
  `source_name` varchar(90) NOT NULL,
  `channel` varchar(90) NOT NULL,
  `title` varchar(450) NOT NULL,
  `creator` varchar(90) DEFAULT NULL,
  `link` varchar(900) DEFAULT NULL,
  `description` text,
  `contents` mediumtext,
  `timestamp` varchar(90) DEFAULT NULL,
  `number_of_images` int(11) DEFAULT NULL,
  `pub_date` varchar(450) DEFAULT NULL,
  `day_created` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;