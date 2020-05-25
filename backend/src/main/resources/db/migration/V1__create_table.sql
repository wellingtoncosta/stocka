CREATE TABLE todo (
    id VARCHAR(26) PRIMARY KEY,
    title VARCHAR NOT NULL,
    description VARCHAR,
    status VARCHAR NOT NULL CHECK (status IN ('PLANNED','IN_PROGRESS', 'DONE'))
);
