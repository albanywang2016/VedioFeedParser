DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `image_id` varchar(90) NOT NULL,
  `image_type` varchar(45) DEFAULT NULL,
  `image_name` varchar(90) DEFAULT NULL,
  `image_file_name` varchar(90) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;