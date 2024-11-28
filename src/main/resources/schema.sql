CREATE TABLE IF NOT EXISTS Book
(
    id     long NOT NULL AUTO_INCREMENT,
    title  VARCHAR,
    author VARCHAR,
    isbn   VARCHAR,
    PRIMARY KEY (id)
);
