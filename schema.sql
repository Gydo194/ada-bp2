DROP DATABASE IF EXISTS dentalpoint;
CREATE DATABASE dentalpoint;

USE dentalpoint;

CREATE TABLE persoon (
    bsn           INT                                    NOT NULL,
    achternaam    VARCHAR(255)                           NOT NULL,
    voorletters   VARCHAR(10)                            NOT NULL,
    geboortedatum DATE                                   NOT NULL,
    geslacht      ENUM('M', 'V')                         NOT NULL,
    email         VARCHAR(100)                           NOT NULL,
    agbcode       INT                                    NULL,
    relatienummer INT                                    NULL,
    type          ENUM('PATIENT', 'BEHANDELAAR')         NOT NULL,
    PRIMARY KEY(bsn)
);

CREATE TABLE behandeling (
    behandelingscode      VARCHAR(4)        NOT NULL,
    prijs                 DOUBLE            NOT NULL,
    omschrijving          TEXT              NOT NULL,
    PRIMARY KEY(behandelingscode)
);

CREATE TABLE nota (
    notanummer        INT        NOT NULL,
    patient           INT        NOT NULL,
    behandeling       VARCHAR(4) NOT NULL,
    startdatum        DATE       NOT NULL,
    einddatum         DATE       NOT NULL,
    PRIMARY KEY (notanummer),
    FOREIGN KEY (patient) REFERENCES persoon(bsn),
    FOREIGN KEY (behandeling) REFERENCES behandeling(behandelingscode)
);