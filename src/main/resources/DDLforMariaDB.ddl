-- Drop existing tables if they exist
DROP TABLE IF EXISTS ClassroomDAO;
DROP TABLE IF EXISTS LectureDAO;
DROP TABLE IF EXISTS LectureTimeDAO;
DROP TABLE IF EXISTS ReviewDAO;
DROP TABLE IF EXISTS UserDAO;

-- Create tables
CREATE TABLE ClassroomDAO (
    classroomId CHAR(36) NOT NULL PRIMARY KEY,
    classroomName VARCHAR(255),
    buildingName VARCHAR(255),
    floor INT
);

CREATE TABLE LectureDAO (
    lectureId CHAR(36) NOT NULL PRIMARY KEY,
    LectureName VARCHAR(255),
    professor VARCHAR(255),
    ClassroomId CHAR(36) NOT NULL
);

CREATE TABLE LectureTimeDAO (
    lectureTimeId CHAR(36) NOT NULL PRIMARY KEY,
    lectureId CHAR(36) NOT NULL,
    day VARCHAR(255),
    time VARCHAR(255)
);

CREATE TABLE ReviewDAO (
    ReviewId CHAR(36) NOT NULL PRIMARY KEY,
    classroomId CHAR(36) NOT NULL,
    userName VARCHAR(255),
    mark INTEGER,
    content VARCHAR(255)
);

CREATE TABLE UserDAO (
    lectureId CHAR(36) NOT NULL PRIMARY KEY,
    LectureName VARCHAR(255),
    professor VARCHAR(255),
    ClassroomId CHAR(36) NOT NULL
);
