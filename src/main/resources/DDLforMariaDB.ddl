-- Drop and create table for Classroom
DROP TABLE IF EXISTS Classroom;
CREATE TABLE Classroom (
    classroomId CHAR(36) PRIMARY KEY,
    classroomName VARCHAR(255),
    buildingName VARCHAR(255),
    floor INT
);

-- Drop and create table for Friend
DROP TABLE IF EXISTS Friend;
CREATE TABLE Friend (
    friendId CHAR(36) PRIMARY KEY,
    fromUserId CHAR(36),
    toUserId CHAR(36),
    isAccepted BOOLEAN
);

-- Drop and create table for Lecture
DROP TABLE IF EXISTS Lecture;
CREATE TABLE Lecture (
    lectureId CHAR(36) PRIMARY KEY,
    lectureName VARCHAR(255),
    professor VARCHAR(255),
    classroomId CHAR(36)
);

-- Drop and create table for LectureTime
DROP TABLE IF EXISTS LectureTime;
CREATE TABLE LectureTime (
    lectureTimeId CHAR(36) PRIMARY KEY,
    lectureId CHAR(36),
    day VARCHAR(50),
    time VARCHAR(50)
);

-- Drop and create table for Review
DROP TABLE IF EXISTS Review;
CREATE TABLE Review (
    reviewId CHAR(36) PRIMARY KEY,
    classroomId CHAR(36),
    userName VARCHAR(255),
    mark INT,
    content TEXT
);

-- Drop and create table for User
DROP TABLE IF EXISTS User;
CREATE TABLE User (
    userId CHAR(36) PRIMARY KEY,
    userName VARCHAR(255),
    password VARCHAR(255),
    nickname VARCHAR(255),
    realName VARCHAR(255),
    isAdmin BOOLEAN
);
