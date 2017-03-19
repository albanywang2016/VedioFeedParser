DROP TABLE IF EXISTS `rssfeed`.`channel`;
CREATE TABLE `rssfeed`.`channel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `time_created` TIMESTAMP NULL,
  PRIMARY KEY (`id`));
