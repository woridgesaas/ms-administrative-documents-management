-- Insert data
INSERT INTO document_types (id, category, template_code, group_name, description)
VALUES

-- ATTESTATIONS
(gen_random_uuid(), 'ATTESTATION_TRAVAIL', 'WORK_CERTIFICATE', 'ATTESTATIONS',
 'Document attestant que le salarié est ou a été employé.'),
(gen_random_uuid(), 'ATTESTATION_SALAIRE', 'SALARY_CERTIFICATE', 'ATTESTATIONS',
 'Document précisant la rémunération du salarié.'),
(gen_random_uuid(), 'CERTIFICAT_TRAVAIL', 'WORK_CERTIFICATE', 'ATTESTATIONS', 'Document remis à la fin du contrat.'),

-- END OF CONTRACT
(gen_random_uuid(), 'LETTRE_LICENCIEMENT', 'MISSION_ORDER', 'END_OF_CONTRACT', 'Notification de licenciement.'),
(gen_random_uuid(), 'RECU_SOLDE_TOUT_COMPTE', 'MISSION_ORDER', 'END_OF_CONTRACT', 'Récapitulatif des sommes versées.'),
(gen_random_uuid(), 'DECISION_FIN_CONTRAT', 'MISSION_ORDER', 'END_OF_CONTRACT',
 'Décision administrative de fin de contrat.'),

-- DISCIPLINE
(gen_random_uuid(), 'AVERTISSEMENT', 'MISSION_ORDER', 'DISCIPLINE', 'Sanction disciplinaire simple.'),
(gen_random_uuid(), 'BLAME', 'MISSION_ORDER', 'DISCIPLINE', 'Sanction disciplinaire plus sévère.'),
(gen_random_uuid(), 'MISE_A_PIED', 'MISSION_ORDER', 'DISCIPLINE', 'Suspension temporaire du salarié.'),
(gen_random_uuid(), 'CONVOCATION_ENTRETIEN', 'MISSION_ORDER', 'DISCIPLINE',
 'Convocation à un entretien disciplinaire.'),

-- TRAINING
(gen_random_uuid(), 'ATTESTATION_STAGE', 'WORK_CERTIFICATE', 'TRAINING', 'Attestation de stage.'),
(gen_random_uuid(), 'CONVENTION_FORMATION', 'MISSION_ORDER', 'TRAINING', 'Convention de formation.'),

-- INTERNAL RH
(gen_random_uuid(), 'DECISION_PROMOTION', 'MISSION_ORDER', 'INTERNAL_RH', 'Décision de promotion.'),
(gen_random_uuid(), 'DECISION_MUTATION', 'MISSION_ORDER', 'INTERNAL_RH', 'Décision de mutation.'),
(gen_random_uuid(), 'NOTE_SERVICE', 'MISSION_ORDER', 'INTERNAL_RH', 'Note interne.'),
(gen_random_uuid(), 'REGLEMENT_INTERIEUR', 'MISSION_ORDER', 'INTERNAL_RH', 'Règlement intérieur.');