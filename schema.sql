DROP DATABASE IF EXISTS dentalpoint;
CREATE DATABASE dentalpoint;

USE dentalpoint;

CREATE TABLE persoon (
    id            INT                                    NOT NULL,
    achternaam    VARCHAR(255)                           NOT NULL,
    voorletters   VARCHAR(10)                            NOT NULL,
    geboortedatum DATE                                   NOT NULL,
    geslacht      ENUM('M', 'V')                         NOT NULL,
    email         VARCHAR(100)                           NOT NULL,
    agbcode       INT                                    NULL,
    relatienummer INT                                    NULL,
    type          ENUM('PATIENT', 'BEHANDELAAR')         NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE behandeling (
    behandelingscode      VARCHAR(4)        NOT NULL,
    prijs                 DOUBLE            NOT NULL,
    omschrijving          TEXT              NOT NULL,
    PRIMARY KEY(behandelingscode)
);

CREATE TABLE nota (
    persoon_id        INT        NOT NULL,
    behandeling_id    VARCHAR(4) NOT NULL,
    notanummer        INT        NOT NULL,
    startdatum        DATE       NOT NULL,
    einddatum         DATE       NOT NULL,
    PRIMARY KEY (persoon_id, behandeling_id, notanummer),
    FOREIGN KEY (persoon_id) REFERENCES persoon(id),
    FOREIGN KEY (behandeling_id) REFERENCES behandeling(behandelingscode)
);