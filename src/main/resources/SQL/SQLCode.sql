DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
BEGIN;
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (1, 'Ricardo', '123456');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (2, 'keli', '123456');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (3, 'yoyo', '1900');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (4, 'fear', '123');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (1900, 'Dreams', '1900');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (1946, 'HanKin', '350145');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (40522, 'FYQS', '123456');
INSERT INTO `user` (`userId`, `userName`, `password`) VALUES (114514, '余生三月', '1234');
COMMIT;

DROP TABLE IF EXISTS `userRank`;
CREATE TABLE `userRank` (
                            `userID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                            `score` int DEFAULT NULL,
                            PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
BEGIN;
INSERT INTO `userRank` (`userID`, `score`) VALUES ('1', 1200);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('114514', 1911);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('1900', 1432);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('1946', 1242);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('2', 1322);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('3', 1312);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('4', 1342);
INSERT INTO `userRank` (`userID`, `score`) VALUES ('40522', 1899);
COMMIT;
