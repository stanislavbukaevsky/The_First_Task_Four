-- 1

ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK ( age > 16 );

-- 2

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);

-- 3

ALTER TABLE student
    ALTER name SET NOT NULL;

-- 4

ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (color, name);

-- 5

ALTER TABLE student
    ALTER age SET DEFAULT 20;