CREATE TABLE account (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    github_id VARCHAR(255) NOT NULL UNIQUE,
    github_username VARCHAR(255),
    name VARCHAR(255),
    nickname VARCHAR(255),
    picture VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255)
);

CREATE TABLE code (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    value VARCHAR(255),
    registration_id BIGINT(20),
    account_id BIGINT(20),
    expire_time DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE registration (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    client_id VARCHAR(255) NOT NULL,
    client_secret VARCHAR(255) NOT NULL,
    client_name VARCHAR(255),
    homepage_uri VARCHAR(255),
    redirect_uri VARCHAR(255),
    scopes VARCHAR(255),
    PRIMARY KEY (id)
);

ALTER TABLE code
    ADD CONSTRAINT fk_code_registration
        FOREIGN KEY (registration_id) REFERENCES registration (id);

ALTER TABLE code
    ADD CONSTRAINT fk_code_account
        FOREIGN KEY (account_id) REFERENCES account (id);
