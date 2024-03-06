-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nhn_academy_19
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nhn_academy_19
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nhn_academy_19` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `nhn_academy_19` ;

-- -----------------------------------------------------
-- Table `nhn_academy_19`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`Categories` (
                                                             `CategoryID` INT NOT NULL AUTO_INCREMENT,
                                                             `CategoryName` VARCHAR(50) NULL DEFAULT NULL,
    PRIMARY KEY (`CategoryID`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`Users` (
                                                        `UserID` VARCHAR(50) NOT NULL COMMENT '아이디',
    `UserName` VARCHAR(50) NOT NULL COMMENT '이름',
    `UserPassword` VARCHAR(200) NOT NULL COMMENT 'mysql password 사용',
    `UserBirth` VARCHAR(8) NOT NULL COMMENT '생년월일 : 19990112',
    `UserAuth` VARCHAR(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
    `UserPoint` INT NOT NULL COMMENT 'default : 1000000',
    `CreatedAt` DATETIME NOT NULL COMMENT '가입일자',
    `LatestLoginAt` DATETIME NULL DEFAULT NULL COMMENT '마지막 로그인 일자',
    PRIMARY KEY (`UserID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci
    COMMENT = '회원';


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`Orders` (
                                                         `OrderID` INT NOT NULL AUTO_INCREMENT,
                                                         `UserID` VARCHAR(50) NULL DEFAULT NULL,
    `OrderDate` DATETIME NULL DEFAULT NULL,
    `ShipDate` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`OrderID`),
    INDEX `fk_Orders_Users` (`UserID` ASC) VISIBLE,
    CONSTRAINT `fk_Orders_Users`
    FOREIGN KEY (`UserID`)
    REFERENCES `nhn_academy_19`.`Users` (`UserID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`Products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`Products` (
                                                           `ProductID` INT NOT NULL AUTO_INCREMENT,
                                                           `CategoryID` INT NULL DEFAULT NULL,
                                                           `ModelNumber` VARCHAR(10) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
    `ModelName` VARCHAR(120) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
    `Quantity` INT NULL DEFAULT NULL,
    `ProductImage` VARCHAR(30) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
    `UnitCost` DECIMAL(15,0) NULL DEFAULT NULL,
    `Description` TEXT NULL DEFAULT NULL,
    PRIMARY KEY (`ProductID`),
    INDEX `fk_Products_Categories` (`CategoryID` ASC) VISIBLE,
    CONSTRAINT `fk_Products_Categories`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `nhn_academy_19`.`Categories` (`CategoryID`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 24
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`OrderDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`OrderDetails` (
                                                               `OrderID` INT NOT NULL,
                                                               `ProductID` INT NOT NULL,
                                                               `Quantity` INT NULL DEFAULT NULL,
                                                               `UnitCost` DECIMAL(15,0) NULL DEFAULT NULL,
    PRIMARY KEY (`OrderID`, `ProductID`),
    INDEX `fk_OrderDetails_Products` (`ProductID` ASC) VISIBLE,
    CONSTRAINT `fk_OrderDetails_Orders`
    FOREIGN KEY (`OrderID`)
    REFERENCES `nhn_academy_19`.`Orders` (`OrderID`),
    CONSTRAINT `fk_OrderDetails_Products`
    FOREIGN KEY (`ProductID`)
    REFERENCES `nhn_academy_19`.`Products` (`ProductID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`Points`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`Points` (
                                                         `PointsID` VARCHAR(36) NOT NULL,
    `userID` VARCHAR(50) NOT NULL,
    `PointAmount` VARCHAR(255) NOT NULL,
    `PointTransactionTime` TIMESTAMP NOT NULL,
    PRIMARY KEY (`PointsID`),
    INDEX `fk_Points_Users` (`userID` ASC) VISIBLE,
    CONSTRAINT `fk_Points_Users`
    FOREIGN KEY (`userID`)
    REFERENCES `nhn_academy_19`.`Users` (`UserID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`Reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`Reviews` (
                                                          `ReviewID` INT NOT NULL AUTO_INCREMENT,
                                                          `ProductID` INT NULL DEFAULT NULL,
                                                          `UserID` VARCHAR(50) NULL DEFAULT NULL,
    `Rating` INT NULL DEFAULT NULL,
    `Comments` TEXT NULL DEFAULT NULL,
    PRIMARY KEY (`ReviewID`),
    INDEX `fk_Review_Products` (`ProductID` ASC) VISIBLE,
    INDEX `fk_Review_Users` (`UserID` ASC) VISIBLE,
    CONSTRAINT `fk_Review_Products`
    FOREIGN KEY (`ProductID`)
    REFERENCES `nhn_academy_19`.`Products` (`ProductID`),
    CONSTRAINT `fk_Review_Users`
    FOREIGN KEY (`UserID`)
    REFERENCES `nhn_academy_19`.`Users` (`UserID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`ShoppingCart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`ShoppingCart` (
                                                               `RecordID` INT NOT NULL AUTO_INCREMENT,
                                                               `CartID` VARCHAR(150) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
    `Quantity` INT NULL DEFAULT NULL,
    `ProductID` INT NULL DEFAULT NULL,
    `DateCreateed` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`RecordID`),
    INDEX `fk_cart_ProductID` (`ProductID` ASC) VISIBLE,
    CONSTRAINT `fk_cart_ProductID`
    FOREIGN KEY (`ProductID`)
    REFERENCES `nhn_academy_19`.`Products` (`ProductID`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nhn_academy_19`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_19`.`address` (
                                                          `addrID` INT NOT NULL AUTO_INCREMENT,
                                                          `address` VARCHAR(150) NOT NULL,
    `userID` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`addrID`),
    UNIQUE INDEX `addrID_UNIQUE` (`addrID` ASC) VISIBLE,
    UNIQUE INDEX `userID_UNIQUE` (`userID` ASC) VISIBLE,
    INDEX `userID_idx` (`userID` ASC) VISIBLE,
    CONSTRAINT `fk_address_userid`
    FOREIGN KEY (`userID`)
    REFERENCES `nhn_academy_19`.`Users` (`UserID`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
