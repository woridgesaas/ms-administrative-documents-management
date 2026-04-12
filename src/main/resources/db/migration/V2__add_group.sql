-- V2__add_group_name_and_seed.sql

-- 1. Ajouter colonne
ALTER TABLE document_types
ADD COLUMN group_name VARCHAR(50);

-- 2. Ajouter contrainte unique
ALTER TABLE document_types
ADD CONSTRAINT uq_document_type_code UNIQUE (category);

-- 3. Insert data
INSERT INTO document_types (id, category, group_name, description) VALUES
(gen_random_uuid(), 'ATTESTATION_TRAVAIL', 'ATTESTATIONS', 'Document attestant que le salarié est ou a été employé.'),
(gen_random_uuid(), 'ATTESTATION_SALAIRE', 'ATTESTATIONS', 'Document précisant la rémunération du salarié.'),
(gen_random_uuid(), 'CERTIFICAT_TRAVAIL', 'ATTESTATIONS', 'Document remis à la fin du contrat.'),

(gen_random_uuid(), 'LETTRE_LICENCIEMENT', 'END_OF_CONTRACT', 'Notification de licenciement.'),
(gen_random_uuid(), 'RECU_SOLDE_TOUT_COMPTE', 'END_OF_CONTRACT', 'Récapitulatif des sommes versées.'),
(gen_random_uuid(), 'DECISION_FIN_CONTRAT', 'END_OF_CONTRACT', 'Décision administrative de fin de contrat.'),

(gen_random_uuid(), 'AVERTISSEMENT', 'DISCIPLINE', 'Sanction disciplinaire simple.'),
(gen_random_uuid(), 'BLAME', 'DISCIPLINE', 'Sanction disciplinaire plus sévère.'),
(gen_random_uuid(), 'MISE_A_PIED', 'DISCIPLINE', 'Suspension temporaire du salarié.'),
(gen_random_uuid(), 'CONVOCATION_ENTRETIEN', 'DISCIPLINE', 'Convocation à un entretien disciplinaire.'),

(gen_random_uuid(), 'ATTESTATION_STAGE', 'TRAINING', 'Attestation de stage.'),
(gen_random_uuid(), 'CONVENTION_FORMATION', 'TRAINING', 'Convention de formation.'),

(gen_random_uuid(), 'DECISION_PROMOTION', 'INTERNAL_RH', 'Décision de promotion.'),
(gen_random_uuid(), 'DECISION_MUTATION', 'INTERNAL_RH', 'Décision de mutation.'),
(gen_random_uuid(), 'NOTE_SERVICE', 'INTERNAL_RH', 'Note interne.'),
(gen_random_uuid(), 'REGLEMENT_INTERIEUR', 'INTERNAL_RH', 'Règlement intérieur.');