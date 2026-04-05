-- Initialisation des types de documents
INSERT INTO document_types (id, category, description) VALUES
                                                           (gen_random_uuid(), 'ATTESTATION_TRAVAIL', 'Attestation confirmant l occupation actuelle du poste'),
                                                           (gen_random_uuid(), 'ATTESTATION_SALAIRE', 'Document détaillant la rémunération annuelle ou mensuelle'),
                                                           (gen_random_uuid(), 'ORDRE_MISSION', 'Autorisation de déplacement professionnel'),
                                                           (gen_random_uuid(), 'CERTIFICAT_TRAVAIL', 'Document remis à la fin du contrat (solde de tout compte)');