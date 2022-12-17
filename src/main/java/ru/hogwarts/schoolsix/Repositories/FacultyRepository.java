package ru.hogwarts.schoolsix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.schoolsix.Model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findFacultiesByNameIgnoreCase(String name);

    Collection<Faculty> findFacultiesByColorIgnoreCase(String color);
}
