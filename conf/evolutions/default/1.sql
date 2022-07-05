# Person schema

# --- !Ups

CREATE TABLE PERSON(
    ID  INT NOT NULL,
    FULL_NAME varchar(255) NOT NULL,
    LAST_NAME varchar(255) NOT NULL,
    EMAIL varchar(255) NOT NULL,
    BIRTH_DATE date NOT NULL,
    CREATED_AT date NOT NULL,
    PRIMARY KEY (ID)
);

# --- !Downs

DROP TABLE PERSON;