USE dentalpoint;

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

DELETE FROM persoon;
INSERT INTO persoon (achternaam, voorletters, geboortedatum, geslacht, email, agbcode, type) VALUES
('Puk', 'P.', '1970-1-1', 'M', 'pietje.puk@dental.point', 12000001, 'BEHANDELAAR'),
('Iliuk', 'A.', '1989-8-5', 'V', 'alona.iliuk@dental.point', 12000002, 'BEHANDELAAR'),
('Plysiuk', 'N.', '1994-6-19', 'V', 'nadiia.plysiuk@dental.point', 12000003, 'BEHANDELAAR'),
('Duvekot', 'K.', '1970-2-14', 'M', 'kees.duvekot@dental.point', 12000004, 'BEHANDELAAR')
;
