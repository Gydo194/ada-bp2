DROP DATABASE IF EXISTS dentalpoint;
CREATE DATABASE dentalpoint;

USE dentalpoint;

CREATE TABLE persoon (
    achternaam    VARCHAR(255)                           NOT NULL,
    voorletters   VARCHAR(10)                            NOT NULL,
    geboortedatum DATE                                   NOT NULL,
    geslacht      ENUM('M', 'V')                         NOT NULL,
    email         VARCHAR(100)                           NOT NULL,
    agbcode       INT                                    NULL,
    relatienummer INT                                    NULL,
    type          ENUM('PATIENT', 'BEHANDELAAR')         NOT NULL,
    PRIMARY KEY(achternaam, voorletters)
);

CREATE TABLE behandeling (
    behandelingscode      VARCHAR(4)        NOT NULL,
    prijs                 DOUBLE            NOT NULL,
    omschrijving          TEXT              NOT NULL,
    PRIMARY KEY(behandelingscode)
);