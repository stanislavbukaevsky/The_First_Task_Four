-- liquibase formatted sql

-- changeset sbukaevsky:1
CREATE INDEX student_name_index ON student (name);

-- changeset sbukaevsky:2
CREATE INDEX faculty_name_color_index ON faculty (name, color);