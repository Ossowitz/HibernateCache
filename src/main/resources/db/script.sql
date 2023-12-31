DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    username   VARCHAR(128) PRIMARY KEY,
    firstname  VARCHAR(128),
    lastname   VARCHAR(128),
    birth_date DATE,
    info       JSONB,
    role       VARCHAR(32)
);

SELECT *
FROM users;