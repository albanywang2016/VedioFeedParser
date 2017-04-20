DROP TABLE IF EXISTS `rssfeed`.`feed_source`;
CREATE TABLE `rssfeed`.`feed_source` (
  `id` int NOT NULL auto_increment,
  `source_name` varchar(90) NOT NULL,
  `channel` varchar(45) NOT NULL,
  `created_time` date DEFAULT null,
  `last_update_time` varchar(90) DEFAULT NULL,
  `previous_last_update` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;