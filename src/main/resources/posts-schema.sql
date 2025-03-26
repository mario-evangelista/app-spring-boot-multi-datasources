-- MICROSOFT SQL SERVER
DROP TABLE IF EXISTS posts;

CREATE TABLE posts(
    ID BIGINT IDENTITY(1,1) PRIMARY KEY,
    text VARCHAR(255) NOT NULL
);

INSERT INTO
    posts (text)
VALUES(
    'Tutorial Múltiplos Dbs com Spring Boot'
);