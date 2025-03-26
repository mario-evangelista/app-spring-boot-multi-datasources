-- MICROSOFT SQL SERVER
DROP TABLE IF EXISTS comments;

CREATE TABLE comments(
    ID BIGINT IDENTITY(1,1) PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    post_id BIGINT NOT NULL
);

INSERT INTO
    comments (text, post_id)
VALUES('Curte o vídeo!', 1);

INSERT INTO
    comments (text, post_id)
VALUES('Comenta o vídeo!', 1);

INSERT INTO
    comments (text, post_id)
VALUES('Compartilha o vídeo!', 1);

INSERT INTO
    comments (text, post_id)
VALUES('Se inscreve no canal ativando as notificações!', 1);

