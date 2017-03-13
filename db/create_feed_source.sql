DROP TABLE IF EXISTS `feed_source`;
CREATE TABLE `feed_source` (
  `source_name` varchar(90) NOT NULL,
  `channel` varchar(45) DEFAULT NULL,
  `last_update_time` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`source_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;