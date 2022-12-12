CREATE TABLE IF NOT EXISTS messages_full (
    id          VARCHAR(60)  PRIMARY KEY,
    sender      VARCHAR      NOT NULL,
    receiver    VARCHAR      NOT NULL,
    text        VARCHAR      NOT NULL,
    isMine      VARCHAR      NOT NULL
    );