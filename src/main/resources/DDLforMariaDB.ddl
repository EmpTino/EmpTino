use emptino;
-- Drop existing tables if they exist
DROP TABLE IF EXISTS classroomdao;
DROP TABLE IF EXISTS lecturedao;
DROP TABLE IF EXISTS lecturetimedao;
DROP TABLE IF EXISTS reviewdao;
DROP TABLE IF EXISTS userdao;
DROP TABLE IF EXISTS frienddao;
DROP TABLE IF EXISTS timetabledao;

-- Create classroomdao table
CREATE TABLE classroomdao (
                              classroom_id CHAR(36) NOT NULL PRIMARY KEY,   -- UUID로 사용
                              classroom_name VARCHAR(255),
                              building_name VARCHAR(255),
                              floor INT
);

-- Create lecturedao table (FK 없이)
CREATE TABLE lecturedao (
                            lecture_id CHAR(36) NOT NULL PRIMARY KEY,  -- UUID로 사용
                            lecture_name VARCHAR(255),
                            professor VARCHAR(255),
                            classroom_id CHAR(36) NOT NULL  -- Foreign Key 없이 단순히 classroom_id만 있음
);

-- Create lecturetimedao table (FK 없이)
CREATE TABLE lecturetimedao (
                                lecture_time_id CHAR(36) NOT NULL PRIMARY KEY,  -- UUID로 사용
                                lecture_id CHAR(36) NOT NULL,  -- Foreign Key 없이 단순히 lecture_id만 있음
                                day VARCHAR(255),
                                time INT(16)
);

-- Create reviewdao table (FK 없이)
CREATE TABLE reviewdao (
                           review_id CHAR(36) NOT NULL PRIMARY KEY,  -- UUID로 사용
                           classroom_id CHAR(36) NOT NULL,  -- Foreign Key 없이 단순히 classroom_id만 있음
                           user_name VARCHAR(255),
                           mark INT,
                           content VARCHAR(255)
);

-- Create userdao table (FK 없이)
CREATE TABLE userdao (
                         user_id CHAR(36) NOT NULL PRIMARY KEY,  -- UUID로 사용
                         user_name VARCHAR(255),
                         password VARCHAR(255),
                         nickname CHAR(255),
                         real_name VARCHAR(255),
                         is_admin BOOLEAN
);

CREATE TABLE frienddao (
                         friend_id CHAR(36) NOT NULL PRIMARY KEY,
                         from_user_id CHAR(36),
                         to_user_id CHAR(36),
                         is_accepted BOOLEAN
);

CREATE TABLE timetabledao (
                         timetable_id CHAR(36) NOT NULL PRIMARY KEY,
                         user_id CHAR(36) NOT NULL,
                         lecture_ids JSON
);