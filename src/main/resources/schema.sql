CREATE TABLE IF NOT EXISTS `auth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL COMMENT '角色：用户/管理员',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `industry` varchar(255) NOT NULL,
  `area` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `reason` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) NOT NULL,
  `enable` int NOT NULL DEFAULT '1',
  `type` varchar(255) NOT NULL COMMENT '角色：用户/管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `claim` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clientId` int NOT NULL,
  `reasonId` int NOT NULL,
  `reviewStatus` varchar(255) NOT NULL,
  `severity` varchar(255) NOT NULL,
  `reviewerId` int DEFAULT NULL,
  `claimTime` datetime NOT NULL,
  `reviewTime` datetime DEFAULT NULL,
  `externSeverity` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL COMMENT '申请类型：违约申请/重生申请',
  `claimerId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `clientId` (`clientId`),
  KEY `reasonId` (`reasonId`),
  KEY `reviewerId` (`reviewerId`),
  KEY `fk_claimerId` (`claimerId`),
  CONSTRAINT `claim_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `client` (`id`),
  CONSTRAINT `claim_ibfk_2` FOREIGN KEY (`reasonId`) REFERENCES `reason` (`id`),
  CONSTRAINT `claim_ibfk_3` FOREIGN KEY (`reviewerId`) REFERENCES `auth` (`id`),
  CONSTRAINT `fk_claimerId` FOREIGN KEY (`claimerId`) REFERENCES `auth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DELIMITER //
CREATE TRIGGER IF NOT EXISTS `after_claim_update` AFTER UPDATE ON `claim` FOR EACH ROW BEGIN
    IF NEW.reviewStatus = '通过' THEN
        IF NEW.type = '违约申请' THEN
            UPDATE client SET status = '已违约' WHERE id = NEW.clientId;
        ELSEIF NEW.type = '重生申请' THEN
            UPDATE client SET status = '未违约' WHERE id = NEW.clientId;
        END IF;
    END IF;
END//
DELIMITER ;

CREATE OR REPLACE VIEW `totalclaims` AS
SELECT YEAR(`claim`.`reviewTime`) AS `year`,
       COUNT(0) AS `totalClaims`,
       SUM(CASE WHEN `claim`.`type` = '违约申请' THEN 1 ELSE 0 END) AS `totalDefaultClaims`,
       SUM(CASE WHEN `claim`.`type` = '重生申请' THEN 1 ELSE 0 END) AS `totalRebirthClaims`
FROM `claim`
WHERE `claim`.`reviewStatus` = '通过'
GROUP BY YEAR(`claim`.`reviewTime`);

CREATE OR REPLACE VIEW `areastatics` AS
SELECT YEAR(`claim`.`reviewTime`) AS `year`,
       `client`.`area` AS `area`,
       COUNT(0) AS `totalClaim`,
       SUM(CASE `claim`.`type` WHEN '违约申请' THEN 1 ELSE 0 END) AS `totalDefaultClaim`,
       SUM(CASE `claim`.`type` WHEN '重生申请' THEN 1 ELSE 0 END) AS `totalRebirthClaim`
FROM (`claim` JOIN `client` ON (`claim`.`clientId` = `client`.`id`))
WHERE `claim`.`reviewStatus` = '通过'
GROUP BY YEAR(`claim`.`reviewTime`), `client`.`area`;

CREATE OR REPLACE VIEW `industrystatics` AS
SELECT YEAR(`claim`.`reviewTime`) AS `year`,
       `client`.`industry` AS `industry`,
       COUNT(0) AS `totalClaim`,
       SUM(CASE `claim`.`type` WHEN '违约申请' THEN 1 ELSE 0 END) AS `totalDefaultClaim`,
       SUM(CASE `claim`.`type` WHEN '重生申请' THEN 1 ELSE 0 END) AS `totalRebirthClaim`
FROM (`claim` JOIN `client` ON (`claim`.`clientId` = `client`.`id`))
WHERE `claim`.`reviewStatus` = '通过'
GROUP BY YEAR(`claim`.`reviewTime`), `client`.`industry`;
