DROP TABLE IF EXISTS `rssfeed`.`comments`;
CREATE TABLE `rssfeed`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `date` DATETIME NULL,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`));