DROP DATABASE IF EXISTS `player_database`;
CREATE DATABASE `player_database`;
USE `player_database`;
DROP TABLE IF EXISTS `players`;
CREATE TABLE `players` (
                        `PLAYER_ID` int(11) NOT NULL AUTO_INCREMENT,
                        `PLAYER_NAME` varchar(50) NOT NULL,
                        `COUNTRY` varchar(50) NOT NULL,
                        `DOB` date NOT NULL,
                        `WEIGHTKG` int(3) NOT NULL,
                        `HEIGHTM` DECIMAL(4,2) NOT NULL,
                        `APPEARANCES` int(5) NOT NULL,
                        `GOALS` int(5) NOT NULL,

                        PRIMARY KEY  (`PLAYER_ID`)
);

INSERT INTO players VALUES (null, "Cristiano Ronaldo", "Portugal", "1985-02-05", 85, 1.87, 1000, 805),
                        (null, "Lionel Messi", "Argentina", "1987-06-24", 67, 1.69, 900, 736),
                        (null, "Neymar Jr", "Brazil", "1992-02-05", 68, 1.75, 600, 450),
                        (null, "Luis Suarez", "Uruguay", "1990-07-11", 73, 1.77, 700, 600),
                        (null, "Vinicius Jr", "Brazil", "2001-02-09", 65, 1.80, 130, 40),
                        (null, "Phil Foden", "England", "2000-03-14", 73, 1.74, 120, 60),
                        (null, "Mason Mount", "England", "1999-06-05", 64, 1.74, 150, 76),
                        (null, "Mohammed Salah", "Egypt", "1995-08-15", 70, 1.75, 360, 250),
                        (null, "Riyad Mahrez", "Algeria", "1994-04-09", 70, 1.83, 500, 200),
                        (null, "Jamie Vardy", "England", "1992-02-14", 67, 1.81, 400, 300);