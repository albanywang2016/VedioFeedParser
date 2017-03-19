DROP TABLE IF EXISTS `rssfeed`.`source`;
CREATE TABLE `rssfeed`.`source` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `source_name` VARCHAR(90) NOT NULL,
  `time_created` TIMESTAMP NULL,
  `last_update` TIMESTAMP NULL,
  PRIMARY KEY (`id`));