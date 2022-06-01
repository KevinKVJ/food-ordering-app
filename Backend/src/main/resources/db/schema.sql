SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS t_merchant;
CREATE TABLE t_merchant
(
    `id`          INT AUTO_INCREMENT NOT NULL COMMENT '主键ID',
    `phone`       VARCHAR(20)        NOT NULL COMMENT '电话',
    `password`    VARCHAR(512)       NOT NULL COMMENT '明文密码',
    `name`        VARCHAR(128)       NULL COMMENT '商家名字',
    `description` VARCHAR(512)       NULL COMMENT '商家描述',
    `address`     VARCHAR(256)       NULL COMMENT '商家地址',
    `create_at`   DATETIME           NULL COMMENT '创建时间',
    `update_at`   DATETIME           NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `phone` (`phone`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商家';
