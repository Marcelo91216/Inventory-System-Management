CREATE DATABASE IF NOT EXISTS inventory; USE
    inventory;
DROP TABLE IF EXISTS
    `stock`;
CREATE TABLE `stock`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) DEFAULT 'No description',
    `name` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1; DROP TABLE IF EXISTS
    `product`;
CREATE TABLE `product`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `stock_id` INT(11) NOT NULL,
    `start_at` DATETIME DEFAULT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`stock_id`) REFERENCES stock(`id`)
);
