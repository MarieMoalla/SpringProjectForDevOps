use gestionbibliodb;

ALTER TABLE users
MODIFY COLUMN dtype VARCHAR(255) DEFAULT 'USER';