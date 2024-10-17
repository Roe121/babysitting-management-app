SET search_path TO projet;

-- Supprime toutes les données des tables
DELETE FROM garde;
DELETE FROM contrat;
DELETE FROM parent;
DELETE FROM compte;
DELETE FROM tarifs_en_vigueur;

-- Insère les données pour la table compte
INSERT INTO compte (IdCompte, Pseudo, MotDePasse, flagadmin) VALUES 
(1, 'geek', 'geek', TRUE),
(2, 'chef', 'chef', FALSE),
(3, 'job', 'job', FALSE);

-- Réinitialise l'identifiant IdCompte à 4
ALTER TABLE compte ALTER COLUMN IdCompte RESTART WITH 4;

-- Insère les données pour la table parent
INSERT INTO parent (IdParent, IdCompte, Nom, Prenom, AdressePostale, AdresseEmail, Telephone) VALUES 
(1, 2, 'Dupont', 'Jean', '123 Rue de Paris, Paris', 'jean.dupont@example.com', '0123456789'),
(2, 3, 'Martin', 'Sophie', '456 Avenue de Lyon, Lyon', 'sophie.martin@example.com', '0987654321');

-- Réinitialise l'identifiant IdParent à 4
ALTER TABLE parent ALTER COLUMN IdParent RESTART WITH 4;

-- Insère les données pour la table tarifs_en_vigueur
INSERT INTO tarifs_en_vigueur (IdTarif, TypeTarif, Montant) VALUES 
(1, 'Tarif Horaire Garde', 3.57),  
(2, 'Indemnité Entretien', 0.35),  
(3, 'Indemnité de Repas', 2.50); 

-- Réinitialise l'identifiant IdTarif à 4
ALTER TABLE tarifs_en_vigueur ALTER COLUMN IdTarif RESTART WITH 4;

-- Insère les données pour la table contrat
INSERT INTO contrat (IdContrat, IdParent, NomEnfant, PrenomEnfant, DateNaissanceEnfant, DateDebut, DateFin, TarifHoraire, IndemniteEntretienTauxHoraire, IndemniteEntretienMinimum, IndemniteRepas) VALUES 
(1, 1, 'Lucas', 'Dupont', '2019-05-15', '2024-09-01', '2025-06-30', 3.57, 0.35, 2.65, 1.00),
(2, 2, 'Emma', 'Martin', '2018-04-20', '2024-09-01', '2025-06-30', 3.50, 0.30, 2.00, 1.50),
(3, 1, 'Léa', 'Dupont', '2020-03-10', '2024-09-01', '2025-06-30', 4.00, 0.40, 2.75, 1.20),  
(4, 2, 'Milo', 'Martin', '2019-11-15', '2024-09-01', '2025-06-30', 3.80, 0.35, 2.50, 1.00);   

-- Réinitialise l'identifiant IdContrat à 4
ALTER TABLE contrat ALTER COLUMN IdContrat RESTART WITH 6;

-- Insère les données pour la table garde
INSERT INTO garde (IdGarde, IdContrat, DateJour, HeureArrivee, HeureDepart, APrisRepas) VALUES 
(1, 1, '2024-10-01', '08:00:00', '16:00:00', TRUE),
(2, 1, '2024-10-02', '08:00:00', '16:00:00', FALSE),
(3, 2, '2024-10-01', '09:00:00', '17:00:00', TRUE),
(4, 2, '2024-10-02', '09:00:00', '17:00:00', FALSE);

-- Réinitialise l'identifiant IdGarde à 5
ALTER TABLE garde ALTER COLUMN IdGarde RESTART WITH 5;
