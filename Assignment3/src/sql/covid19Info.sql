CREATE DATABASE `covid19Info`
CREATE TABLE `country` (
                           `ID` int NOT NULL,
                           `Name` varchar(50) NOT NULL,
                           `sq_km_area` int NOT NULL,
                           `capital_city` varchar(50) NOT NULL,
                           `continent` varchar(50) NOT NULL,
                           `location` varchar(50) NOT NULL,
                           `abbreviation` varchar(10) NOT NULL,
                           `confirmed` int NOT NULL,
                           `recovered` int NOT NULL,
                           `deaths` int NOT NULL,
                           PRIMARY KEY (`ID`)
);
CREATE TABLE `city` (
                        `ID` int NOT NULL AUTO_INCREMENT,
                        `Name` varchar(50) NOT NULL,
                        `CID` int NOT NULL,
                        `confirmed` int NOT NULL,
                        `recovered` int NOT NULL,
                        `deaths` int NOT NULL,
                        PRIMARY KEY (`ID`),
                        KEY `cityToCountry` (`CID`),
                        CONSTRAINT `cityToCountry` FOREIGN KEY (`CID`) REFERENCES `country` (`ID`)
)