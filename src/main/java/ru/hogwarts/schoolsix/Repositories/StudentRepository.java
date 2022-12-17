package ru.hogwarts.schoolsix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolsix.Model.ListOfStudentsLimit;
import ru.hogwarts.schoolsix.Model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Collection<Student> findByAgeBetween(int min, int max);

    public Collection<Student> findByFacultiesId(long id);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    public int getAllStudentsNumber();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    public double getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    public List<ListOfStudentsLimit> getLastStudentsById();
}
