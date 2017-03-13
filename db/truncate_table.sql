TRUNCATE `source`.`message`;
TRUNCATE `source`.`image`;
truncate `source`.`feed_source`;
UPDATE `source`.`feed_source` SET `last_update_time`='' WHERE `source_name`='Asahi' AND `channel`='Top Stories';

