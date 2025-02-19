SHOW DATABASES;
CREATE DATABASE IF NOT EXISTS demo;

USE demo;
DROP TABLE IF EXISTS BidList;
CREATE TABLE BidList (
  BidListId int NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP NULL,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP NULL,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP NULL,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (BidListId)
);

USE demo;
DROP TABLE IF EXISTS Trade;
CREATE TABLE Trade (
  TradeId int NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP NULL,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP NULL,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP NULL,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);

USE demo;
DROP TABLE IF EXISTS CurvePoint;
CREATE TABLE CurvePoint (
  Id int NOT NULL AUTO_INCREMENT,
  CurveId int,
  asOfDate TIMESTAMP NULL,
  term DOUBLE,
  value DOUBLE,
  creationDate TIMESTAMP NULL,

  PRIMARY KEY (Id)
);

USE demo;
DROP TABLE IF EXISTS Rating;
CREATE TABLE Rating (
  Id int NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber int,

  PRIMARY KEY (Id)
);

USE demo;
DROP TABLE IF EXISTS RuleName;
CREATE TABLE RuleName (
  Id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125),

  PRIMARY KEY (Id)
);

USE demo;
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
  Id int NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (Id)
);

-- Initial data to start
USE demo;
insert into Users(fullname, username, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN");
insert into Users(fullname, username, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER");

INSERT INTO BidList (account, type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary, security, status, trader, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
VALUES
('Account1', 'TypeA', 100.0, 150.0, 50.5, 51.0, 'BenchmarkA', NOW(), 'CommentA', 'SecurityA', 'Active', 'TraderA', 'BookA', 'Admin', NOW(), 'Admin', NOW(), 'DealA', 'Type1', 'SourceA', 'SideA'),
('Account2', 'TypeB', 200.0, 250.0, 60.5, 61.0, 'BenchmarkB', NOW(), 'CommentB', 'SecurityB', 'Inactive', 'TraderB', 'BookB', 'Admin', NOW(), 'Admin', NOW(), 'DealB', 'Type2', 'SourceB', 'SideB');

INSERT INTO Trade (account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
VALUES
('Account1', 'TypeA', 50.0, 40.0, 100.5, 102.0, NOW(), 'SecurityA', 'Completed', 'TraderA', 'BenchmarkA', 'BookA', 'Admin', NOW(), 'Admin', NOW(), 'DealA', 'Type1', 'SourceA', 'SideA'),
('Account2', 'TypeB', 60.0, 55.0, 120.5, 122.0, NOW(), 'SecurityB', 'Pending', 'TraderB', 'BenchmarkB', 'BookB', 'Admin', NOW(), 'Admin', NOW(), 'DealB', 'Type2', 'SourceB', 'SideB');

INSERT INTO CurvePoint (CurveId, asOfDate, term, value, creationDate)
VALUES
(1, NOW(), 5.0, 105.5, NOW()),
(2, NOW(), 10.0, 110.0, NOW());

INSERT INTO Rating (moodysRating, sandPRating, fitchRating, orderNumber)
VALUES
('A1', 'AA', 'AAA', 1),
('Baa2', 'BBB', 'BB+', 2);

INSERT INTO RuleName (name, description, json, template, sqlStr, sqlPart)
VALUES
('Rule1', 'Description1', '{"rule":"test1"}', 'Template1', 'SELECT * FROM Trade', 'SQLPart1'),
('Rule2', 'Description2', '{"rule":"test2"}', 'Template2', 'SELECT * FROM BidList', 'SQLPart2');

-- Verifications
USE demo;
SELECT * FROM Users;
SELECT * FROM RuleName;
SELECT * FROM Rating;
SELECT * FROM CurvePoint;
SELECT * FROM Trade;
SELECT * FROM BidList;