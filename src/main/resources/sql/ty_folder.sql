CREATE TABLE `ty_folder` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `file_name` text,
  `is_dir` int(1) NOT NULL DEFAULT '1',
  `parent_path` text NOT NULL,
  `level` varchar(255) DEFAULT '1',
  `comment` text,
  `create_time` bigint(14) NOT NULL DEFAULT '0',
  `update_time` bigint(14) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  `size` bigint(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;