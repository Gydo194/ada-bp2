USE dentalpoint;


DELETE FROM nota;
DELETE FROM persoon;
DELETE FROM behandeling;

INSERT INTO behandeling VALUES
('C001', 50.54, 'Consult ten behoeve van een intake'),
('C002', 25.27, 'Consult voor een periodieke controle'),
('C003', 25.27, 'Consult, niet zijnde periodieke controle'),
('X10', 18.62, 'Maken en beoordelen kleine röntgenfoto'),
('M03', 14.91, 'Gebitsreiniging, per vijf minuten'),
('V91', 53.20, 'Eénvlaksvulling composiet'),
('V92', 69.82, 'Tweevlaksvulling composiet'),
('V93', 83.12, 'Drievlaksvulling composiet'),
('H11', 49.87, 'Trekken tand of kies')
;

INSERT INTO persoon (bsn, achternaam, voorletters, geboortedatum, geslacht, email, agbcode, type) VALUES
(237844013, 'Puk', 'P.', '1970-1-1', 'M', 'pietje.puk@dental.point', 12000001, 'BEHANDELAAR'),
(237944149, 'Iliuk', 'A.', '1989-8-5', 'V', 'alona.iliuk@dental.point', 12000002, 'BEHANDELAAR'),
(237944161, 'Plysiuk', 'N.', '1994-6-19', 'V', 'nadiia.plysiuk@dental.point', 12000003, 'BEHANDELAAR'),
(237843651, 'Duvekot', 'K.', '1970-2-14', 'M', 'kees.duvekot@dental.point', 12000004, 'BEHANDELAAR')
;

INSERT INTO persoon (bsn, achternaam, voorletters, geboortedatum, geslacht, email, relatienummer, type) VALUES
(237943979, 'Kosten', 'G.J.', '2000-6-19', 'M', 'gydo.kosten@mail.nl', 1608019, 'PATIENT'),
(237943927, 'Jansen', 'J.', '2001-7-9', 'M', 'jan.jansen@mail.nl', 1608020, 'PATIENT'),
(237943904, 'Pieterse', 'P.J.', '1987-3-26', 'M', 'piet.pieterse@mail.nl', 1608021, 'PATIENT')
;


INSERT INTO nota (patient, behandeling, startdatum, einddatum) VALUES
(237943979, 'C001', '2023-01-28', '2023-01-28'),
(237943979, 'M03', '2023-01-28', '2023-01-28'),
(237943927, 'V92', '2022-05-10', '2022-05-10'),
(237943904, 'H11', '2023-01-15', '2023-01-15')
;
