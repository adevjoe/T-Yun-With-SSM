CREATE TABLE `ty_u_disk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark` varchar(16) DEFAULT NULL,
  `code` varchar(8) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_time` bigint(14) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8