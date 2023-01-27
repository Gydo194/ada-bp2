DROP DATABASE IF EXISTS dentalpoint;
CREATE DATABASE dentalpoint;

USE dentalpoint;

CREATE TABLE persoon (
    achternaam    VARCHAR(255),
    voorletters   VARCHAR(10),
    geboortedatum DATE,
    geslacht      ENUM('M', 'F'),
    email         VARCHAR(100),
    type          ENUM('PATIENT', 'BEHANDELAAR')
);