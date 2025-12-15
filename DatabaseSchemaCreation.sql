-- Création de la table Users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    number_phone VARCHAR(10) UNIQUE,
    descp VARCHAR(255),
    experience_actual VARCHAR(255)
);

-- Création de la table Skills
CREATE TABLE skills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(255) NOT NULL
);

-- Création de la table Education
CREATE TABLE education (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    degree VARCHAR(255) NOT NULL,
    school VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_education_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Création de la table Experience
CREATE TABLE experience (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(255) NOT NULL,
    label VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE, -- Nullable dans Experience.java
    descp VARCHAR(255),
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_experience_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table de jointure pour Education <-> Skills
CREATE TABLE education_skills (
    education_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    PRIMARY KEY (education_id, skill_id),
    CONSTRAINT fk_eduskill_education FOREIGN KEY (education_id) REFERENCES education(id),
    CONSTRAINT fk_eduskill_skill FOREIGN KEY (skill_id) REFERENCES skills(id)
);

-- Table de jointure pour Experience <-> Skills
CREATE TABLE experience_skills (
    experience_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    PRIMARY KEY (experience_id, skill_id),
    CONSTRAINT fk_expskill_experience FOREIGN KEY (experience_id) REFERENCES experience(id),
    CONSTRAINT fk_expskill_skill FOREIGN KEY (skill_id) REFERENCES skills(id)
);
