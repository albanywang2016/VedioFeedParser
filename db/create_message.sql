DROP TABLE IF EXISTS `rssfeed`.`message`;
CREATE TABLE `rssfeed`.`message` (
  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,
  `source_name` varchar(90) NOT NULL,
  `channel` varchar(90) NOT NULL,
  `title` varchar(450) NOT NULL,
  `creator` varchar(90) DEFAULT NULL,
  `link` varchar(900) DEFAULT NULL,
  `description` text,
  `contents` mediumtext,
  `timestamp` varchar(90) DEFAULT NULL,
  `has_image` boolean default false,
  `pub_date` varchar(450) DEFAULT NULL,
  `day_created` varchar(45) DEFAULT NULL,
  `image_type` char(10) DEFAULT NULL,
  `image_url` varchar(450) DEFAULT null,
  `image_width` int DEFAULT 0,
  `image_height` int DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;