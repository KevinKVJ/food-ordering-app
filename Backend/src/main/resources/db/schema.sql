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
    `public_phone` VARCHAR(512)     NULL COMMENT '商家对外电话',
    `public_address` VARCHAR(512)    NULL COMMENT '商家对外地址',
    `business_hours` VARCHAR(512)    NULL COMMENT '商家营业时间',
    `create_at`   DATETIME           NULL COMMENT '创建时间',
    `update_at`   DATETIME           NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `phone` (`phone`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商家';

DROP TABLE IF EXISTS t_product;
CREATE TABLE t_product
(
    `id`    INT AUTO_INCREMENT NOT NULL COMMENT '主键ID',
    `name` VARCHAR(512) NULL COMMENT '商品名称',
    `monthly` INT DEFAULT 0 NOT NULL COMMENT '月售量',
    `inventory` INT NULL COMMENT '库存量',
    `discount`  INT DEFAULT 0 COMMENT '折扣百分比(0-100间整数)',
    `price` INT NULL COMMENT '商品原价(以分为单位)',
    `merchant_id` INT NULL COMMENT '商家ID',
    `create_at`   DATETIME           NULL COMMENT '创建时间',
    `update_at`   DATETIME           NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商品';

DROP TABLE IF EXISTS t_category;
CREATE TABLE t_category
(
    `id`    INT AUTO_INCREMENT NOT NULL COMMENT '主键ID',
    `name` VARCHAR(512)       NULL COMMENT '类目名称',
    `merchant_id`   INT NULL COMMENT '商家ID',
    `create_at`   DATETIME           NULL COMMENT '创建时间',
    `update_at`   DATETIME           NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商品类目';

DROP TABLE IF EXISTS t_product_to_category;
CREATE TABLE t_product_to_category
(
    `product_id` INT NOT NULL COMMENT '商品ID',
    `category_id` INT NOT NULL COMMENT '类目ID',
    PRIMARY KEY (`product_id`,`category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='商品对应类目表';

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order
(
    `id` INT AUTO_INCREMENT NOT NULL COMMENT '订单ID',
    `client_id` INT NULL COMMENT '买家ID',
    `merchant_id` INT NULL COMMENT '商家ID',
    `address` VARCHAR(512) NULL COMMENT '配送地址',
    `phone` VARCHAR(30) NULL COMMENT '配送电话',
    `payment_method` INT NULL COMMENT '支付方式',
    `delivery_time` DATETIME NULL COMMENT '送达时间',
    `shipment_time` DATETIME NULL COMMENT '送货时间',
    `delivery_method_id` INT NULL COMMENT '配送方式外键ID',
    `total_price` INT NULL COMMENT '总价',
    `delivery_fee` INT NULL COMMENT '配送费',
    `status_id` INT NULL COMMENT '订单状态外键ID',
    `comment` VARCHAR(512) NULL COMMENT '评价',
    `create_at` DATETIME NULL COMMENT '创建时间',
    `update_at` DATETIME NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='订单';

DROP TABLE IF EXISTS t_order_delivery_method;
CREATE TABLE t_order_delivery_method
(
    `id` INT AUTO_INCREMENT NOT NULL COMMENT '配送方式ID',
    `description` VARCHAR(512) NULL COMMENT '配送方式描述',
    `create_at` DATETIME NULL COMMENT '创建时间',
    `update_at` DATETIME NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='配送方式';

DROP TABLE IF EXISTS t_order_status;
CREATE TABLE t_order_status
(
    `id` INT AUTO_INCREMENT NOT NULL COMMENT '订单状态ID',
    `description` VARCHAR(512) NULL COMMENT '订单状态描述',
    `create_at` DATETIME NULL COMMENT '创建时间',
    `update_at` DATETIME NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='订单状态';

DROP TABLE IF EXISTS t_client;
CREATE TABLE t_client
(
    `id` INT AUTO_INCREMENT NOT NULL COMMENT '客户ID',
    `create_at` DATETIME NULL COMMENT '创建时间',
    `update_at` DATETIME NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='客户';