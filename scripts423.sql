-- 1

SELECT student.name, student.age, faculty.name
FROM student
         INNER JOIN faculty ON student.faculty_id = faculty.id;

-- 2

SELECT student.name, student.age
FROM student
         INNER JOIN avatar a ON student.id = a.student_id;